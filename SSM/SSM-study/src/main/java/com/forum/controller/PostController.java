/*
 * 控制类
 * 与帖子功能相关的前后端交互
 */

package com.forum.controller;

import com.forum.pojo.Comment;
import com.forum.pojo.ForumUser;
import com.forum.pojo.Post;
import com.forum.service.CommentService;
import com.forum.service.ForumUserService;
import com.forum.service.PostService;
import com.forum.utils.UpdateExpStd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ForumUserService forumUserService;
    @Autowired
    private CommentService commentService;

    /*
    * 上传/编辑帖子
    * 编辑帖子页面中请求
    * 参数PostID为0时，帖子不存在，上传新帖子
    *           不为0时，帖子已存在，修改该帖子
    * 返回上传结果
    */
    @RequestMapping("/uploadPost")
    @ResponseBody
    public int uploadPost(@RequestParam("postID") int postID,
                          @RequestParam("userID") int UserID,
                          @RequestParam("title") String Title,
                          @RequestParam("postIdentity") int PostIdentity,
                          @RequestParam("content") String Content,
                          @RequestParam("postLevel") int PostLevel,
                          MultipartFile userfile,
                          HttpServletRequest request){
        try {
            int PostID=postID;
            //如果帖子不存在，上传新帖子
            if(PostID==0){
                System.out.println("发布帖子");
                PostID=postService.getMaxPostID()+1;
                Post post=new Post(PostID,UserID,Title,Content,PostIdentity,PostLevel);
                //资源共享板块独有
                if(PostIdentity==4){
                    String accessoryPath=saveAccessory(userfile,request);
                    post.setAccessoryPath(accessoryPath);
                    System.out.println("附件成功添加并保存");
                }
                postService.addPost(post);
                forumUserService.updateForumUserExperience(UserID, UpdateExpStd.pushPost.getValue());
            }
            //如果帖子已存在，修改旧帖子
            else{
                System.out.println("修改帖子");
                Post post=new Post(PostID,UserID,Title,Content,PostIdentity,PostLevel);
                if(PostIdentity==4){
                    String accessoryPath=saveAccessory(userfile,request);
                    post.setAccessoryPath(accessoryPath);
                }
                postService.updatePost(post);
            }
            System.out.println("帖子编辑成功");
            return PostID;
        }
        catch (Exception e){
            System.out.println("帖子编辑产生异常");
            e.printStackTrace();
            return 0;
        }
    }

    /*
    * 处理附件方法
    * 传入multipartfile，保存，返回文件路径
    */
    public String saveAccessory(MultipartFile fileData,HttpServletRequest request){
        try{
            if(fileData.isEmpty())
                return null;
            String filePath=request.getSession().getServletContext().getRealPath("/uploadfiles/")
                    +fileData.getOriginalFilename();
            File savedir=new File(filePath);
            if(!savedir.getParentFile().exists())
                savedir.getParentFile().mkdirs();
            fileData.transferTo(savedir);
            System.out.println("附件上传成功");
            return "http://39.99.154.244:8080/files/"+fileData.getOriginalFilename();
        }
        catch (Exception e){
            System.out.println("附件上传产生异常");
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 上传图片
     * 编辑帖子页面中请求
     * 返回该图片的存储地址
     */
    @RequestMapping(value="/upimg",method =RequestMethod.POST)
    @ResponseBody
    public String uploadImage(MultipartFile img,HttpServletRequest request){
        try{
            if(img.isEmpty())
                return null;
            String filePath=request.getSession().getServletContext().getRealPath("/upload/")
                    +img.getOriginalFilename();
            File savedir=new File(filePath);
            if(!savedir.getParentFile().exists())
                savedir.getParentFile().mkdirs();
            img.transferTo(savedir);
            System.out.println("图片添加成功");
            return "http://39.99.154.244:8080/image/"+img.getOriginalFilename();
        }
        catch (Exception e){
            System.out.println("图片上传产生异常");
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 加载帖子内容
    * 进入帖子页面时请求
    * 参数PostID
    * 返回该PostID对应的Post对象
    */
    @RequestMapping("/downPost")
    @ResponseBody
    public Post downPost(@RequestParam("postID") int PostID){
        try{
            //获取该帖子，发帖用户对象
            Post post=postService.getPostByPostID(PostID);
            ForumUser forumUser=forumUserService.getForumUserByID(post.getUserID());
            //查询帖子动态数据
            List<Comment> ListComment=commentService.getCommentByPostID(PostID);
            for(Comment comment:ListComment){
                ForumUser commentUser=forumUserService.getForumUserByID(comment.getUserID());
                comment.setForumUserInfo(commentUser);
                comment.alterTimeFormat();
            }
            int[] ListLikes=postService.getListLikesByPostID(PostID);
            int BrowseNum=postService.getBrowseNumByPostID(PostID);
            //补充到帖子对象中
            post.setForumUserInfo(forumUser);
            post.setDynamicInfo(ListComment,ListLikes,BrowseNum);
            post.alterTimeFormat();
            //解析附件
            if(post.getAccessoryPath()!=null){
                File file=new File(post.getAccessoryPath());
                post.setFileName(file.getName());
                String[] split=file.getName().split("\\.");
                String fileType=split[split.length-1];
                post.setFileType(fileType);
            }
            System.out.println("帖子加载成功");
            return post;
        }
        catch (Exception e){
            System.out.println("帖子加载异常");
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 更新帖子的点赞、浏览信息
     * 关闭帖子页面时触发
     * 传入参数likeState为0时，未点赞，为1时，点赞
     * 根据现有点赞情况更新点赞信息，直接添加浏览信息
     * 返回结果0成功，1异常
     */
    @RequestMapping("/likePost")
    @ResponseBody
    public int likePost(@RequestParam("userID")int userID, @RequestParam("postID") int postID,
                        @RequestParam("likeState") int likeState){
        try{
            //浏览帖子处理
            postService.browsePost(postID,userID);

            //点赞帖子处理
            int oldLikeState=0;//判断是否曾点赞
            int[] listLikes=postService.getListLikesByPostID(postID);
            for(int i:listLikes)
                if(i==userID)
                    oldLikeState=1;

            if(oldLikeState==1 && likeState==0){
                System.out.println("用户已点赞，现在取消点赞");
                postService.cancelLikePost(postID,userID);
            }
            if(oldLikeState==0 && likeState==1){
                System.out.println("用户未点赞，现在点赞");
                postService.likePost(postID,userID);
            }

            //经验系统
            Post post=postService.getPostByPostID(postID);
            forumUserService.updateForumUserExperience(userID,UpdateExpStd.likePost.getValue());
            forumUserService.updateForumUserExperience(post.getUserID(),UpdateExpStd.likedByOthers.getValue());

            return 0;
        }
        catch (Exception e){
            System.out.println("点赞浏览信息添加失败");
            e.printStackTrace();
            return 1;
        }
    }

    /*
    * 发表评论
    * 帖子页面中请求
    * 传入评论相关参数
    * 返回新建的评论Comment对象
    */
    @RequestMapping("/pushComment")
    @ResponseBody
    public Comment pushComment(@RequestParam("postID") int PostID, @RequestParam("userID") int UserID,
                           @RequestParam("content") String Content){
        try{
            //添加新帖子，存入数据库
            int CommentID=commentService.getMaxCommentID()+1;
            Comment comment=new Comment(CommentID,UserID,PostID,Content);
            commentService.addComment(comment);
            //补充信息，返回给前端
            ForumUser forumUser=forumUserService.getForumUserByID(UserID);
            Post post=postService.getPostByPostID(PostID);
            comment.setForumUserInfo(forumUser);
            //经验系统
            forumUserService.updateForumUserExperience(UserID,UpdateExpStd.pushComment.getValue());
            forumUserService.updateForumUserExperience(post.getUserID(),UpdateExpStd.CommentByOthers.getValue());

            System.out.println("评论发表成功");
            return comment;
        }
        catch (Exception e){
            System.out.println("评论发表失败");
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 删除帖子
    * 帖子页面中请求
    * 传入参数userID代表删除该帖子的用户
    * 返回结果
    */
    @RequestMapping("/deletePost")
    @ResponseBody
    public int deletePost(@RequestParam("userID")int userID, @RequestParam("postID") int postID){
        try{
            postService.deletePost(postID);
            System.out.println("帖子删除成功");
            return 0;
        }
        catch(Exception e){
            System.out.println("帖子删除失败");
            e.printStackTrace();
            return 1;
        }
    }

    /*
    * 删除评论
    * 同上
    */
    @RequestMapping("/deleteComment")
    @ResponseBody
    public int deleteComment(@RequestParam("userID")int userID, @RequestParam("commentID") int commentID){
        try{
            commentService.deleteComment(commentID);
            System.out.println("评论删除成功");
            return 0;
        }
        catch(Exception e){
            System.out.println("评论删除失败");
            e.printStackTrace();
            return 1;
        }
    }

    /*
    * 置顶帖子
    * 帖子页面中请求
    * stickstate为1时置顶，为0时取消置顶
    */
    @RequestMapping("/stickPost")
    @ResponseBody
    public int stickPost(@RequestParam("postID") int postID, @RequestParam("adminID") int userID,
                         @RequestParam("stickstate") int stickstate){
        try{
            postService.updateStickState(postID,stickstate);
            //经验系统
            if(stickstate==1){
                Post post=postService.getPostByPostID(postID);
                forumUserService.updateForumUserExperience(post.getUserID(),UpdateExpStd.stick.getValue());
                System.out.println("帖子置顶成功");
            }
            return 0;
        }
        catch (Exception e){
            System.out.println("更新置顶状态失效");
            e.printStackTrace();
            return 1;
        }
    }
}

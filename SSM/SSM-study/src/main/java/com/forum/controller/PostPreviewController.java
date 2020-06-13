/*
 * 控制类
 * 与显示帖子预览功能相关的前后端交互
 */

package com.forum.controller;

import com.forum.pojo.*;
import com.forum.returntype.CommentPlus;
import com.forum.returntype.PostPreview;
import com.forum.service.CommentService;
import com.forum.service.ForumUserService;
import com.forum.service.PostService;
import com.forum.utils.PostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostPreviewController {

    @Autowired
    private PostService postService;
    @Autowired
    private ForumUserService forumUserService;
    @Autowired
    private CommentService commentService;
    /*
    * 根据帖子类型获取帖子列表
    * 主页中请求
    * 返回帖子预览信息列表
    */
    @RequestMapping("/getPostList")
    @ResponseBody
    public List<PostPreview> getPostList(@RequestParam("postIdentity") int postIdentity){
        try {
            List<Post> list=postService.getPostByPostIdentity(postIdentity);
            return getPostPreviewfromPost(list);
        }
        catch (Exception e){
            System.out.println("帖子列表获取出现异常");
            return null;
        }
    }

    /*
     * 根据userID获取我的发帖列表
     * 个人信息中请求
     * 返回帖子预览信息列表
     */
    @RequestMapping("/getMyPost")
    @ResponseBody
    public List<PostPreview> getMyPost(@RequestParam("userID") int userID){
        try {
            List<Post> list=postService.getPostByUserID(userID);
            return getPostPreviewfromPost(list);
        }
        catch (Exception e){
            System.out.println("帖子列表获取出现异常");
            return null;
        }
    }

    /*
     * 根据userID获取我的点赞列表
     * 个人信息中请求
     * 返回帖子预览信息列表
     */
    @RequestMapping("/getLikedPost")
    @ResponseBody
    public List<PostPreview> getLikedPost(@RequestParam("userID") int userID){
        try {
            List<Post> list=postService.getPostByLikes(userID);
            return getPostPreviewfromPost(list);
        }
        catch (Exception e){
            System.out.println("帖子列表获取出现异常");
            return null;
        }
    }

    /*
     * 根据userID获取我的浏览列表
     * 个人信息中请求
     * 返回帖子预览信息列表
     */
    @RequestMapping("/getBrowsePost")
    @ResponseBody
    public List<PostPreview> getBrowsePost(@RequestParam("userID") int userID){
        try {
            List<Post> list=postService.getPostByBrowses(userID);
            return getPostPreviewfromPost(list);
        }
        catch (Exception e){
            System.out.println("帖子列表获取出现异常");
            return null;
        }
    }

    /*
     * 根据userID获取我的评论列表
     * 个人信息中请求
     * 返回CommentPlus列表
     */
    @RequestMapping("/getComment")
    @ResponseBody
    public List<CommentPlus> getComment(@RequestParam("userID") int userID){
        try{
            List<Comment> list=commentService.getCommentByUserID(userID);
            List<CommentPlus> newlist=new ArrayList<CommentPlus>();
            for (Comment comment:list){
                ForumUser forumUser=forumUserService.getForumUserByID(userID);
                comment.setForumUserInfo(forumUser);
                comment.alterTimeFormat();
                Post post=postService.getPostByPostID(comment.getPostID());
                CommentPlus commentPlus=new CommentPlus(comment,post);
                newlist.add(commentPlus);
            }
            return newlist;
        }
        catch (Exception e){
            System.out.println("评论列表获取出现异常");
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/searchPost")
    @ResponseBody
    public List<PostPreview> searchPost(@RequestParam("keyword") String keyword){
        List<Post> list=postService.getPostByKeyword(keyword);
        return getPostPreviewfromPost(list);
    }

    /*
    * 将Post列表转化为PostPreview列表
    * PostPreview类中的类方法
    * 将数据库中返回的Post对象补充信息，再精简为需要显示的PostPreview对象
    */
    public List<PostPreview> getPostPreviewfromPost(List<Post> list) {
        try {
            List<PostPreview> newlist=new ArrayList<PostPreview>();
            for(Post post:list){
                ForumUser forumUser=forumUserService.getForumUserByID(post.getUserID());
                List<Comment> listComment=commentService.getCommentByPostID(post.getPostID());
                int[] listLikes=postService.getListLikesByPostID(post.getPostID());
                int browseNum=postService.getBrowseNumByPostID(post.getPostID());
                //对post进行加工，便于postPreview展示
                post.setForumUserInfo(forumUser);
                post.setDynamicInfo(listComment,listLikes,browseNum);
                post.alterTimeFormat();
                post.setContent(PostUtil.getPreviewContent(post.getContent()));
                newlist.add(new PostPreview(post));
            }
            return newlist;
        }
        catch (Exception e){
            System.out.println("帖子列表补充信息时出现异常");
            e.printStackTrace();
            return null;
        }
    }
}

package com.forum.mapper;

import com.forum.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    /*
    根据帖子类型，返回该类型所有帖子的所有信息
    返回一个Post列表
     */
    List<Post> getPostByPostIdentity(@Param("PostIdentity") int PostIdentity);

    /*
    根据帖子ID，返回帖子所有信息
    返回一个Post对象
     */
    Post getPostByPostID(@Param("PostID") int PostID);

    /*
    添加帖子,发布帖子需要
    返回结果为插入条数，结果为0时插入失败
    默认设定LikesNum, BrowseNum, StickState值为0，设定Time, EditTime值为GETDATE()，即当前时间
     */
    int addPost(Post post);

    /*
    修改帖子，编辑帖子需要
    返回结果为更新行数，结果为0时更新失败
    只允许修改Title, Content，并将EditTime置为GETDATE()
     */
    int updatePost(Post post);

    /*
    删除帖子
    返回删除行数，结果为0时删除失败
     */
    int deletePost(@Param("PostID") int PostID);

    //以下部分需要连接Likes或Browses数据库查找
    /*
    根据用户ID查找我点赞过的帖子，返回帖子所有信息
    返回一个Post列表
     */
    List<Post> getPostByLikes(@Param("UserID") int UserID);

    /*
    根据用户ID查找我浏览过的帖子
    返回一个Post列表
     */
    List<Post> getPostByBrowses(@Param("UserID") int UserID);

}

package com.forum.mapper;

import com.forum.pojo.Post;

import java.util.List;

public interface PostMapper {
    //根据帖子类型，返回该类型所有帖子的所有信息
    List<Post> getPostByPostIdentity(int PostIdentity);

    //根据帖子ID，返回帖子所有信息
    Post getPostByPostID(int PostID);

    //添加帖子,发布帖子需要
    int addPost(Post post);

    //修改帖子，编辑帖子需要
    int updatePost(Post post);

    //删除帖子
    int deletePost(int PostID);

    //以下部分需要连接Likes或Browses数据库查找
    //根据用户ID查找我点赞过的帖子，返回帖子所有信息
    List<Post> getPostByLikes(int UserID);

    //根据用户ID查找我浏览过的帖子
    List<Post> getPostByBrowses(int UserID);

}

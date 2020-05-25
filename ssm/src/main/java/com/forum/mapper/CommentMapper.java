package com.forum.mapper;

import com.forum.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    //根据帖子ID返回所有评论
    List<Comment> getCommentByPostID(int PostID);

    //根据用户ID返回所有评论
    List<Comment> getCommentByCommentID(int UserID);

    //增加评论
    int addComment(Comment comment);

    //删除评论
    int deleteComment(int CommentID);

}

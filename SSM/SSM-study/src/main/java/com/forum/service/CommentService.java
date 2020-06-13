/*
 * Service层接口
 * 关联CommentMapper
 */

package com.forum.service;

import com.forum.pojo.Comment;

import java.util.List;

public interface CommentService {

    Comment getCommentByCommentID(int CommentID);

    List<Comment> getCommentByPostID(int PostID);

    List<Comment> getCommentByUserID(int UserID);

    int addComment(Comment comment);

    int deleteComment(int CommentID);

    int getMaxCommentID();
}

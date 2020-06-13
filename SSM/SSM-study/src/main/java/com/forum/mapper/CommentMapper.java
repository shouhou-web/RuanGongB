/*
 * 关联数据库表Comment
 * 数据交互类
 */

package com.forum.mapper;

import com.forum.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    /*
    * 查找当前CommentID对应的Comment
    * 返回一个Comment对象
    */
    Comment getCommentByCommentID(@Param("CommentID") int CommentID);

    /*
    根据帖子ID返回所有评论
    返回一个Comment列表
     */
    List<Comment> getCommentByPostID(@Param("PostID") int PostID);

    /*
    根据用户ID返回所有评论
    返回一个Comment列表
     */
    List<Comment> getCommentByUserID(@Param("UserID") int UserID);

    /*
    增加评论
    返回结果为插入条数，结果为0时插入失败
     */
    int addComment(Comment comment);

    /*
    删除评论
    返回删除行数，结果为0时删除失败
     */
    int deleteComment(@Param("CommentID") int CommentID);

    /*
    查找当前最大的评论ID，用于设置新评论ID
    返回int最大CommentID
     */
    int getMaxCommentID();

}

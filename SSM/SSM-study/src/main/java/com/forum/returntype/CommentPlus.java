/*
 * 评论及补充信息
 * 用于在【我的评论】页面显示当前评论所在的帖子名称
 * 交互类
 */

package com.forum.returntype;

import com.forum.pojo.Comment;
import com.forum.pojo.Post;
import lombok.Data;

@Data
public class CommentPlus {
    int CommentID;
    int UserID;
    int PostID;
    String Content;
    String CreateTime;
    //帖子标题
    String Title;

    public CommentPlus(Comment comment, Post post){
        this.CommentID=comment.getCommentID();
        this.UserID=comment.getUserID();
        this.PostID=comment.getPostID();
        this.Content=comment.getContent();
        this.CreateTime=comment.getCreateTime();
        this.Title=post.getTitle();
    }
}

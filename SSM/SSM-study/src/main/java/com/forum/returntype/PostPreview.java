/*
 * 帖子预览信息，Post类精简版
 * 用于在其他页面显示帖子的预览信息
 * 交互类
 */

package com.forum.returntype;

import com.forum.pojo.Post;
import com.forum.utils.PostUtil;
import lombok.Data;

@Data
public class PostPreview {
    int PostID;
    String Title;
    String Content;
    int StickState;
    String CreateTime;
    int PostLevel;

    //用户数据，不存储在数据库中，需要返回给前端
    String UserName;
    String ImagePath;
    int UserLevel;

    //帖子相关动态数据，不存储在数据库中
    String CommentNum;
    String LikesNum;
    String BrowseNum;

    public PostPreview(Post post){
        this.PostID=post.getPostID();
        this.Title=post.getTitle();
        this.Content=post.getContent();
        this.StickState=post.getStickState();
        this.CreateTime=post.getEditTime();
        this.PostLevel=post.getPostLevel();
        this.UserName=post.getUserName();
        this.ImagePath=post.getImagePath();
        this.UserLevel=post.getUserLevel();
        this.CommentNum= PostUtil.AlterNumFormat(post.getCommentNum());
        this.LikesNum=PostUtil.AlterNumFormat(post.getLikesNum());
        this.BrowseNum=PostUtil.AlterNumFormat(post.getBrowseNum());
    }

}

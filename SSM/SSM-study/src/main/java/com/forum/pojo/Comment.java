/*
* 评论及详细信息
* 实体类
*/

package com.forum.pojo;

import com.forum.utils.PostUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    int CommentID;
    int UserID;
    int PostID;
    String Content;
    String CreateTime;
    //用户数据，不存储在数据库中，需要返回给前端
    String UserName;
    String ImagePath;
    int UserLevel;

    //初始化评论
    public Comment(int CommentID,int UserID,int PostID,String Content){
        this.CommentID=CommentID;
        this.UserID=UserID;
        this.PostID=PostID;
        this.Content=Content;
    }

    //补充评论用户的信息
    public void setForumUserInfo(ForumUser user){
        this.UserName=user.getUserName();
        this.ImagePath=user.getImagePath();
        this.UserLevel=user.getUserLevel();
    }

    public void alterTimeFormat(){
        this.CreateTime= PostUtil.AlterTimeFormat(this.CreateTime);
    }
}

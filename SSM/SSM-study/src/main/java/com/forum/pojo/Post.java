/*
* 帖子及详细信息
* 实体类，存储在数据库中
*/

package com.forum.pojo;

import com.forum.utils.PostUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Post {

    int PostID;
    int UserID;
    String Title;
    String Content;
    String AccessoryPath;
    int StickState;
    int PostIdentity;
    int PostLevel;
    String CreateTime;
    String EditTime;

    //用户数据，不存储在数据库中，需要返回给前端
    String UserName;
    String ImagePath;
    int UserLevel;

    //帖子相关动态数据，不存储在数据库中
    List<Comment> ListComment;
    int CommentNum;
    int[] ListLikes;
    int LikesNum;
    int BrowseNum;

    //附件
    String fileName;
    String fileType;

    //初始化帖子
    public Post(int PostID,int UserID,String Title,String Content,int PostIdentity,int PostLevel){
        this();
        this.PostID=PostID;
        this.UserID=UserID;
        this.Title=Title;
        this.Content=Content;
        this.PostIdentity=PostIdentity;
        this.PostLevel=PostLevel;
    }

    //补充发帖用户信息
    public void setForumUserInfo(ForumUser user){
        this.UserName=user.getUserName();
        this.ImagePath=user.getImagePath();
        this.UserLevel=user.getUserLevel();
    }

    //补充帖子动态信息
    public void setDynamicInfo(List<Comment> ListComment,int[] ListLikes,int BrowseNum){
        this.CommentNum=ListComment.size();
        this.ListComment=ListComment;
        this.ListLikes=ListLikes;
        this.LikesNum=ListLikes.length;
        this.BrowseNum=BrowseNum;
    }

    public void alterTimeFormat(){
        this.CreateTime= PostUtil.AlterTimeFormat(this.CreateTime);
        this.EditTime=PostUtil.AlterTimeFormat(this.EditTime);
    }
}

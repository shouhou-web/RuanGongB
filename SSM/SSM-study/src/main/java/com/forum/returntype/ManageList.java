/*
 * 禁言用户及信息类
 * 用于在管理员管理用户列表返回当前用户禁言状态信息
 * 交互类
 */

package com.forum.returntype;


import com.forum.pojo.ForumUser;
import com.forum.pojo.Message;
import lombok.Data;

@Data
public class ManageList {
    private int UserID;
    private String UserName;
    private String AdminName;
    private String CreateTime;
    private int Duration;
    private String Content;

    public ManageList(Message message, ForumUser forumUser,ForumUser admin){
        this.UserID=forumUser.getUserID();
        this.UserName=forumUser.getUserName();
        this.AdminName=admin.getUserName();
        this.CreateTime=message.getCreateTime();
        this.Duration=message.getDuration();
        this.Content=message.getContent();
    }
}

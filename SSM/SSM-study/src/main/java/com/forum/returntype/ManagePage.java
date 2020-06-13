/*
 * 该类为管理员管理用户页面所返回的信息
 * 包含部分用户信息及禁言消息信息
 * 交互类
 */

package com.forum.returntype;

import com.forum.pojo.ForumUser;
import com.forum.pojo.Message;
import lombok.Data;

@Data
public class ManagePage {
    private String UserName;
    private int UserState;
    private int Duration;
    private String Content;

    public ManagePage(Message message, ForumUser forumUser){
        this.UserName=forumUser.getUserName();
        this.UserState=forumUser.getUserState();
        if(this.UserState==1){
            this.Duration=message.getDuration();
            this.Content=message.getContent();
        }
        else{
            this.Duration=0;
            this.Content=null;
        }
    }
}

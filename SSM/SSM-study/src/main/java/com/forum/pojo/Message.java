/*
* 消息，目前为止存储禁言信息
* 实体类，存储在数据库中
*/

package com.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    int UserID;
    int User2ID;
    int MessageType;
    int Duration;
    String CreateTime;
    String Content;

    //初始化消息
    public Message(int UserID,int User2ID,int MessageType,int Duration,String Content){
        this.UserID=UserID;
        this.User2ID=User2ID;
        this.MessageType=MessageType;
        this.Duration=Duration;
        this.Content=Content;
    }
}

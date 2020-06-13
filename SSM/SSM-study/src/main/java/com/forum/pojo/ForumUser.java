/*
* 用户信息
* 实体类，存储在数据库中
*/

package com.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForumUser {
    private int UserID;
    private String UserName;
    private String UserPassword;
    private int Sex;
    private String UserWord;
    private String ImagePath;
    private int Experience;
    private int UserLevel;
    private int UserIdentity;
    private int UserState;
    private int isNew;

    /*
    *用于用户注册时初始化用户对象
    *用户名、密码指定，UserID系统分配，其他值默认
     */
    public ForumUser(int UserID,String UserName,String UserPassword){
        this();
        this.UserID=UserID;
        this.UserName=UserName;
        this.UserPassword=UserPassword;
        this.Sex=0;
        this.UserLevel=1;
        this.isNew=1;
        this.UserWord="系统原装签名，送给每一位肝ddl的勇士";
        this.ImagePath="https://img-static.mihoyo.com/communityweb/upload/2adac6e4e0195c39d90696955f9a7902.png";
    }
}

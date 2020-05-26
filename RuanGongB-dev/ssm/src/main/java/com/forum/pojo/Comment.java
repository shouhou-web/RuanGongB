package com.forum.pojo;

public class Comment {
    int CommentID;
    int UserID;
    int PostID;
    String Content;
    //用户数据，不存储在数据库中，需要返回给前端
    String UserName;
    String ImagePath;
    int UserLevel;
}

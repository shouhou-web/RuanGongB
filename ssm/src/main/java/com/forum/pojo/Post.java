package com.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    int PostID;
    int UserID;
    String Title;
    String Content;
    int LikesNum;
    int BrowseNum;
    String AccessoryPath;
    int StickState;
    int PostIdentity;
    String Time;
    String EditTime;
    //用户数据，不存储在数据库中，需要返回给前端
    String UserName;
    String ImagePath;
    int UserLevel;
}

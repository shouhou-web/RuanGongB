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
    private boolean Sex;
    private String ImagePath;
    private int Experience;
    private int UserLevel;
    private boolean UserIdentity;
    private boolean UserState;
}

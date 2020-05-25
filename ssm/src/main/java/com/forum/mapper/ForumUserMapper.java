package com.forum.mapper;

import com.forum.pojo.ForumUser;
import java.util.List;

public interface ForumUserMapper {

    //增加一个新用户，注册需要
    int addForumUser(ForumUser forumUser);

    //根据ID获取用户所有信息
    ForumUser getForumUserByID(int UserID);

    //修改用户信息
    int updateForumUser(ForumUser forumUser);

    //修改用户发言状态
    int updateForumUserState(boolean UserState);

    //用户增加（减少）经验，？修改等级（？外层判断）
    int updateForumUserExperienceLevel(int Experience,int UserLevel);

}

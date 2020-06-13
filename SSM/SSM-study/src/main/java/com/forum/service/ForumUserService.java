/*
 * Service层接口
 * 关联ForumUserMapper
 */

package com.forum.service;

import com.forum.pojo.ForumUser;

public interface ForumUserService {

    int addForumUser(ForumUser forumUser);

    ForumUser getForumUserByUserName(String UserName);

    ForumUser getForumUserByID(int UserID);

    int updateForumUser(ForumUser forumUser);

    int updateForumUserState(int UserID, int UserState);

    int[] getBannedUserList();

    int updateForumUserExperience(int UserID, int Experience);

    int updateNewForumUser(int UserID);

    int getMaxUserID();

}

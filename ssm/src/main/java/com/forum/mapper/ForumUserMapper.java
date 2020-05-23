package com.forum.mapper;

import com.forum.pojo.ForumUser;
import java.util.List;

public interface ForumUserMapper {

    int addForumUser(ForumUser forumUser);

    int deleteForumUser(int UserID);

    ForumUser getForumUserByID(int UserID);

    List<ForumUser> getAllForumUser();

    int updateForumUser(ForumUser forumUser);


}

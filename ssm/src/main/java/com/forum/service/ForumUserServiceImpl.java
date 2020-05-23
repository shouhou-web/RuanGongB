package com.forum.service;

import com.forum.mapper.ForumUserMapper;
import com.forum.pojo.ForumUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumUserServiceImpl implements ForumUserService{

    @Autowired
    private ForumUserMapper mapper;

    public void registerForumUser(String UserName,String UserPassword){
        ForumUser forumUser=new ForumUser();
        forumUser.setUserName(UserName);
        forumUser.setUserPassword(UserPassword);

        mapper.addForumUser(forumUser);
    }

    public void setMapper(ForumUserMapper mapper) {
        this.mapper=mapper;
    }
}

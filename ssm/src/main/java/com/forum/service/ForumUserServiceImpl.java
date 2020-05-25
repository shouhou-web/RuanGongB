package com.forum.service;

import com.forum.mapper.ForumUserMapper;
import com.forum.pojo.ForumUser;
import com.forum.utils.ForumUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumUserServiceImpl implements ForumUserService{

    @Autowired
    private ForumUserMapper mapper;
    public void setMapper(ForumUserMapper mapper) {
        this.mapper=mapper;
    }

    public int register(String UserName,String UserPassword,String UserPassword2){
        if(mapper.getForumUserByUserName(UserName)!=null)
            return 1;
        if(!UserPassword.equals(UserPassword2))
            return 2;
        if(!ForumUserUtil.checkPasswordIllegal(UserPassword))
            return 3;
        ForumUser forumUser=new ForumUser();
        forumUser.setUserName(UserName);
        forumUser.setUserPassword(UserPassword);
        forumUser.setUserID(ForumUserUtil.UserNum);
        ForumUserUtil.UserNum++;
        mapper.addForumUser(forumUser);
        return 0;
    }

    public ForumUser login(String UserName,String Password){
        ForumUser forumUser=mapper.getForumUserByUserName(UserName);
        if(forumUser==null||!forumUser.getUserPassword().equals(Password))
            return null;
        return forumUser;
    }


}

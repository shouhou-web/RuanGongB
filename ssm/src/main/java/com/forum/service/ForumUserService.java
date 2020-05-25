package com.forum.service;

import com.forum.pojo.ForumUser;

public interface ForumUserService {

    int register(String UserName,String UserPassword,String UserPassword2);

    ForumUser login(String UserName,String Password);

}

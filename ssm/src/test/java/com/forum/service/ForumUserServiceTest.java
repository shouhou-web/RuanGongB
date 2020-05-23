package com.forum.service;

import org.junit.Test;

public class ForumUserServiceTest {
    @Test
    public void registerTest(){
        ForumUserService forumUserService=new ForumUserServiceImpl();
        forumUserService.registerForumUser("hhh","123456");
    }
}

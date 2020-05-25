package com.forum.controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ForunUserControllerTest {
    @Test
    public void loginTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ForumUserController controller=context.getBean("forumUserController",ForumUserController.class);
        System.out.println(controller.login("hhh","Abc123"));
    }
    @Test
    public void registerTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ForumUserController controller=context.getBean("forumUserController",ForumUserController.class);
        System.out.println(controller.register("hhh","123","123"));
    }
}

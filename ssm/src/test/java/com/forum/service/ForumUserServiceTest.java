package com.forum.service;

import com.forum.mapper.ForumUserMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

public class ForumUserServiceTest {
    @Test
    public void loginTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        /*ComboPooledDataSource dataSource=(ComboPooledDataSource) context.getBean("dataSource");
        try{
            Connection connection=dataSource.getConnection();
        }
        catch (Exception e){}*/
        ForumUserService forumUserService=context.getBean("forumUserServiceImpl",ForumUserService.class);
        System.out.println(forumUserService.login("hhh","123456"));
    }
}

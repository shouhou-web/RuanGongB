package com.forum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class ForumUserControllerTest {

    @Autowired
    private ForumUserController controller;

    @Test
    public void register() {
        System.out.println(controller.register("testforjava","abc123","abc123"));//成功
        System.out.println(controller.register("testforjava","abc123","abc123"));//用户名已存在
        System.out.println(controller.register("test2","abc123","abc1234"));//不一致
        System.out.println(controller.register("test2","abc","abc"));//不合法
    }

    @Test
    public void loginTest(){
        System.out.println(controller.login("马兔兔1号","Abc123"));
        System.out.println(controller.login("不存在的昵称","abc123"));
        System.out.println(controller.login("论坛指定管理员","abc123"));
    }

    @Test
    public void edit(){
        System.out.println(controller.editUser("test1","hhh",1,10008,null));//用户名存在
        System.out.println(controller.editUser("testnew","hhh",1,10008,null));//成功
    }

    @Test
    public void editnewState(){
        System.out.println(controller.editNewState(10008));
    }
}

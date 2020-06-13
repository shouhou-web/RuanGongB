package com.forum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class AdminControllerTest {

    @Autowired
    private AdminController controller;

    @Test
    public void upManageTest(){
        System.out.println(controller.upManage(10008,10001,7,1,"leyi"));//禁言
        System.out.println(controller.getUser(10008));//已禁言
        System.out.println(controller.upManage(10008,10001,0,0,null));//取消禁言
        System.out.println(controller.getUser(10008));//未被禁言
    }

    @Test
    public void getManagedUserTest(){
        System.out.println(controller.getManagedUser());
    }
}

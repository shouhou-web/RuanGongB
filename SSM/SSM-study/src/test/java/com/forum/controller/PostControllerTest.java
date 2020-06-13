package com.forum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class PostControllerTest {

    @Autowired
    private PostController controller;

    @Test
    public void uploadTest(){
        //需接受前端返回的文件MultipartFile类
    }

    @Test
    public void upimgTest(){
        //需接受前端返回的MultipartFile类
    }

    @Test
    public void downTest(){
        System.out.println(controller.downPost(10001));//已存入数据库的帖子
        System.out.println(controller.downPost(10030));//不存在的帖子ID
    }

    @Test
    public void likeTest(){
        System.out.println(controller.likePost(10008,10001,0));//未点赞，不更新
        System.out.println(controller.likePost(10008,10001,1));//点赞帖子
        System.out.println(controller.likePost(10008,10001,1));//已点赞，不更新
        System.out.println(controller.likePost(10008,10001,0));//取消点赞
    }

    @Test
    public void pushCommentTest(){
        System.out.println(controller.pushComment(10001,10008,"hhhhhhhhh"));//发表评论
        //前端传递数据保证无法传递不存在的帖子ID或用户ID
    }

    @Test
    public void deletePostTest(){
        System.out.println(controller.deletePost(10024,10008));//删除帖子
        //前端传递数据保证无法传递不存在的帖子ID或用户ID
    }

    @Test
    public void deleteCommentTest(){
        System.out.println(controller.deleteComment(10024,10018));//删除帖子
        //前端传递数据保证无法传递不存在的评论ID或用户ID
    }

    @Test
    public void stickTest(){
        System.out.println(controller.stickPost(10001,10001,1));
        System.out.println(controller.stickPost(10001,10001,0));
    }
}

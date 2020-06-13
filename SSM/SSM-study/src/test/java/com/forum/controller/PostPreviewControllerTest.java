package com.forum.controller;

import com.forum.returntype.CommentPlus;
import com.forum.returntype.PostPreview;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class PostPreviewControllerTest {

    @Autowired
    private PostPreviewController controller;

    @Test
    public void getPostListTest(){
        List<PostPreview> list=controller.getPostList(1);
        for(PostPreview postPreview:list){
            System.out.println(postPreview);
        }
    }

    @Test
    public void getMyPostTest(){
        List<PostPreview> list=controller.getMyPost(10001);
        for(PostPreview postPreview:list){
            System.out.println(postPreview);
        }
    }

    @Test
    public void getLikePostTest(){
        List<PostPreview> list=controller.getLikedPost(10001);
        for(PostPreview postPreview:list){
            System.out.println(postPreview);
        }
    }

    @Test
    public void getBrowsePostTest(){
        List<PostPreview> list=controller.getBrowsePost(10001);
        for(PostPreview postPreview:list){
            System.out.println(postPreview);
        }
    }

    @Test
    public void getCommentTest(){
        List<CommentPlus> list=controller.getComment(10001);
        for(CommentPlus commentPlus:list){
            System.out.println(commentPlus);
        }
    }

    @Test
    public void searchTest(){
        List<PostPreview> list=controller.searchPost("ha");
        for(PostPreview postPreview:list){
            System.out.println(postPreview);
        }
    }
}

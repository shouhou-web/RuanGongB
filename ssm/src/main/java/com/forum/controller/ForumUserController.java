package com.forum.controller;

import com.forum.pojo.ForumUser;
import com.forum.service.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForumUserController {

    @Autowired
    private ForumUserService forumUserService;

    @RequestMapping("/register")
    @ResponseBody
    public int register(@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("password")String password2){
        return forumUserService.register(username,password,password2);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ForumUser login(@RequestParam("username")String username,@RequestParam("password")String password){
        return forumUserService.login(username,password);
    }

}

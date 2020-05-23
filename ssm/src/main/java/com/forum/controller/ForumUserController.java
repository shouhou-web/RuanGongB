package com.forum.controller;

import com.forum.service.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForumUserController {

    @Autowired
    private ForumUserService forumUserService;

    @RequestMapping("/register")
    @ResponseBody
    public String register(){
        forumUserService.registerForumUser("hhh","123456");
        return "good";
    }

}

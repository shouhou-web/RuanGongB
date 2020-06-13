/*
 * 控制类
 * 与用户功能相关的前后端交互
 */

package com.forum.controller;

import com.forum.pojo.ForumUser;
import com.forum.service.ForumUserService;
import com.forum.utils.ForumUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForumUserController {

    @Autowired
    private ForumUserService forumUserService;

    /*
    * 用户注册
    * 提交注册所需参数，判断
    * 返回注册结果，为int类型，对应不同结果
    */
    @RequestMapping("/register")
    @ResponseBody
    public int register(@RequestParam("username")String UserName,@RequestParam("password")String UserPassword,@RequestParam("password2")String UserPassword2){
        try {
            if(forumUserService.getForumUserByUserName(UserName)!=null)
                return 1;//用户名已存在
            if(!UserPassword.equals(UserPassword2))
                return 2;//两次密码不相等
            if(!ForumUserUtil.checkPasswordIllegal(UserPassword))
                return 3;//密码不合法
            int UserID=forumUserService.getMaxUserID()+1;
            ForumUser forumUser=new ForumUser(UserID,UserName,UserPassword);
            forumUserService.addForumUser(forumUser);
            System.out.println("注册已录入");
            return 0;//成功
        }
        catch (Exception e){
            System.out.println("注册后台出现异常");
            e.printStackTrace();
            return 4;
        }
    }

    /*
    * 用户登录
    * 提交用户名及密码
    * 登陆成功返回该用户信息
    */
    @RequestMapping("/login")
    @ResponseBody
    public ForumUser login(@RequestParam("username")String UserName,@RequestParam("password")String UserPassword){
        try {
            ForumUser forumUser=forumUserService.getForumUserByUserName(UserName);
            if(forumUser==null||!forumUser.getUserPassword().equals(UserPassword)){
                System.out.println("信息不正确");
                return null;
            }
            System.out.println("用户信息正确，登录成功");
            return forumUser;
        }
        catch (Exception e){
            System.out.println("登录时后台出现异常");
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 编辑用户资料
    * 提交修改后的用户资料
    * 修改成功返回forumUser对象
    */
    @RequestMapping("/editUser")
    @ResponseBody
    public ForumUser editUser(@RequestParam("userName")String UserName,@RequestParam("userWord")String UserWord,
                        @RequestParam("sex") int Sex,@RequestParam("userID") int UserID,
                        @RequestParam("imagePath") String imagePath){
        try {
            ForumUser forumUser=forumUserService.getForumUserByID(UserID);
            ForumUser checkUser=forumUserService.getForumUserByUserName(UserName);
            if(!ForumUserUtil.checkEditUserName(checkUser,UserID))
                return null;//用户名已存在
            forumUser.setUserName(UserName);
            forumUser.setUserWord(UserWord);
            forumUser.setSex(Sex);
            forumUser.setImagePath(imagePath);
            forumUserService.updateForumUser(forumUser);
            System.out.println("修改信息已录入");
            return forumUser;//成功
        }
        catch (Exception e){
            System.out.println("修改资料后端异常");
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 修改用户是否为新注册用户状态
    * 在查看完新手上路后触发
    * 修改成功返回0
    */
    @RequestMapping("/isNotNew")
    @ResponseBody
    public int editNewState(@RequestParam("userID") int userID){
        try {
            forumUserService.updateNewForumUser(userID);
            System.out.println("用户已阅读新手入门，状态更新");
            return 0;
        }
        catch (Exception e){
            System.out.println("用户新手状态更新出现异常");
            e.printStackTrace();
            return 1;
        }
    }
}

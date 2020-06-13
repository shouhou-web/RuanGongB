/*
* 控制类
* 与管理员功能相关的前后端交互
*/

package com.forum.controller;

import com.forum.pojo.ForumUser;
import com.forum.pojo.Message;
import com.forum.returntype.ManageList;
import com.forum.returntype.ManagePage;
import com.forum.service.ForumUserService;
import com.forum.service.MessageService;
import com.forum.utils.UpdateExpStd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ForumUserService forumUserService;

    /*
    * 管理员获取管理界面的用户信息
    * 参数userID
    * 返回ManagePage信息
    */
    @RequestMapping("/getUser")
    @ResponseBody
    public ManagePage getUser(@RequestParam("userID") int userID){
        try {
            Message message=messageService.getRecentBanMessageByUserID(userID);
            ForumUser forumUser=forumUserService.getForumUserByID(userID);
            ManagePage managePage=new ManagePage(message,forumUser);
            return managePage;
        }
        catch (Exception e){
            System.out.println("用户管理页异常");
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 管理员提交管理信息，设置禁言、解禁
    * 返回处理结果
    */
    @RequestMapping("/upManage")
    @ResponseBody
    public int upManage(@RequestParam("userID") int userID,@RequestParam("adminID") int adminID,
                        @RequestParam("duration") int duration, @RequestParam("userState") int userState,
                        @RequestParam("content") String content){
        try {
            forumUserService.updateForumUserState(userID,userState);
            if(userState==1){
                //经验系统
                forumUserService.updateForumUserExperience(userID, UpdateExpStd.banned.getValue());
                //消息系统
                Message message=new Message(userID,adminID,3,duration,content);
                messageService.addMessage(message);
            }
            System.out.println("禁言状态已更新");
            return 0;
        }
        catch (Exception e){
            System.out.println("更新禁言状态异常");
            e.printStackTrace();
            return 1;
        }
    }

    /*
    * 管理员获取所有禁言用户的列表
    * 返回ManageList信息列表
    */
    @RequestMapping("/getManagedUser")
    @ResponseBody
    public List<ManageList> getManagedUser(){
        try {
            int[] list=forumUserService.getBannedUserList();
            List<ManageList> returnList=new ArrayList<ManageList>();
            for(int userID:list){
                Message message=messageService.getRecentBanMessageByUserID(userID);
                ForumUser forumUser=forumUserService.getForumUserByID(userID);
                ForumUser admin=forumUserService.getForumUserByID(message.getUser2ID());
                ManageList manageList=new ManageList(message,forumUser,admin);
                returnList.add(manageList);
            }
            System.out.println("获取禁言用户列表成功");
            return returnList;
        }
        catch (Exception e){
            System.out.println("获取禁言用户列表异常");
            e.printStackTrace();
            return null;
        }
    }
}

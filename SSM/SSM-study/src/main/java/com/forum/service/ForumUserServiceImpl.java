package com.forum.service;

import com.forum.mapper.ForumUserMapper;
import com.forum.pojo.ForumUser;
import com.forum.utils.ForumUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
/*
 * ForumUserService接口实现类
 */

import org.springframework.stereotype.Service;

@Service
public class ForumUserServiceImpl implements ForumUserService{

    @Autowired
    private ForumUserMapper mapper;

    public void setMapper(ForumUserMapper mapper) {
        this.mapper = mapper;
    }

    public int addForumUser(ForumUser forumUser) {
        return mapper.addForumUser(forumUser);
    }

    public ForumUser getForumUserByUserName(String UserName) {
        return mapper.getForumUserByUserName(UserName);
    }

    public ForumUser getForumUserByID(int UserID) {
        return mapper.getForumUserByID(UserID);
    }

    public int updateForumUser(ForumUser forumUser) {
        return mapper.updateForumUser(forumUser);
    }

    public int updateForumUserState(int UserID, int UserState) {
        return mapper.updateForumUserState(UserID,UserState);
    }

    public int[] getBannedUserList(){
        return mapper.getBannedUserList();
    }

    public int updateForumUserExperience(int UserID, int Experience) {
        mapper.updateForumUserExperience(UserID,Experience);
        ForumUser forumUser=mapper.getForumUserByID(UserID);
        int level=ForumUserUtil.expToLevel.get(forumUser.getExperience());
        mapper.updateForumUserLevel(UserID,level);
        return 1;
    }

    public int updateNewForumUser(int UserID) {
        return mapper.updateNewForumUser(UserID);
    }

    public int getMaxUserID(){
        int maxID;
        try{
            maxID=mapper.getMaxUserID();
            return maxID;
        }
        catch(Exception e){
            return 10000;
        }
    }
}

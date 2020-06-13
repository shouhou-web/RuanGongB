/*
* 用户相关工具类
*/

package com.forum.utils;

import com.forum.pojo.ForumUser;

import java.util.HashMap;
import java.util.Map;

public class ForumUserUtil {

    //用户等级及经验对照依据
    public static Map<Integer,Integer> expToLevel = new HashMap<Integer, Integer>();
    static int[] expOfEachLevel = {0,20,80,180,320,500,720,980,1280,1640,2020,2580,3200,3880,4750,6001};
    static{
        for(int i=1;i<expOfEachLevel.length;i++){
            for(int j=expOfEachLevel[i-1];j<expOfEachLevel[i];j++){
                expToLevel.put(j,i);
            }
        }
    }

    //检查密码是否合法
    public static boolean checkPasswordIllegal(String password){
        return password.matches("^(?!\\d*$|[a-z]*$|[A-Z]*$|\\W*$)[!-~]{6,18}$");
    }

    //检查用户名是否被占用
    public static boolean checkEditUserName(ForumUser user, int UserID){
        if(user==null||user.getUserID()==UserID)
            return true;
        return false;
    }
}
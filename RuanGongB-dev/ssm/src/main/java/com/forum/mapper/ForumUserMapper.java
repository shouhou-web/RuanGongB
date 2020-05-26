package com.forum.mapper;

import com.forum.pojo.ForumUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumUserMapper {

    /*
    增加一个新用户，注册需要
    创建时isNew属性默认置为1，如需修改需要使用updateNewForumUser
    返回结果为插入条数，结果为0时插入失败
     */
    int addForumUser(ForumUser forumUser);

    /*
    根据ID获取用户所有信息
    返回一个ForumUser
     */
    ForumUser getForumUserByID(@Param("UserID") int UserID);

    /*
    修改用户信息
    返回结果为更新行数，结果为0时更新失败
    只更新UserName,UserPassword,Sex,ImagePath四个参数，而且会将四个参数对应的值全部置为传入参数中对应属性的值
    对修改单属性不方便，需要先获取原forumUser对象并将其中不修改的属性值输入到传入参数的对应属性中
     */
    int updateForumUser(ForumUser forumUser);

    /*
    修改用户发言状态
    返回结果为更新行数，结果为0时更新失败
    修改为传入参数UserState对应值
     */
    int updateForumUserState(@Param("UserID") int UserID, @Param("UserState") boolean UserState);

    /*
    用户增加（减少）经验，？修改等级（？外层判断）
    这个接口的具体操作我没太理解，暂时用直接将用户的经验数据和等级设置为传入参数的方法代替了
    留待完善
     */
    int updateForumUserExperienceLevel(@Param("UserID") int UserID, @Param("Experience") int Experience, @Param("UserLevel") int UserLevel);

    /*
    修改用户新手上路状态
    返回结果为更新行数，结果为0时更新失败
    将isNew属性置为0，不可逆修改
     */
    int updateNewForumUser(@Param("UserID") int UserID);

}

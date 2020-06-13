/*
 * 关联数据库表ForumUser
 * 数据交互类
 */

package com.forum.mapper;

import com.forum.pojo.ForumUser;
import org.apache.ibatis.annotations.Param;

public interface ForumUserMapper {

    /*
    增加一个新用户，注册需要
    创建时isNew属性默认置为1，如需修改需要使用updateNewForumUser
    返回结果为插入条数，结果为0时插入失败
     */
    int addForumUser(ForumUser forumUser);

    /*
    根据UserName获取用户所有信息*
    返回一个ForumUser
     */
    ForumUser getForumUserByUserName(String UserName);

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
    int updateForumUserState(@Param("UserID") int UserID, @Param("UserState") int UserState);

    /*
    * 获取所有禁言用户的列表
    * 返回List<ForumUser>
    */
    int[] getBannedUserList();

    /*
    用户增加（减少）经验，
    返回更新行数
     */
    int updateForumUserExperience(@Param("UserID") int UserID, @Param("dExperience") int dExperience);

    /*
    * 用户更新当前等级，与更新经验值绑定
    * 返回更新行数
    */
    int updateForumUserLevel(@Param("UserID") int UserID, @Param("UserLevel") int UserLevel);

    /*
    修改用户新手上路状态
    返回结果为更新行数，结果为0时更新失败
    将isNew属性置为0，不可逆修改
     */
    int updateNewForumUser(@Param("UserID") int UserID);

    /*
    查找当前最大的用户ID，用于设置新用户的用户ID
    返回int最大UserID
     */
    int getMaxUserID();

}

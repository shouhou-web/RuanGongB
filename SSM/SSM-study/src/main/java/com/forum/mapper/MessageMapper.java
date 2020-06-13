/*
 * 关联数据库表Message
 * 数据交互类
 */

package com.forum.mapper;

import com.forum.pojo.Message;
import org.apache.ibatis.annotations.Param;


public interface MessageMapper {

    /*
    * 添加Message信息到表中
    * 返回添加行数
    */
    int addMessage(Message message);

    /*
    * 查找当前用户最近一次的禁言信息
    * 返回消息类型
    */
    Message getRecentBanMessageByUserID(@Param("UserID") int UserID);

    /*
    * 根据用户ID查找收到的赞
    * 返回Message列表
    */
    //List<Message> getLikesMessage(@Param("UserID") int UserID);

    /*
     * 根据用户ID查找收到的回复
     * 返回Message列表
     */
    //List<Message> getCommentMessage(@Param("UserID") int UserID);

    /*
     * 根据用户ID查找系统消息
     * 返回Message列表
     */
    //List<Message> getSystemMessage(@Param("UserID") int UserID);
}

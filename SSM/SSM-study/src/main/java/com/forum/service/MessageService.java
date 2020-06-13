/*
 * Service层接口
 * 关联MessageMapper
 */

package com.forum.service;

import com.forum.pojo.Message;

public interface MessageService {

    int addMessage(Message message);

    Message getRecentBanMessageByUserID(int UserID);
}

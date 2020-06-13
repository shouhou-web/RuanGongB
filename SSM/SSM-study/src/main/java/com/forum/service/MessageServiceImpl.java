/*
 * MessageService接口实现类
 */

package com.forum.service;

import com.forum.mapper.MessageMapper;
import com.forum.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper mapper;

    public void setMapper(MessageMapper mapper) {
        this.mapper = mapper;
    }

    public int addMessage(Message message) {
        return mapper.addMessage(message);
    }

    public Message getRecentBanMessageByUserID(int UserID) {
        return mapper.getRecentBanMessageByUserID(UserID);
    }
}

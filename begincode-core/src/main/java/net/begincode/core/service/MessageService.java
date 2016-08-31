package net.begincode.core.service;

import net.begincode.core.mapper.MessageMapper;
import net.begincode.core.model.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/30  22:57.
 */
@Service
public class MessageService {
    @Resource
    private MessageMapper messageMapper;

    public void createMessage(Message message)
    {
        messageMapper.insertSelective(message);
    }

}

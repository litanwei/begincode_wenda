package net.begincode.core.handler;

import net.begincode.core.model.Message;
import net.begincode.core.service.MessageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/31  12:30.
 */
@Component
public class MessageHandler {
    @Resource
    private MessageService messageService;

    public void addMessage(Message message)
    {
        messageService.createMessage(message);
    }
}

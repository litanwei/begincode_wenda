package net.begincode.core.service;

import net.begincode.core.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/30  22:57.
 */
@Service
public class MessageService {
    @Resource
    private MessageMapper messageMapper;


}

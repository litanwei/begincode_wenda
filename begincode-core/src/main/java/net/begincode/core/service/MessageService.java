package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.begincode.core.mapper.Biz_MessageMapper;
import net.begincode.core.mapper.MessageMapper;
import net.begincode.core.model.Message;
import net.begincode.core.model.MessageRemind;

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
    @Resource
    private Biz_MessageMapper biz_MessageMapper;
    /**
	 * 查询提醒信息
	 * @param begincode_user_id
	 * @param nowpage
	 * @param pagesize
	 */
	public List<MessageRemind> selectByMessageRemind(Integer begincode_user_id,Integer nowpage,Integer pagesize){
		return biz_MessageMapper.selectByMessageRemind(begincode_user_id, nowpage, pagesize);
	}
	
	 /**
     * 修改message已读状态
     * @param message_id
     */
	public void updatemessagedelete(Integer message_id){
		biz_MessageMapper.updatemessagedelete(message_id);
	}
	/**
	 * 获取当前message数量
	 */
	public int countByMessageRemind(Integer user_id){
		return biz_MessageMapper.countByMessageRemind(user_id);
	}

}

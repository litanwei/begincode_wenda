package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.begincode.core.mapper.MessageMapper;
import net.begincode.core.model.MessageRemind;

@Service
public class MessageService {
	@Resource
	private MessageMapper messageMapper;
	
	/**
	 * 查询提醒信息
	 * @param begincode_user_id
	 * @param nowpage
	 * @param pagesize
	 */
	public List<MessageRemind> selectByMessageRemind(Integer begincode_user_id,Integer nowpage,Integer pagesize){
		return messageMapper.selectByMessageRemind(begincode_user_id, nowpage, pagesize);
	}
}

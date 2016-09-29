package net.begincode.core.handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.begincode.core.model.MessageRemind;
import net.begincode.core.service.MessageService;

@Component
public class MessageHandler {
	@Resource
	private MessageService messageService;
	
	/**
	 * 查询提醒信息，控制分页属性
	 * @param begincode_user_id 当前用户ID
	 * @param nowpage	当前页	
	 * @param pagesize	分页大小
	 */
	public List<MessageRemind> selectByMessageRemind(Integer begincode_user_id,Integer nowpage,Integer pagesize){
		if(begincode_user_id==null){
			return null;
		}
		if(pagesize==null){
			pagesize=15;
		}
		if(nowpage==1){
			nowpage=0;
		}else{
			nowpage=(nowpage-1)*pagesize-1;
		}
		
		return messageService.selectByMessageRemind(begincode_user_id, nowpage, pagesize);
	}
	 /**
     * 修改message已读状态
     * @param message_id
     */
	public void updatemessagedelete(Integer message_id){
		messageService.updatemessagedelete(message_id);
	}
	/**
	 * 获得message数量
	 * @return
	 */
	public int countByMessageRemind(Integer user_id){
		return messageService.countByMessageRemind(user_id);
	}
}

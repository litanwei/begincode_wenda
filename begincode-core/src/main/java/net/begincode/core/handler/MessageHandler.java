package net.begincode.core.handler;

import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.Message;
import net.begincode.core.model.MessageRemind;
import net.begincode.core.service.BegincodeUserService;
import net.begincode.core.service.MessageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static net.begincode.utils.PatternUtil.filterNickName;
@Component
public class MessageHandler {
	@Resource
	private MessageService messageService;

	@Resource
	private BegincodeUserService begincodeUserService;

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
			pagesize=10;
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

	/**
	 * 新建message
	 * @param problemId，answerId,content
	 * @return
	 * */
	public void createMessage(Integer problemId,Integer answerId,String content) {
		Integer[] userId = contentFilter(content);
		Message message = new Message();
		if (userId != null && userId.length == 1) {
			message.setBegincodeUserId(userId[0]);
			message.setProId(problemId);
			message.setAnswerId(answerId);
			messageService.createMessage(message);
		} else if (userId != null && userId.length > 1) {
			for (int i = 0; i < userId.length; i++) {
				//消息添加
				message.setBegincodeUserId(userId[i]);
				message.setProId(problemId);
				message.setAnswerId(answerId);
				messageService.createMessage(message);
			}
		}
	}
	/**
	 * 传进的问题过滤出@ 后面的nickName 返回该用户的id
	 *
	 * @param content 传入的内容
	 * @return 用户id数组
	 */
	private Integer[] contentFilter(String content) {
		Set<String> stringSet = filterNickName(content);
		int i = 0;
		Integer[] userId = new Integer[stringSet.size()];
		if (stringSet != null && stringSet.size() > 0) {
			for (String nickName : stringSet) {
				BegincodeUser begincodeUser = begincodeUserService.selectByNickName(nickName.replace("@", ""));
				if (begincodeUser == null) {
					continue;
				} else {
					userId[i] = begincodeUser.getBegincodeUserId();
					i++;
				}
			}
		}
		return userId;
	}
}

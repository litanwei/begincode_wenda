package net.begincode.core.service;

import net.begincode.core.enums.DeleteFlagEnum;
import net.begincode.core.mapper.MessageMapper;
import net.begincode.core.model.Message;
import net.begincode.core.model.MessageExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Stay on 2016/8/30  22:57.
 */
@Service
public class MessageService {

    @Resource
    private MessageMapper messageMapper;

    public void createMessage(Message message) {
        messageMapper.insertSelective(message);
    }


    /**
     * 新建message
     *
     * @param problemId
     * @param answerId
     * @param userId
     */
    public void createMessage(Integer problemId, Integer answerId, Integer[] userId) {
        Message message = new Message();
        if (userId != null) {
            if (userId.length == 1) {
                message.setBegincodeUserId(userId[0]);
                message.setProId(problemId);
                message.setAnswerId(answerId);
                createMessage(message);
            } else if (userId.length > 1) {
                for (int i = 0; i < userId.length; i++) {
                    message.setBegincodeUserId(userId[i]);
                    message.setProId(problemId);
                    message.setAnswerId(answerId);
                    createMessage(message);
                }
            }
        }
    }

    /**
     * 根据用户id查找 未读 消息表集合
     *
     * @param userId
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<Message> findMessByUserId(Integer userId, Integer currentNum, Integer eachSize) {
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("message_id desc");
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andDeleteFlagEqualTo(Integer.parseInt(DeleteFlagEnum.NO_DEL_FLAG.getCode()));
        criteria.andBegincodeUserIdEqualTo(userId);
        return messageMapper.selectByExampleWithRowbounds(messageExample, new RowBounds((currentNum - 1) * eachSize,
                eachSize));
    }

    /**
     * 查找总的消息个数
     *
     * @param userId
     * @return
     */
    public Integer findMessSize(Integer userId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andDeleteFlagEqualTo(Integer.parseInt(DeleteFlagEnum.NO_DEL_FLAG.getCode()));
        criteria.andBegincodeUserIdEqualTo(userId);
        return messageMapper.countByExample(messageExample);
    }

    /**
     * 通过问题id更改提醒表的删除标识 变为1
     *
     * @param userId
     * @param problemId
     * @return
     */
    public Integer updateMessageByProblemId(Integer userId, Integer problemId) {
        Message message = new Message();
        message.setDeleteFlag(Integer.parseInt(DeleteFlagEnum.DEL_FLAG.getCode()));
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andBegincodeUserIdEqualTo(userId);
        criteria.andProIdEqualTo(problemId);
        return messageMapper.updateByExampleSelective(message, messageExample);
    }

    /**
     * 通过回答id更改提醒表的删除标识 变为1
     *
     * @param userId
     * @param answerId
     * @return
     */
    public Integer updateMessageByAnswerId(Integer userId, Integer answerId) {
        Message message = new Message();
        message.setDeleteFlag(Integer.parseInt(DeleteFlagEnum.DEL_FLAG.getCode()));
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andBegincodeUserIdEqualTo(userId);
        criteria.andAnswerIdEqualTo(answerId);
        return messageMapper.updateByExampleSelective(message, messageExample);
    }


}

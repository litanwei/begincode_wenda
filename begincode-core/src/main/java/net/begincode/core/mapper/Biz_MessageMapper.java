package net.begincode.core.mapper;

import java.util.List;
import net.begincode.core.model.MessageRemind;

import org.apache.ibatis.annotations.Param;

public interface Biz_MessageMapper extends MessageMapper {
    /**
     * 获得一组MessageRemind信息
     * @param nowpage 获得点击的页数字
     * @param begincode_user_id	获得当前用户的id
     * @param pagesize 分页大小
     * @return
     */
    List<MessageRemind> selectByMessageRemind(@Param("id")Integer begincode_user_id,@Param("nowpage")Integer nowpage,@Param("pagesize")int pagesize);
    /**
     * 获得messageRemind的数量
     * 可以进行分页
     * @return
     */
    int countByMessageRemind(Integer user_id);
    /**
     * 修改message已读状态
     * @param message_id
     */
    void updatemessagedelete(Integer message_id);
}
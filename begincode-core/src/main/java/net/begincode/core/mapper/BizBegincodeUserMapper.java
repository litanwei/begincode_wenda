package net.begincode.core.mapper;

import java.util.Date;
import java.util.List;

import net.begincode.core.model.BizBegincodeUser;
import org.apache.ibatis.annotations.Param;

import net.begincode.core.model.BegincodeUser;

public interface BizBegincodeUserMapper {
    
    /**
     * 获取活跃用户，一个月内提问和回复的总数，排序，取前10
     */
    public List<BizBegincodeUser> getActiverUser(@Param(value = "dateBefore") Date dateBefore);
    
    /**
     * 用于获取列名的所有值
     */
    public List<String> selectAllByNickName();
}
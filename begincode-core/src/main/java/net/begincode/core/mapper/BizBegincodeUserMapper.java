package net.begincode.core.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.begincode.core.model.BegincodeUser;

public interface BizBegincodeUserMapper {
    
    /**
     * 获取活跃用户，一个月内提问和回复的总数，排序，取前10
     */
    public List<BegincodeUser> getActiverUser(@Param(value = "dateBefore") Date dateBefore);
}
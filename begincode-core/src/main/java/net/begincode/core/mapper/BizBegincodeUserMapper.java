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
    
    /*
     * 通过列表名查询和数据查询是否存在数据
     */
    public Integer selectCountIsExist(@Param("columnName") String columnName,@Param("data") Object data);
    /*
     * 通过login和passwor登录用户
     */
    public BegincodeUser selectUserByUsernamePassword(@Param("username")String username,@Param("password") String password);
}
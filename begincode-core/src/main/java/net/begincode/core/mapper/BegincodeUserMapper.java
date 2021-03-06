package net.begincode.core.mapper;

import java.util.List;

import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.BegincodeUserExample;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface BegincodeUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int countByExample(BegincodeUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int deleteByExample(BegincodeUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer begincodeUserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int insert(BegincodeUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int insertSelective(BegincodeUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    List<BegincodeUser> selectByExampleWithRowbounds(BegincodeUserExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    List<BegincodeUser> selectByExample(BegincodeUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    BegincodeUser selectByPrimaryKey(Integer begincodeUserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BegincodeUser record, @Param("example") BegincodeUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BegincodeUser record, @Param("example") BegincodeUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BegincodeUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BegincodeUser record);
}
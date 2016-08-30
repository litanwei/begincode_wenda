package net.begincode.core.mapper;

import java.util.List;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProblemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int countByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int deleteByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer problemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int insert(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int insertSelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    List<Problem> selectByExampleWithBLOBsWithRowbounds(ProblemExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    List<Problem> selectByExampleWithBLOBs(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    List<Problem> selectByExampleWithRowbounds(ProblemExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    List<Problem> selectByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    Problem selectByPrimaryKey(Integer problemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Problem record);
}
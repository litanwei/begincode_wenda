package net.begincode.core.mapper;

import java.util.List;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.model.ProblemLabelExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProblemLabelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int countByExample(ProblemLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int deleteByExample(ProblemLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer proLabelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int insert(ProblemLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int insertSelective(ProblemLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    List<ProblemLabel> selectByExampleWithRowbounds(ProblemLabelExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    List<ProblemLabel> selectByExample(ProblemLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    ProblemLabel selectByPrimaryKey(Integer proLabelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ProblemLabel record, @Param("example") ProblemLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ProblemLabel record, @Param("example") ProblemLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProblemLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProblemLabel record);
}
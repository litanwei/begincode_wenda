package net.begincode.core.model;

import java.io.Serializable;

public class ProblemLabel implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column problem_label.pro_label_id
     *
     * @mbggenerated
     */
    private Integer proLabelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column problem_label.problem_id
     *
     * @mbggenerated
     */
    private Integer problemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column problem_label.label_id
     *
     * @mbggenerated
     */
    private Integer labelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table problem_label
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column problem_label.pro_label_id
     *
     * @return the value of problem_label.pro_label_id
     *
     * @mbggenerated
     */
    public Integer getProLabelId() {
        return proLabelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column problem_label.pro_label_id
     *
     * @param proLabelId the value for problem_label.pro_label_id
     *
     * @mbggenerated
     */
    public void setProLabelId(Integer proLabelId) {
        this.proLabelId = proLabelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column problem_label.problem_id
     *
     * @return the value of problem_label.problem_id
     *
     * @mbggenerated
     */
    public Integer getProblemId() {
        return problemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column problem_label.problem_id
     *
     * @param problemId the value for problem_label.problem_id
     *
     * @mbggenerated
     */
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column problem_label.label_id
     *
     * @return the value of problem_label.label_id
     *
     * @mbggenerated
     */
    public Integer getLabelId() {
        return labelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column problem_label.label_id
     *
     * @param labelId the value for problem_label.label_id
     *
     * @mbggenerated
     */
    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}
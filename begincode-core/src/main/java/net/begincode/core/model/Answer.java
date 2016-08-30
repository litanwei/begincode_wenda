package net.begincode.core.model;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.answer_id
     *
     * @mbggenerated
     */
    private Integer answerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.agree_count
     *
     * @mbggenerated
     */
    private Integer agreeCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.opposition_count
     *
     * @mbggenerated
     */
    private Integer oppositionCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.adopt
     *
     * @mbggenerated
     */
    private Integer adopt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.feedback
     *
     * @mbggenerated
     */
    private Integer feedback;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.problem_id
     *
     * @mbggenerated
     */
    private Integer problemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.begincode_user_id
     *
     * @mbggenerated
     */
    private Integer begincodeUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table answer
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.answer_id
     *
     * @return the value of answer.answer_id
     *
     * @mbggenerated
     */
    public Integer getAnswerId() {
        return answerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.answer_id
     *
     * @param answerId the value for answer.answer_id
     *
     * @mbggenerated
     */
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.create_time
     *
     * @return the value of answer.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.create_time
     *
     * @param createTime the value for answer.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.user_name
     *
     * @return the value of answer.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.user_name
     *
     * @param userName the value for answer.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.agree_count
     *
     * @return the value of answer.agree_count
     *
     * @mbggenerated
     */
    public Integer getAgreeCount() {
        return agreeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.agree_count
     *
     * @param agreeCount the value for answer.agree_count
     *
     * @mbggenerated
     */
    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.opposition_count
     *
     * @return the value of answer.opposition_count
     *
     * @mbggenerated
     */
    public Integer getOppositionCount() {
        return oppositionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.opposition_count
     *
     * @param oppositionCount the value for answer.opposition_count
     *
     * @mbggenerated
     */
    public void setOppositionCount(Integer oppositionCount) {
        this.oppositionCount = oppositionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.adopt
     *
     * @return the value of answer.adopt
     *
     * @mbggenerated
     */
    public Integer getAdopt() {
        return adopt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.adopt
     *
     * @param adopt the value for answer.adopt
     *
     * @mbggenerated
     */
    public void setAdopt(Integer adopt) {
        this.adopt = adopt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.delete_flag
     *
     * @return the value of answer.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.delete_flag
     *
     * @param deleteFlag the value for answer.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.feedback
     *
     * @return the value of answer.feedback
     *
     * @mbggenerated
     */
    public Integer getFeedback() {
        return feedback;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.feedback
     *
     * @param feedback the value for answer.feedback
     *
     * @mbggenerated
     */
    public void setFeedback(Integer feedback) {
        this.feedback = feedback;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.problem_id
     *
     * @return the value of answer.problem_id
     *
     * @mbggenerated
     */
    public Integer getProblemId() {
        return problemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.problem_id
     *
     * @param problemId the value for answer.problem_id
     *
     * @mbggenerated
     */
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.begincode_user_id
     *
     * @return the value of answer.begincode_user_id
     *
     * @mbggenerated
     */
    public Integer getBegincodeUserId() {
        return begincodeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.begincode_user_id
     *
     * @param begincodeUserId the value for answer.begincode_user_id
     *
     * @mbggenerated
     */
    public void setBegincodeUserId(Integer begincodeUserId) {
        this.begincodeUserId = begincodeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.content
     *
     * @return the value of answer.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.content
     *
     * @param content the value for answer.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content;
    }
}
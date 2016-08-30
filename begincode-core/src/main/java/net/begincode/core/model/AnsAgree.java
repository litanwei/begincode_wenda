package net.begincode.core.model;

import java.io.Serializable;

public class AnsAgree implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ans_agree.ans_agree_id
     *
     * @mbggenerated
     */
    private Integer ansAgreeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ans_agree.agree
     *
     * @mbggenerated
     */
    private Integer agree;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ans_agree.answer_id
     *
     * @mbggenerated
     */
    private Integer answerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ans_agree.begincode_user_id
     *
     * @mbggenerated
     */
    private Integer begincodeUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ans_agree
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ans_agree.ans_agree_id
     *
     * @return the value of ans_agree.ans_agree_id
     *
     * @mbggenerated
     */
    public Integer getAnsAgreeId() {
        return ansAgreeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ans_agree.ans_agree_id
     *
     * @param ansAgreeId the value for ans_agree.ans_agree_id
     *
     * @mbggenerated
     */
    public void setAnsAgreeId(Integer ansAgreeId) {
        this.ansAgreeId = ansAgreeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ans_agree.agree
     *
     * @return the value of ans_agree.agree
     *
     * @mbggenerated
     */
    public Integer getAgree() {
        return agree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ans_agree.agree
     *
     * @param agree the value for ans_agree.agree
     *
     * @mbggenerated
     */
    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ans_agree.answer_id
     *
     * @return the value of ans_agree.answer_id
     *
     * @mbggenerated
     */
    public Integer getAnswerId() {
        return answerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ans_agree.answer_id
     *
     * @param answerId the value for ans_agree.answer_id
     *
     * @mbggenerated
     */
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ans_agree.begincode_user_id
     *
     * @return the value of ans_agree.begincode_user_id
     *
     * @mbggenerated
     */
    public Integer getBegincodeUserId() {
        return begincodeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ans_agree.begincode_user_id
     *
     * @param begincodeUserId the value for ans_agree.begincode_user_id
     *
     * @mbggenerated
     */
    public void setBegincodeUserId(Integer begincodeUserId) {
        this.begincodeUserId = begincodeUserId;
    }
}
package net.begincode.core.model;

import java.io.Serializable;

public class ProAttention implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_attention.pro_attention_id
     *
     * @mbggenerated
     */
    private Integer proAttentionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_attention.vote
     *
     * @mbggenerated
     */
    private Integer vote;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_attention.collect
     *
     * @mbggenerated
     */
    private Integer collect;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_attention.begincode_user_id
     *
     * @mbggenerated
     */
    private Integer begincodeUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_attention.problem_id
     *
     * @mbggenerated
     */
    private Integer problemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_attention.pro_attention_id
     *
     * @return the value of pro_attention.pro_attention_id
     *
     * @mbggenerated
     */
    public Integer getProAttentionId() {
        return proAttentionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_attention.pro_attention_id
     *
     * @param proAttentionId the value for pro_attention.pro_attention_id
     *
     * @mbggenerated
     */
    public void setProAttentionId(Integer proAttentionId) {
        this.proAttentionId = proAttentionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_attention.vote
     *
     * @return the value of pro_attention.vote
     *
     * @mbggenerated
     */
    public Integer getVote() {
        return vote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_attention.vote
     *
     * @param vote the value for pro_attention.vote
     *
     * @mbggenerated
     */
    public void setVote(Integer vote) {
        this.vote = vote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_attention.collect
     *
     * @return the value of pro_attention.collect
     *
     * @mbggenerated
     */
    public Integer getCollect() {
        return collect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_attention.collect
     *
     * @param collect the value for pro_attention.collect
     *
     * @mbggenerated
     */
    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_attention.begincode_user_id
     *
     * @return the value of pro_attention.begincode_user_id
     *
     * @mbggenerated
     */
    public Integer getBegincodeUserId() {
        return begincodeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_attention.begincode_user_id
     *
     * @param begincodeUserId the value for pro_attention.begincode_user_id
     *
     * @mbggenerated
     */
    public void setBegincodeUserId(Integer begincodeUserId) {
        this.begincodeUserId = begincodeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_attention.problem_id
     *
     * @return the value of pro_attention.problem_id
     *
     * @mbggenerated
     */
    public Integer getProblemId() {
        return problemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_attention.problem_id
     *
     * @param problemId the value for pro_attention.problem_id
     *
     * @mbggenerated
     */
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
}
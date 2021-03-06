package net.begincode.core.model;

import java.util.ArrayList;
import java.util.List;

public class ProAttentionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public ProAttentionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andProAttentionIdIsNull() {
            addCriterion("pro_attention_id is null");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdIsNotNull() {
            addCriterion("pro_attention_id is not null");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdEqualTo(Integer value) {
            addCriterion("pro_attention_id =", value, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdNotEqualTo(Integer value) {
            addCriterion("pro_attention_id <>", value, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdGreaterThan(Integer value) {
            addCriterion("pro_attention_id >", value, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pro_attention_id >=", value, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdLessThan(Integer value) {
            addCriterion("pro_attention_id <", value, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdLessThanOrEqualTo(Integer value) {
            addCriterion("pro_attention_id <=", value, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdIn(List<Integer> values) {
            addCriterion("pro_attention_id in", values, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdNotIn(List<Integer> values) {
            addCriterion("pro_attention_id not in", values, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdBetween(Integer value1, Integer value2) {
            addCriterion("pro_attention_id between", value1, value2, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andProAttentionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pro_attention_id not between", value1, value2, "proAttentionId");
            return (Criteria) this;
        }

        public Criteria andVoteIsNull() {
            addCriterion("vote is null");
            return (Criteria) this;
        }

        public Criteria andVoteIsNotNull() {
            addCriterion("vote is not null");
            return (Criteria) this;
        }

        public Criteria andVoteEqualTo(Integer value) {
            addCriterion("vote =", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotEqualTo(Integer value) {
            addCriterion("vote <>", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThan(Integer value) {
            addCriterion("vote >", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote >=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThan(Integer value) {
            addCriterion("vote <", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThanOrEqualTo(Integer value) {
            addCriterion("vote <=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteIn(List<Integer> values) {
            addCriterion("vote in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotIn(List<Integer> values) {
            addCriterion("vote not in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteBetween(Integer value1, Integer value2) {
            addCriterion("vote between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotBetween(Integer value1, Integer value2) {
            addCriterion("vote not between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andCollectIsNull() {
            addCriterion("collect is null");
            return (Criteria) this;
        }

        public Criteria andCollectIsNotNull() {
            addCriterion("collect is not null");
            return (Criteria) this;
        }

        public Criteria andCollectEqualTo(Integer value) {
            addCriterion("collect =", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectNotEqualTo(Integer value) {
            addCriterion("collect <>", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectGreaterThan(Integer value) {
            addCriterion("collect >", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect >=", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectLessThan(Integer value) {
            addCriterion("collect <", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectLessThanOrEqualTo(Integer value) {
            addCriterion("collect <=", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectIn(List<Integer> values) {
            addCriterion("collect in", values, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectNotIn(List<Integer> values) {
            addCriterion("collect not in", values, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectBetween(Integer value1, Integer value2) {
            addCriterion("collect between", value1, value2, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectNotBetween(Integer value1, Integer value2) {
            addCriterion("collect not between", value1, value2, "collect");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdIsNull() {
            addCriterion("begincode_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdIsNotNull() {
            addCriterion("begincode_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdEqualTo(Integer value) {
            addCriterion("begincode_user_id =", value, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdNotEqualTo(Integer value) {
            addCriterion("begincode_user_id <>", value, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdGreaterThan(Integer value) {
            addCriterion("begincode_user_id >", value, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("begincode_user_id >=", value, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdLessThan(Integer value) {
            addCriterion("begincode_user_id <", value, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("begincode_user_id <=", value, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdIn(List<Integer> values) {
            addCriterion("begincode_user_id in", values, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdNotIn(List<Integer> values) {
            addCriterion("begincode_user_id not in", values, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdBetween(Integer value1, Integer value2) {
            addCriterion("begincode_user_id between", value1, value2, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andBegincodeUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("begincode_user_id not between", value1, value2, "begincodeUserId");
            return (Criteria) this;
        }

        public Criteria andProblemIdIsNull() {
            addCriterion("problem_id is null");
            return (Criteria) this;
        }

        public Criteria andProblemIdIsNotNull() {
            addCriterion("problem_id is not null");
            return (Criteria) this;
        }

        public Criteria andProblemIdEqualTo(Integer value) {
            addCriterion("problem_id =", value, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdNotEqualTo(Integer value) {
            addCriterion("problem_id <>", value, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdGreaterThan(Integer value) {
            addCriterion("problem_id >", value, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("problem_id >=", value, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdLessThan(Integer value) {
            addCriterion("problem_id <", value, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdLessThanOrEqualTo(Integer value) {
            addCriterion("problem_id <=", value, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdIn(List<Integer> values) {
            addCriterion("problem_id in", values, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdNotIn(List<Integer> values) {
            addCriterion("problem_id not in", values, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdBetween(Integer value1, Integer value2) {
            addCriterion("problem_id between", value1, value2, "problemId");
            return (Criteria) this;
        }

        public Criteria andProblemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("problem_id not between", value1, value2, "problemId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pro_attention
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pro_attention
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
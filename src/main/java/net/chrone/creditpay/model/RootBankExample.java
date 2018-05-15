package net.chrone.creditpay.model;

import java.util.ArrayList;
import java.util.List;

public class RootBankExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public RootBankExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
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
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
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

        public Criteria andBankNoIsNull() {
            addCriterion("bank_no is null");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNotNull() {
            addCriterion("bank_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankNoEqualTo(String value) {
            addCriterion("bank_no =", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotEqualTo(String value) {
            addCriterion("bank_no <>", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThan(String value) {
            addCriterion("bank_no >", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_no >=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThan(String value) {
            addCriterion("bank_no <", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThanOrEqualTo(String value) {
            addCriterion("bank_no <=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLike(String value) {
            addCriterion("bank_no like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotLike(String value) {
            addCriterion("bank_no not like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoIn(List<String> values) {
            addCriterion("bank_no in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotIn(List<String> values) {
            addCriterion("bank_no not in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoBetween(String value1, String value2) {
            addCriterion("bank_no between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotBetween(String value1, String value2) {
            addCriterion("bank_no not between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNmIsNull() {
            addCriterion("bank_nm is null");
            return (Criteria) this;
        }

        public Criteria andBankNmIsNotNull() {
            addCriterion("bank_nm is not null");
            return (Criteria) this;
        }

        public Criteria andBankNmEqualTo(String value) {
            addCriterion("bank_nm =", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmNotEqualTo(String value) {
            addCriterion("bank_nm <>", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmGreaterThan(String value) {
            addCriterion("bank_nm >", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmGreaterThanOrEqualTo(String value) {
            addCriterion("bank_nm >=", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmLessThan(String value) {
            addCriterion("bank_nm <", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmLessThanOrEqualTo(String value) {
            addCriterion("bank_nm <=", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmLike(String value) {
            addCriterion("bank_nm like", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmNotLike(String value) {
            addCriterion("bank_nm not like", value, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmIn(List<String> values) {
            addCriterion("bank_nm in", values, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmNotIn(List<String> values) {
            addCriterion("bank_nm not in", values, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmBetween(String value1, String value2) {
            addCriterion("bank_nm between", value1, value2, "bankNm");
            return (Criteria) this;
        }

        public Criteria andBankNmNotBetween(String value1, String value2) {
            addCriterion("bank_nm not between", value1, value2, "bankNm");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_root_bank
     *
     * @mbg.generated do_not_delete_during_merge Fri Nov 17 17:01:57 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_root_bank
     *
     * @mbg.generated Fri Nov 17 17:01:57 CST 2017
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
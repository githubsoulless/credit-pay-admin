package net.chrone.creditpay.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LevelOrderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public LevelOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
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
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderDtIsNull() {
            addCriterion("order_dt is null");
            return (Criteria) this;
        }

        public Criteria andOrderDtIsNotNull() {
            addCriterion("order_dt is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDtEqualTo(String value) {
            addCriterion("order_dt =", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtNotEqualTo(String value) {
            addCriterion("order_dt <>", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtGreaterThan(String value) {
            addCriterion("order_dt >", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtGreaterThanOrEqualTo(String value) {
            addCriterion("order_dt >=", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtLessThan(String value) {
            addCriterion("order_dt <", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtLessThanOrEqualTo(String value) {
            addCriterion("order_dt <=", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtLike(String value) {
            addCriterion("order_dt like", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtNotLike(String value) {
            addCriterion("order_dt not like", value, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtIn(List<String> values) {
            addCriterion("order_dt in", values, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtNotIn(List<String> values) {
            addCriterion("order_dt not in", values, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtBetween(String value1, String value2) {
            addCriterion("order_dt between", value1, value2, "orderDt");
            return (Criteria) this;
        }

        public Criteria andOrderDtNotBetween(String value1, String value2) {
            addCriterion("order_dt not between", value1, value2, "orderDt");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andTheLevelIsNull() {
            addCriterion("the_level is null");
            return (Criteria) this;
        }

        public Criteria andTheLevelIsNotNull() {
            addCriterion("the_level is not null");
            return (Criteria) this;
        }

        public Criteria andTheLevelEqualTo(Integer value) {
            addCriterion("the_level =", value, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelNotEqualTo(Integer value) {
            addCriterion("the_level <>", value, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelGreaterThan(Integer value) {
            addCriterion("the_level >", value, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("the_level >=", value, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelLessThan(Integer value) {
            addCriterion("the_level <", value, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelLessThanOrEqualTo(Integer value) {
            addCriterion("the_level <=", value, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelIn(List<Integer> values) {
            addCriterion("the_level in", values, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelNotIn(List<Integer> values) {
            addCriterion("the_level not in", values, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelBetween(Integer value1, Integer value2) {
            addCriterion("the_level between", value1, value2, "theLevel");
            return (Criteria) this;
        }

        public Criteria andTheLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("the_level not between", value1, value2, "theLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelIsNull() {
            addCriterion("end_level is null");
            return (Criteria) this;
        }

        public Criteria andEndLevelIsNotNull() {
            addCriterion("end_level is not null");
            return (Criteria) this;
        }

        public Criteria andEndLevelEqualTo(Integer value) {
            addCriterion("end_level =", value, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelNotEqualTo(Integer value) {
            addCriterion("end_level <>", value, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelGreaterThan(Integer value) {
            addCriterion("end_level >", value, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_level >=", value, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelLessThan(Integer value) {
            addCriterion("end_level <", value, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelLessThanOrEqualTo(Integer value) {
            addCriterion("end_level <=", value, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelIn(List<Integer> values) {
            addCriterion("end_level in", values, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelNotIn(List<Integer> values) {
            addCriterion("end_level not in", values, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelBetween(Integer value1, Integer value2) {
            addCriterion("end_level between", value1, value2, "endLevel");
            return (Criteria) this;
        }

        public Criteria andEndLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("end_level not between", value1, value2, "endLevel");
            return (Criteria) this;
        }

        public Criteria andPayStIsNull() {
            addCriterion("pay_st is null");
            return (Criteria) this;
        }

        public Criteria andPayStIsNotNull() {
            addCriterion("pay_st is not null");
            return (Criteria) this;
        }

        public Criteria andPayStEqualTo(Integer value) {
            addCriterion("pay_st =", value, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStNotEqualTo(Integer value) {
            addCriterion("pay_st <>", value, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStGreaterThan(Integer value) {
            addCriterion("pay_st >", value, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_st >=", value, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStLessThan(Integer value) {
            addCriterion("pay_st <", value, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStLessThanOrEqualTo(Integer value) {
            addCriterion("pay_st <=", value, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStIn(List<Integer> values) {
            addCriterion("pay_st in", values, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStNotIn(List<Integer> values) {
            addCriterion("pay_st not in", values, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStBetween(Integer value1, Integer value2) {
            addCriterion("pay_st between", value1, value2, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayStNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_st not between", value1, value2, "paySt");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoIsNull() {
            addCriterion("chnl_order_no is null");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoIsNotNull() {
            addCriterion("chnl_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoEqualTo(String value) {
            addCriterion("chnl_order_no =", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoNotEqualTo(String value) {
            addCriterion("chnl_order_no <>", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoGreaterThan(String value) {
            addCriterion("chnl_order_no >", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("chnl_order_no >=", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoLessThan(String value) {
            addCriterion("chnl_order_no <", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoLessThanOrEqualTo(String value) {
            addCriterion("chnl_order_no <=", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoLike(String value) {
            addCriterion("chnl_order_no like", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoNotLike(String value) {
            addCriterion("chnl_order_no not like", value, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoIn(List<String> values) {
            addCriterion("chnl_order_no in", values, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoNotIn(List<String> values) {
            addCriterion("chnl_order_no not in", values, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoBetween(String value1, String value2) {
            addCriterion("chnl_order_no between", value1, value2, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andChnlOrderNoNotBetween(String value1, String value2) {
            addCriterion("chnl_order_no not between", value1, value2, "chnlOrderNo");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrIsNull() {
            addCriterion("row_crt_usr is null");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrIsNotNull() {
            addCriterion("row_crt_usr is not null");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrEqualTo(String value) {
            addCriterion("row_crt_usr =", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotEqualTo(String value) {
            addCriterion("row_crt_usr <>", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrGreaterThan(String value) {
            addCriterion("row_crt_usr >", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrGreaterThanOrEqualTo(String value) {
            addCriterion("row_crt_usr >=", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrLessThan(String value) {
            addCriterion("row_crt_usr <", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrLessThanOrEqualTo(String value) {
            addCriterion("row_crt_usr <=", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrLike(String value) {
            addCriterion("row_crt_usr like", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotLike(String value) {
            addCriterion("row_crt_usr not like", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrIn(List<String> values) {
            addCriterion("row_crt_usr in", values, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotIn(List<String> values) {
            addCriterion("row_crt_usr not in", values, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrBetween(String value1, String value2) {
            addCriterion("row_crt_usr between", value1, value2, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotBetween(String value1, String value2) {
            addCriterion("row_crt_usr not between", value1, value2, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsIsNull() {
            addCriterion("row_crt_ts is null");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsIsNotNull() {
            addCriterion("row_crt_ts is not null");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsEqualTo(Date value) {
            addCriterion("row_crt_ts =", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsNotEqualTo(Date value) {
            addCriterion("row_crt_ts <>", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsGreaterThan(Date value) {
            addCriterion("row_crt_ts >", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsGreaterThanOrEqualTo(Date value) {
            addCriterion("row_crt_ts >=", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsLessThan(Date value) {
            addCriterion("row_crt_ts <", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsLessThanOrEqualTo(Date value) {
            addCriterion("row_crt_ts <=", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsIn(List<Date> values) {
            addCriterion("row_crt_ts in", values, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsNotIn(List<Date> values) {
            addCriterion("row_crt_ts not in", values, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsBetween(Date value1, Date value2) {
            addCriterion("row_crt_ts between", value1, value2, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsNotBetween(Date value1, Date value2) {
            addCriterion("row_crt_ts not between", value1, value2, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrIsNull() {
            addCriterion("rec_upd_usr is null");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrIsNotNull() {
            addCriterion("rec_upd_usr is not null");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrEqualTo(String value) {
            addCriterion("rec_upd_usr =", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotEqualTo(String value) {
            addCriterion("rec_upd_usr <>", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrGreaterThan(String value) {
            addCriterion("rec_upd_usr >", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrGreaterThanOrEqualTo(String value) {
            addCriterion("rec_upd_usr >=", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrLessThan(String value) {
            addCriterion("rec_upd_usr <", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrLessThanOrEqualTo(String value) {
            addCriterion("rec_upd_usr <=", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrLike(String value) {
            addCriterion("rec_upd_usr like", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotLike(String value) {
            addCriterion("rec_upd_usr not like", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrIn(List<String> values) {
            addCriterion("rec_upd_usr in", values, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotIn(List<String> values) {
            addCriterion("rec_upd_usr not in", values, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrBetween(String value1, String value2) {
            addCriterion("rec_upd_usr between", value1, value2, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotBetween(String value1, String value2) {
            addCriterion("rec_upd_usr not between", value1, value2, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsIsNull() {
            addCriterion("rec_upd_ts is null");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsIsNotNull() {
            addCriterion("rec_upd_ts is not null");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsEqualTo(Date value) {
            addCriterion("rec_upd_ts =", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsNotEqualTo(Date value) {
            addCriterion("rec_upd_ts <>", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsGreaterThan(Date value) {
            addCriterion("rec_upd_ts >", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsGreaterThanOrEqualTo(Date value) {
            addCriterion("rec_upd_ts >=", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsLessThan(Date value) {
            addCriterion("rec_upd_ts <", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsLessThanOrEqualTo(Date value) {
            addCriterion("rec_upd_ts <=", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsIn(List<Date> values) {
            addCriterion("rec_upd_ts in", values, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsNotIn(List<Date> values) {
            addCriterion("rec_upd_ts not in", values, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsBetween(Date value1, Date value2) {
            addCriterion("rec_upd_ts between", value1, value2, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsNotBetween(Date value1, Date value2) {
            addCriterion("rec_upd_ts not between", value1, value2, "recUpdTs");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_level_order
     *
     * @mbg.generated do_not_delete_during_merge Thu Nov 16 10:53:39 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_level_order
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
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
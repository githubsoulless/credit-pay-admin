package net.chrone.creditpay.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentFeeRateExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */public AgentFeeRateExample(){oredCriteria=new ArrayList<Criteria>();}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */public List<Criteria> getOredCriteria(){return oredCriteria;}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */protected abstract static class GeneratedCriteria {protected List<Criterion> criteria;protected GeneratedCriteria(){super();criteria=new ArrayList<Criterion>();}public boolean isValid(){return criteria.size() > 0;}public List<Criterion> getAllCriteria(){return criteria;}public List<Criterion> getCriteria(){return criteria;}protected void addCriterion(String condition){if (condition == null){throw new RuntimeException("Value for condition cannot be null");}criteria.add(new Criterion(condition));}protected void addCriterion(String condition,Object value,String property){if (value == null){throw new RuntimeException("Value for " + property + " cannot be null");}criteria.add(new Criterion(condition,value));}protected void addCriterion(String condition,Object value1,Object value2,String property){if (value1 == null || value2 == null){throw new RuntimeException("Between values for " + property + " cannot be null");}criteria.add(new Criterion(condition,value1,value2));}public Criteria andIdIsNull(){addCriterion("id is null");return (Criteria)this;}public Criteria andIdIsNotNull(){addCriterion("id is not null");return (Criteria)this;}public Criteria andIdEqualTo(String value){addCriterion("id =",value,"id");return (Criteria)this;}public Criteria andIdNotEqualTo(String value){addCriterion("id <>",value,"id");return (Criteria)this;}public Criteria andIdGreaterThan(String value){addCriterion("id >",value,"id");return (Criteria)this;}public Criteria andIdGreaterThanOrEqualTo(String value){addCriterion("id >=",value,"id");return (Criteria)this;}public Criteria andIdLessThan(String value){addCriterion("id <",value,"id");return (Criteria)this;}public Criteria andIdLessThanOrEqualTo(String value){addCriterion("id <=",value,"id");return (Criteria)this;}public Criteria andIdLike(String value){addCriterion("id like",value,"id");return (Criteria)this;}public Criteria andIdNotLike(String value){addCriterion("id not like",value,"id");return (Criteria)this;}public Criteria andIdIn(List<String> values){addCriterion("id in",values,"id");return (Criteria)this;}public Criteria andIdNotIn(List<String> values){addCriterion("id not in",values,"id");return (Criteria)this;}public Criteria andIdBetween(String value1,String value2){addCriterion("id between",value1,value2,"id");return (Criteria)this;}public Criteria andIdNotBetween(String value1,String value2){addCriterion("id not between",value1,value2,"id");return (Criteria)this;}public Criteria andLevelIdIsNull(){addCriterion("level_id is null");return (Criteria)this;}public Criteria andLevelIdIsNotNull(){addCriterion("level_id is not null");return (Criteria)this;}public Criteria andLevelIdEqualTo(Integer value){addCriterion("level_id =",value,"levelId");return (Criteria)this;}public Criteria andLevelIdNotEqualTo(Integer value){addCriterion("level_id <>",value,"levelId");return (Criteria)this;}public Criteria andLevelIdGreaterThan(Integer value){addCriterion("level_id >",value,"levelId");return (Criteria)this;}public Criteria andLevelIdGreaterThanOrEqualTo(Integer value){addCriterion("level_id >=",value,"levelId");return (Criteria)this;}public Criteria andLevelIdLessThan(Integer value){addCriterion("level_id <",value,"levelId");return (Criteria)this;}public Criteria andLevelIdLessThanOrEqualTo(Integer value){addCriterion("level_id <=",value,"levelId");return (Criteria)this;}public Criteria andLevelIdIn(List<Integer> values){addCriterion("level_id in",values,"levelId");return (Criteria)this;}public Criteria andLevelIdNotIn(List<Integer> values){addCriterion("level_id not in",values,"levelId");return (Criteria)this;}public Criteria andLevelIdBetween(Integer value1,Integer value2){addCriterion("level_id between",value1,value2,"levelId");return (Criteria)this;}public Criteria andLevelIdNotBetween(Integer value1,Integer value2){addCriterion("level_id not between",value1,value2,"levelId");return (Criteria)this;}public Criteria andPayFeeRateIsNull(){addCriterion("pay_fee_rate is null");return (Criteria)this;}public Criteria andPayFeeRateIsNotNull(){addCriterion("pay_fee_rate is not null");return (Criteria)this;}public Criteria andPayFeeRateEqualTo(BigDecimal value){addCriterion("pay_fee_rate =",value,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateNotEqualTo(BigDecimal value){addCriterion("pay_fee_rate <>",value,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateGreaterThan(BigDecimal value){addCriterion("pay_fee_rate >",value,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateGreaterThanOrEqualTo(BigDecimal value){addCriterion("pay_fee_rate >=",value,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateLessThan(BigDecimal value){addCriterion("pay_fee_rate <",value,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateLessThanOrEqualTo(BigDecimal value){addCriterion("pay_fee_rate <=",value,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateIn(List<BigDecimal> values){addCriterion("pay_fee_rate in",values,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateNotIn(List<BigDecimal> values){addCriterion("pay_fee_rate not in",values,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateBetween(BigDecimal value1,BigDecimal value2){addCriterion("pay_fee_rate between",value1,value2,"payFeeRate");return (Criteria)this;}public Criteria andPayFeeRateNotBetween(BigDecimal value1,BigDecimal value2){addCriterion("pay_fee_rate not between",value1,value2,"payFeeRate");return (Criteria)this;}public Criteria andTransTypeIsNull(){addCriterion("trans_type is null");return (Criteria)this;}public Criteria andTransTypeIsNotNull(){addCriterion("trans_type is not null");return (Criteria)this;}public Criteria andTransTypeEqualTo(Integer value){addCriterion("trans_type =",value,"transType");return (Criteria)this;}public Criteria andTransTypeNotEqualTo(Integer value){addCriterion("trans_type <>",value,"transType");return (Criteria)this;}public Criteria andTransTypeGreaterThan(Integer value){addCriterion("trans_type >",value,"transType");return (Criteria)this;}public Criteria andTransTypeGreaterThanOrEqualTo(Integer value){addCriterion("trans_type >=",value,"transType");return (Criteria)this;}public Criteria andTransTypeLessThan(Integer value){addCriterion("trans_type <",value,"transType");return (Criteria)this;}public Criteria andTransTypeLessThanOrEqualTo(Integer value){addCriterion("trans_type <=",value,"transType");return (Criteria)this;}public Criteria andTransTypeIn(List<Integer> values){addCriterion("trans_type in",values,"transType");return (Criteria)this;}public Criteria andTransTypeNotIn(List<Integer> values){addCriterion("trans_type not in",values,"transType");return (Criteria)this;}public Criteria andTransTypeBetween(Integer value1,Integer value2){addCriterion("trans_type between",value1,value2,"transType");return (Criteria)this;}public Criteria andTransTypeNotBetween(Integer value1,Integer value2){addCriterion("trans_type not between",value1,value2,"transType");return (Criteria)this;}public Criteria andStatusIsNull(){addCriterion("status is null");return (Criteria)this;}public Criteria andStatusIsNotNull(){addCriterion("status is not null");return (Criteria)this;}public Criteria andStatusEqualTo(Integer value){addCriterion("status =",value,"status");return (Criteria)this;}public Criteria andStatusNotEqualTo(Integer value){addCriterion("status <>",value,"status");return (Criteria)this;}public Criteria andStatusGreaterThan(Integer value){addCriterion("status >",value,"status");return (Criteria)this;}public Criteria andStatusGreaterThanOrEqualTo(Integer value){addCriterion("status >=",value,"status");return (Criteria)this;}public Criteria andStatusLessThan(Integer value){addCriterion("status <",value,"status");return (Criteria)this;}public Criteria andStatusLessThanOrEqualTo(Integer value){addCriterion("status <=",value,"status");return (Criteria)this;}public Criteria andStatusIn(List<Integer> values){addCriterion("status in",values,"status");return (Criteria)this;}public Criteria andStatusNotIn(List<Integer> values){addCriterion("status not in",values,"status");return (Criteria)this;}public Criteria andStatusBetween(Integer value1,Integer value2){addCriterion("status between",value1,value2,"status");return (Criteria)this;}public Criteria andStatusNotBetween(Integer value1,Integer value2){addCriterion("status not between",value1,value2,"status");return (Criteria)this;}public Criteria andSettleTypeIsNull(){addCriterion("settle_type is null");return (Criteria)this;}public Criteria andSettleTypeIsNotNull(){addCriterion("settle_type is not null");return (Criteria)this;}public Criteria andSettleTypeEqualTo(Integer value){addCriterion("settle_type =",value,"settleType");return (Criteria)this;}public Criteria andSettleTypeNotEqualTo(Integer value){addCriterion("settle_type <>",value,"settleType");return (Criteria)this;}public Criteria andSettleTypeGreaterThan(Integer value){addCriterion("settle_type >",value,"settleType");return (Criteria)this;}public Criteria andSettleTypeGreaterThanOrEqualTo(Integer value){addCriterion("settle_type >=",value,"settleType");return (Criteria)this;}public Criteria andSettleTypeLessThan(Integer value){addCriterion("settle_type <",value,"settleType");return (Criteria)this;}public Criteria andSettleTypeLessThanOrEqualTo(Integer value){addCriterion("settle_type <=",value,"settleType");return (Criteria)this;}public Criteria andSettleTypeIn(List<Integer> values){addCriterion("settle_type in",values,"settleType");return (Criteria)this;}public Criteria andSettleTypeNotIn(List<Integer> values){addCriterion("settle_type not in",values,"settleType");return (Criteria)this;}public Criteria andSettleTypeBetween(Integer value1,Integer value2){addCriterion("settle_type between",value1,value2,"settleType");return (Criteria)this;}public Criteria andSettleTypeNotBetween(Integer value1,Integer value2){addCriterion("settle_type not between",value1,value2,"settleType");return (Criteria)this;}public Criteria andIntegralTypeIsNull(){addCriterion("integral_type is null");return (Criteria)this;}public Criteria andIntegralTypeIsNotNull(){addCriterion("integral_type is not null");return (Criteria)this;}public Criteria andIntegralTypeEqualTo(Integer value){addCriterion("integral_type =",value,"integralType");return (Criteria)this;}public Criteria andIntegralTypeNotEqualTo(Integer value){addCriterion("integral_type <>",value,"integralType");return (Criteria)this;}public Criteria andIntegralTypeGreaterThan(Integer value){addCriterion("integral_type >",value,"integralType");return (Criteria)this;}public Criteria andIntegralTypeGreaterThanOrEqualTo(Integer value){addCriterion("integral_type >=",value,"integralType");return (Criteria)this;}public Criteria andIntegralTypeLessThan(Integer value){addCriterion("integral_type <",value,"integralType");return (Criteria)this;}public Criteria andIntegralTypeLessThanOrEqualTo(Integer value){addCriterion("integral_type <=",value,"integralType");return (Criteria)this;}public Criteria andIntegralTypeIn(List<Integer> values){addCriterion("integral_type in",values,"integralType");return (Criteria)this;}public Criteria andIntegralTypeNotIn(List<Integer> values){addCriterion("integral_type not in",values,"integralType");return (Criteria)this;}public Criteria andIntegralTypeBetween(Integer value1,Integer value2){addCriterion("integral_type between",value1,value2,"integralType");return (Criteria)this;}public Criteria andIntegralTypeNotBetween(Integer value1,Integer value2){addCriterion("integral_type not between",value1,value2,"integralType");return (Criteria)this;}public Criteria andFeeRateIsNull(){addCriterion("fee_rate is null");return (Criteria)this;}public Criteria andFeeRateIsNotNull(){addCriterion("fee_rate is not null");return (Criteria)this;}public Criteria andFeeRateEqualTo(BigDecimal value){addCriterion("fee_rate =",value,"feeRate");return (Criteria)this;}public Criteria andFeeRateNotEqualTo(BigDecimal value){addCriterion("fee_rate <>",value,"feeRate");return (Criteria)this;}public Criteria andFeeRateGreaterThan(BigDecimal value){addCriterion("fee_rate >",value,"feeRate");return (Criteria)this;}public Criteria andFeeRateGreaterThanOrEqualTo(BigDecimal value){addCriterion("fee_rate >=",value,"feeRate");return (Criteria)this;}public Criteria andFeeRateLessThan(BigDecimal value){addCriterion("fee_rate <",value,"feeRate");return (Criteria)this;}public Criteria andFeeRateLessThanOrEqualTo(BigDecimal value){addCriterion("fee_rate <=",value,"feeRate");return (Criteria)this;}public Criteria andFeeRateIn(List<BigDecimal> values){addCriterion("fee_rate in",values,"feeRate");return (Criteria)this;}public Criteria andFeeRateNotIn(List<BigDecimal> values){addCriterion("fee_rate not in",values,"feeRate");return (Criteria)this;}public Criteria andFeeRateBetween(BigDecimal value1,BigDecimal value2){addCriterion("fee_rate between",value1,value2,"feeRate");return (Criteria)this;}public Criteria andFeeRateNotBetween(BigDecimal value1,BigDecimal value2){addCriterion("fee_rate not between",value1,value2,"feeRate");return (Criteria)this;}public Criteria andUpperlimitIsNull(){addCriterion("upperlimit is null");return (Criteria)this;}public Criteria andUpperlimitIsNotNull(){addCriterion("upperlimit is not null");return (Criteria)this;}public Criteria andUpperlimitEqualTo(Integer value){addCriterion("upperlimit =",value,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitNotEqualTo(Integer value){addCriterion("upperlimit <>",value,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitGreaterThan(Integer value){addCriterion("upperlimit >",value,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitGreaterThanOrEqualTo(Integer value){addCriterion("upperlimit >=",value,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitLessThan(Integer value){addCriterion("upperlimit <",value,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitLessThanOrEqualTo(Integer value){addCriterion("upperlimit <=",value,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitIn(List<Integer> values){addCriterion("upperlimit in",values,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitNotIn(List<Integer> values){addCriterion("upperlimit not in",values,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitBetween(Integer value1,Integer value2){addCriterion("upperlimit between",value1,value2,"upperlimit");return (Criteria)this;}public Criteria andUpperlimitNotBetween(Integer value1,Integer value2){addCriterion("upperlimit not between",value1,value2,"upperlimit");return (Criteria)this;}public Criteria andPayFeeIsNull(){addCriterion("pay_fee is null");return (Criteria)this;}public Criteria andPayFeeIsNotNull(){addCriterion("pay_fee is not null");return (Criteria)this;}public Criteria andPayFeeEqualTo(Integer value){addCriterion("pay_fee =",value,"payFee");return (Criteria)this;}public Criteria andPayFeeNotEqualTo(Integer value){addCriterion("pay_fee <>",value,"payFee");return (Criteria)this;}public Criteria andPayFeeGreaterThan(Integer value){addCriterion("pay_fee >",value,"payFee");return (Criteria)this;}public Criteria andPayFeeGreaterThanOrEqualTo(Integer value){addCriterion("pay_fee >=",value,"payFee");return (Criteria)this;}public Criteria andPayFeeLessThan(Integer value){addCriterion("pay_fee <",value,"payFee");return (Criteria)this;}public Criteria andPayFeeLessThanOrEqualTo(Integer value){addCriterion("pay_fee <=",value,"payFee");return (Criteria)this;}public Criteria andPayFeeIn(List<Integer> values){addCriterion("pay_fee in",values,"payFee");return (Criteria)this;}public Criteria andPayFeeNotIn(List<Integer> values){addCriterion("pay_fee not in",values,"payFee");return (Criteria)this;}public Criteria andPayFeeBetween(Integer value1,Integer value2){addCriterion("pay_fee between",value1,value2,"payFee");return (Criteria)this;}public Criteria andPayFeeNotBetween(Integer value1,Integer value2){addCriterion("pay_fee not between",value1,value2,"payFee");return (Criteria)this;}public Criteria andPayChnlCodeIsNull(){addCriterion("pay_chnl_code is null");return (Criteria)this;}public Criteria andPayChnlCodeIsNotNull(){addCriterion("pay_chnl_code is not null");return (Criteria)this;}public Criteria andPayChnlCodeEqualTo(String value){addCriterion("pay_chnl_code =",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeNotEqualTo(String value){addCriterion("pay_chnl_code <>",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeGreaterThan(String value){addCriterion("pay_chnl_code >",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeGreaterThanOrEqualTo(String value){addCriterion("pay_chnl_code >=",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeLessThan(String value){addCriterion("pay_chnl_code <",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeLessThanOrEqualTo(String value){addCriterion("pay_chnl_code <=",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeLike(String value){addCriterion("pay_chnl_code like",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeNotLike(String value){addCriterion("pay_chnl_code not like",value,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeIn(List<String> values){addCriterion("pay_chnl_code in",values,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeNotIn(List<String> values){addCriterion("pay_chnl_code not in",values,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeBetween(String value1,String value2){addCriterion("pay_chnl_code between",value1,value2,"payChnlCode");return (Criteria)this;}public Criteria andPayChnlCodeNotBetween(String value1,String value2){addCriterion("pay_chnl_code not between",value1,value2,"payChnlCode");return (Criteria)this;}public Criteria andRowCrtUsrIsNull(){addCriterion("row_crt_usr is null");return (Criteria)this;}public Criteria andRowCrtUsrIsNotNull(){addCriterion("row_crt_usr is not null");return (Criteria)this;}public Criteria andRowCrtUsrEqualTo(String value){addCriterion("row_crt_usr =",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrNotEqualTo(String value){addCriterion("row_crt_usr <>",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrGreaterThan(String value){addCriterion("row_crt_usr >",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrGreaterThanOrEqualTo(String value){addCriterion("row_crt_usr >=",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrLessThan(String value){addCriterion("row_crt_usr <",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrLessThanOrEqualTo(String value){addCriterion("row_crt_usr <=",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrLike(String value){addCriterion("row_crt_usr like",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrNotLike(String value){addCriterion("row_crt_usr not like",value,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrIn(List<String> values){addCriterion("row_crt_usr in",values,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrNotIn(List<String> values){addCriterion("row_crt_usr not in",values,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrBetween(String value1,String value2){addCriterion("row_crt_usr between",value1,value2,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtUsrNotBetween(String value1,String value2){addCriterion("row_crt_usr not between",value1,value2,"rowCrtUsr");return (Criteria)this;}public Criteria andRowCrtTsIsNull(){addCriterion("row_crt_ts is null");return (Criteria)this;}public Criteria andRowCrtTsIsNotNull(){addCriterion("row_crt_ts is not null");return (Criteria)this;}public Criteria andRowCrtTsEqualTo(Date value){addCriterion("row_crt_ts =",value,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsNotEqualTo(Date value){addCriterion("row_crt_ts <>",value,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsGreaterThan(Date value){addCriterion("row_crt_ts >",value,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsGreaterThanOrEqualTo(Date value){addCriterion("row_crt_ts >=",value,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsLessThan(Date value){addCriterion("row_crt_ts <",value,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsLessThanOrEqualTo(Date value){addCriterion("row_crt_ts <=",value,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsIn(List<Date> values){addCriterion("row_crt_ts in",values,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsNotIn(List<Date> values){addCriterion("row_crt_ts not in",values,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsBetween(Date value1,Date value2){addCriterion("row_crt_ts between",value1,value2,"rowCrtTs");return (Criteria)this;}public Criteria andRowCrtTsNotBetween(Date value1,Date value2){addCriterion("row_crt_ts not between",value1,value2,"rowCrtTs");return (Criteria)this;}public Criteria andRecUpdUsrIsNull(){addCriterion("rec_upd_usr is null");return (Criteria)this;}public Criteria andRecUpdUsrIsNotNull(){addCriterion("rec_upd_usr is not null");return (Criteria)this;}public Criteria andRecUpdUsrEqualTo(String value){addCriterion("rec_upd_usr =",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrNotEqualTo(String value){addCriterion("rec_upd_usr <>",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrGreaterThan(String value){addCriterion("rec_upd_usr >",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrGreaterThanOrEqualTo(String value){addCriterion("rec_upd_usr >=",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrLessThan(String value){addCriterion("rec_upd_usr <",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrLessThanOrEqualTo(String value){addCriterion("rec_upd_usr <=",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrLike(String value){addCriterion("rec_upd_usr like",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrNotLike(String value){addCriterion("rec_upd_usr not like",value,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrIn(List<String> values){addCriterion("rec_upd_usr in",values,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrNotIn(List<String> values){addCriterion("rec_upd_usr not in",values,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrBetween(String value1,String value2){addCriterion("rec_upd_usr between",value1,value2,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdUsrNotBetween(String value1,String value2){addCriterion("rec_upd_usr not between",value1,value2,"recUpdUsr");return (Criteria)this;}public Criteria andRecUpdTsIsNull(){addCriterion("rec_upd_ts is null");return (Criteria)this;}public Criteria andRecUpdTsIsNotNull(){addCriterion("rec_upd_ts is not null");return (Criteria)this;}public Criteria andRecUpdTsEqualTo(Date value){addCriterion("rec_upd_ts =",value,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsNotEqualTo(Date value){addCriterion("rec_upd_ts <>",value,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsGreaterThan(Date value){addCriterion("rec_upd_ts >",value,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsGreaterThanOrEqualTo(Date value){addCriterion("rec_upd_ts >=",value,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsLessThan(Date value){addCriterion("rec_upd_ts <",value,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsLessThanOrEqualTo(Date value){addCriterion("rec_upd_ts <=",value,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsIn(List<Date> values){addCriterion("rec_upd_ts in",values,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsNotIn(List<Date> values){addCriterion("rec_upd_ts not in",values,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsBetween(Date value1,Date value2){addCriterion("rec_upd_ts between",value1,value2,"recUpdTs");return (Criteria)this;}public Criteria andRecUpdTsNotBetween(Date value1,Date value2){addCriterion("rec_upd_ts not between",value1,value2,"recUpdTs");return (Criteria)this;}}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_agent_fee_rate
	 * @mbg.generated  Wed Apr 03 19:53:24 CST 2019
	 */public static class Criterion {private String condition;private Object value;private Object secondValue;private boolean noValue;private boolean singleValue;private boolean betweenValue;private boolean listValue;private String typeHandler;public String getCondition(){return condition;}public Object getValue(){return value;}public Object getSecondValue(){return secondValue;}public boolean isNoValue(){return noValue;}public boolean isSingleValue(){return singleValue;}public boolean isBetweenValue(){return betweenValue;}public boolean isListValue(){return listValue;}public String getTypeHandler(){return typeHandler;}protected Criterion(String condition){super();this.condition=condition;this.typeHandler=null;this.noValue=true;}protected Criterion(String condition,Object value,String typeHandler){super();this.condition=condition;this.value=value;this.typeHandler=typeHandler;if (value instanceof List<?>){this.listValue=true;} else {this.singleValue=true;}}protected Criterion(String condition,Object value){this(condition,value,null);}protected Criterion(String condition,Object value,Object secondValue,String typeHandler){super();this.condition=condition;this.value=value;this.secondValue=secondValue;this.typeHandler=typeHandler;this.betweenValue=true;}protected Criterion(String condition,Object value,Object secondValue){this(condition,value,secondValue,null);}}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_agent_fee_rate
     *
     * @mbg.generated do_not_delete_during_merge Thu Aug 09 15:09:32 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}
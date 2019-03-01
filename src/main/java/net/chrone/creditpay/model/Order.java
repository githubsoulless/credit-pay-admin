package net.chrone.creditpay.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Order extends ParentMode{
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String orderNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.order_dt
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String orderDt;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.order_tp
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private Integer orderTp;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.busi_order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String busiOrderNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.user_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.task_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String taskId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.plan_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String planId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.amount
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private Integer amount;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.fee
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private Integer fee;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.card_name
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String cardName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.card_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String cardNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.card_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String cardId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.bank_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String bankNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.pay_st
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private Integer paySt;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.chnl_order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String chnlOrderNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.create_time
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.pay_time
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private Date payTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.remark
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String remark;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_order.chnl_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	private String chnlId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.order_no
	 * @return  the value of t_order.order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.order_no
	 * @param orderNo  the value for t_order.order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.order_dt
	 * @return  the value of t_order.order_dt
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getOrderDt() {
		return orderDt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.order_dt
	 * @param orderDt  the value for t_order.order_dt
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setOrderDt(String orderDt) {
		this.orderDt = orderDt == null ? null : orderDt.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.order_tp
	 * @return  the value of t_order.order_tp
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public Integer getOrderTp() {
		return orderTp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.order_tp
	 * @param orderTp  the value for t_order.order_tp
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setOrderTp(Integer orderTp) {
		this.orderTp = orderTp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.busi_order_no
	 * @return  the value of t_order.busi_order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getBusiOrderNo() {
		return busiOrderNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.busi_order_no
	 * @param busiOrderNo  the value for t_order.busi_order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setBusiOrderNo(String busiOrderNo) {
		this.busiOrderNo = busiOrderNo == null ? null : busiOrderNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.user_id
	 * @return  the value of t_order.user_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.user_id
	 * @param userId  the value for t_order.user_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.task_id
	 * @return  the value of t_order.task_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.task_id
	 * @param taskId  the value for t_order.task_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.plan_id
	 * @return  the value of t_order.plan_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.plan_id
	 * @param planId  the value for t_order.plan_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setPlanId(String planId) {
		this.planId = planId == null ? null : planId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.amount
	 * @return  the value of t_order.amount
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.amount
	 * @param amount  the value for t_order.amount
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.fee
	 * @return  the value of t_order.fee
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public Integer getFee() {
		return fee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.fee
	 * @param fee  the value for t_order.fee
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setFee(Integer fee) {
		this.fee = fee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.card_name
	 * @return  the value of t_order.card_name
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.card_name
	 * @param cardName  the value for t_order.card_name
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName == null ? null : cardName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.card_no
	 * @return  the value of t_order.card_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.card_no
	 * @param cardNo  the value for t_order.card_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.card_id
	 * @return  the value of t_order.card_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.card_id
	 * @param cardId  the value for t_order.card_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId == null ? null : cardId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.bank_no
	 * @return  the value of t_order.bank_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.bank_no
	 * @param bankNo  the value for t_order.bank_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo == null ? null : bankNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.pay_st
	 * @return  the value of t_order.pay_st
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public Integer getPaySt() {
		return paySt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.pay_st
	 * @param paySt  the value for t_order.pay_st
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setPaySt(Integer paySt) {
		this.paySt = paySt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.chnl_order_no
	 * @return  the value of t_order.chnl_order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getChnlOrderNo() {
		return chnlOrderNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.chnl_order_no
	 * @param chnlOrderNo  the value for t_order.chnl_order_no
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setChnlOrderNo(String chnlOrderNo) {
		this.chnlOrderNo = chnlOrderNo == null ? null : chnlOrderNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.create_time
	 * @return  the value of t_order.create_time
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.create_time
	 * @param createTime  the value for t_order.create_time
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.pay_time
	 * @return  the value of t_order.pay_time
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.pay_time
	 * @param payTime  the value for t_order.pay_time
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.remark
	 * @return  the value of t_order.remark
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.remark
	 * @param remark  the value for t_order.remark
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_order.chnl_id
	 * @return  the value of t_order.chnl_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public String getChnlId() {
		return chnlId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_order.chnl_id
	 * @param chnlId  the value for t_order.chnl_id
	 * @mbggenerated  Mon Dec 11 15:34:20 CST 2017
	 */
	public void setChnlId(String chnlId) {
		this.chnlId = chnlId == null ? null : chnlId.trim();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer startAmt;
	private Integer endAmt;
	private String startAmtStr;
	private String endAmtStr;
	
	private String chnlName;
	private Integer settleType;
	private int userProfits;
	private int agentProfits;
	private int platFee;
	
	private String agentId;
	private String agentName;
	
	
	//fileds design for export excel begin
	private String orderTpFormat;
	private String amountFormat;
	private String platFeeFormat;
	private String feeFormat;
	private String plantProfitFormat;
	private String userProfitFormat;
	private String agentProfitFormat;
	private String cardFormat;
	private String payStFormat;
	private String totalProfitFormat;
	//fileds design for export excel end
	
	
	public String getAgentName() {
		return agentName;
	}

	public String getPlatFeeFormat() {
		return platFeeFormat;
	}

	public void setPlatFeeFormat(String platFeeFormat) {
		this.platFeeFormat = platFeeFormat;
	}

	public String getOrderTpFormat() {
		return orderTpFormat;
	}

	public void setOrderTpFormat(String orderTpFormat) {
		this.orderTpFormat = orderTpFormat;
	}

	public String getAmountFormat() {
		return amountFormat;
	}

	public void setAmountFormat(String amountFormat) {
		this.amountFormat = amountFormat;
	}

	public String getPlantProfitFormat() {
		return plantProfitFormat;
	}

	public void setPlantProfitFormat(String plantProfitFormat) {
		this.plantProfitFormat = plantProfitFormat;
	}

	public String getFeeFormat() {
		return feeFormat;
	}

	public void setFeeFormat(String feeFormat) {
		this.feeFormat = feeFormat;
	}

	public String getUserProfitFormat() {
		return userProfitFormat;
	}

	public void setUserProfitFormat(String userProfitFormat) {
		this.userProfitFormat = userProfitFormat;
	}

	public String getAgentProfitFormat() {
		return agentProfitFormat;
	}

	public void setAgentProfitFormat(String agentProfitFormat) {
		this.agentProfitFormat = agentProfitFormat;
	}

	public String getCardFormat() {
		return cardFormat;
	}

	public void setCardFormat(String cardFormat) {
		this.cardFormat = cardFormat;
	}

	public String getPayStFormat() {
		return payStFormat;
	}

	public void setPayStFormat(String payStFormat) {
		this.payStFormat = payStFormat;
	}

	public String getTotalProfitFormat() {
		return totalProfitFormat;
	}

	public void setTotalProfitFormat(String totalProfitFormat) {
		this.totalProfitFormat = totalProfitFormat;
	}

	public void setStartAmt(Integer startAmt) {
		this.startAmt = startAmt;
	}

	public void setEndAmt(Integer endAmt) {
		this.endAmt = endAmt;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public int getUserProfits() {
		return userProfits;
	}

	public void setUserProfits(int userProfits) {
		this.userProfits = userProfits;
	}

	public int getAgentProfits() {
		return agentProfits;
	}

	public void setAgentProfits(int agentProfits) {
		this.agentProfits = agentProfits;
	}

	public int getPlatFee() {
		return platFee;
	}

	public void setPlatFee(int platFee) {
		this.platFee = platFee;
	}

	public String getStartAmtStr() {
		return startAmtStr;
	}

	public void setStartAmtStr(String startAmtStr) {
		this.startAmtStr = startAmtStr;
	}

	public String getEndAmtStr() {
		return endAmtStr;
	}

	public void setEndAmtStr(String endAmtStr) {
		this.endAmtStr = endAmtStr;
	}

	public Integer getStartAmt() {
		if(StringUtils.isNotEmpty(startAmtStr)){
			return new BigDecimal(startAmtStr).multiply(new BigDecimal(100)).intValue();
		}
		return startAmt;
	}

	public Integer getEndAmt() {
		if(StringUtils.isNotEmpty(endAmtStr)){
			return new BigDecimal(endAmtStr).multiply(new BigDecimal(100)).intValue();
		}
		return endAmt;
	}

	public String getChnlName() {
		return chnlName;
	}

	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}

	public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	
}
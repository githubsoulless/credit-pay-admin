package net.chrone.creditpay.model;

import java.util.Date;

public class TxRecord extends ParentMode {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.record_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private String recordId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.user_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private String userId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.card_name
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private String cardName;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.card_no
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private String cardNo;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.card_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private String cardId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.bank_no
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private String bankNo;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.amount
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private Integer amount;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.fee
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private Integer fee;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.status
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private Integer status;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.remark
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private String remark;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.create_time
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private Date createTime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_tx_record.pay_time
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	private Date payTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.record_id
	 *
	 * @return the value of t_tx_record.record_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public String getRecordId() {
		return recordId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.record_id
	 *
	 * @param recordId
	 *            the value for t_tx_record.record_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId == null ? null : recordId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.user_id
	 *
	 * @return the value of t_tx_record.user_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.user_id
	 *
	 * @param userId
	 *            the value for t_tx_record.user_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.card_name
	 *
	 * @return the value of t_tx_record.card_name
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.card_name
	 *
	 * @param cardName
	 *            the value for t_tx_record.card_name
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName == null ? null : cardName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.card_no
	 *
	 * @return the value of t_tx_record.card_no
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.card_no
	 *
	 * @param cardNo
	 *            the value for t_tx_record.card_no
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.card_id
	 *
	 * @return the value of t_tx_record.card_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.card_id
	 *
	 * @param cardId
	 *            the value for t_tx_record.card_id
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId == null ? null : cardId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.bank_no
	 *
	 * @return the value of t_tx_record.bank_no
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.bank_no
	 *
	 * @param bankNo
	 *            the value for t_tx_record.bank_no
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo == null ? null : bankNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.amount
	 *
	 * @return the value of t_tx_record.amount
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.amount
	 *
	 * @param amount
	 *            the value for t_tx_record.amount
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.fee
	 *
	 * @return the value of t_tx_record.fee
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public Integer getFee() {
		return fee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.fee
	 *
	 * @param fee
	 *            the value for t_tx_record.fee
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setFee(Integer fee) {
		this.fee = fee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.status
	 *
	 * @return the value of t_tx_record.status
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.status
	 *
	 * @param status
	 *            the value for t_tx_record.status
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.remark
	 *
	 * @return the value of t_tx_record.remark
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.remark
	 *
	 * @param remark
	 *            the value for t_tx_record.remark
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.create_time
	 *
	 * @return the value of t_tx_record.create_time
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.create_time
	 *
	 * @param createTime
	 *            the value for t_tx_record.create_time
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_tx_record.pay_time
	 *
	 * @return the value of t_tx_record.pay_time
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_tx_record.pay_time
	 *
	 * @param payTime
	 *            the value for t_tx_record.pay_time
	 *
	 * @mbg.generated Thu Nov 16 10:53:39 CST 2017
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	private Integer arrAmt;
	private String bankNm;

	public Integer getArrAmt() {
		return arrAmt;
	}

	public void setArrAmt(Integer arrAmt) {
		this.arrAmt = arrAmt;
	}

	public String getBankNm() {
		return bankNm;
	}

	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}

}
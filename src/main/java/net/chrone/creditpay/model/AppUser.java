package net.chrone.creditpay.model;

import java.util.Date;
import java.util.List;

public class AppUser extends ParentMode{
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.user_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.login_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String loginId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.mer_name
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String merName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.level_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Integer levelId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.parent_user_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String parentUserId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.agent_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String agentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.account_name
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String accountName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.card_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String cardNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.cert_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String certNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.pms_bank_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String pmsBankNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.mobile
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String mobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.ty_count
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Integer tyCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.close_plan_count
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Integer closePlanCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.last_login_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Date lastLoginTs;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.cert_correct
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String certCorrect;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.cert_opposite
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String certOpposite;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.cert_meet
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String certMeet;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.cert_limitdt
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String certLimitdt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.card_correct
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String cardCorrect;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.card_opposite
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String cardOpposite;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.cert_status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Integer certStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.audit_status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Integer auditStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.audit_desc
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String auditDesc;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.login_pwd
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String loginPwd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.pay_pwd
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String payPwd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.owner_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String ownerId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.row_crt_usr
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String rowCrtUsr;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.row_crt_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Date rowCrtTs;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.rec_upd_usr
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private String recUpdUsr;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_app_user.rec_upd_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	private Date recUpdTs;




	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.user_id
	 * @return  the value of t_app_user.user_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.user_id
	 * @param userId  the value for t_app_user.user_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.login_id
	 * @return  the value of t_app_user.login_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.login_id
	 * @param loginId  the value for t_app_user.login_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId == null ? null : loginId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.mer_name
	 * @return  the value of t_app_user.mer_name
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getMerName() {
		return merName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.mer_name
	 * @param merName  the value for t_app_user.mer_name
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setMerName(String merName) {
		this.merName = merName == null ? null : merName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.level_id
	 * @return  the value of t_app_user.level_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Integer getLevelId() {
		return levelId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.level_id
	 * @param levelId  the value for t_app_user.level_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.status
	 * @return  the value of t_app_user.status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.status
	 * @param status  the value for t_app_user.status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.parent_user_id
	 * @return  the value of t_app_user.parent_user_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getParentUserId() {
		return parentUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.parent_user_id
	 * @param parentUserId  the value for t_app_user.parent_user_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setParentUserId(String parentUserId) {
		this.parentUserId = parentUserId == null ? null : parentUserId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.agent_id
	 * @return  the value of t_app_user.agent_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getAgentId() {
		return agentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.agent_id
	 * @param agentId  the value for t_app_user.agent_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId == null ? null : agentId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.account_name
	 * @return  the value of t_app_user.account_name
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.account_name
	 * @param accountName  the value for t_app_user.account_name
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.card_no
	 * @return  the value of t_app_user.card_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.card_no
	 * @param cardNo  the value for t_app_user.card_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.cert_no
	 * @return  the value of t_app_user.cert_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.cert_no
	 * @param certNo  the value for t_app_user.cert_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo == null ? null : certNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.pms_bank_no
	 * @return  the value of t_app_user.pms_bank_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getPmsBankNo() {
		return pmsBankNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.pms_bank_no
	 * @param pmsBankNo  the value for t_app_user.pms_bank_no
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setPmsBankNo(String pmsBankNo) {
		this.pmsBankNo = pmsBankNo == null ? null : pmsBankNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.mobile
	 * @return  the value of t_app_user.mobile
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.mobile
	 * @param mobile  the value for t_app_user.mobile
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.ty_count
	 * @return  the value of t_app_user.ty_count
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Integer getTyCount() {
		return tyCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.ty_count
	 * @param tyCount  the value for t_app_user.ty_count
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setTyCount(Integer tyCount) {
		this.tyCount = tyCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.close_plan_count
	 * @return  the value of t_app_user.close_plan_count
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Integer getClosePlanCount() {
		return closePlanCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.close_plan_count
	 * @param closePlanCount  the value for t_app_user.close_plan_count
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setClosePlanCount(Integer closePlanCount) {
		this.closePlanCount = closePlanCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.last_login_ts
	 * @return  the value of t_app_user.last_login_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Date getLastLoginTs() {
		return lastLoginTs;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.last_login_ts
	 * @param lastLoginTs  the value for t_app_user.last_login_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setLastLoginTs(Date lastLoginTs) {
		this.lastLoginTs = lastLoginTs;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.cert_correct
	 * @return  the value of t_app_user.cert_correct
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCertCorrect() {
		return certCorrect;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.cert_correct
	 * @param certCorrect  the value for t_app_user.cert_correct
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCertCorrect(String certCorrect) {
		this.certCorrect = certCorrect == null ? null : certCorrect.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.cert_opposite
	 * @return  the value of t_app_user.cert_opposite
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCertOpposite() {
		return certOpposite;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.cert_opposite
	 * @param certOpposite  the value for t_app_user.cert_opposite
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCertOpposite(String certOpposite) {
		this.certOpposite = certOpposite == null ? null : certOpposite.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.cert_meet
	 * @return  the value of t_app_user.cert_meet
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCertMeet() {
		return certMeet;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.cert_meet
	 * @param certMeet  the value for t_app_user.cert_meet
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCertMeet(String certMeet) {
		this.certMeet = certMeet == null ? null : certMeet.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.cert_limitdt
	 * @return  the value of t_app_user.cert_limitdt
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCertLimitdt() {
		return certLimitdt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.cert_limitdt
	 * @param certLimitdt  the value for t_app_user.cert_limitdt
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCertLimitdt(String certLimitdt) {
		this.certLimitdt = certLimitdt == null ? null : certLimitdt.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.card_correct
	 * @return  the value of t_app_user.card_correct
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCardCorrect() {
		return cardCorrect;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.card_correct
	 * @param cardCorrect  the value for t_app_user.card_correct
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCardCorrect(String cardCorrect) {
		this.cardCorrect = cardCorrect == null ? null : cardCorrect.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.card_opposite
	 * @return  the value of t_app_user.card_opposite
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getCardOpposite() {
		return cardOpposite;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.card_opposite
	 * @param cardOpposite  the value for t_app_user.card_opposite
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCardOpposite(String cardOpposite) {
		this.cardOpposite = cardOpposite == null ? null : cardOpposite.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.cert_status
	 * @return  the value of t_app_user.cert_status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Integer getCertStatus() {
		return certStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.cert_status
	 * @param certStatus  the value for t_app_user.cert_status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setCertStatus(Integer certStatus) {
		this.certStatus = certStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.audit_status
	 * @return  the value of t_app_user.audit_status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.audit_status
	 * @param auditStatus  the value for t_app_user.audit_status
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.audit_desc
	 * @return  the value of t_app_user.audit_desc
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getAuditDesc() {
		return auditDesc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.audit_desc
	 * @param auditDesc  the value for t_app_user.audit_desc
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc == null ? null : auditDesc.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.login_pwd
	 * @return  the value of t_app_user.login_pwd
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.login_pwd
	 * @param loginPwd  the value for t_app_user.login_pwd
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd == null ? null : loginPwd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.pay_pwd
	 * @return  the value of t_app_user.pay_pwd
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getPayPwd() {
		return payPwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.pay_pwd
	 * @param payPwd  the value for t_app_user.pay_pwd
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd == null ? null : payPwd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.owner_id
	 * @return  the value of t_app_user.owner_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.owner_id
	 * @param ownerId  the value for t_app_user.owner_id
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId == null ? null : ownerId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.row_crt_usr
	 * @return  the value of t_app_user.row_crt_usr
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getRowCrtUsr() {
		return rowCrtUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.row_crt_usr
	 * @param rowCrtUsr  the value for t_app_user.row_crt_usr
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setRowCrtUsr(String rowCrtUsr) {
		this.rowCrtUsr = rowCrtUsr == null ? null : rowCrtUsr.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.row_crt_ts
	 * @return  the value of t_app_user.row_crt_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Date getRowCrtTs() {
		return rowCrtTs;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.row_crt_ts
	 * @param rowCrtTs  the value for t_app_user.row_crt_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setRowCrtTs(Date rowCrtTs) {
		this.rowCrtTs = rowCrtTs;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.rec_upd_usr
	 * @return  the value of t_app_user.rec_upd_usr
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public String getRecUpdUsr() {
		return recUpdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.rec_upd_usr
	 * @param recUpdUsr  the value for t_app_user.rec_upd_usr
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setRecUpdUsr(String recUpdUsr) {
		this.recUpdUsr = recUpdUsr == null ? null : recUpdUsr.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_app_user.rec_upd_ts
	 * @return  the value of t_app_user.rec_upd_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public Date getRecUpdTs() {
		return recUpdTs;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_app_user.rec_upd_ts
	 * @param recUpdTs  the value for t_app_user.rec_upd_ts
	 * @mbg.generated  Mon Jun 03 13:51:47 CST 2019
	 */
	public void setRecUpdTs(Date recUpdTs) {
		this.recUpdTs = recUpdTs;
	}

	void tStatus() {
	}

	void tStatus(Integer audi) {
	}

	void Status() {
	}

	void Status(Integer audit) {
	}

	void Desc() {
	}

	void Desc(String audit) {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int directCount;//直接
	private int subUserCount;//�???????有下�???????
	private int cardNum;
	private String pmsBankName;
	private String agentId1;
	private String agentId2;
	private String agentId3;
	private String agentId4;
	private String agentLevel;
	private List<String> ids;
	private String pid;
	private List<String> agentIds;
	
	private Integer sumOrder;
	
	
	//fileds design for export excel begin
	private String levelNameFormat;
	private String statusFormat;
	private String certStatusFormat;
	private String agentNameFormat;
	//fileds design for export excel end
	
	private String monthStr;
	
	private String rowCrtTsFormat;
	private String sumOrderFormat;
	
	
	

	public String getRowCrtTsFormat() {
		return rowCrtTsFormat;
	}

	public void setRowCrtTsFormat(String rowCrtTsFormat) {
		this.rowCrtTsFormat = rowCrtTsFormat;
	}

	public String getSumOrderFormat() {
		return sumOrderFormat;
	}

	public void setSumOrderFormat(String sumOrderFormat) {
		this.sumOrderFormat = sumOrderFormat;
	}

	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	public List<String> getAgentIds() {
		return agentIds;
	}

	public String getLevelNameFormat() {
		return levelNameFormat;
	}

	public void setLevelNameFormat(String levelNameFormat) {
		this.levelNameFormat = levelNameFormat;
	}

	public String getStatusFormat() {
		return statusFormat;
	}

	public void setStatusFormat(String statusFormat) {
		this.statusFormat = statusFormat;
	}

	public String getCertStatusFormat() {
		return certStatusFormat;
	}

	public void setCertStatusFormat(String certStatusFormat) {
		this.certStatusFormat = certStatusFormat;
	}

	public String getAgentNameFormat() {
		return agentNameFormat;
	}

	public void setAgentNameFormat(String agentNameFormat) {
		this.agentNameFormat = agentNameFormat;
	}

	public void setAgentIds(List<String> agentIds) {
		this.agentIds = agentIds;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAgentId1() {
		return agentId1;
	}

	public void setAgentId1(String agentId1) {
		this.agentId1 = agentId1;
	}

	public String getAgentId2() {
		return agentId2;
	}

	public void setAgentId2(String agentId2) {
		this.agentId2 = agentId2;
	}

	public String getAgentId3() {
		return agentId3;
	}

	public void setAgentId3(String agentId3) {
		this.agentId3 = agentId3;
	}

	public String getPmsBankName() {
		return pmsBankName;
	}

	public void setPmsBankName(String pmsBankName) {
		this.pmsBankName = pmsBankName;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public int getDirectCount() {
		return directCount;
	}

	public void setDirectCount(int directCount) {
		this.directCount = directCount;
	}

	public int getSubUserCount() {
		return subUserCount;
	}

	public String getAgentLevel() {
		return agentLevel;
	}

	public void setAgentLevel(String agentLevel) {
		this.agentLevel = agentLevel;
	}

	public void setSubUserCount(int subUserCount) {
		this.subUserCount = subUserCount;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getAgentId4() {
		return agentId4;
	}

	public void setAgentId4(String agentId4) {
		this.agentId4 = agentId4;
	}

	public Integer getSumOrder() {
		return sumOrder;
	}

	public void setSumOrder(Integer sumOrder) {
		this.sumOrder = sumOrder;
	}

}
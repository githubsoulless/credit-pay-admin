package net.chrone.creditpay.model;

import java.math.BigDecimal;
import java.util.Date;

public class PayPlan extends ParentMode{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.plan_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String planId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.user_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.card_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String cardId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.card_name
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String cardName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.card_no
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String cardNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.bank_no
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String bankNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.plan_amt
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer planAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.pay_amt
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer payAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer fee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.plan_dt_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer planDtNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.status
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.xf_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer xfNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.pay_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer payNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.pay_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer payBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.df_fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer dfFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.df_fee_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer dfFeeBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.hk_fee_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer hkFeeBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.pay_chnl
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String payChnl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.plan_type
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer planType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.pay_fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Double payFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.remark
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.row_crt_usr
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String rowCrtUsr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.row_crt_ts
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Date rowCrtTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.rec_upd_usr
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private String recUpdUsr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.rec_upd_ts
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Date recUpdTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.pay_chnl_type
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer payChnlType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pay_plan.hk_min_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    private Integer hkMinBalance;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.plan_id
     *
     * @return the value of t_pay_plan.plan_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.plan_id
     *
     * @param planId the value for t_pay_plan.plan_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.user_id
     *
     * @return the value of t_pay_plan.user_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.user_id
     *
     * @param userId the value for t_pay_plan.user_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.card_id
     *
     * @return the value of t_pay_plan.card_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.card_id
     *
     * @param cardId the value for t_pay_plan.card_id
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.card_name
     *
     * @return the value of t_pay_plan.card_name
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.card_name
     *
     * @param cardName the value for t_pay_plan.card_name
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.card_no
     *
     * @return the value of t_pay_plan.card_no
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.card_no
     *
     * @param cardNo the value for t_pay_plan.card_no
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.bank_no
     *
     * @return the value of t_pay_plan.bank_no
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.bank_no
     *
     * @param bankNo the value for t_pay_plan.bank_no
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.plan_amt
     *
     * @return the value of t_pay_plan.plan_amt
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getPlanAmt() {
        return planAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.plan_amt
     *
     * @param planAmt the value for t_pay_plan.plan_amt
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPlanAmt(Integer planAmt) {
        this.planAmt = planAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.pay_amt
     *
     * @return the value of t_pay_plan.pay_amt
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getPayAmt() {
        return payAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.pay_amt
     *
     * @param payAmt the value for t_pay_plan.pay_amt
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPayAmt(Integer payAmt) {
        this.payAmt = payAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.fee
     *
     * @return the value of t_pay_plan.fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.fee
     *
     * @param fee the value for t_pay_plan.fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.plan_dt_num
     *
     * @return the value of t_pay_plan.plan_dt_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getPlanDtNum() {
        return planDtNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.plan_dt_num
     *
     * @param planDtNum the value for t_pay_plan.plan_dt_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPlanDtNum(Integer planDtNum) {
        this.planDtNum = planDtNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.status
     *
     * @return the value of t_pay_plan.status
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.status
     *
     * @param status the value for t_pay_plan.status
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.xf_num
     *
     * @return the value of t_pay_plan.xf_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getXfNum() {
        return xfNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.xf_num
     *
     * @param xfNum the value for t_pay_plan.xf_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setXfNum(Integer xfNum) {
        this.xfNum = xfNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.pay_num
     *
     * @return the value of t_pay_plan.pay_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getPayNum() {
        return payNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.pay_num
     *
     * @param payNum the value for t_pay_plan.pay_num
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.pay_balance
     *
     * @return the value of t_pay_plan.pay_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getPayBalance() {
        return payBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.pay_balance
     *
     * @param payBalance the value for t_pay_plan.pay_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPayBalance(Integer payBalance) {
        this.payBalance = payBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.df_fee
     *
     * @return the value of t_pay_plan.df_fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getDfFee() {
        return dfFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.df_fee
     *
     * @param dfFee the value for t_pay_plan.df_fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setDfFee(Integer dfFee) {
        this.dfFee = dfFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.df_fee_balance
     *
     * @return the value of t_pay_plan.df_fee_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getDfFeeBalance() {
        return dfFeeBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.df_fee_balance
     *
     * @param dfFeeBalance the value for t_pay_plan.df_fee_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setDfFeeBalance(Integer dfFeeBalance) {
        this.dfFeeBalance = dfFeeBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.hk_fee_balance
     *
     * @return the value of t_pay_plan.hk_fee_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getHkFeeBalance() {
        return hkFeeBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.hk_fee_balance
     *
     * @param hkFeeBalance the value for t_pay_plan.hk_fee_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setHkFeeBalance(Integer hkFeeBalance) {
        this.hkFeeBalance = hkFeeBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.pay_chnl
     *
     * @return the value of t_pay_plan.pay_chnl
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getPayChnl() {
        return payChnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.pay_chnl
     *
     * @param payChnl the value for t_pay_plan.pay_chnl
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPayChnl(String payChnl) {
        this.payChnl = payChnl == null ? null : payChnl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.plan_type
     *
     * @return the value of t_pay_plan.plan_type
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getPlanType() {
        return planType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.plan_type
     *
     * @param planType the value for t_pay_plan.plan_type
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.pay_fee
     *
     * @return the value of t_pay_plan.pay_fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Double getPayFee() {
        return payFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.pay_fee
     *
     * @param payFee the value for t_pay_plan.pay_fee
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPayFee(Double payFee) {
        this.payFee = payFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.remark
     *
     * @return the value of t_pay_plan.remark
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.remark
     *
     * @param remark the value for t_pay_plan.remark
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.row_crt_usr
     *
     * @return the value of t_pay_plan.row_crt_usr
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getRowCrtUsr() {
        return rowCrtUsr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.row_crt_usr
     *
     * @param rowCrtUsr the value for t_pay_plan.row_crt_usr
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setRowCrtUsr(String rowCrtUsr) {
        this.rowCrtUsr = rowCrtUsr == null ? null : rowCrtUsr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.row_crt_ts
     *
     * @return the value of t_pay_plan.row_crt_ts
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Date getRowCrtTs() {
        return rowCrtTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.row_crt_ts
     *
     * @param rowCrtTs the value for t_pay_plan.row_crt_ts
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setRowCrtTs(Date rowCrtTs) {
        this.rowCrtTs = rowCrtTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.rec_upd_usr
     *
     * @return the value of t_pay_plan.rec_upd_usr
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public String getRecUpdUsr() {
        return recUpdUsr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.rec_upd_usr
     *
     * @param recUpdUsr the value for t_pay_plan.rec_upd_usr
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setRecUpdUsr(String recUpdUsr) {
        this.recUpdUsr = recUpdUsr == null ? null : recUpdUsr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.rec_upd_ts
     *
     * @return the value of t_pay_plan.rec_upd_ts
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Date getRecUpdTs() {
        return recUpdTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.rec_upd_ts
     *
     * @param recUpdTs the value for t_pay_plan.rec_upd_ts
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setRecUpdTs(Date recUpdTs) {
        this.recUpdTs = recUpdTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.pay_chnl_type
     *
     * @return the value of t_pay_plan.pay_chnl_type
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getPayChnlType() {
        return payChnlType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.pay_chnl_type
     *
     * @param payChnlType the value for t_pay_plan.pay_chnl_type
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setPayChnlType(Integer payChnlType) {
        this.payChnlType = payChnlType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pay_plan.hk_min_balance
     *
     * @return the value of t_pay_plan.hk_min_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public Integer getHkMinBalance() {
        return hkMinBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pay_plan.hk_min_balance
     *
     * @param hkMinBalance the value for t_pay_plan.hk_min_balance
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    public void setHkMinBalance(Integer hkMinBalance) {
        this.hkMinBalance = hkMinBalance;
    }
    private static final long serialVersionUID = 1L;

    private String bankName;

    private int successXfCount;
    private int successPayCount;
    private int usePreAmt;//



    public int getUsePreAmt() {
        return usePreAmt;
    }

    public void setUsePreAmt(int usePreAmt) {
        this.usePreAmt = usePreAmt;
    }

    public int getSuccessXfCount() {
        return successXfCount;
    }

    public void setSuccessXfCount(int successXfCount) {
        this.successXfCount = successXfCount;
    }

    public int getSuccessPayCount() {
        return successPayCount;
    }

    public void setSuccessPayCount(int successPayCount) {
        this.successPayCount = successPayCount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
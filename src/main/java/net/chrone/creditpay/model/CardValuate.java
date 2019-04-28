package net.chrone.creditpay.model;

import java.util.Date;

public class CardValuate extends ParentMode {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.user_id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.name
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.cert_id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String certId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.card_no
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String cardNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.mobile
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.status
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.memo
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String memo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.money
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private Integer money;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.url
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.pay_type
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private Integer payType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.pay_st
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private Integer paySt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.order_no
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.create_time
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.update_time
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_valuate.chnl_code
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    private String chnlCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.id
     *
     * @return the value of t_card_valuate.id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.id
     *
     * @param id the value for t_card_valuate.id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.user_id
     *
     * @return the value of t_card_valuate.user_id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.user_id
     *
     * @param userId the value for t_card_valuate.user_id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.name
     *
     * @return the value of t_card_valuate.name
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.name
     *
     * @param name the value for t_card_valuate.name
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.cert_id
     *
     * @return the value of t_card_valuate.cert_id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getCertId() {
        return certId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.cert_id
     *
     * @param certId the value for t_card_valuate.cert_id
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setCertId(String certId) {
        this.certId = certId == null ? null : certId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.card_no
     *
     * @return the value of t_card_valuate.card_no
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.card_no
     *
     * @param cardNo the value for t_card_valuate.card_no
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.mobile
     *
     * @return the value of t_card_valuate.mobile
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.mobile
     *
     * @param mobile the value for t_card_valuate.mobile
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.status
     *
     * @return the value of t_card_valuate.status
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.status
     *
     * @param status the value for t_card_valuate.status
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.memo
     *
     * @return the value of t_card_valuate.memo
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.memo
     *
     * @param memo the value for t_card_valuate.memo
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.money
     *
     * @return the value of t_card_valuate.money
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.money
     *
     * @param money the value for t_card_valuate.money
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.url
     *
     * @return the value of t_card_valuate.url
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.url
     *
     * @param url the value for t_card_valuate.url
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.pay_type
     *
     * @return the value of t_card_valuate.pay_type
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.pay_type
     *
     * @param payType the value for t_card_valuate.pay_type
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.pay_st
     *
     * @return the value of t_card_valuate.pay_st
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public Integer getPaySt() {
        return paySt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.pay_st
     *
     * @param paySt the value for t_card_valuate.pay_st
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setPaySt(Integer paySt) {
        this.paySt = paySt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.order_no
     *
     * @return the value of t_card_valuate.order_no
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.order_no
     *
     * @param orderNo the value for t_card_valuate.order_no
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.create_time
     *
     * @return the value of t_card_valuate.create_time
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.create_time
     *
     * @param createTime the value for t_card_valuate.create_time
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.update_time
     *
     * @return the value of t_card_valuate.update_time
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.update_time
     *
     * @param updateTime the value for t_card_valuate.update_time
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_valuate.chnl_code
     *
     * @return the value of t_card_valuate.chnl_code
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public String getChnlCode() {
        return chnlCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_valuate.chnl_code
     *
     * @param chnlCode the value for t_card_valuate.chnl_code
     *
     * @mbg.generated Thu Apr 11 14:01:47 CST 2019
     */
    public void setChnlCode(String chnlCode) {
        this.chnlCode = chnlCode == null ? null : chnlCode.trim();
    }
}
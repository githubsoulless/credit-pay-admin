package net.chrone.creditpay.model;

import java.util.Date;

public class UpProfitsDetail extends ParentMode {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.profits_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String profitsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.profits_dt
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String profitsDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.order_no
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.profits_num
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private Integer profitsNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.profits_user_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String profitsUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.profits_user_name
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String profitsUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.agent_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String agentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.agent_name
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String agentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.amount
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private Integer amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.row_crt_usr
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String rowCrtUsr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.row_crt_ts
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private Date rowCrtTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.rec_upd_usr
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private String recUpdUsr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_up_profits_detail.rec_upd_ts
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    private Date recUpdTs;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.id
     *
     * @return the value of t_up_profits_detail.id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.id
     *
     * @param id the value for t_up_profits_detail.id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.profits_id
     *
     * @return the value of t_up_profits_detail.profits_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getProfitsId() {
        return profitsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.profits_id
     *
     * @param profitsId the value for t_up_profits_detail.profits_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setProfitsId(String profitsId) {
        this.profitsId = profitsId == null ? null : profitsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.profits_dt
     *
     * @return the value of t_up_profits_detail.profits_dt
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getProfitsDt() {
        return profitsDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.profits_dt
     *
     * @param profitsDt the value for t_up_profits_detail.profits_dt
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setProfitsDt(String profitsDt) {
        this.profitsDt = profitsDt == null ? null : profitsDt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.order_no
     *
     * @return the value of t_up_profits_detail.order_no
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.order_no
     *
     * @param orderNo the value for t_up_profits_detail.order_no
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.profits_num
     *
     * @return the value of t_up_profits_detail.profits_num
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public Integer getProfitsNum() {
        return profitsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.profits_num
     *
     * @param profitsNum the value for t_up_profits_detail.profits_num
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setProfitsNum(Integer profitsNum) {
        this.profitsNum = profitsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.profits_user_id
     *
     * @return the value of t_up_profits_detail.profits_user_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getProfitsUserId() {
        return profitsUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.profits_user_id
     *
     * @param profitsUserId the value for t_up_profits_detail.profits_user_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setProfitsUserId(String profitsUserId) {
        this.profitsUserId = profitsUserId == null ? null : profitsUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.profits_user_name
     *
     * @return the value of t_up_profits_detail.profits_user_name
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getProfitsUserName() {
        return profitsUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.profits_user_name
     *
     * @param profitsUserName the value for t_up_profits_detail.profits_user_name
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setProfitsUserName(String profitsUserName) {
        this.profitsUserName = profitsUserName == null ? null : profitsUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.agent_id
     *
     * @return the value of t_up_profits_detail.agent_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.agent_id
     *
     * @param agentId the value for t_up_profits_detail.agent_id
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.agent_name
     *
     * @return the value of t_up_profits_detail.agent_name
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.agent_name
     *
     * @param agentName the value for t_up_profits_detail.agent_name
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.amount
     *
     * @return the value of t_up_profits_detail.amount
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.amount
     *
     * @param amount the value for t_up_profits_detail.amount
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.row_crt_usr
     *
     * @return the value of t_up_profits_detail.row_crt_usr
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getRowCrtUsr() {
        return rowCrtUsr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.row_crt_usr
     *
     * @param rowCrtUsr the value for t_up_profits_detail.row_crt_usr
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setRowCrtUsr(String rowCrtUsr) {
        this.rowCrtUsr = rowCrtUsr == null ? null : rowCrtUsr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.row_crt_ts
     *
     * @return the value of t_up_profits_detail.row_crt_ts
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public Date getRowCrtTs() {
        return rowCrtTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.row_crt_ts
     *
     * @param rowCrtTs the value for t_up_profits_detail.row_crt_ts
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setRowCrtTs(Date rowCrtTs) {
        this.rowCrtTs = rowCrtTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.rec_upd_usr
     *
     * @return the value of t_up_profits_detail.rec_upd_usr
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public String getRecUpdUsr() {
        return recUpdUsr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.rec_upd_usr
     *
     * @param recUpdUsr the value for t_up_profits_detail.rec_upd_usr
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setRecUpdUsr(String recUpdUsr) {
        this.recUpdUsr = recUpdUsr == null ? null : recUpdUsr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_up_profits_detail.rec_upd_ts
     *
     * @return the value of t_up_profits_detail.rec_upd_ts
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public Date getRecUpdTs() {
        return recUpdTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_up_profits_detail.rec_upd_ts
     *
     * @param recUpdTs the value for t_up_profits_detail.rec_upd_ts
     *
     * @mbggenerated Tue Nov 21 18:17:01 CST 2017
     */
    public void setRecUpdTs(Date recUpdTs) {
        this.recUpdTs = recUpdTs;
    }
    
    private int level;
    private String accountName;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
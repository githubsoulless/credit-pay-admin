package net.chrone.creditpay.model;

import java.util.Date;

public class AgentProfitVO extends ParentMode {

	private String agentId;   //代理ID
	private String agentName;  //代理名称
	private Integer level;     //代理级别
	private Integer profitsType;   //分润类型  1还款分润  2升级分润
	private int profitsAmount;  //分润金额
	private String orderNo;    //关联订单号
	private int amount;       //订单金额
	private String userId;   //受益人账号
	private String accountName;  //受益人真是姓名
	private Date rowCrtTs;      //分润生成时间
	private String taskId;
	
	private String srcUserId;
	private String srcAccountName;
	
	private String rowNum;
	
	
	

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getSrcUserId() {
		return srcUserId;
	}

	public void setSrcUserId(String srcUserId) {
		this.srcUserId = srcUserId;
	}

	public String getSrcAccountName() {
		return srcAccountName;
	}

	public void setSrcAccountName(String srcAccountName) {
		this.srcAccountName = srcAccountName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getProfitsType() {
		return profitsType;
	}

	public void setProfitsType(Integer profitsType) {
		this.profitsType = profitsType;
	}

	public int getProfitsAmount() {
		return profitsAmount;
	}

	public void setProfitsAmount(int profitsAmount) {
		this.profitsAmount = profitsAmount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getRowCrtTs() {
		return rowCrtTs;
	}

	public void setRowCrtTs(Date rowCrtTs) {
		this.rowCrtTs = rowCrtTs;
	}

}

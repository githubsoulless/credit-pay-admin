package net.chrone.creditpay.model;

import java.util.Date;

import net.chrone.creditpay.taglibs.Fen2YuanTag;
import net.chrone.creditpay.util.DateUtils;

public class PayPlanDCStatisticsDTO extends ParentMode{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String plan_id;
	private Integer status;//状态
	private Date row_crt_ts; 
	private int plan_amt;//计划总金额
	private int fee;//计划服务费
	private int df_fee;//代付服务费
	private int pay_amt;//实际支付金额
	private int user_profits;//用户分润
	private int agent_profits;//代理分润
	private int refund_fee;//服务费退款
	private int refund_dffee;//代付费退款
	private int xf_chnl_amt;//消费成本
	private int df_chnl_amt;//代付成本
	
	public String transPlan_amt(){
		return Fen2YuanTag.formatAmt(plan_amt+"");
	}
	public String transSyAmt(){
		return Fen2YuanTag.formatAmt((df_fee+pay_amt-user_profits-agent_profits-refund_fee-refund_dffee-xf_chnl_amt-df_chnl_amt)+"");
	}
	public String transFee(){
		return Fen2YuanTag.formatAmt(fee+"");
	}
	public String transDf_fee(){
		return Fen2YuanTag.formatAmt(df_fee+"");
	}
	public String transUser_profits(){
		return Fen2YuanTag.formatAmt(user_profits+"");
	}
	public String transAgent_profits(){
		return Fen2YuanTag.formatAmt(agent_profits+"");
	}
	public String transRefund_fee(){
		return Fen2YuanTag.formatAmt(refund_fee+"");
	}
	public String transRefund_dffee(){
		return Fen2YuanTag.formatAmt(refund_dffee+"");
	}
	public String transXf_chnl_amt(){
		return Fen2YuanTag.formatAmt(xf_chnl_amt+"");
	}
	public String transDf_chnl_amt(){
		return Fen2YuanTag.formatAmt(df_chnl_amt+"");
	}
	public String transYhAmt(){
		return Fen2YuanTag.formatAmt((fee-pay_amt)+"");
	}
	
	public String transStatus(){
		if(status==0){
			return "进行中";
		}else if(status==1){
			return "执行失败";
		}else if(status==2){
			return "已完成";
		}else if(status==3){
			return "已终止";
		}else{
			return "";
		}
		
	}
	
	public String transRow_crt_ts(){
		if(row_crt_ts==null){
			return "";
		}
		return DateUtils.formatDate(row_crt_ts, "yyyy-MM-dd");
	}
	
	
	
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getRow_crt_ts() {
		return row_crt_ts;
	}
	public void setRow_crt_ts(Date row_crt_ts) {
		this.row_crt_ts = row_crt_ts;
	}
	public Integer getPlan_amt() {
		return plan_amt;
	}
	public void setPlan_amt(Integer plan_amt) {
		this.plan_amt = plan_amt;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public Integer getDf_fee() {
		return df_fee;
	}
	public void setDf_fee(Integer df_fee) {
		this.df_fee = df_fee;
	}
	public Integer getPay_amt() {
		return pay_amt;
	}
	public void setPay_amt(Integer pay_amt) {
		this.pay_amt = pay_amt;
	}
	public Integer getUser_profits() {
		return user_profits;
	}
	public void setUser_profits(Integer user_profits) {
		this.user_profits = user_profits;
	}
	public Integer getAgent_profits() {
		return agent_profits;
	}
	public void setAgent_profits(Integer agent_profits) {
		this.agent_profits = agent_profits;
	}
	public Integer getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}
	public Integer getRefund_dffee() {
		return refund_dffee;
	}
	public void setRefund_dffee(Integer refund_dffee) {
		this.refund_dffee = refund_dffee;
	}
	public Integer getXf_chnl_amt() {
		return xf_chnl_amt;
	}
	public void setXf_chnl_amt(Integer xf_chnl_amt) {
		this.xf_chnl_amt = xf_chnl_amt;
	}
	public Integer getDf_chnl_amt() {
		return df_chnl_amt;
	}
	public void setDf_chnl_amt(Integer df_chnl_amt) {
		this.df_chnl_amt = df_chnl_amt;
	}
	
	
}

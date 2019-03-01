package net.chrone.creditpay.model;

/**
 * 
 * Title: SysParamDTO Description: 平台设置
 * 
 * @author huoliang
 * @data 2017年11月22日 上午11:57:51
 *
 */
public class SysParamDTO {
	
	private String type;  //0 平台参数  1还款参数  2快捷参数

	// 交易风控参数
	private String transStartTime; // 交易允许执行时间-开始
	private String transEndTime; // 交易允许执行时间-结束
	private String transPayMaxAmt; // 单笔消费最大金额 单位：分
	private String transCardPayMaxCount; // 单卡单日最多消费笔数

	// 制定计划参数
	private String planRePayLessAmt; // 单个还款计划金额小于等于指定金额
	private String planRePayLessAmtType; // 单个还款计划金额小于等于指定金额时，信用卡至少留存类型 0:金额，1比例
	private String planCardLessBalance; // 单个还款计划金额小于等于指定金额时，最少留存金额

	private String planRePayMoreAmt; // 单个还款计划金额大于指定金额
	private String planRePayMoreAmtType; // 单个还款计划金额大于指定金额时，信用卡至少留存类型 0:金额，1比例
	private String planCardMoreBalance; // 单个还款计划金额大于指定金额时，最少留存金额

	// 还款分润参数-用户
	private String profitsDirectUserFee; // 直接推荐人分润比率
	private String profitsInDirectUserFee; // 间接推荐人分润比率
	private String profitsUpUserFee; // 上上上级推荐人分润比率
	private String profitsType;//分润类型

	// 还款分润参数-代理
	private String profitsLevelOneAgentBase; // 一级代理成本基数
	private String profitsLevelTwoAgentBase; // 二级代理成本基数
	private String profitsLevelThreeAgentBase; // 三级代理成本基数

	// 抽奖次数设置
	private String lotteryTimesFNewUser; // 新用户注册奖励抽奖次数
	private String lotteryTimesFOneRePayPlan; // 每执行完1个还款计划，奖励抽奖机会次数
	private String lotteryTimesFBindOneCard; // 每成功绑定1张信用卡，奖励抽奖机会次数
	private String lotteryTimesFRecomOneUser; // 每推荐1个新用户，奖励抽奖机会次数
	
	//注册奖励机制
	private String awardRegisterType; //0无任何奖励  1注册奖励 2实名认证奖励
	private String awardRegisterDirUserAmount;//直邀奖励
	private String awardRegisterAgentAmount; //代理奖励
	//快捷刷卡消费奖励机制
	private String awardFastPayType; //0无任何奖励  1首刷奖励 2累积奖励
	private String awardFastPayDirUserAmount;//直邀奖励
	private String awardFastPayAgentAmount; //代理奖励
	private String awardFastPayTotal; //累积金额
	
	//完美快捷刷卡消费奖励机制
	private String awardWMFastPayType; //0无任何奖励  1首刷奖励 2累积奖励
	private String awardWMFastPayDirUserAmount;//直邀奖励
	private String awardWMFastPayAgentAmount; //代理奖励
	private String awardWMFastPayTotal; //累积金额
	
	
	public String getLotteryTimesFOneRePayPlan() {
		return lotteryTimesFOneRePayPlan;
	}

	public void setLotteryTimesFOneRePayPlan(String lotteryTimesFOneRePayPlan) {
		this.lotteryTimesFOneRePayPlan = lotteryTimesFOneRePayPlan;
	}

	public String getAwardRegisterType() {
		return awardRegisterType;
	}

	public void setAwardRegisterType(String awardRegisterType) {
		this.awardRegisterType = awardRegisterType;
	}

	public String getAwardRegisterDirUserAmount() {
		return awardRegisterDirUserAmount;
	}

	public void setAwardRegisterDirUserAmount(String awardRegisterDirUserAmount) {
		this.awardRegisterDirUserAmount = awardRegisterDirUserAmount;
	}

	public String getAwardRegisterAgentAmount() {
		return awardRegisterAgentAmount;
	}

	public void setAwardRegisterAgentAmount(String awardRegisterAgentAmount) {
		this.awardRegisterAgentAmount = awardRegisterAgentAmount;
	}

	public String getAwardFastPayType() {
		return awardFastPayType;
	}

	public void setAwardFastPayType(String awardFastPayType) {
		this.awardFastPayType = awardFastPayType;
	}

	public String getAwardFastPayDirUserAmount() {
		return awardFastPayDirUserAmount;
	}

	public void setAwardFastPayDirUserAmount(String awardFastPayDirUserAmount) {
		this.awardFastPayDirUserAmount = awardFastPayDirUserAmount;
	}

	public String getAwardFastPayAgentAmount() {
		return awardFastPayAgentAmount;
	}

	public void setAwardFastPayAgentAmount(String awardFastPayAgentAmount) {
		this.awardFastPayAgentAmount = awardFastPayAgentAmount;
	}

	public String getAwardFastPayTotal() {
		return awardFastPayTotal;
	}

	public void setAwardFastPayTotal(String awardFastPayTotal) {
		this.awardFastPayTotal = awardFastPayTotal;
	}

	public String getAwardWMFastPayType() {
		return awardWMFastPayType;
	}

	public void setAwardWMFastPayType(String awardWMFastPayType) {
		this.awardWMFastPayType = awardWMFastPayType;
	}

	public String getAwardWMFastPayDirUserAmount() {
		return awardWMFastPayDirUserAmount;
	}

	public void setAwardWMFastPayDirUserAmount(String awardWMFastPayDirUserAmount) {
		this.awardWMFastPayDirUserAmount = awardWMFastPayDirUserAmount;
	}

	public String getAwardWMFastPayAgentAmount() {
		return awardWMFastPayAgentAmount;
	}

	public void setAwardWMFastPayAgentAmount(String awardWMFastPayAgentAmount) {
		this.awardWMFastPayAgentAmount = awardWMFastPayAgentAmount;
	}

	public String getAwardWMFastPayTotal() {
		return awardWMFastPayTotal;
	}

	public void setAwardWMFastPayTotal(String awardWMFastPayTotal) {
		this.awardWMFastPayTotal = awardWMFastPayTotal;
	}

	public String getLotteryTimesFNewUser() {
		return lotteryTimesFNewUser;
	}

	public void setLotteryTimesFNewUser(String lotteryTimesFNewUser) {
		this.lotteryTimesFNewUser = lotteryTimesFNewUser;
	}

	

	public String getLotteryTimesFBindOneCard() {
		return lotteryTimesFBindOneCard;
	}

	public void setLotteryTimesFBindOneCard(String lotteryTimesFBindOneCard) {
		this.lotteryTimesFBindOneCard = lotteryTimesFBindOneCard;
	}

	public String getLotteryTimesFRecomOneUser() {
		return lotteryTimesFRecomOneUser;
	}

	public void setLotteryTimesFRecomOneUser(String lotteryTimesFRecomOneUser) {
		this.lotteryTimesFRecomOneUser = lotteryTimesFRecomOneUser;
	}

	// 短信模板设置
	private String templateUserRegist; // 用户注册
	private String templateResetPassword; // 重设密码
	private String templatePlanFaildNotice; // 计划失败通知
	
	
	private String fastStartTime;//快捷开始时间
	private String fastEndTime;//快捷结束时间
	private String fastMinAmt;//最小金额
	private String fastMaxAmt;//最大金额
	private String fastCreditPayDayCount;//同张信用卡单日最多交易
	private String fastRateType;//费率类型
	private String fastRateVal;//费率值
	private String fastAgentpayRate;//代付费
	private String fastSettlePayDayCount;//同张结算卡单日最多结算
	
	// 快捷分润参数-用户
	private String fastProfitsDirectUserFee; // 直接推荐人分润比率
	private String fastProfitsInDirectUserFee; // 间接推荐人分润比率
	private String fastProfitsUpUserFee; // 上上上级推荐人分润比率
	private String fastProfitsType;//分润类型

	public String getFastProfitsInDirectUserFee() {
		return fastProfitsInDirectUserFee;
	}

	public void setFastProfitsInDirectUserFee(String fastProfitsInDirectUserFee) {
		this.fastProfitsInDirectUserFee = fastProfitsInDirectUserFee;
	}

	public String getFastProfitsUpUserFee() {
		return fastProfitsUpUserFee;
	}

	public void setFastProfitsUpUserFee(String fastProfitsUpUserFee) {
		this.fastProfitsUpUserFee = fastProfitsUpUserFee;
	}

	public String getFastProfitsType() {
		return fastProfitsType;
	}

	public void setFastProfitsType(String fastProfitsType) {
		this.fastProfitsType = fastProfitsType;
	}

	public String getFastProfitsLevelOneAgentBase() {
		return fastProfitsLevelOneAgentBase;
	}

	public void setFastProfitsLevelOneAgentBase(String fastProfitsLevelOneAgentBase) {
		this.fastProfitsLevelOneAgentBase = fastProfitsLevelOneAgentBase;
	}

	public String getFastProfitsLevelTwoAgentBase() {
		return fastProfitsLevelTwoAgentBase;
	}

	public void setFastProfitsLevelTwoAgentBase(String fastProfitsLevelTwoAgentBase) {
		this.fastProfitsLevelTwoAgentBase = fastProfitsLevelTwoAgentBase;
	}

	public String getFastProfitsLevelThreeAgentBase() {
		return fastProfitsLevelThreeAgentBase;
	}

	public void setFastProfitsLevelThreeAgentBase(String fastProfitsLevelThreeAgentBase) {
		this.fastProfitsLevelThreeAgentBase = fastProfitsLevelThreeAgentBase;
	}

	// 快捷分润参数-代理
	private String fastProfitsLevelOneAgentBase; // 一级代理成本基数
	private String fastProfitsLevelTwoAgentBase; // 二级代理成本基数
	private String fastProfitsLevelThreeAgentBase; // 三级代理成本基数
	

	public String getFastStartTime() {
		return fastStartTime;
	}

	public void setFastStartTime(String fastStartTime) {
		this.fastStartTime = fastStartTime;
	}

	public String getFastEndTime() {
		return fastEndTime;
	}

	public void setFastEndTime(String fastEndTime) {
		this.fastEndTime = fastEndTime;
	}

	public String getFastMinAmt() {
		return fastMinAmt;
	}

	public void setFastMinAmt(String fastMinAmt) {
		this.fastMinAmt = fastMinAmt;
	}

	public String getFastMaxAmt() {
		return fastMaxAmt;
	}

	public void setFastMaxAmt(String fastMaxAmt) {
		this.fastMaxAmt = fastMaxAmt;
	}

	public String getFastCreditPayDayCount() {
		return fastCreditPayDayCount;
	}

	public void setFastCreditPayDayCount(String fastCreditPayDayCount) {
		this.fastCreditPayDayCount = fastCreditPayDayCount;
	}

	public String getFastRateType() {
		return fastRateType;
	}

	public void setFastRateType(String fastRateType) {
		this.fastRateType = fastRateType;
	}

	public String getFastRateVal() {
		return fastRateVal;
	}

	public void setFastRateVal(String fastRateVal) {
		this.fastRateVal = fastRateVal;
	}

	public String getFastAgentpayRate() {
		return fastAgentpayRate;
	}

	public void setFastAgentpayRate(String fastAgentpayRate) {
		this.fastAgentpayRate = fastAgentpayRate;
	}

	public String getFastSettlePayDayCount() {
		return fastSettlePayDayCount;
	}

	public void setFastSettlePayDayCount(String fastSettlePayDayCount) {
		this.fastSettlePayDayCount = fastSettlePayDayCount;
	}

	public String getTransStartTime() {
		return transStartTime;
	}

	public void setTransStartTime(String transStartTime) {
		this.transStartTime = transStartTime;
	}

	public String getTransEndTime() {
		return transEndTime;
	}

	public void setTransEndTime(String transEndTime) {
		this.transEndTime = transEndTime;
	}

	public String getTransPayMaxAmt() {
		return transPayMaxAmt;
	}

	public void setTransPayMaxAmt(String transPayMaxAmt) {
		this.transPayMaxAmt = transPayMaxAmt;
	}

	public String getTransCardPayMaxCount() {
		return transCardPayMaxCount;
	}

	public void setTransCardPayMaxCount(String transCardPayMaxCount) {
		this.transCardPayMaxCount = transCardPayMaxCount;
	}

	public String getPlanRePayLessAmt() {
		return planRePayLessAmt;
	}

	public void setPlanRePayLessAmt(String planRePayLessAmt) {
		this.planRePayLessAmt = planRePayLessAmt;
	}

	public String getPlanRePayLessAmtType() {
		return planRePayLessAmtType;
	}

	public void setPlanRePayLessAmtType(String planRePayLessAmtType) {
		this.planRePayLessAmtType = planRePayLessAmtType;
	}

	public String getPlanCardLessBalance() {
		return planCardLessBalance;
	}

	public void setPlanCardLessBalance(String planCardLessBalance) {
		this.planCardLessBalance = planCardLessBalance;
	}

	public String getPlanRePayMoreAmt() {
		return planRePayMoreAmt;
	}

	public void setPlanRePayMoreAmt(String planRePayMoreAmt) {
		this.planRePayMoreAmt = planRePayMoreAmt;
	}

	public String getPlanRePayMoreAmtType() {
		return planRePayMoreAmtType;
	}

	public void setPlanRePayMoreAmtType(String planRePayMoreAmtType) {
		this.planRePayMoreAmtType = planRePayMoreAmtType;
	}

	public String getPlanCardMoreBalance() {
		return planCardMoreBalance;
	}

	public void setPlanCardMoreBalance(String planCardMoreBalance) {
		this.planCardMoreBalance = planCardMoreBalance;
	}

	public String getProfitsDirectUserFee() {
		return profitsDirectUserFee;
	}

	public void setProfitsDirectUserFee(String profitsDirectUserFee) {
		this.profitsDirectUserFee = profitsDirectUserFee;
	}

	public String getProfitsInDirectUserFee() {
		return profitsInDirectUserFee;
	}

	public void setProfitsInDirectUserFee(String profitsInDirectUserFee) {
		this.profitsInDirectUserFee = profitsInDirectUserFee;
	}

	public String getProfitsUpUserFee() {
		return profitsUpUserFee;
	}

	public void setProfitsUpUserFee(String profitsUpUserFee) {
		this.profitsUpUserFee = profitsUpUserFee;
	}

	public String getProfitsLevelOneAgentBase() {
		return profitsLevelOneAgentBase;
	}

	public void setProfitsLevelOneAgentBase(String profitsLevelOneAgentBase) {
		this.profitsLevelOneAgentBase = profitsLevelOneAgentBase;
	}

	public String getProfitsLevelTwoAgentBase() {
		return profitsLevelTwoAgentBase;
	}

	public void setProfitsLevelTwoAgentBase(String profitsLevelTwoAgentBase) {
		this.profitsLevelTwoAgentBase = profitsLevelTwoAgentBase;
	}

	public String getProfitsLevelThreeAgentBase() {
		return profitsLevelThreeAgentBase;
	}

	public void setProfitsLevelThreeAgentBase(String profitsLevelThreeAgentBase) {
		this.profitsLevelThreeAgentBase = profitsLevelThreeAgentBase;
	}

	public String getTemplateUserRegist() {
		return templateUserRegist;
	}

	public void setTemplateUserRegist(String templateUserRegist) {
		this.templateUserRegist = templateUserRegist;
	}

	public String getTemplateResetPassword() {
		return templateResetPassword;
	}

	public void setTemplateResetPassword(String templateResetPassword) {
		this.templateResetPassword = templateResetPassword;
	}

	public String getTemplatePlanFaildNotice() {
		return templatePlanFaildNotice;
	}

	public void setTemplatePlanFaildNotice(String templatePlanFaildNotice) {
		this.templatePlanFaildNotice = templatePlanFaildNotice;
	}

	private String closePlanFee;//终止计划比例
	
	private String closePlanCount;

	public String getClosePlanCount() {
		return closePlanCount;
	}

	public void setClosePlanCount(String closePlanCount) {
		this.closePlanCount = closePlanCount;
	}

	public String getClosePlanFee() {
		return closePlanFee;
	}

	public void setClosePlanFee(String closePlanFee) {
		this.closePlanFee = closePlanFee;
	}

	public String getProfitsType() {
		return profitsType;
	}

	public void setProfitsType(String profitsType) {
		this.profitsType = profitsType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFastProfitsDirectUserFee() {
		return fastProfitsDirectUserFee;
	}

	public void setFastProfitsDirectUserFee(String fastProfitsDirectUserFee) {
		this.fastProfitsDirectUserFee = fastProfitsDirectUserFee;
	}
	
	
}

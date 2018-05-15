package net.chrone.creditpay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.mapper.SysParamMapper;
import net.chrone.creditpay.model.SysParam;
import net.chrone.creditpay.model.SysParamDTO;
import net.chrone.creditpay.service.SysParamService;
import net.chrone.creditpay.util.RedisClient;

/**
 * 
 * Title: SysParamServiceImpl Description: 平台参数设置
 * 
 * @author huoliang
 * @data 2017年11月22日 下午6:04:03
 *
 */

@Service
public class SysParamServiceImpl implements SysParamService {

	@Autowired
	private SysParamMapper sysParamMapper;

	@Override
	public Map<String, String> listSysParam() {
		List<SysParam> list = sysParamMapper.selectByExample(null);
		Map<String, String> resMap = new HashMap<>();
		for (SysParam sysParam : list) {
			resMap.put(sysParam.getName(), sysParam.getValue());
		}
		return resMap;
	}

	@Override
	public void updateSysParam(SysParamDTO sysParamDTO) {
		//平台参数
		if("0".equals(sysParamDTO.getType())) {
			platSysParam(sysParamDTO);
		}
		
		//还款参数
		if("1".equals(sysParamDTO.getType())) {
			repaySysParam(sysParamDTO);
		}
		
		//快捷消费
		if("2".equals(sysParamDTO.getType())) {
			fastPaySysParam(sysParamDTO);
		}
		

	}
	
	/**
	 * 平台参数
	 */
	public void platSysParam(SysParamDTO sysParamDTO) {

		// 用户注册
		SysParam templateUserRegist = new SysParam();
		templateUserRegist.setName("template_user_regist");
		templateUserRegist.setValue(sysParamDTO.getTemplateUserRegist());
		templateUserRegist.setMemo("用户注册短信模板");
		sysParamMapper.saveSysParam(templateUserRegist);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"template_user_regist", JSON.toJSONString(templateUserRegist));
		// 重设密码
		SysParam templateResetPassword = new SysParam();
		templateResetPassword.setName("template_reset_password");
		templateResetPassword.setValue(sysParamDTO.getTemplateResetPassword());
		templateResetPassword.setMemo("重设密码短信模板");
		sysParamMapper.saveSysParam(templateResetPassword);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"template_reset_password", JSON.toJSONString(templateResetPassword));
		// 计划失败通知
		SysParam templatePlanFaildNotice = new SysParam();
		templatePlanFaildNotice.setName("template_plan_faild_notice");
		templatePlanFaildNotice.setValue(sysParamDTO.getTemplatePlanFaildNotice());
		templatePlanFaildNotice.setMemo("计划失败通知短信模板");
		sysParamMapper.saveSysParam(templatePlanFaildNotice);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"template_plan_faild_notice", JSON.toJSONString(templatePlanFaildNotice));
		// 新用户注册奖励抽奖次数
		SysParam lotteryTimesFNewUser = new SysParam();
		lotteryTimesFNewUser.setName("lottery_times_f_new_user");
		lotteryTimesFNewUser.setValue(sysParamDTO.getLotteryTimesFNewUser());
		lotteryTimesFNewUser.setMemo("新用户注册奖励抽奖次数");
		sysParamMapper.saveSysParam(lotteryTimesFNewUser);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"lottery_times_f_new_user", JSON.toJSONString(lotteryTimesFNewUser));
		// 每执行完1个还款计划，奖励抽奖机会次数
		SysParam lotteryTimesFOneRePayPlan = new SysParam();
		lotteryTimesFOneRePayPlan.setName("lottery_times_f_one_repay_play");
		lotteryTimesFOneRePayPlan.setValue(sysParamDTO.getLotteryTimesFOneRePayPlan());
		lotteryTimesFOneRePayPlan.setMemo("每执行完1个还款计划，奖励抽奖机会次数");
		sysParamMapper.saveSysParam(lotteryTimesFOneRePayPlan);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"lottery_times_f_one_repay_play", JSON.toJSONString(lotteryTimesFOneRePayPlan));
		// 每成功绑定1张信用卡，奖励抽奖机会次数
		SysParam lotteryTimesFBindOneCard = new SysParam(); 
		lotteryTimesFBindOneCard.setName("lottery_times_f_bind_one_card");
		lotteryTimesFBindOneCard.setValue(sysParamDTO.getLotteryTimesFBindOneCard());
		lotteryTimesFBindOneCard.setMemo("每成功绑定1张信用卡，奖励抽奖机会次数");
		sysParamMapper.saveSysParam(lotteryTimesFBindOneCard);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"lottery_times_f_bind_one_card", JSON.toJSONString(lotteryTimesFBindOneCard));
		// 每推荐1个新用户，奖励抽奖机会次数
		SysParam lotteryTimesFRecomOneUser = new SysParam(); 
		lotteryTimesFRecomOneUser.setName("lottery_times_f_recom_one_user");
		lotteryTimesFRecomOneUser.setValue(sysParamDTO.getLotteryTimesFRecomOneUser());
		lotteryTimesFRecomOneUser.setMemo("每推荐1个新用户，奖励抽奖机会次数");
		sysParamMapper.saveSysParam(lotteryTimesFRecomOneUser);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"lottery_times_f_recom_one_user", JSON.toJSONString(lotteryTimesFRecomOneUser));
		
	
	}
	
	public void repaySysParam(SysParamDTO sysParamDTO) {
		SysParam transStartTime = new SysParam();
		// 交易允许执行开始时间
		transStartTime.setName("trans_start_time");
		transStartTime.setValue(sysParamDTO.getTransStartTime());
		transStartTime.setMemo("交易允许执行开始时间");
		sysParamMapper.saveSysParam(transStartTime);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"trans_start_time", JSON.toJSONString(transStartTime));
		// 交易允许执行结束时间
		SysParam transEndTime = new SysParam();
		transEndTime.setName("trans_end_time");
		transEndTime.setValue(sysParamDTO.getTransEndTime());
		transEndTime.setMemo("交易允许执行结束时间");
		sysParamMapper.saveSysParam(transEndTime);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"trans_end_time", JSON.toJSONString(transEndTime));
		// 单笔消费最大金额 单位：分
		SysParam transPayMaxAmt = new SysParam();
		transPayMaxAmt.setName("trans_pay_max_amt");
		transPayMaxAmt.setValue(sysParamDTO.getTransPayMaxAmt());
		transPayMaxAmt.setMemo("单笔消费最大金额 单位：元");
		sysParamMapper.saveSysParam(transPayMaxAmt);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"trans_pay_max_amt", JSON.toJSONString(transPayMaxAmt));
		// 单卡单日最多消费笔数
		SysParam transCardPayMaxCount = new SysParam();
		transCardPayMaxCount.setName("trans_card_pay_max_count");
		transCardPayMaxCount.setValue(sysParamDTO.getTransCardPayMaxCount());
		transCardPayMaxCount.setMemo("单卡单日最多消费笔数");
		sysParamMapper.saveSysParam(transCardPayMaxCount);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"trans_card_pay_max_count", JSON.toJSONString(transCardPayMaxCount));
		// 单个还款计划金额小于等于指定金额 单位分
		SysParam planRePayLessAmt = new SysParam();
		planRePayLessAmt.setName("plan_repay_less_amt");
		planRePayLessAmt.setValue(sysParamDTO.getPlanRePayLessAmt());
		planRePayLessAmt.setMemo("单个还款计划金额小于等于指定金额 单位:元");
		sysParamMapper.saveSysParam(planRePayLessAmt);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"plan_repay_less_amt", JSON.toJSONString(planRePayLessAmt));
		// 单个还款计划金额小于等于指定金额时，信用卡至少留存类型 0:金额，1比例
		SysParam planRePayLessAmtType = new SysParam();
		planRePayLessAmtType.setName("plan_repay_less_amt_type");
		planRePayLessAmtType.setValue(sysParamDTO.getPlanRePayLessAmtType());
		planRePayLessAmtType.setMemo("单个还款计划金额小于等于指定金额时，信用卡至少留存类型  0:金额，1比例");
		sysParamMapper.saveSysParam(planRePayLessAmtType);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"plan_repay_less_amt_type", JSON.toJSONString(planRePayLessAmtType));
		// 单个还款计划金额小于等于指定金额时，最少留存金额，单位：分
		SysParam planCardLessBalance = new SysParam();
		planCardLessBalance.setName("plan_card_less_balance");
		planCardLessBalance.setValue(sysParamDTO.getPlanCardLessBalance());
		planCardLessBalance.setMemo("单个还款计划金额小于等于指定金额时，当留存类型为金额是，存储金额单位为元，单存储类型为比例是，存储比例单位为百分比");
		sysParamMapper.saveSysParam(planCardLessBalance);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"plan_card_less_balance", JSON.toJSONString(planCardLessBalance));
		// 单个还款计划金额大于指定金额，单位：分
		SysParam planRePayMoreAmt = new SysParam();
		planRePayMoreAmt.setName("plan_repay_more_amt");
		planRePayMoreAmt.setValue(sysParamDTO.getPlanRePayMoreAmt());
		planRePayMoreAmt.setMemo("单个还款计划金额大于指定金额，单位：元");
		sysParamMapper.saveSysParam(planRePayMoreAmt);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"plan_repay_more_amt", JSON.toJSONString(planRePayMoreAmt));
		// 单个还款计划金额大于指定金额时，信用卡至少留存类型 0:金额，1比例
		SysParam planRePayMoreAmtType = new SysParam();
		planRePayMoreAmtType.setName("plan_repay_more_amt_type");
		planRePayMoreAmtType.setValue(sysParamDTO.getPlanRePayMoreAmtType());
		planRePayMoreAmtType.setMemo("单个还款计划金额大于指定金额时，信用卡至少留存类型  0:金额，1比例");
		sysParamMapper.saveSysParam(planRePayMoreAmtType);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"plan_repay_more_amt_type", JSON.toJSONString(planRePayMoreAmtType));
		// 单个还款计划金额大于指定金额时，最少留存金额，单位：分
		SysParam planCardMoreBalance = new SysParam();
		planCardMoreBalance.setName("plan_card_more_balance");
		planCardMoreBalance.setValue(sysParamDTO.getPlanCardMoreBalance());
		planCardMoreBalance.setMemo("单个还款计划金额大于指定金额时，当留存类型为金额是，存储金额单位为元，单存储类型为比例是，存储比例单位为百分比");
		sysParamMapper.saveSysParam(planCardMoreBalance);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"plan_card_more_balance", JSON.toJSONString(planCardMoreBalance));
		
		// 关闭计划服务费,百分比
		SysParam closePlanFee = new SysParam();
		closePlanFee.setName("close_plan_fee");
		closePlanFee.setValue(sysParamDTO.getClosePlanFee());
		closePlanFee.setMemo("关闭计划服务费,百分比");
		sysParamMapper.saveSysParam(closePlanFee);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"close_plan_fee", JSON.toJSONString(closePlanFee));
		
		// 关闭计划次数
		SysParam closePlanCount = new SysParam();
		closePlanCount.setName("close_plan_count");
		closePlanCount.setValue(sysParamDTO.getClosePlanCount());
		closePlanCount.setMemo("关闭计划服务费,百分比");
		sysParamMapper.saveSysParam(closePlanCount);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"close_plan_count", JSON.toJSONString(closePlanCount));
		// 还款分润用户分润方案
		SysParam profitsType = new SysParam();
		profitsType.setName("profits_type");
		profitsType.setValue(sysParamDTO.getProfitsType());
		profitsType.setMemo("分润方案,1:推荐关系固定分润;2:比较等级差值分润");
		sysParamMapper.saveSysParam(profitsType);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"profits_type", JSON.toJSONString(profitsType));
		// 直接推荐人分润比率
		SysParam profitsDirectUserFee = new SysParam();
		profitsDirectUserFee.setName("profits_direct_user_fee");
		profitsDirectUserFee.setValue(sysParamDTO.getProfitsDirectUserFee());
		profitsDirectUserFee.setMemo("直接推荐人分润比率，存储单位为百分比");
		sysParamMapper.saveSysParam(profitsDirectUserFee);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"profits_direct_user_fee", JSON.toJSONString(profitsDirectUserFee));
		// 间接推荐人分润比率
		SysParam profitsInDirectUserFee = new SysParam();
		profitsInDirectUserFee.setName("profits_indirect_user_fee");
		profitsInDirectUserFee.setValue(sysParamDTO.getProfitsInDirectUserFee());
		profitsInDirectUserFee.setMemo("间接推荐人分润比率，存储单位为百分比");
		sysParamMapper.saveSysParam(profitsInDirectUserFee);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"profits_indirect_user_fee", JSON.toJSONString(profitsInDirectUserFee));
		// 上上上级推荐人分润比率
		SysParam profitsUpUserFee = new SysParam();
		profitsUpUserFee.setName("profits_up_user_fee");
		profitsUpUserFee.setValue(sysParamDTO.getProfitsUpUserFee());
		profitsUpUserFee.setMemo("上上上级推荐人分润比率，存储单位为百分比");
		sysParamMapper.saveSysParam(profitsUpUserFee);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"profits_up_user_fee", JSON.toJSONString(profitsUpUserFee));
		// 一级代理成本基数
		SysParam profitsLevelOneAgentBase = new SysParam();
		profitsLevelOneAgentBase.setName("profits_level_one_agent_base");
		profitsLevelOneAgentBase.setValue(sysParamDTO.getProfitsLevelOneAgentBase());
		profitsLevelOneAgentBase.setMemo("一级代理成本基数，存储单位为百分比");
		sysParamMapper.saveSysParam(profitsLevelOneAgentBase);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"profits_level_one_agent_base", JSON.toJSONString(profitsLevelOneAgentBase));
		// 二级代理成本基数
		SysParam profitsLevelTwoAgentBase = new SysParam();
		profitsLevelTwoAgentBase.setName("profits_level_two_agent_base");
		profitsLevelTwoAgentBase.setValue(sysParamDTO.getProfitsLevelTwoAgentBase());
		profitsLevelTwoAgentBase.setMemo("二级代理成本基数，存储单位为百分比");
		sysParamMapper.saveSysParam(profitsLevelTwoAgentBase);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"profits_level_two_agent_base", JSON.toJSONString(profitsLevelTwoAgentBase));
		// 三级代理成本基数
		SysParam profitsLevelThreeAgentBase = new SysParam();
		profitsLevelThreeAgentBase.setName("profits_level_three_agent_base");
		profitsLevelThreeAgentBase.setValue(sysParamDTO.getProfitsLevelThreeAgentBase());
		profitsLevelThreeAgentBase.setMemo("三级代理成本基数，存储单位为百分比");
		sysParamMapper.saveSysParam(profitsLevelThreeAgentBase);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"profits_level_three_agent_base", JSON.toJSONString(profitsLevelThreeAgentBase));
	
	}
	
	public void fastPaySysParam(SysParamDTO sysParamDTO) {

		// 快捷消费开始时间
		SysParam fastStartTime = new SysParam(); 
		fastStartTime.setName("fast_start_time");
		fastStartTime.setValue(sysParamDTO.getFastStartTime());
		fastStartTime.setMemo("快捷消费开始时间");
		sysParamMapper.saveSysParam(fastStartTime);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_start_time", JSON.toJSONString(fastStartTime));
		// 快捷消费结束时间
		SysParam fastEndTime = new SysParam(); 
		fastEndTime.setName("fast_end_time");
		fastEndTime.setValue(sysParamDTO.getFastEndTime());
		fastEndTime.setMemo("快捷消费结束时间");
		sysParamMapper.saveSysParam(fastEndTime);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_end_time", JSON.toJSONString(fastEndTime));
		// 快捷消费快捷消费单笔交易限额
//		SysParam fastMinAmt = new SysParam(); 
//		fastMinAmt.setName("fast_min_amt");
//		fastMinAmt.setValue(sysParamDTO.getFastMinAmt());
//		fastMinAmt.setMemo("快捷消费单笔最小金额");
//		sysParamMapper.saveSysParam(fastMinAmt);
//		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_min_amt", JSON.toJSONString(fastMinAmt));
//		
//		SysParam fastMaxAmt = new SysParam(); 
//		fastMaxAmt.setName("fast_max_amt");
//		fastMaxAmt.setValue(sysParamDTO.getFastMaxAmt());
//		fastMaxAmt.setMemo("快捷消费单笔最大金额");
//		sysParamMapper.saveSysParam(fastMaxAmt);
//		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_max_amt", JSON.toJSONString(fastMaxAmt));
		
		//同张信用卡单日最多交易
		SysParam fastCreditPayDayCount = new SysParam(); 
		fastCreditPayDayCount.setName("fast_credit_pay_day_count");
		fastCreditPayDayCount.setValue(sysParamDTO.getFastCreditPayDayCount());
		fastCreditPayDayCount.setMemo("快捷消费同张信用卡单日最多交易");
		sysParamMapper.saveSysParam(fastCreditPayDayCount);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_credit_pay_day_count", JSON.toJSONString(fastCreditPayDayCount));
		//交易费率
//		SysParam fastRateType = new SysParam(); 
//		fastRateType.setName("fast_rate_type");
//		fastRateType.setValue(sysParamDTO.getFastRateType());
//		fastRateType.setMemo("快捷消费交易费率类型  0:金额，1比例");
//		sysParamMapper.saveSysParam(fastRateType);
//		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_rate_type", JSON.toJSONString(fastRateType));
//		
//		SysParam fastRateVal = new SysParam(); 
//		fastRateVal.setName("fast_rate_val");
//		fastRateVal.setValue(sysParamDTO.getFastRateVal());
//		fastRateVal.setMemo("快捷消费费率值,比例为百分比,固定为金额元");
//		sysParamMapper.saveSysParam(fastRateVal);
//		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_rate_val", JSON.toJSONString(fastRateVal));
//		
//		SysParam fastAgentpayRate = new SysParam(); 
//		fastAgentpayRate.setName("fast_agentpay_rate");
//		fastAgentpayRate.setValue(sysParamDTO.getFastAgentpayRate());
//		fastAgentpayRate.setMemo("快捷消费代付手续费");
//		sysParamMapper.saveSysParam(fastAgentpayRate);
//		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_agentpay_rate", JSON.toJSONString(fastAgentpayRate));
//		
		SysParam fastSettlePayDayCount = new SysParam(); 
		fastSettlePayDayCount.setName("fast_settle_pay_day_count");
		fastSettlePayDayCount.setValue(sysParamDTO.getFastSettlePayDayCount());
		fastSettlePayDayCount.setMemo("同张结算卡单日最多结算");
		sysParamMapper.saveSysParam(fastSettlePayDayCount);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_settle_pay_day_count", JSON.toJSONString(fastSettlePayDayCount));
		
		// 快捷分润用户分润方案
		SysParam fastProfitsType = new SysParam();
		fastProfitsType.setName("fast_profits_type");
		fastProfitsType.setValue(sysParamDTO.getFastProfitsType());
		fastProfitsType.setMemo("快捷分润方案,1:推荐关系固定分润;2:比较等级差值分润");
		sysParamMapper.saveSysParam(fastProfitsType);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_profits_type", JSON.toJSONString(fastProfitsType));
		// 直接推荐人分润比率
		SysParam fastProfitsDirectUserFee = new SysParam();
		fastProfitsDirectUserFee.setName("fast_profits_direct_user_fee");
		fastProfitsDirectUserFee.setValue(sysParamDTO.getFastProfitsDirectUserFee());
		fastProfitsDirectUserFee.setMemo("快捷直接推荐人分润比率，存储单位为百分比");
		sysParamMapper.saveSysParam(fastProfitsDirectUserFee);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_profits_direct_user_fee", JSON.toJSONString(fastProfitsDirectUserFee));
		// 间接推荐人分润比率
		SysParam fastProfitsInDirectUserFee = new SysParam();
		fastProfitsInDirectUserFee.setName("fast_profits_indirect_user_fee");
		fastProfitsInDirectUserFee.setValue(sysParamDTO.getFastProfitsInDirectUserFee());
		fastProfitsInDirectUserFee.setMemo("快捷间接推荐人分润比率，存储单位为百分比");
		sysParamMapper.saveSysParam(fastProfitsInDirectUserFee);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_profits_indirect_user_fee", JSON.toJSONString(fastProfitsInDirectUserFee));
		// 上上上级推荐人分润比率
		SysParam fastProfitsUpUserFee = new SysParam();
		fastProfitsUpUserFee.setName("fast_profits_up_user_fee");
		fastProfitsUpUserFee.setValue(sysParamDTO.getFastProfitsUpUserFee());
		fastProfitsUpUserFee.setMemo("快捷上上上级推荐人分润比率，存储单位为百分比");
		sysParamMapper.saveSysParam(fastProfitsUpUserFee);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_profits_up_user_fee", JSON.toJSONString(fastProfitsUpUserFee));
		// 一级代理成本基数
		SysParam fastProfitsLevelOneAgentBase = new SysParam();
		fastProfitsLevelOneAgentBase.setName("fast_profits_level_one_agent_base");
		fastProfitsLevelOneAgentBase.setValue(sysParamDTO.getFastProfitsLevelOneAgentBase());
		fastProfitsLevelOneAgentBase.setMemo("快捷一级代理成本基数，存储单位为百分比");
		sysParamMapper.saveSysParam(fastProfitsLevelOneAgentBase);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_profits_level_one_agent_base", JSON.toJSONString(fastProfitsLevelOneAgentBase));
		// 二级代理成本基数
		SysParam fastProfitsLevelTwoAgentBase = new SysParam();
		fastProfitsLevelTwoAgentBase.setName("fast_profits_level_two_agent_base");
		fastProfitsLevelTwoAgentBase.setValue(sysParamDTO.getFastProfitsLevelTwoAgentBase());
		fastProfitsLevelTwoAgentBase.setMemo("快捷二级代理成本基数，存储单位为百分比");
		sysParamMapper.saveSysParam(fastProfitsLevelTwoAgentBase);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_profits_level_two_agent_base", JSON.toJSONString(fastProfitsLevelTwoAgentBase));
		// 三级代理成本基数
		SysParam fastProfitsLevelThreeAgentBase = new SysParam();
		fastProfitsLevelThreeAgentBase.setName("fast_profits_level_three_agent_base");
		fastProfitsLevelThreeAgentBase.setValue(sysParamDTO.getFastProfitsLevelThreeAgentBase());
		fastProfitsLevelThreeAgentBase.setMemo("快捷三级代理成本基数，存储单位为百分比");
		sysParamMapper.saveSysParam(fastProfitsLevelThreeAgentBase);
		RedisClient.set(RedisClient.CACHE_PREFIX_SYSPARAM+"fast_profits_level_three_agent_base", JSON.toJSONString(fastProfitsLevelThreeAgentBase));
	
	}

	@Override
	public SysParam getSysParam(String name) {
		return sysParamMapper.selectByPrimaryKey(name);
	}

}

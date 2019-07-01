package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.PayPlan;
import net.chrone.creditpay.model.PayPlanDCStatisticsDTO;
import net.chrone.creditpay.model.PayPlanTask;

public interface PayPlanService {

	Map<String,Object> getPayPlanByPageCount(PayPlan payPlan);

	List<PayPlan> getPayPlanByPage(PayPlan payPlan);

	PayPlan getPayPlanByPlanId(String planId);
	
	List<Map<String,Object>> listPayPlayStatistics(PayPlan payPlan);
	
	Map<String,Object> countPayPlayStatistics(PayPlan payPlan);

	Map<String, Object> countPayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO);

	List<PayPlanDCStatisticsDTO> pagePayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO);

	List<PayPlanTask> getPayPlanTaskList(String planId);
	
	/**
	 * 计算整个计划任务列中的手续费,内部会自动的区分 先扣,后扣小数,后扣整数
	 * @param list
	 * @return 返回一个长度为3的数组 array[0]=总手续费  array[1]己执行手续费 array[2]未执行手续费
	 */
	Integer[] calcTaskListFees(String planId);

	void continuePayPlan(String planId);
}

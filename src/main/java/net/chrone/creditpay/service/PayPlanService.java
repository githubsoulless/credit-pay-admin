package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.PayPlan;
import net.chrone.creditpay.model.PayPlanDCStatisticsDTO;

public interface PayPlanService {

	Map<String,Object> getPayPlanByPageCount(PayPlan payPlan);

	List<PayPlan> getPayPlanByPage(PayPlan payPlan);

	PayPlan getPayPlanByPlanId(String planId);
	
	List<Map<String,Object>> listPayPlayStatistics(PayPlan payPlan);
	
	Map<String,Object> countPayPlayStatistics(PayPlan payPlan);

	Map<String, Object> countPayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO);

	List<PayPlanDCStatisticsDTO> pagePayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO);

}

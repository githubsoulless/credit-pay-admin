package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.PayPlanTask;

public interface PayPlanTaskService {

	List<PayPlanTask> getPayPlanTaskListByPlanId(String planId);

	Map<String, Object> getPayPlanTaskByPageCount(PayPlanTask payPlanTask);

	List<PayPlanTask> getPayPlanTaskByPage(PayPlanTask payPlanTask);
	
	Map<String, Object> yesterdayTask(String dateTime);
	
	Map<String, Object> taskRealTimeStatistics(String dateTime);
	
	Map<String, Object> countTaskStatistics(PayPlanTask payPlanTask);
	
	List<Map<String, Object>> listTaskStatistics(PayPlanTask payPlanTask);
	
	PayPlanTask getPayPlanTaskById(String id);

}

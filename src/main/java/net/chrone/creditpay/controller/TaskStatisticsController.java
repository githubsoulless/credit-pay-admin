package net.chrone.creditpay.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.chrone.creditpay.model.PayPlanTask;
import net.chrone.creditpay.service.PayPlanTaskService;
import net.chrone.creditpay.util.AmountUtil;
import net.chrone.creditpay.util.DateUtils;

/**
 * 
 * Title: TaskStatisticsController 
 * Description: 任务统计 
 * @author huoliang
 * @data 2017年12月8日 上午10:06:46
 *
 */

@Controller
@RequestMapping("taskStatistics")
public class TaskStatisticsController {
	
	@Autowired
	private PayPlanTaskService payPlanTaskService;

	@RequestMapping("list")
	public String list(PayPlanTask payPlanTask, String dateType, String flag, Model model){
		if (StringUtils.isEmpty(payPlanTask.getStartDate())) {
			payPlanTask.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(payPlanTask.getEndDate())) {
			payPlanTask.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		//昨日任务总览
		Map<String, Object> yesterdayTask = payPlanTaskService.yesterdayTask(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -1), "yyyyMMdd"));
		//任务实时统计
//		Map<String, Object> taskRealTimeStatistics = payPlanTaskService.taskRealTimeStatistics(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		String realTime = DateUtils.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss");
		//自定义任务查询
		if(StringUtils.isEmpty(flag)){
			dateType = "1";
			flag = "1";
		}
		//按照下拉日期
		if("1".equals(flag)){
			if("1".equals(dateType)){//最近7天
				payPlanTask.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyyMMdd"));
			}else if("2".equals(dateType)) {
				payPlanTask.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -14), "yyyyMMdd"));
			}else if("3".equals(dateType)) {
				payPlanTask.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -29), "yyyyMMdd"));
			}
			payPlanTask.setEndDate(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		}
		Map<String, Object> countMap = payPlanTaskService.countTaskStatistics(payPlanTask);
		List<Map<String, Object>> list = payPlanTaskService.listTaskStatistics(payPlanTask);
		
		model.addAttribute("yesterdayTask", yesterdayTask);
//		model.addAttribute("taskRealTimeStatistics", taskRealTimeStatistics);
		model.addAttribute("realTime", realTime); 
		model.addAttribute("countMap", countMap);
		model.addAttribute("list", list);
		model.addAttribute("dateType", dateType);
		model.addAttribute("flag", flag);
		
		return "taskStatistics/list";
	}
	
	@RequestMapping("realTimeTask")
	public @ResponseBody Map<String, Object> realTimeTaskStatistics(){
		//任务实时统计
		Map<String, Object> resMap = new HashMap<>();
		Map<String, Object> taskRealTimeStatistics = payPlanTaskService.taskRealTimeStatistics("");//所有
		if(null == taskRealTimeStatistics){
			taskRealTimeStatistics = new HashMap<>();
		}
		BigDecimal sumPayTaskAmt = (BigDecimal)taskRealTimeStatistics.get("sumPayTaskAmt");
		if(null == sumPayTaskAmt){
			sumPayTaskAmt = new BigDecimal(0);
		}
		resMap.put("sumPayTaskAmt", AmountUtil.parseAmountLong2Str(sumPayTaskAmt.longValue()));
		BigDecimal sumRePayTaskAmt = (BigDecimal)taskRealTimeStatistics.get("sumRePayTaskAmt");
		if(null == sumRePayTaskAmt){
			sumRePayTaskAmt = new BigDecimal(0);
		}
		resMap.put("countPayTask", taskRealTimeStatistics.get("countPayTask"));
		resMap.put("countRePayTask", taskRealTimeStatistics.get("countRePayTask"));
		resMap.put("sumRePayTaskAmt", AmountUtil.parseAmountLong2Str(sumRePayTaskAmt.longValue()));
		String realTime = DateUtils.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss");
		resMap.put("realTime", realTime);
		//今天
		taskRealTimeStatistics = payPlanTaskService.taskRealTimeStatistics(DateUtils.formatDate(new Date(), "yyyyMMdd"));//所有
		if(null == taskRealTimeStatistics){
			taskRealTimeStatistics = new HashMap<>();
		}
		sumPayTaskAmt = (BigDecimal)taskRealTimeStatistics.get("sumPayTaskAmt");
		if(null == sumPayTaskAmt){
			sumPayTaskAmt = new BigDecimal(0);
		}
		resMap.put("countPayTaskD", taskRealTimeStatistics.get("countPayTask"));
		resMap.put("countRePayTaskD", taskRealTimeStatistics.get("countRePayTask"));
		resMap.put("sumPayTaskAmtD", AmountUtil.parseAmountLong2Str(sumPayTaskAmt.longValue()));
		sumRePayTaskAmt = (BigDecimal)taskRealTimeStatistics.get("sumRePayTaskAmt");
		if(null == sumRePayTaskAmt){
			sumRePayTaskAmt = new BigDecimal(0);
		}
		resMap.put("sumRePayTaskAmtD", AmountUtil.parseAmountLong2Str(sumRePayTaskAmt.longValue()));
		//明天
		taskRealTimeStatistics = payPlanTaskService.taskRealTimeStatistics(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), 1), "yyyyMMdd"));//所有
		if(null == taskRealTimeStatistics){
			taskRealTimeStatistics = new HashMap<>();
		}
		sumPayTaskAmt = (BigDecimal)taskRealTimeStatistics.get("sumPayTaskAmt");
		if(null == sumPayTaskAmt){
			sumPayTaskAmt = new BigDecimal(0);
		}
		resMap.put("countPayTaskT", taskRealTimeStatistics.get("countPayTask"));
		resMap.put("countRePayTaskT", taskRealTimeStatistics.get("countRePayTask"));
		resMap.put("sumPayTaskAmtT", AmountUtil.parseAmountLong2Str(sumPayTaskAmt.longValue()));
		sumRePayTaskAmt = (BigDecimal)taskRealTimeStatistics.get("sumRePayTaskAmt");
		if(null == sumRePayTaskAmt){
			sumRePayTaskAmt = new BigDecimal(0);
		}
		resMap.put("sumRePayTaskAmtT", AmountUtil.parseAmountLong2Str(sumRePayTaskAmt.longValue()));
		
		return resMap;
	}
	
}

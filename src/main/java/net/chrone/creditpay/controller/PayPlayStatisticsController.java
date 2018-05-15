package net.chrone.creditpay.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.PayPlan;
import net.chrone.creditpay.service.PayPlanService;
import net.chrone.creditpay.util.DateUtils;

/**
 * 
 * Title: PayPlayStatisticsController 
 * Description: 计划统计 
 * @author huoliang
 * @data 2017年12月7日 下午3:30:29
 *
 */

@Controller
@RequestMapping("payPlanStatistics")
public class PayPlayStatisticsController {
	
	@Autowired
	private PayPlanService payPlanService;
	
	@RequestMapping("list")
	public String list(PayPlan payPlan, String dateType, String flag, HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(flag)){
			dateType = "1";
			flag = "1";
		}
		//按照下拉日期
		if("1".equals(flag)){
			if("1".equals(dateType)){//最近7天
				payPlan.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
			}else if("2".equals(dateType)) {
				payPlan.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -14), "yyyy-MM-dd"));
			}else if("3".equals(dateType)) {
				payPlan.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -29), "yyyy-MM-dd"));
			}
			payPlan.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		List<Map<String, Object>> list = payPlanService.listPayPlayStatistics(payPlan);
		Map<String, Object> countMap = payPlanService.countPayPlayStatistics(payPlan);
		model.addAttribute("list", list);
		model.addAttribute("countMap", countMap);
		model.addAttribute("payPlan", payPlan);
		model.addAttribute("dateType", dateType);
		model.addAttribute("flag", flag);
		
		return "payPlanStatistics/list";
	}
	

}

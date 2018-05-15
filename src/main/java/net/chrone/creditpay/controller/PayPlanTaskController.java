package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.PayPlanTask;
import net.chrone.creditpay.service.PayPlanTaskService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: PayPlanTaskController 
 * Description: 计划明细查询 
 * @author huoliang
 * @data 2017年11月24日 上午9:44:39
 *
 */

@Controller
@RequestMapping("payPlanTask")
public class PayPlanTaskController {
	
	@Autowired
	private PayPlanTaskService payPlanTaskService;
	
	@RequestMapping("list")
	public String list(PayPlanTask payPlanTask,String start,Model model){
		int starIndex = StringUtils.isEmpty(start)?0:Integer.valueOf(start);
		payPlanTask.setStartRow(starIndex);
		Map<String,Object> countMap = payPlanTaskService.getPayPlanTaskByPageCount(payPlanTask);
		int rowTotal= Integer.valueOf(countMap.get("count")+"");
		List<PayPlanTask>  list =new ArrayList<PayPlanTask>();
		if(rowTotal>0){
			list = payPlanTaskService.getPayPlanTaskByPage(payPlanTask);
		}
		
		MyPage page=new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("start",starIndex);
		model.addAttribute("payPlanTask",payPlanTask);
		model.addAttribute("countMap",countMap);
		return "payPlanTask/list"; 
	}
}

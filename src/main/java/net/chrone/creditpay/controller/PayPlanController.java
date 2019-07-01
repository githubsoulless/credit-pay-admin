package net.chrone.creditpay.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.PayPlan;
import net.chrone.creditpay.model.PayPlanTask;
import net.chrone.creditpay.service.PayPlanService;
import net.chrone.creditpay.service.PayPlanTaskService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: PayPlanController 
 * Description: 还款计划查询 
 * @author huoliang
 * @data 2017年11月24日 上午9:44:16
 *
 */

@Controller
@RequestMapping("payPlan")
public class PayPlanController {
	@Autowired
	private PayPlanService payPlanService;
	@Autowired
	private PayPlanTaskService payPlanTaskService;
	
	@RequestMapping("list")
	public String list(PayPlan payPlan,String start,Model model){
		int starIndex = StringUtils.isEmpty(start)?0:Integer.valueOf(start);
		payPlan.setStartRow(starIndex);
		if (StringUtils.isEmpty(payPlan.getStartDate())) {
			payPlan.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(),-30), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(payPlan.getEndDate())) {
			payPlan.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String,Object> countMap = payPlanService.getPayPlanByPageCount(payPlan);
		int rowTotal= Integer.valueOf(countMap.get("count")+"");
		List<PayPlan>  list =new ArrayList<PayPlan>();
		if(rowTotal>0){
			list = payPlanService.getPayPlanByPage(payPlan);
		}
		
		MyPage page=new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("start",starIndex);
		model.addAttribute("payPlan",payPlan);
		model.addAttribute("countMap",countMap);
		return "payPlan/list"; 
	}
	
	@RequestMapping("detail")
	public String detail(PayPlan payPlan,Model model){
		payPlan = payPlanService.getPayPlanByPlanId(payPlan.getPlanId());
		List<PayPlanTask> payPlanTaskList = payPlanTaskService.getPayPlanTaskListByPlanId(payPlan.getPlanId());
		Integer[] feesArray = payPlanService.calcTaskListFees(payPlan.getPlanId());
		
		if(payPlanTaskList != null && payPlanTaskList.size()>0) {
			for(PayPlanTask task: payPlanTaskList) {
				if(task.getType() == 0) {//消费
					if(task.getPlanType() ==0) {
						task.setActualAmt(task.getAmount());
						task.setIncludeAgentFee(true);
					}else if(task.getPlanType() ==1) {
						task.setActualAmt(task.getAmount() - task.getHkFee());
						if((task.getTarnsGroup()+3)%3==0) {//第一笔包含代付费
							task.setIncludeAgentFee(true);
						}
					}else if(task.getPlanType() ==2) {
						if(task.getTarnsGroup() ==0) {//后扣整数手续费会在第一笔中存放
							task.setActualAmt(task.getAmount() - task.getHkFee());
							task.setIncludeAgentFee(true);
						}else {
							task.setActualAmt(task.getAmount());
						}
					}
				}else {
					task.setActualAmt(task.getAmount());
				}
			}
		}
		
		model.addAttribute("payPlan", payPlan);
		model.addAttribute("payPlanTaskList", payPlanTaskList);
		model.addAttribute("totalFee", feesArray[0]);
		model.addAttribute("execTotalFee", feesArray[1]);
		model.addAttribute("reduceFee", feesArray[2]);
		
		return "payPlan/detail"; 
	}
	
	/**
	 * 继续执行计划
	 */
	@RequestMapping("continuePayPlan")
	public void checkPwd(String planIdStr,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String message="";
		try {
			if(StringUtils.isEmpty(planIdStr)){
				throw new CHException("参数为空");
			}
			String[] planIds = planIdStr.split("\\,");
			
			if(planIds.length==0) {
				throw new CHException("参数为空");
			}
			for(int i=0;i<planIds.length;i++) {
				try {
					payPlanService.continuePayPlan(planIds[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			message="true";
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error(e.getErrInfo());
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}

}

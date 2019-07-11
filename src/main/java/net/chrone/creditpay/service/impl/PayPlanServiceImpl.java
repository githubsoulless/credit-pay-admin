package net.chrone.creditpay.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CreditRootBankMapper;
import net.chrone.creditpay.mapper.PayPlanMapper;
import net.chrone.creditpay.mapper.PayPlanTaskMapper;
import net.chrone.creditpay.model.CreditRootBank;
import net.chrone.creditpay.model.PayPlan;
import net.chrone.creditpay.model.PayPlanDCStatisticsDTO;
import net.chrone.creditpay.model.PayPlanExample;
import net.chrone.creditpay.model.PayPlanTask;
import net.chrone.creditpay.model.PayPlanTaskExample;
import net.chrone.creditpay.model.RootBank;
import net.chrone.creditpay.service.PayPlanService;
import net.chrone.creditpay.service.RootBankService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.Fen2YuanUtil;

@Service
public class PayPlanServiceImpl implements PayPlanService {
	
	@Autowired
	private PayPlanMapper payPlanMapper;
	@Autowired
	private CreditRootBankMapper creditRootBankMapper;
	@Autowired
	private PayPlanTaskMapper payPlanTaskMapper;

	@Override
	public Map<String,Object> getPayPlanByPageCount(PayPlan payPlan) {
		return payPlanMapper.getPayPlanByPageCount(payPlan);
	}

	@Override
	public List<PayPlan> getPayPlanByPage(PayPlan payPlan) {
		List<PayPlan> list = payPlanMapper.getPayPlanByPage(payPlan);
		for(PayPlan p:list){
			 CreditRootBank rootBank = creditRootBankMapper.selectByPrimaryKey(p.getBankNo());
			if(rootBank!=null){
				p.setBankName(rootBank.getBankNm());
			}
		}
		return list;
	}

	@Override
	public PayPlan getPayPlanByPlanId(String planId) {
		return payPlanMapper.selectByPrimaryKey(planId);
	}

	@Override
	public List<Map<String, Object>> listPayPlayStatistics(PayPlan payPlan) {
		List<Map<String, Object>> list = payPlanMapper.listPayPlayStatistics(payPlan);
		List<Map<String, Object>> listPayPlayStatistics = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.setTime(payPlan.getEndTime());
		while (DateUtils.compareDate(payPlan.getStartTime(), c.getTime()) < 1) {
			boolean isTrue = false;
			Map<String, Object> m = new HashMap<String, Object>();
			for (Map<String,Object> map : list) {
				if(DateUtils.formatDate(c.getTime(), "yyyy-MM-dd").equals(map.get("days"))){
					m.put("days", DateUtils.formatDate(c.getTime(), "yyyy-MM-dd"));
					if("0".equals(map.get("STATUS")+"")) {
						m.put("newCount", map.get("count"));
					}else if("1".equals(map.get("STATUS")+"")) {
						m.put("failCount", map.get("count"));
					}else if("2".equals(map.get("STATUS")+"")) {
						m.put("finishCount", map.get("count"));
					}else if("3".equals(map.get("STATUS")+"")) {
						m.put("stopCount", map.get("count"));
					}
					isTrue = true;
				}
			}
			if(isTrue == false){
				m.put("days", DateUtils.formatDate(c.getTime(), "yyyy-MM-dd"));
				m.put("newCount", 0);
				m.put("failCount", 0);
				m.put("finishCount", 0);
				m.put("stopCount", 0);
			}
			listPayPlayStatistics.add(m);
			c.add(Calendar.DAY_OF_WEEK, -1);
		}
		
		return listPayPlayStatistics;
	}

	@Override
	public Map<String, Object> countPayPlayStatistics(PayPlan payPlan) {
		return payPlanMapper.countPayPlayStatistics(payPlan);
	}

	@Override
	public Map<String, Object> countPayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO) {
		return payPlanMapper.countPayPlayDCStatistics(dcStatisticsDTO);
	}

	@Override
	public List<PayPlanDCStatisticsDTO> pagePayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO) {
		return payPlanMapper.pagePayPlayDCStatistics(dcStatisticsDTO);
	}
	
	@Override
	public List<PayPlanTask> getPayPlanTaskList(String planId) {
	
		PayPlanTaskExample payPlanTaskExample = new PayPlanTaskExample();
		PayPlanTaskExample.Criteria criteria = payPlanTaskExample.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		payPlanTaskExample.setOrderByClause("tarns_group asc");
		return payPlanTaskMapper.selectByExample(payPlanTaskExample);
	}
	
	@Override
	public Integer[] calcTaskListFees(String planId) {
		
		List<PayPlanTask> list = getPayPlanTaskList(planId);
		Integer[] feeArray = new Integer[3];
		int execTotalFee = 0;
		int totalFee = 0;
		int hkIntegerFee = 0;
		if(list != null && list.size()>0) {
			for(PayPlanTask task : list) {
				if(task.getPlanType() ==2 && task.getTarnsGroup() == 0) {
					hkIntegerFee = task.getHkFee();
				}
				//己执行手续费
				if(task.getStatus() == 2) {//任务己执行
					if(task.getPlanType() ==0 || task.getPlanType() ==2) {//前扣每笔都要计算手续费
						if(task.getType() == 0) {//消费
							execTotalFee += Fen2YuanUtil.caclFee(task.getAmount(), task.getPayFee()==null?0:task.getPayFee());
						}else {//还款
							execTotalFee += task.getDfFee();
						}
					}else if(task.getPlanType() ==1) {//后扣小数,手续费己经计算
						if(task.getType() == 0) {
							execTotalFee += task.getHkFee();
						}else {
							execTotalFee += task.getDfFee();
						}
					}
//					else if(task.getPlanType() ==2) {//后扣整数,手续费全部的收取到了第一笔中,若计划开始执行则剩余手续费余额会在计划的后扣余额中
//						PayPlan payPlan = getPayPlanByPlanId(planId);
//						execTotalFee = hkIntegerFee - payPlan.getHkFeeBalance();
//					}
				}
				
				//总手续费
				if(task.getPlanType() ==0) {//前扣
					if(task.getType() == 0) {
						totalFee += Fen2YuanUtil.caclFee(task.getAmount(), task.getPayFee());
					}else {
						totalFee += task.getDfFee();
					}
				}else if(task.getPlanType() == 1) {//后扣小数
					if(task.getType() == 0) {
						totalFee += task.getHkFee();
					}else {
						totalFee += task.getDfFee();
					}
				}else if(task.getPlanType() ==2) {//后扣整数
					totalFee = hkIntegerFee;
				}
			}
		}
		PayPlan plan = getPayPlanByPlanId(planId);
		if(execTotalFee>0&&(plan.getStatus()==1||plan.getStatus()==3)) {//执行过计划,并且不成功,要多计算一笔代付费
			execTotalFee=execTotalFee+100;
		}
		feeArray[0] = totalFee;
		feeArray[1] = execTotalFee;
		feeArray[2] = (totalFee - execTotalFee);
		return feeArray;
	}

	/**
	 * 继续执行计划
	 * @param planId
	 */
	@Override
	public void continuePayPlan(String planId) {
		//继续计划
		PayPlan payPlan = new PayPlan();
		payPlan.setPlanId(planId);
		payPlan.setStatus(0);//进行中
		payPlan.setRecUpdTs(new Date());
		PayPlanExample example = new PayPlanExample();
		example.createCriteria()
		.andPlanIdEqualTo(planId)
		.andStatusEqualTo(4);
		int pres = payPlanMapper.updateByExampleSelective(payPlan, example);
		if(pres!=1){
			throw new CHException("216","计划不为待处理,不能继续执行");
		}
		//继续任务
		PayPlanTask payPlanTask = new PayPlanTask();
		payPlanTask.setPlanId(planId);
		payPlanTask.setStatus(0);//进行中
		payPlanTask.setTransBatch("");
		payPlanTask.setRemark("");
		payPlanTask.setRecUpdTs(new Date());
		PayPlanTaskExample payPlanTaskExample = new PayPlanTaskExample();
		payPlanTaskExample.createCriteria()
		.andPlanIdEqualTo(planId)
		.andStatusEqualTo(1);//失败
		int tres = payPlanTaskMapper.updateByExampleSelective(payPlanTask, payPlanTaskExample);
		if(tres<1){
			throw new CHException("500","系统异常");
		}
	}
	
}

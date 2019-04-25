package net.chrone.creditpay.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CreditRootBankMapper;
import net.chrone.creditpay.mapper.PayPlanTaskMapper;
import net.chrone.creditpay.model.CreditRootBank;
import net.chrone.creditpay.model.PayPlanTask;
import net.chrone.creditpay.model.PayPlanTaskExample;
import net.chrone.creditpay.model.RootBank;
import net.chrone.creditpay.service.PayPlanTaskService;
import net.chrone.creditpay.service.RootBankService;
import net.chrone.creditpay.util.DateUtils;
@Service
public class PayPlanTaskServiceImpl implements PayPlanTaskService {
	@Autowired
	private PayPlanTaskMapper payPlanTaskMapper;
	@Autowired
	private CreditRootBankMapper creditRootBankMapper;

	@Override
	public List<PayPlanTask> getPayPlanTaskListByPlanId(String planId) {
		PayPlanTaskExample example = new PayPlanTaskExample();
		example.setOrderByClause("plan_dt asc,tarns_group asc");
		example.createCriteria().andPlanIdEqualTo(planId);
		return payPlanTaskMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> getPayPlanTaskByPageCount(PayPlanTask payPlanTask) {
		return payPlanTaskMapper.getPayPlanTaskByPageCount(payPlanTask);
	}

	@Override
	public List<PayPlanTask> getPayPlanTaskByPage(PayPlanTask payPlanTask) {
		List<PayPlanTask> list = payPlanTaskMapper.getPayPlanTaskByPage(payPlanTask);
		for(PayPlanTask p:list){
			CreditRootBank rootBank = creditRootBankMapper.selectByPrimaryKey(p.getBankNo());
			if(rootBank!=null){
				p.setBankName(rootBank.getBankNm());
			}
		}
		return list;
	}

	/**
	 * 昨日任务总览
	 */
	@Override
	public Map<String, Object> yesterdayTask(String dateTime) {
		return payPlanTaskMapper.yesterdayTask(dateTime);
	}

	/**
	 * 任务实时统计
	 */
	@Override
	public Map<String, Object> taskRealTimeStatistics(String dateTime) {
		return payPlanTaskMapper.taskRealTimeStatistics(dateTime);
	}

	@Override
	public Map<String, Object> countTaskStatistics(PayPlanTask payPlanTask) {
		Map<String, Object> map = payPlanTaskMapper.countTaskStatistics(payPlanTask);
		if(null == map){
			map = new HashMap<>();
		}
		BigDecimal sumPaySuccessAmt = (BigDecimal) map.get("sumPaySuccessAmt");
		BigDecimal sumPayFaildAmt = (BigDecimal) map.get("sumPayFaildAmt");
		BigDecimal sumPayStopAmt = (BigDecimal) map.get("sumPayStopAmt");
		BigDecimal sumRePaySuccessAmt = (BigDecimal) map.get("sumRePaySuccessAmt");
		BigDecimal sumRePayFaildAmt = (BigDecimal) map.get("sumRePayFaildAmt");
		BigDecimal sumRePayStopAmt = (BigDecimal) map.get("sumRePayStopAmt");
		if(sumPaySuccessAmt == null){
			map.put("sumPaySuccessAmt", 0);
		}
		if(sumPayFaildAmt == null){
			map.put("sumPayFaildAmt", 0);
		}
		if(sumPayStopAmt == null){
			map.put("sumPayStopAmt", 0);
		}
		if(sumRePaySuccessAmt == null){
			map.put("sumRePaySuccessAmt", 0);
		}
		if(sumRePayFaildAmt == null){
			map.put("sumRePayFaildAmt", 0);
		}
		if(sumRePayStopAmt == null){
			map.put("sumRePayStopAmt", 0);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> listTaskStatistics(PayPlanTask payPlanTask) {
		List<Map<String, Object>> list = payPlanTaskMapper.listTaskStatistics(payPlanTask);
		List<Map<String, Object>> listTaskStatistics = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.setTime(payPlanTask.getEndTime());
		while (DateUtils.compareDate(payPlanTask.getStartTime(), c.getTime()) < 1) {
			boolean isTrue = false;
			for (Map<String,Object> map : list) {
				if(DateUtils.formatDate(c.getTime(), "yyyyMMdd").equals(map.get("plan_dt"))){
					isTrue = true;
					BigDecimal sumPaySuccessAmt = (BigDecimal) map.get("sumPaySuccessAmt");
					BigDecimal sumPayFaildAmt = (BigDecimal) map.get("sumPayFaildAmt");
					BigDecimal sumPayStopAmt = (BigDecimal) map.get("sumPayStopAmt");
					BigDecimal sumRePaySuccessAmt = (BigDecimal) map.get("sumRePaySuccessAmt");
					BigDecimal sumRePayFaildAmt = (BigDecimal) map.get("sumRePayFaildAmt");
					BigDecimal sumRePayStopAmt = (BigDecimal) map.get("sumRePayStopAmt");
					if(sumPaySuccessAmt == null){
						map.put("sumPaySuccessAmt", 0);
					}
					if(sumPayFaildAmt == null){
						map.put("sumPayFaildAmt", 0);
					}
					if(sumPayStopAmt == null){
						map.put("sumPayStopAmt", 0);
					}
					if(sumRePaySuccessAmt == null){
						map.put("sumRePaySuccessAmt", 0);
					}
					if(sumRePayFaildAmt == null){
						map.put("sumRePayFaildAmt", 0);
					}
					if(sumRePayStopAmt == null){
						map.put("sumRePayStopAmt", 0);
					}
					listTaskStatistics.add(map);
					break;
				}
			}
			if(isTrue == false){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("plan_dt", DateUtils.formatDate(c.getTime(), "yyyyMMdd"));
				m.put("countPaySuccess", 0);
				m.put("sumPaySuccessAmt", 0);
				m.put("countPayFaild", 0);
				m.put("sumPayFaildAmt", 0);
				m.put("countPayStop", 0);
				m.put("sumPayStopAmt", 0);
				m.put("countRePaySuccess", 0);
				m.put("sumRePaySuccessAmt", 0);
				m.put("countRePayFaild", 0);
				m.put("sumRePayFaildAmt", 0);
				m.put("countRePayStop", 0);
				m.put("sumRePayStopAmt", 0);
				listTaskStatistics.add(m);
			}
			
			c.add(Calendar.DAY_OF_WEEK, -1);
		}
		
		return listTaskStatistics;
	}
	@Override
	public PayPlanTask getPayPlanTaskById(String id) {
		return	payPlanTaskMapper.selectByPrimaryKey(id);
	}
}

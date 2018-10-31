package net.chrone.creditpay.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CreditRootBankMapper;
import net.chrone.creditpay.mapper.PayPlanMapper;
import net.chrone.creditpay.model.CreditRootBank;
import net.chrone.creditpay.model.PayPlan;
import net.chrone.creditpay.model.PayPlanDCStatisticsDTO;
import net.chrone.creditpay.model.RootBank;
import net.chrone.creditpay.service.PayPlanService;
import net.chrone.creditpay.service.RootBankService;
import net.chrone.creditpay.util.DateUtils;

@Service
public class PayPlanServiceImpl implements PayPlanService {
	
	@Autowired
	private PayPlanMapper payPlanMapper;
	@Autowired
	private CreditRootBankMapper creditRootBankMapper;

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

}

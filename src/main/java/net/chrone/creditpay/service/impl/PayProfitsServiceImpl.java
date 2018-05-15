package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.PayProfitsMapper;
import net.chrone.creditpay.model.PayProfits;
import net.chrone.creditpay.service.PayProfitsService;

/**
 * 
 * Title: PayProfitsServiceImpl 
 * Description: 还款分润明细 
 * @author huoliang
 * @data 2017年11月21日 下午2:37:55
 *
 */
@Service
public class PayProfitsServiceImpl implements PayProfitsService {
	
	@Autowired
	private PayProfitsMapper payProfitsMapper;

	@Override
	public Map<String,Object> countPayProfits(PayProfits payProfits) {
		return payProfitsMapper.countPayProfits(payProfits);
	}

	@Override
	public List<PayProfits> listPayProfitsPage(PayProfits payProfits) {
		return payProfitsMapper.listPayProfitsPage(payProfits);
	}

	@Override
	public PayProfits getPayProfitsByProfitsId(String profitsId) {
		return payProfitsMapper.selectByPrimaryKey(profitsId);
	}

}

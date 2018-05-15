package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.PayProfitsDetailMapper;
import net.chrone.creditpay.model.PayProfitsDetail;
import net.chrone.creditpay.service.PayProfitsDetailService;

/**
 * 
 * Title: PayProfitsDetailServiceImpl 
 * Description: 分润明细 
 * @author huoliang
 * @data 2017年11月21日 下午2:37:55
 *
 */
@Service
public class PayProfitsDetailServiceImpl implements PayProfitsDetailService {
	
	@Autowired
	private PayProfitsDetailMapper payProfitsDetailMapper;

	@Override
	public List<PayProfitsDetail> listPayProfitsDetail(String profitsId) {
		return payProfitsDetailMapper.listPayProfitsDetail(profitsId);
	}


}

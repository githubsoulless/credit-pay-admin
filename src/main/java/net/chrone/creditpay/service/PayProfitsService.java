package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.PayProfits;

/**
 * 
 * Title: PayProfitsService 
 * Description: 分润明细
 * @author huoliang
 * @data 2017年11月21日 下午2:19:31
 *
 */
public interface PayProfitsService {
	
	Map<String,Object> countPayProfits(PayProfits payProfits);

	List<PayProfits> listPayProfitsPage(PayProfits payProfits);
	
	PayProfits getPayProfitsByProfitsId(String profitsId);
	
}

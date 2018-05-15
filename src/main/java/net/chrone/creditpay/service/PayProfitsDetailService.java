package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.PayProfitsDetail;

/**
 * 
 * Title: PayProfitsDetailService 
 * Description: 分润明细
 * @author huoliang
 * @data 2017年11月21日 下午2:19:31
 *
 */
public interface PayProfitsDetailService {
	

	List<PayProfitsDetail> listPayProfitsDetail(String profitsId);
	
}

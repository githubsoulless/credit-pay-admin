package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.FastProfits;

/**
 * 
 * Title: PayProfitsService 
 * Description: 分润明细
 * @author huoliang
 * @data 2017年11月21日 下午2:19:31
 *
 */
public interface FastProfitsService {
	
	Map<String,Object> countFastProfits(FastProfits fastProfits);

	List<FastProfits> listFastProfitsPage(FastProfits fastProfits);
	
	FastProfits getFastProfitsByProfitsId(String profitsId);
	
}

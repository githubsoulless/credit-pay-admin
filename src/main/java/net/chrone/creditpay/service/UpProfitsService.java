package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.UpProfits;

/**
 * 
 * Title: UpProfitsService 
 * Description: 分润明细
 * @author huoliang
 * @data 2017年11月21日 下午2:19:31
 *
 */
public interface UpProfitsService {
	
	Map<String,Object> countUpProfits(UpProfits upProfits);

	List<UpProfits> listUpProfitsPage(UpProfits upProfits);
	
	UpProfits getUpProfitsByProfitsId(String profitsId);
	
}

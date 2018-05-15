package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.TxRecord;

/**
 * 提现记录
 * Title: TxRecordService 
 * Description: 
 * @author huoliang
 * @data 2017年11月21日 下午4:00:12
 *
 */
public interface TxRecordService {
	
	Map<String,Object> countTxRecord(TxRecord txRecord);

	List<TxRecord> listTxRecordPage(TxRecord txRecord);
	
}

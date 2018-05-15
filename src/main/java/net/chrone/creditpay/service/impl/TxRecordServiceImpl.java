package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.TxRecordMapper;
import net.chrone.creditpay.model.TxRecord;
import net.chrone.creditpay.service.TxRecordService;

/**
 * 
 * Title: TxRecordServiceImpl 
 * Description: 提现记录 
 * @author huoliang
 * @data 2017年11月21日 下午2:37:55
 *
 */
@Service
public class TxRecordServiceImpl implements TxRecordService {
	
	@Autowired
	private TxRecordMapper txRecordMapper;

	@Override
	public Map<String,Object> countTxRecord(TxRecord txRecord) {
		return txRecordMapper.countTxRecord(txRecord);
	}

	@Override
	public List<TxRecord> listTxRecordPage(TxRecord txRecord) {
		return txRecordMapper.listTxRecordPage(txRecord);
	}

}

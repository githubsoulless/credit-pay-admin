package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.OperationLogMapper;
import net.chrone.creditpay.model.OperationLog;
import net.chrone.creditpay.service.OperationLogService;


/**
 * 
 * Title: OperationLogServiceImpl 
 * Description: 日志管理 
 * @author huoliang
 * @data 2017年11月23日 上午10:20:22
 *
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private OperationLogMapper operationLogMapper;
	
	@Override
	public int saveOperationLog(OperationLog operationLog) {
		return operationLogMapper.insertSelective(operationLog);
	}

	@Override
	public List<OperationLog> listOperationLog(OperationLog operationLog) {
		return operationLogMapper.listOperationLog(operationLog);
	}

	@Override
	public int countOperationLog(OperationLog operationLog) {
		return operationLogMapper.countOperationLog(operationLog);
	}

	@Override
	public OperationLog getOperationLog(String id) {
		return operationLogMapper.selectByPrimaryKey(id);
	}

}

package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.OperationLog;

/**
 * 
 * Title: OperationLogService 
 * Description: 日志管理 
 * @author huoliang
 * @data 2017年11月23日 上午10:18:31
 *
 */
public interface OperationLogService {
	
	int saveOperationLog(OperationLog operationLog);
	
	List<OperationLog> listOperationLog(OperationLog operationLog);
	
	int countOperationLog(OperationLog operationLog);
	
	OperationLog getOperationLog(String id);

}

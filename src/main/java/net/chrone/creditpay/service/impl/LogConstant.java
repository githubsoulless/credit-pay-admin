package net.chrone.creditpay.service.impl;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chrone.creditpay.model.OperationLog;
import net.chrone.creditpay.service.OperationLogService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.util.IdGen;


/**
 * 
 * Title: LogConstant 
 * Description: 日志工具类 
 * @author huoliang
 * @data 2017年11月23日 上午10:20:58
 *
 */
@Component
public class LogConstant {
	
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private SeqService seqService;
	
	private final static Logger logger = Logger.getLogger(LogConstant.class);
    public static HashMap<Integer, String> logTypeMap = new HashMap<Integer, String>();

    static {
        logTypeMap.put(1, "用户等级设置");
        logTypeMap.put(2, "平台参数设置");
        logTypeMap.put(3, "用户信息修改");
        logTypeMap.put(4, "用户等级调整");
        logTypeMap.put(5, "用户代理调整");
        logTypeMap.put(6, "通道管理");
        logTypeMap.put(7, "优惠券管理");
        logTypeMap.put(8, "版本管理");
        logTypeMap.put(9, "APP消息推送");
        logTypeMap.put(10, "短信管理");
        logTypeMap.put(11, "代理迁移");
    }
    
    public static String getlogType(String errorCode) {
        String logType = logTypeMap.get(errorCode);
        return logType;
    }
    
    public void createTweblog(String loguserId, String memo, int type, HttpServletRequest req) {
        try {
            String id = new IdGen().nextId();
            
            OperationLog operationLog = new OperationLog();
            operationLog.setId(id);
            operationLog.setType(type);
            operationLog.setOperationUser(loguserId);
            operationLog.setContent(memo);
            operationLog.setIp(getIpAddr(req));
            operationLog.setRowCrtTs(new Date());
            operationLog.setRowCrtUsr(loguserId);
            operationLog.setRowUpdTs(new Date());
            operationLog.setRowUpdUsr(loguserId);

            operationLogService.saveOperationLog(operationLog);
            
        } catch (Exception e) {
            logger.error("插入operationLog出现异常", e);
        }
    }

    public String getIpAddr(HttpServletRequest request) {
        if (null == request) {
            return "";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        return ip;
    }
    
}

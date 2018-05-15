package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.SmsMessage;

/**
 * 
 * Title: SmsMessageService 
 * Description: 短信管理 
 * @author huoliang
 * @data 2017年12月4日 下午4:52:39
 *
 */
public interface SmsMessageService {

	int countSmsMessage(SmsMessage smsMessage);
	
	List<SmsMessage> listSmsMessage(SmsMessage smsMessage);
	
	int saveMessage(SmsMessage smsMessage);
	
	SmsMessage getSmsMessage(String id);
}

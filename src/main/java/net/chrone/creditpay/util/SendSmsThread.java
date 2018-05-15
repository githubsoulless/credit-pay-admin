package net.chrone.creditpay.util;

import org.apache.log4j.Logger;

import net.chrone.creditpay.service.SmsService;

/**
 * 推送短信线程
 * 
 *
 */
public class SendSmsThread implements Runnable {
	private Logger logger = Logger.getLogger(SendSmsThread.class);
	private String mobiles;
	private String content;
	private SmsService smsService;

	public SendSmsThread(String mobiles, String content, SmsService smsService) {
		super();
		this.mobiles = mobiles;
		this.content = content;
		this.smsService = smsService;
	}

	@Override
	public void run() {
		logger.info("自定义短信发送开始！");
		try {
			smsService.sendSMS(mobiles, DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"), content);
		} catch (Exception e) {
			logger.info("自定义短信发送失败！", e);
		}
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}
}

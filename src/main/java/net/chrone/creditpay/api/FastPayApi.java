package net.chrone.creditpay.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.service.CardExtService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.ConfigReader;

public class FastPayApi {
private static final Logger logger = Logger.getLogger(FastPayApi.class);
	
	public final static String CHRONE="chrone";//乾恩
	public final static String HUAPAY="huapay";//融信优贝
	public final static String REAPALFAST="reapalfast";//融宝
	public final static String YITONG="yitong";//易通
	public final static String HUIFU="huifu";//汇富
	public final static String YSPAY="yspay";//银盛
	
	
	/**
	 * 代付接口(TX)
	 * @param order
	 * @param code 通道代码
	 * @return status 1:成功
	 */
	public static Map<String, String> fastPay_df(FastOrder order,CardExtService cardExtService,String code){
		Map<String, String> resultMap = new HashMap<String, String>();
		logger.info("找到通道:"+code);
		if(CHRONE.equals(code)){
			Map<String, String> resMap = ChroneApi.agentPay(order, ConfigReader.getConfig("chronePayOrgId"), 
					ConfigReader.getConfig("chronePayPriKey"));
			if(resMap!=null && "200".equals(resMap.get("respCode"))){
				resultMap.put("status", "1");//成功
			}
		}if(REAPALFAST.equals(code)){
			Map<String, String> resMap = ChroneApi.agentPay(order, ConfigReader.getConfig("chronePayOrgId"), 
					ConfigReader.getConfig("chronePayPriKey"));
			if(resMap!=null && "200".equals(resMap.get("respCode"))){
				resultMap.put("status", "1");//成功
			}
		}else if(YITONG.equals(code)) {
			throw new CHException("500","YT快捷不支持重新代付操作...");
			
		}else if(HUIFU.equals(code)) {

			Map<String, String> resMap = ChroneApi.agentPayByHuifu(order, cardExtService,ConfigReader.getConfig("chronePayOrgId"), 
					ConfigReader.getConfig("chronePayPriKey"));

			if(resMap!=null && "200".equals(resMap.get("respCode"))){
				resultMap.put("status", "1");//成功
			}
			
		}else if(YSPAY.equals(code)) {

			Map<String, String> resMap = ChroneApi.agentPayByHuifu(order, cardExtService,ConfigReader.getConfig("chronePayOrgId"), 
					ConfigReader.getConfig("chronePayPriKey"));
			if(resMap!=null && "200".equals(resMap.get("respCode"))){
				resultMap.put("status", "1");//成功
			}
		}
		
		else{
			logger.error("fastPay_df 未找到通道");
		}
		return resultMap;
	}

	
}

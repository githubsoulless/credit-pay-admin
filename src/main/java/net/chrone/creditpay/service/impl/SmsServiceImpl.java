package net.chrone.creditpay.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.service.SMSMQService;
import net.chrone.creditpay.service.SmsService;
import net.chrone.creditpay.util.ConfigReader;

@Service
public class SmsServiceImpl implements SmsService {
    private static final Logger logger = Logger.getLogger(SmsServiceImpl.class);
    
    @Autowired
    private SMSMQService smsmqService;
    /**
     * 短信发送公共方法
     * 
     * @param mobiles 发送手机号(多个逗号分隔)
     * @param randname 模板ID(可空)
     * @param randValMap 模块参数Map(可空)
     * @param dateTime   短信内容
     * @return 0成功，其余失败
     */
    @Override
    public int sendSMS(String mobiles,String dateTime, String content) {
        if (null == mobiles || "".equals(mobiles)) {
            logger.info("短信发送手机号码为空");
            return -1;
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("orgId", ConfigReader.getConfig("smsOrgId"));
        map.put("mobiles", mobiles);
        map.put("dateTime", dateTime);
        map.put("type", "1");//营销短信
        if (null != content && !"".equals(content)) {
            map.put("message", content);
        } 
        return smsmqService.produce(map);
    }
    
    
}


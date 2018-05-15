package net.chrone.creditpay.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.api.ChroneApi;
import net.chrone.creditpay.mapper.SmsWarningMapper;
import net.chrone.creditpay.model.SmsWarning;
import net.chrone.creditpay.model.SmsWarningDTO;
import net.chrone.creditpay.service.SmsWarningService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.ConfigReader;

@Service
public class SmsWarningServiceImpl implements SmsWarningService {
	@Autowired
	private SmsWarningMapper smsWarningMapper;

	@Override
	public List<SmsWarning> getSmsWarningList() {
		return smsWarningMapper.selectByExample(null);
	}

	@Override
	@Transactional
	public void update(SmsWarningDTO smsWarningDTO) {
		List<SmsWarning> list = smsWarningDTO.getSmsWarnings();
		for(SmsWarning s:list){
			try {
				if(StringUtils.isNotEmpty(s.getAmt1Str())){
					s.setAmt1(new BigDecimal(s.getAmt1Str()).multiply(new BigDecimal(100)).intValue());
				}else{
					s.setAmt1(0);
				}
				if(StringUtils.isNotEmpty(s.getAmt2Str())){
					s.setAmt2(new BigDecimal(s.getAmt2Str()).multiply(new BigDecimal(100)).intValue());
				}else{
					s.setAmt2(0);
				}
				if(StringUtils.isNotEmpty(s.getAmt3Str())){
					s.setAmt3(new BigDecimal(s.getAmt3Str()).multiply(new BigDecimal(100)).intValue());
				}else{
					s.setAmt3(0);
				}
				String orgId = "";
				String privateKey="";
				if(s.getWarnType()==0){//交易账户
					orgId = ConfigReader.getConfig("chroneFastPayOrgId");
					privateKey = ConfigReader.getConfig("chroneFastPayPriKey");
				}else{
					orgId = ConfigReader.getConfig("chroneAgentPayOrgId");
					privateKey = ConfigReader.getConfig("chroneAgentPayPriKey");
				}
				
				Map<String, String> resMap = ChroneApi.balanceWarning(s, orgId, privateKey);
				if(!"200".equals(resMap.get("respCode"))){
					throw new CHException(resMap.get("respMsg"));
				}
				smsWarningMapper.updateByPrimaryKeySelective(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

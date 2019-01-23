package net.chrone.creditpay.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.mapper.CreditRootBankMapper;
import net.chrone.creditpay.model.CreditRootBank;
import net.chrone.creditpay.service.CreditRootBankService;
import net.chrone.creditpay.util.RedisClient;
@Service
public class CreditRootBankServiceImpl implements CreditRootBankService {

	@Autowired
	private CreditRootBankMapper creditRootBankMapper;
	
	@Override
	public CreditRootBank getCreditRootBankByBankNo(String bankNo) {
		String key=RedisClient.CACHE_PREFIX_CREDITROOTBANK+bankNo;
		String json = RedisClient.getByKey(key);
		if(StringUtils.isNotEmpty(json)){
			return JSON.parseObject(json, CreditRootBank.class);
		}
		
		CreditRootBank rootBank = creditRootBankMapper.selectByPrimaryKey(bankNo);
		RedisClient.set(key, JSON.toJSONString(rootBank));
		return rootBank;
	}

	@Override
	public List<CreditRootBank> geCreditRootBankAll() {
		return creditRootBankMapper.selectByExample(null);
	}
	
	@Override
	public void updateCreditRootBank(CreditRootBank creditRootBank) {
		
		int up = creditRootBankMapper.updateByPrimaryKeySelective(creditRootBank);
		if(up > 0) {
			String key=RedisClient.CACHE_PREFIX_CREDITROOTBANK+creditRootBank.getBankNo();
			RedisClient.set(key, JSON.toJSONString(creditRootBank));
		}
	}
}

package net.chrone.creditpay.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.mapper.RootBankMapper;
import net.chrone.creditpay.model.RootBank;
import net.chrone.creditpay.service.RootBankService;
import net.chrone.creditpay.util.RedisClient;


@Service
public class RootBankServiceImpl implements RootBankService {

	@Autowired
	private RootBankMapper rootBankMapper;
	
	@Override
	public RootBank getRootBankByBankNo(String bankNo) {
		String key=RedisClient.CACHE_PREFIX_ROOTBANK+bankNo;
		String json = RedisClient.getByKey(key);
		if(StringUtils.isNotEmpty(json)){
			return JSON.parseObject(json, RootBank.class);
		}
		
		RootBank rootBank = rootBankMapper.selectByPrimaryKey(bankNo);
		RedisClient.set(key, JSON.toJSONString(rootBank));
		return rootBank;
	}

}

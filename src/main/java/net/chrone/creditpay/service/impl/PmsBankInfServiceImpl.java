package net.chrone.creditpay.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.mapper.PmsBankInfMapper;
import net.chrone.creditpay.model.PmsBankInf;
import net.chrone.creditpay.service.PmsBankInfService;
import net.chrone.creditpay.util.RedisClient;

@Service
public class PmsBankInfServiceImpl implements PmsBankInfService {
	
	@Autowired
	private PmsBankInfMapper pmsBankInfMapper;

	@Override
	public PmsBankInf find(String pmsBankNo) {
		String key=RedisClient.CACHE_PREFIX_PMSBANKINF+pmsBankNo;
		String json = RedisClient.getByKey(key);
		if(StringUtils.isNotEmpty(json)){
			return JSON.parseObject(json, PmsBankInf.class);
		}
		
		PmsBankInf pmsBankInf = pmsBankInfMapper.selectByPrimaryKey(pmsBankNo);
		RedisClient.set(key, JSON.toJSONString(pmsBankInf));
		return pmsBankInf;
	}

}

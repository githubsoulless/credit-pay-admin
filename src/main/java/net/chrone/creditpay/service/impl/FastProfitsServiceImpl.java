package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.FastProfitsMapper;
import net.chrone.creditpay.model.FastProfits;
import net.chrone.creditpay.service.FastProfitsService;

@Service
public class FastProfitsServiceImpl implements FastProfitsService {
	
	@Autowired
	private FastProfitsMapper fastProfitsMapper;

	@Override
	public Map<String, Object> countFastProfits(FastProfits fastProfits) {
		return fastProfitsMapper.countFastProfits(fastProfits);
	}

	@Override
	public List<FastProfits> listFastProfitsPage(FastProfits fastProfits) {
		return fastProfitsMapper.listFastProfitsPage(fastProfits);
	}

	@Override
	public FastProfits getFastProfitsByProfitsId(String profitsId) {
		return fastProfitsMapper.selectByPrimaryKey(profitsId);
	}


}

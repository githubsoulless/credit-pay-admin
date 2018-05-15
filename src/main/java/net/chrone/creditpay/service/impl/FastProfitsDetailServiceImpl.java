package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.FastProfitsDetailMapper;
import net.chrone.creditpay.model.FastProfitsDetail;
import net.chrone.creditpay.service.FastProfitsDetailService;


@Service
public class FastProfitsDetailServiceImpl implements FastProfitsDetailService {
	
	@Autowired
	private FastProfitsDetailMapper fastProfitsDetailMapper;

	@Override
	public List<FastProfitsDetail> listFastProfitsDetail(String profitsId) {
		return fastProfitsDetailMapper.listFastProfitsDetail(profitsId);
	}


}

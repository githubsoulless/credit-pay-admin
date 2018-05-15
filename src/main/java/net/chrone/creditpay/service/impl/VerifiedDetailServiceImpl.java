package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.VerifiedDetailMapper;
import net.chrone.creditpay.model.VerifiedDetail;
import net.chrone.creditpay.service.VerifiedDetailService;


@Service
public class VerifiedDetailServiceImpl implements VerifiedDetailService {
	
	@Autowired
	private VerifiedDetailMapper verifiedDetailMapper;

	@Override
	public int countVerifiedDetail(VerifiedDetail verifiedDetail) {
		return verifiedDetailMapper.countVerifiedDetail(verifiedDetail);
	}

	@Override
	public List<VerifiedDetail> listVerifiedDetail(VerifiedDetail verifiedDetail) {
		return verifiedDetailMapper.listVerifiedDetail(verifiedDetail);
	}

}

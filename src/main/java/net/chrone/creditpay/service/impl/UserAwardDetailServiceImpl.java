package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.UserAwardDetailMapper;
import net.chrone.creditpay.model.UserAwardDetail;
import net.chrone.creditpay.model.UserAwardDetailExample;
import net.chrone.creditpay.service.UserAwardDetailService;

@Service
public class UserAwardDetailServiceImpl implements UserAwardDetailService {

	@Autowired
	private UserAwardDetailMapper UserAwardDetailMapper;
	
	@Override
	public List<UserAwardDetail> getUserAwardDetailList() {
		
		UserAwardDetailExample example = new UserAwardDetailExample();
		UserAwardDetailExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("id desc");
		
		return UserAwardDetailMapper.selectByExample(example);
	}
	
	@Override
	public void insertUserAwardDetail(UserAwardDetail userAwardDetail) {
		
		UserAwardDetailMapper.insertSelective(userAwardDetail);
	}

}

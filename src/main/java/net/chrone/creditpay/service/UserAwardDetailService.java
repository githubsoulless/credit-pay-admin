package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.UserAwardDetail;

public interface UserAwardDetailService {
	
	public void insertUserAwardDetail(UserAwardDetail userAwardDetail);
	
	public List<UserAwardDetail> getUserAwardDetailList();
}

package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.AppUser;

public interface AppUserService {

	int getAppUserByPageCount(AppUser appuser);

	List<AppUser> getAppUserByPage(AppUser appuser);

	AppUser getAppUserByUserId(String userId);

	void update(AppUser appuser);
	
	int countSubAppUser(AppUser appUser);
	
	List<AppUser> listSubAppUser(AppUser appUser);
	
	List<AppUser> getUserLikeUserId(String userId);
	
	//等级分布
	Map<String, Object> levelDistribution();
	
	//日增统计
	List<Map<String, Object>> listIncreasing(AppUser appUser);
	
	List<Map<String, Object>> listIncreasingForMap(AppUser appUser);

	AppUser getAppUserByLoginId(String loginId);

}

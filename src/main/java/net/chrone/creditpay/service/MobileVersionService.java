package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.MobileVersion;

/**
 * 
 * Title: MobileVersion 
 * Description: 客户端版本管理 
 * @author huoliang
 * @data 2017年11月30日 上午10:11:53
 *
 */
public interface MobileVersionService {
	
	int countMobileVersion(MobileVersion mobileVersion);
	
	List<MobileVersion> listMobileVersion(MobileVersion mobileVersion);
	
	int saveMobileVersion(MobileVersion mobileVersion);
	
	int updateMobileVersion(MobileVersion mobileVersion);
	
	MobileVersion getMobileVersion(MobileVersion mobileVersion);

}

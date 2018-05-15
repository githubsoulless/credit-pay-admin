package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.MobileVersionMapper;
import net.chrone.creditpay.model.MobileVersion;
import net.chrone.creditpay.service.MobileVersionService;


/**
 * 
 * Title: MobileVersionServiceImpl 
 * Description: 手机客户端版本管理 
 * @author huoliang
 * @data 2017年11月30日 上午10:15:16
 *
 */

@Service
public class MobileVersionServiceImpl implements MobileVersionService {
	
	@Autowired
	private MobileVersionMapper mobileVersionMapper;

	@Override
	public int countMobileVersion(MobileVersion mobileVersion) {
		return mobileVersionMapper.countMobileVersion(mobileVersion);
	}

	@Override
	public List<MobileVersion> listMobileVersion(MobileVersion mobileVersion) {
		return mobileVersionMapper.listMobileVersion(mobileVersion);
	}

	@Override
	public int saveMobileVersion(MobileVersion mobileVersion) {
		return mobileVersionMapper.insertSelective(mobileVersion);
	}

	@Override
	public int updateMobileVersion(MobileVersion mobileVersion) {
		return mobileVersionMapper.updateByPrimaryKeySelective(mobileVersion);
	}

	@Override
	public MobileVersion getMobileVersion(MobileVersion mobileVersion) {
		return mobileVersionMapper.selectByPrimaryKey(mobileVersion.getAppVersion(), mobileVersion.getOsType());
	}

}

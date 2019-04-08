package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.mapper.RegionInfoMapper;
import net.chrone.creditpay.model.RegionInfo;
import net.chrone.creditpay.model.RegionInfoExample;
import net.chrone.creditpay.service.RegionInfoService;
import net.chrone.creditpay.util.RedisClient;

@Service
public class RegionInfoServiceImpl implements RegionInfoService {
	@Autowired
	private RegionInfoMapper regionInfoMapper;

	@Override
	public List<Map<String, String>> selectProvinceAll() {
		return regionInfoMapper.selectProvinceAll();
	}

	@Override
	public List<Map<String, String>> selectCityInfo(String provCd) {
		return regionInfoMapper.selectCityInfo(provCd);
	}
	@Override
	public List<Map<String, String>> selectCountyInfo(String regionCd) {
		return regionInfoMapper.selectCountyInfo(regionCd);
	}
	
	@Override
	public RegionInfo getRegionInfoByFyRegionCd(String fyRegionCd) {
		
		RegionInfoExample regionInfoExample = new RegionInfoExample();
		RegionInfoExample.Criteria criteria = regionInfoExample.createCriteria();
		criteria.andFyRegionCdEqualTo(fyRegionCd);
		
		List<RegionInfo> list = regionInfoMapper.selectByExample(regionInfoExample);
		if(!list.isEmpty())
			return list.get(0);
		
		return null;
	}
	
	@Override
	public RegionInfo getRegionInfoByFyCountyCd(String countyCd) {
		
		String key = RedisClient.CACHE_PREFIX_REGION_COUNTY_CD_+countyCd;
		String countyStr = RedisClient.getByKey(key);
		if(StringUtils.isNotEmpty(countyStr)) {
			return JSON.parseObject(countyStr, RegionInfo.class);
		}
		
		RegionInfoExample regionInfoExample = new RegionInfoExample();
		RegionInfoExample.Criteria criteria = regionInfoExample.createCriteria();
		criteria.andFyCountyCdEqualTo(countyCd);
		
		List<RegionInfo> list = regionInfoMapper.selectByExample(regionInfoExample);
		if(!list.isEmpty()) {
			RedisClient.set(key, JSON.toJSONString(list.get(0)));
			return list.get(0);
		}
		return null;
	}

}

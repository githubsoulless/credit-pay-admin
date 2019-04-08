package net.chrone.creditpay.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.chrone.creditpay.mapper.RegionInfoMapper;

public class RefreshCacheUtil {
	
	public static void refreshCacheAll(){
		
		LogWriter.info("加载卡区县数据开始...");
		loadRegion();
		LogWriter.info("加载卡区县数据结束...");
	}
	
	private static void loadRegion() {
		String regionStr = RedisClient.getByKey(RedisClient.CACHE_PREFIX_REGION_LIST);
		if(StringUtils.isNotEmpty(regionStr)) {
			LogWriter.info("已存在不再加载...");
			return;
		}
		RegionInfoMapper regionInfoMapper = (RegionInfoMapper) ApplicationContextKeeper.getBean("regionInfoMapper");
		List<Map<String, String>> provinceList = regionInfoMapper.selectProvinceAll();
		List<JSONObject> pJsonList = new ArrayList<>();
		for(Map<String, String> province:provinceList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("provCd", province.get("provCd"));
			jsonObject.put("provNm", province.get("provNm"));
			List<JSONObject> cJsonList = new ArrayList<>();
			List<Map<String, String>> cityList =  regionInfoMapper.selectCityInfo(province.get("provCd"));
			for(Map<String, String> city:cityList) {
				JSONObject cjsonObject = new JSONObject();
				cjsonObject.put("cityCd", city.get("cityCd"));
				cjsonObject.put("cityNm", city.get("cityNm"));
				List<Map<String, String>> regionList = regionInfoMapper.selectCountyInfo(city.get("cityCd"));
				List<JSONObject> rJsonList = new ArrayList<>();
				for(Map<String, String> region:regionList) {
					JSONObject rjsonObject = new JSONObject();
					rjsonObject.put("countyCd", region.get("countyCd"));
					rjsonObject.put("countyNm", region.get("countyNm"));
					rJsonList.add(rjsonObject);
				}
				cjsonObject.put("regions", rJsonList);
				cJsonList.add(cjsonObject);
			}
			jsonObject.put("citys", cJsonList);
			pJsonList.add(jsonObject);
		}
		RedisClient.set(RedisClient.CACHE_PREFIX_REGION_LIST,JSON.toJSONString(pJsonList));
	}

}

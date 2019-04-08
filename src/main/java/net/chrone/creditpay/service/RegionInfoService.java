package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.RegionInfo;

public interface RegionInfoService {

	List<Map<String, String>> selectProvinceAll();

	List<Map<String, String>> selectCityInfo(String provCd);
	/**
	 * 通过政府市区ID号查询
	 * @param govRegionCd
	 * @return
	 */
	RegionInfo getRegionInfoByFyRegionCd(String fyRegionCd);

	List<Map<String, String>> selectCountyInfo(String regionCd);

	RegionInfo getRegionInfoByFyCountyCd(String countyCd);

}

package net.chrone.creditpay.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.util.DateUtils;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserMapper appUserMapper;

	@Override
	public int getAppUserByPageCount(AppUser appuser) {
		return appUserMapper.getAppUserByPageCount(appuser);
	}

	@Override
	public List<AppUser> getAppUserByPage(AppUser appuser) {
		return appUserMapper.getAppUserByPage(appuser);
	}

	@Override
	public AppUser getAppUserByUserId(String userId) {
		return appUserMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void update(AppUser appuser) {
		appUserMapper.updateByPrimaryKeySelective(appuser);
	}

	@Override
	public int countSubAppUser(AppUser appUser) {
		List<String> userIdList = new ArrayList<String>();
		List<String> allIdList = new ArrayList<String>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(appUser.getParentUserId());
		idMap.put("ids", ids);
		userIdList = appUserMapper.getAppUserByParentIdList(idMap);
		allIdList.addAll(userIdList);
        while (!userIdList.isEmpty()) {
            idMap.clear();
            idMap.put("ids", userIdList);
            userIdList = appUserMapper.getAppUserByParentIdList(idMap);
            allIdList.addAll(userIdList);
        }
        if(!allIdList.isEmpty()){
            appUser.setIds(allIdList);
            appUser.setParentUserId("");
        }
		return this.getAppUserByPageCount(appUser);
	}

	@Override
	public List<AppUser> listSubAppUser(AppUser appUser) {
		appUser.setIds(null);
		List<String> userIdList = new ArrayList<String>();
		List<String> allIdList = new ArrayList<String>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(appUser.getParentUserId());
		idMap.put("ids", ids);
		userIdList = appUserMapper.getAppUserByParentIdList(idMap);
		allIdList.addAll(userIdList);
        while (!userIdList.isEmpty()) {
            idMap.clear();
            idMap.put("ids", userIdList);
            userIdList = appUserMapper.getAppUserByParentIdList(idMap);
            allIdList.addAll(userIdList);
        }
        if(!allIdList.isEmpty()){
            appUser.setIds(allIdList);
            appUser.setParentUserId("");
        }
        
		return this.getAppUserByPage(appUser);
	}

	@Override
	public List<AppUser> getUserLikeUserId(String userId) {
		return appUserMapper.getUserLikeUserId(userId);
	}

	@Override
	public Map<String, Object> levelDistribution() {
		Map<String, Object> retMap = new HashMap<>();
		List<Map<String, Object>> listMap = appUserMapper.levelDistribution();
		int totalCount = appUserMapper.countByExample(null);
		NumberFormat numberFormat = NumberFormat.getInstance(); 
		numberFormat.setMaximumFractionDigits(2);
		for(Map<String, Object> map : listMap) {
			long levelCount = (long) map.get("levelCount");
			map.put("proportion", numberFormat.format((float) levelCount / (float) totalCount * 100));
		}
		retMap.put("levelDis", listMap);
		retMap.put("totalCount", totalCount);
		return retMap;
	}

	@Override
	public List<Map<String, Object>> listIncreasing(AppUser appUser) {
		List<Map<String, Object>> list = appUserMapper.listIncreasing(appUser);
		
		List<Map<String, Object>> listIncreasing = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.setTime(appUser.getEndTime());
		int totalCount = 0;
		while (DateUtils.compareDate(appUser.getStartTime(), c.getTime()) < 1) {
			boolean isTrue = false;
			for (Map<String,Object> map : list) {
				if(0 == totalCount && c.getTime().equals(appUser.getEndTime())){
					totalCount = Integer.valueOf(map.get("totalcount")+"") + Integer.valueOf(map.get("count")+"");
					map.put("totalcount", totalCount);
				}
				if(DateUtils.formatDate(c.getTime(), "yyyyMMdd").equals(map.get("days"))){
					isTrue = true;
					totalCount = totalCount - Integer.valueOf(map.get("count")+"");
					map.put("totalcount", totalCount);
					map.put("TOTALCOUNT", totalCount);
					listIncreasing.add(map);
					break;
				}
			}
			if(isTrue == false){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("days", DateUtils.formatDate(c.getTime(), "yyyyMMdd"));
				m.put("count", 0);
				m.put("totalcount", totalCount);
				listIncreasing.add(m);
			}
			
			c.add(Calendar.DAY_OF_WEEK, -1);
		}
		return listIncreasing;
	}

	@Override
	public List<Map<String, Object>> listIncreasingForMap(AppUser appUser) {
		List<Map<String, Object>> list = appUserMapper.listIncreasing(appUser);
		
		List<Map<String, Object>> listIncreasing = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.setTime(appUser.getStartTime());
		while (DateUtils.compareDate(c.getTime(), appUser.getEndTime()) < 1) {
			boolean isTrue = false;
			for (Map<String,Object> map : list) {
				if(DateUtils.formatDate(c.getTime(), "yyyyMMdd").equals(map.get("days"))){
					isTrue = true;
					listIncreasing.add(map);
					break;
				}
			}
			if(isTrue == false){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("days", DateUtils.formatDate(c.getTime(), "yyyyMMdd"));
				m.put("count", 0);
				listIncreasing.add(m);
			}
			
			c.add(Calendar.DAY_OF_WEEK, 1);
		}
		return listIncreasing;
	}
	
}

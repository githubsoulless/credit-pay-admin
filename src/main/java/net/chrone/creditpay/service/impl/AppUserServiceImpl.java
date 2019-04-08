package net.chrone.creditpay.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AgentMapper;
import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.AppUserExample;
import net.chrone.creditpay.model.LevelOrder;
import net.chrone.creditpay.model.RegionInfo;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.service.LevelOrderService;
import net.chrone.creditpay.service.RegionInfoService;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.IdGen;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private LevelOrderService levelOrderService;
	@Autowired
	private RegionInfoService regionInfoService;

	@Override
	public int getAppUserByPageCount(AppUser appuser) {
		return appUserMapper.getAppUserByPageCount(appuser);
	}

	@Override
	public List<AppUser> getAppUserByPage(AppUser appuser) {
		 List<AppUser> list = appUserMapper.getAppUserByPage(appuser);
		 for(AppUser ag:list) {
				if(StringUtils.isNotEmpty(ag.getAgentId())) {
					RegionInfo region = regionInfoService.getRegionInfoByFyCountyCd(ag.getAgentId());
					if(region !=null) {
						ag.setCountyNm(region.getProvNmCn()+" "+region.getRegionNmCn()+" "+region.getCountyNmCn());
					}
				}
			}
		return list;
	}

	@Override
	public AppUser getAppUserByUserId(String userId) {
		return appUserMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public AppUser getAppUserByLoginId(String loginId) {
		AppUserExample example = new AppUserExample();
		AppUserExample.Criteria criteria = example.createCriteria();
		criteria.andLoginIdEqualTo(loginId);
		List<AppUser> list = appUserMapper.selectByExample(example);
		return list!=null&&list.size()>0?list.get(0):null;
	}

	@Override
	public void update(AppUser appuser) {
		//是否更改了等级
		AppUser appUserTemp = appUserMapper.selectByPrimaryKey(appuser.getUserId());
		if(appuser.getLevelId()!=null&&appuser.getLevelId()!=appUserTemp.getLevelId()) {
			LevelOrder levelOrder = new LevelOrder();
			String orderNo = "手动调整"+new IdGen().nextId();
			levelOrder.setOrderNo(orderNo);
			levelOrder.setOrderDt(DateUtils.getCurrentDate());
			levelOrder.setUserId(appuser.getUserId());
			levelOrder.setUserName(appuser.getAccountName());
			levelOrder.setAmount(0);
			levelOrder.setTheLevel(appUserTemp.getLevelId());
			levelOrder.setEndLevel(appuser.getLevelId());
			levelOrder.setRowCrtTs(new Date());
			levelOrder.setRowCrtUsr(appuser.getRecUpdUsr());
			levelOrderService.add(levelOrder);
		}
		
		appUserMapper.updateByPrimaryKeySelective(appuser);
	}

	@Override
	public int countSubAppUser(AppUser appUser) {
		if(StringUtils.isNotEmpty(appUser.getAgentId1())) {
			String agentIds = agentMapper.getSUBAgentIdByAgentId(appUser.getAgentId1());
			appUser.setAgentIds(Arrays.asList(agentIds.split("\\,")));
		}
		return appUserMapper.countSubAppUser(appUser);
	}

	@Override
	public List<AppUser> listSubAppUser(AppUser appUser) {
		if(StringUtils.isNotEmpty(appUser.getAgentId1())) {
			String agentIds = agentMapper.getSUBAgentIdByAgentId(appUser.getAgentId1());
			appUser.setAgentIds(Arrays.asList(agentIds.split("\\,")));
		}
		return appUserMapper.listSubAppUser(appUser);
	}

	@Override
	public List<AppUser> getUserLikeUserId(String userId) {
		return appUserMapper.getUserLikeUserId(userId);
	}

	@Override
	public Map<String, Object> levelDistribution() {
		Map<String, Object> retMap = new HashMap<>();
		List<Map<String, Object>> listMap = appUserMapper.levelDistribution();
		long totalCount = appUserMapper.countByExample(null);
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

	@Override
	public List<AppUser> getAppUserAll() {
		return appUserMapper.selectByExample(null);
	}

	@Override
	public int getUserOrderStaticsPageCount(AppUser appuser) {
		return appUserMapper.getUserOrderStaticsPageCount(appuser);
	}

	@Override
	public List<AppUser> getUserOrderStaticsByPage(AppUser appuser) {
		return appUserMapper.getUserOrderStaticsByPage(appuser);
	}
	
}

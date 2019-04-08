package net.chrone.creditpay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.mapper.AgentMapper;
import net.chrone.creditpay.mapper.AgentRoleMapper;
import net.chrone.creditpay.mapper.AgentRoleMenuMapper;
import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AgentExample;
import net.chrone.creditpay.model.AgentMenu;
import net.chrone.creditpay.model.AgentProfitVO;
import net.chrone.creditpay.model.AgentRole;
import net.chrone.creditpay.model.AgentRoleMenu;
import net.chrone.creditpay.model.AgentUser;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.AppUserExample;
import net.chrone.creditpay.model.RegionInfo;
import net.chrone.creditpay.service.AgentMenuService;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.AgentUserService;
import net.chrone.creditpay.service.RegionInfoService;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private AgentUserService agentUserService;
	@Autowired
	private AgentRoleMenuMapper agentRoleMenuMapper;
	@Autowired
	private AgentMenuService agentMenuService;
	@Autowired
	private AgentRoleMapper agentRoleMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private RegionInfoService regionInfoService;
	
	@Override
	public List<Agent> getAgentAll() {
		return agentMapper.selectByExample(null);
	}

	
	@Override
	public Agent getAgentBygAentId(String agentId) {
		return agentMapper.selectByPrimaryKey(agentId);
	}

	@Override
	public int getAgentByPageCount(Agent agent) {
		return agentMapper.getAgentByPageCount(agent);
	}

	@Override
	public List<Agent> getAgentByPage(Agent agent) {
		List<Agent> list = agentMapper.getAgentByPage(agent);
		for(Agent ag:list) {
			if(StringUtils.isNotEmpty(ag.getCountyCd())) {
				RegionInfo region = regionInfoService.getRegionInfoByFyCountyCd(ag.getCountyCd());
				if(region !=null) {
					ag.setCountyNm(region.getProvNmCn()+" "+region.getRegionNmCn()+" "+region.getCountyNmCn());
				}
			}
		}
		return list;
	}

	@Transactional
	@Override
	public void add(Agent agent) {
		// 保存代理商用户表信息
		AgentUser agentUser = new AgentUser();
		agentUser.setLoginId(agent.getAgentLoginId());
		agentUser.setAgentId(agent.getAgentId());
		agentUser.setLastLoginIp("");
		agentUser.setUserName(agent.getAgentName());
		agentUser.setLoginPwd(DigestUtils.md5Hex(agent.getPassword()));
		agentUser.setMobileNo(agent.getUserId());
		agentUser.setEmail(agent.getEmail());
		agentUser.setRoleId("900000000");
		agentUser.setUserTp("1");
		agentUser.setUserSt("1");
		agentUser.setRowCrtTs(new Date());
		agentUser.setRowCrtUsr(agent.getRowCrtUsr());
		agentUser.setRecUpdUsr(agent.getRowCrtUsr());
		agentUserService.saveAgentUser(agentUser);

		// 保存代理商角色表
		AgentRole agentRole = new AgentRole();
		agentRole.setRoleId(agentUser.getRoleId());
		agentRole.setAgentId(agent.getAgentId());
		agentRole.setRoleNm("代理管理员");
		agentRole.setRoleDesc("代理管理员");
		agentRole.setRowCrtTs(new Date());
		agentRole.setRowCrtUsr(agent.getRowCrtUsr());
		agentRole.setRecUpdUsr(agent.getRowCrtUsr());
		agentRoleMapper.insertSelective(agentRole);
		// 保存代理商角色与菜单表
		AgentRoleMenu agentRoleMenu;
		List<AgentMenu> agentMenus = agentMenuService.listAgentMenu();
		for (AgentMenu agentMenu : agentMenus) {
			agentRoleMenu = new AgentRoleMenu();
			agentRoleMenu.setRoleId(agentRole.getRoleId());
			agentRoleMenu.setMenuId(agentMenu.getMenuId());
			agentRoleMenu.setAgentId(agent.getAgentId());
			agentRoleMenu.setRowCrtTs(new Date());
			agentRoleMenu.setRowCrtUsr(agent.getRowCrtUsr());
			agentRoleMenu.setRecUpdUsr(agent.getRowCrtUsr());
			agentRoleMenuMapper.insertSelective(agentRoleMenu);
		}
		//更新当前代理绑定的用户的直属代理
		AppUser agentAppUser = new AppUser();
		agentAppUser.setUserId(agent.getUserId());
		agentAppUser.setAgentId(agent.getParentAgentId());
		agentAppUser.setRecUpdTs(new Date());
		agentAppUser.setRecUpdUsr(agent.getRowCrtUsr());
		appUserMapper.updateByPrimaryKeySelective(agentAppUser);
		
		//更新当前绑定的用户所有下属用户agent_id字段
		List<String> userIds = getUserIdsByParentId(agent.getUserId());
		if(userIds.size() >0) {
			AppUser appUser = new AppUser();
			appUser.setAgentId(agent.getAgentId());
			AppUserExample appUserExample = new AppUserExample();
			appUserExample.createCriteria().andUserIdIn(getUserIdsByParentId(agent.getUserId()));
			appUserMapper.updateByExampleSelective(appUser, appUserExample);
		}
		//保存代理商信息
		agentMapper.insertSelective(agent);
	}
	
	public List<String> getUserIdsByParentId(String parentUserId){
		List<String> userIdList = new ArrayList<String>();
		List<String> allIdList = new ArrayList<String>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(parentUserId);
		idMap.put("ids", ids);
		userIdList = appUserMapper.getAppUserByParentIdList(idMap);
		allIdList.addAll(userIdList);
		while (!userIdList.isEmpty()) {
            idMap.clear();
            idMap.put("ids", userIdList);
            List<String> tempList = new ArrayList<>();
            for(String temUserId : userIdList){
            	Agent tempAgent = getAgentByUserId(temUserId);
            	if(tempAgent==null){
            		tempList.add(temUserId);
            	}
            }
            idMap.put("ids", tempList);
            if(tempList.size()>0) {
            	userIdList = appUserMapper.getAppUserByParentIdList(idMap);
            	allIdList.addAll(userIdList);
            }else {
            	userIdList = new ArrayList<>();
            }

        }
        return allIdList;
	}

	@Override
	public void update(Agent agent) {
		agentMapper.updateByPrimaryKeySelective(agent);
	}

	@Override
	public Agent getAgentByAgentName(String agentName) {
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andAgentNameEqualTo(agentName);
		List<Agent> list = agentMapper.selectByExample(agentExample);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}


	@Override
	public Agent getAgentByUserId(String userId) {
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andUserIdEqualTo(userId);
		List<Agent> list = agentMapper.selectByExample(agentExample);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int updateAllAgentUser() {
		List<Agent> agentList = getAgentAll();
		int count=0;
		for(Agent agent:agentList) {
			// 更新当前绑定的用户所有下属用户agent_id字段
			List<String> userIds = getUserIdsByParentId(agent.getUserId());
			if (userIds.size() > 0) {
				AppUser appUser = new AppUser();
				appUser.setAgentId(agent.getAgentId());
				AppUserExample appUserExample = new AppUserExample();
				appUserExample.createCriteria().andUserIdIn(getUserIdsByParentId(agent.getUserId()));
				count+=appUserMapper.updateByExampleSelective(appUser, appUserExample);
			}
		}
		return count;
	}


	@Override
	public int getAgentUserStatisticsCount(Agent agent) {
		return agentMapper.getAgentUserStatisticsCount(agent);
	}


	@Override
	public List<Agent> getAgentUserStatisticsByPage(Agent agent) {
		return agentMapper.getAgentUserStatisticsByPage(agent);
	}


	@Override
	public Agent getAgentByCountyCd(String countyCd) {
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andCountyCdEqualTo(countyCd);
		List<Agent> list = agentMapper.selectByExample(agentExample);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}

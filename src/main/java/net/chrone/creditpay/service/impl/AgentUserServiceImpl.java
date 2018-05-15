package net.chrone.creditpay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AgentUserMapper;
import net.chrone.creditpay.model.AgentUser;
import net.chrone.creditpay.service.AgentUserService;

/**
 * 
 * Title: AgentUserServiceImpl 
 * Description: 代理商系统用户表 
 * @author huoliang
 * @data 2017年11月23日 下午5:11:31
 *
 */
@Service
public class AgentUserServiceImpl implements AgentUserService {

	@Autowired
	private AgentUserMapper agentUserMapper;
	
	@Override
	public int saveAgentUser(AgentUser agentUser) {
		return agentUserMapper.insertSelective(agentUser);
	}

	@Override
	public int updateAgentUser(AgentUser agentUser) {
		return agentUserMapper.updateByPrimaryKeySelective(agentUser);
	}

	@Override
	public AgentUser getAgentUserByLoginId(String loginId, String agentId) {
		return agentUserMapper.selectByPrimaryKey(agentId, loginId);
	}

	
	
}

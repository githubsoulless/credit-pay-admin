package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.mapper.AgentLevelMapper;
import net.chrone.creditpay.model.AgentLevel;
import net.chrone.creditpay.service.AgentLevelService;

@Service
public class AgentLevelServiceImpl implements AgentLevelService {
	@Autowired
	private AgentLevelMapper agentLevelMapper;

	@Override
	public List<AgentLevel> getAgentLevelAll() {
		List<AgentLevel> list = agentLevelMapper.selectByExample(null);
		for(AgentLevel a:list) {
			a.setCommissionRate(a.getCommissionRate()*100);
		}
		return list;
	}

	@Override
	@Transactional
	public void updateLevels(List<AgentLevel> agentLevels) {
		for(AgentLevel agentLevel:agentLevels) {
			agentLevel.setCommissionRate(agentLevel.getCommissionRate()/ 100);
			agentLevelMapper.updateByPrimaryKeySelective(agentLevel);
		}
	}

}

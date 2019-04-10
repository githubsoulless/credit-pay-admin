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
		return agentLevelMapper.selectByExample(null);
	}

	@Override
	@Transactional
	public void updateLevels(List<AgentLevel> agentLevels) {
		for(AgentLevel agentLevel:agentLevels) {
			agentLevelMapper.updateByPrimaryKeySelective(agentLevel);
		}
	}

}

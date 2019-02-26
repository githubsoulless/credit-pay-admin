package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.AgentLevel;

public interface AgentLevelService {

	List<AgentLevel> getAgentLevelAll();

	void updateLevels(List<AgentLevel> agentLevels);

}

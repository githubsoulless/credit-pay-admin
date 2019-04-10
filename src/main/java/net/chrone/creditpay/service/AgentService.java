package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AgentProfitVO;

public interface AgentService {
	List<Agent> getAgentAll();

	Agent getAgentBygAentId(String agentId);

	int getAgentByPageCount(Agent agent);

	List<Agent> getAgentByPage(Agent agent);

	void add(Agent agent);

	void update(Agent agent);

	Agent getAgentByAgentName(String agentName);
	
	Agent getAgentByUserId(String userId);

	int updateAllAgentUser();

	int getAgentUserStatisticsCount(Agent agent);

	List<Agent> getAgentUserStatisticsByPage(Agent agent);

}

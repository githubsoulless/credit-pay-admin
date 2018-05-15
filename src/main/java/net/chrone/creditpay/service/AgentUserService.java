package net.chrone.creditpay.service;

import net.chrone.creditpay.model.AgentUser;

/**
 * 
 * Title: AgentUserService 
 * Description: 代理系统用户表 
 * @author huoliang
 * @data 2017年11月23日 下午5:10:01
 * 
 */
public interface AgentUserService {
	
	int saveAgentUser(AgentUser agentUser);
	
	int updateAgentUser(AgentUser agentUser);
	
	AgentUser getAgentUserByLoginId(String loginId, String agentId);

}

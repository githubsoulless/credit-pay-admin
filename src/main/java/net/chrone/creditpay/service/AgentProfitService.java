package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.AgentProfitVO;

/**
 * 
 * Title: AgentProfitService Description: 代理分润明细
 * 
 * @author huoliang
 * @data 2017年12月6日 上午11:27:56
 *
 */
public interface AgentProfitService {

	/**
	 * 还款分润明细
	 * @param agentProfitVO
	 * @return
	 */
	List<AgentProfitVO> listAgentPayProfits(AgentProfitVO agentProfitVO);

	Map<String, Object> countAgentPayProfits(AgentProfitVO agentProfitVO);

	/**
	 * 升级分润明细
	 * @param agentProfitVO
	 * @return
	 */
	List<AgentProfitVO> listAgentUpProfits(AgentProfitVO agentProfitVO);

	Map<String, Object> countAgentUpProfits(AgentProfitVO agentProfitVO);

	/**
	 * 还款+分润明细
	 * @param agentProfitVO
	 * @return
	 */
	List<AgentProfitVO> listAgentProfits(AgentProfitVO agentProfitVO);

	Map<String, Object> countAgentProfits(AgentProfitVO agentProfitVO);
	
	/**
	 * 快捷分润明细
	 * @param agentProfitVO
	 * @return
	 */
	List<AgentProfitVO> listAgentFastProfits(AgentProfitVO agentProfitVO);

	Map<String, Object> countAgentFastProfits(AgentProfitVO agentProfitVO);

	List<AgentProfitVO> getAgentProfitsStatisticsByPage(AgentProfitVO agentProfitVO);

	int getAgentProfitsStatisticsCount(AgentProfitVO agentProfitVO);

}

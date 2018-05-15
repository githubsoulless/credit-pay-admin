package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.PayProfitsDetailMapper;
import net.chrone.creditpay.model.AgentProfitVO;
import net.chrone.creditpay.service.AgentProfitService;

@Service
public class AgentProfitServiceImpl implements AgentProfitService {
	
	@Autowired
	private PayProfitsDetailMapper payProfitsDetailMapper;

	@Override
	public List<AgentProfitVO> listAgentPayProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.listAgentPayProfits(agentProfitVO);
	}

	@Override
	public Map<String, Object> countAgentPayProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.countAgentPayProfits(agentProfitVO);
	}

	@Override
	public List<AgentProfitVO> listAgentUpProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.listAgentUpProfits(agentProfitVO);
	}

	@Override
	public Map<String, Object> countAgentUpProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.countAgentUpProfits(agentProfitVO);
	}

	@Override
	public List<AgentProfitVO> listAgentProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.listAgentProfits(agentProfitVO);
	}

	@Override
	public Map<String, Object> countAgentProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.countAgentProfits(agentProfitVO);
	}

	@Override
	public List<AgentProfitVO> listAgentFastProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.listAgentFastProfits(agentProfitVO);
	}

	@Override
	public Map<String, Object> countAgentFastProfits(AgentProfitVO agentProfitVO) {
		return payProfitsDetailMapper.countAgentFastProfits(agentProfitVO);
	}

}

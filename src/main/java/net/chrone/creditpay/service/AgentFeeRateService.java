package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.AgentFeeRate;
import net.chrone.creditpay.model.LevelFeeRate;
import net.chrone.creditpay.model.LevelFeeRateDTO;

public interface AgentFeeRateService {

	List<AgentFeeRate> listLevelFeeRate(int levelId);

	void updateLevelFeeRate(LevelFeeRateDTO levelFeeRateDTO) throws Exception;
	
	LevelFeeRate agentFeeRate(AgentFeeRate agentFeeRate);

}

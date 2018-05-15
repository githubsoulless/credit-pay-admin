package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.LevelFeeRate;

public interface LevelFeeRateService {
	
	List<LevelFeeRate> listLevelFeeRate(int levelId);
	
}

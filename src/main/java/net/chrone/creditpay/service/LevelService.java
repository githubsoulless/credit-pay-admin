package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.LevelFeeRateDTO;

public interface LevelService {
	List<Level> getLevelAll();
	Level getLevelByLevelId(int levelId);
	
	void updateLevels(List<Level> levels);
	
	List<Level> listLevel();
	
	void updateLevelFeeRate(LevelFeeRateDTO levelFeeRateDTO);
}

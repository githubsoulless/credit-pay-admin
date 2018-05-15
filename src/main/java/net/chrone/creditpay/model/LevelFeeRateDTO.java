package net.chrone.creditpay.model;

import java.util.List;

public class LevelFeeRateDTO {
	
	private Level level;

	private List<LevelFeeRate> levelFeeRates;

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<LevelFeeRate> getLevelFeeRates() {
		return levelFeeRates;
	}

	public void setLevelFeeRates(List<LevelFeeRate> levelFeeRates) {
		this.levelFeeRates = levelFeeRates;
	}


}

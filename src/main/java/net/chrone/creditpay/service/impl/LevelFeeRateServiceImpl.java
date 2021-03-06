package net.chrone.creditpay.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.LevelFeeRateMapper;
import net.chrone.creditpay.model.LevelFeeRate;
import net.chrone.creditpay.model.LevelFeeRateExample;
import net.chrone.creditpay.model.LevelFeeRateExample.Criteria;
import net.chrone.creditpay.service.LevelFeeRateService;

@Service
public class LevelFeeRateServiceImpl implements LevelFeeRateService {
	
	@Autowired
	private LevelFeeRateMapper levelFeeRateMapper;

	@Override
	public List<LevelFeeRate> listLevelFeeRate(int levelId) {
		LevelFeeRateExample levelFeeRateExample = new LevelFeeRateExample();
		levelFeeRateExample.createCriteria().andLevelIdEqualTo(levelId).andTransTypeEqualTo(1).andPayChnlCodeNotEqualTo("");
		List<LevelFeeRate> levelFeeRates = levelFeeRateMapper.selectByExample(levelFeeRateExample);
		for(LevelFeeRate levelFeeRate : levelFeeRates) {
			levelFeeRate.setFeeRate(levelFeeRate.getFeeRate().multiply(new BigDecimal(100)));
		}
		return levelFeeRates;
	}
	
	@Override
	public LevelFeeRate getLevelFeeRate(LevelFeeRate levelFeeRate) {
		
		LevelFeeRateExample levelFeeRateExample = new LevelFeeRateExample();
		Criteria criteria = levelFeeRateExample.createCriteria();
		criteria.andLevelIdEqualTo(levelFeeRate.getLevelId());
		
		if(StringUtils.isNotEmpty(levelFeeRate.getPayChnlCode())) {
			criteria.andPayChnlCodeEqualTo(levelFeeRate.getPayChnlCode());	
		}else {
			criteria.andPayChnlCodeNotEqualTo("");	
		}
		List<LevelFeeRate> levelFeeRates = levelFeeRateMapper.selectByExample(levelFeeRateExample);
		if(levelFeeRates.isEmpty() || levelFeeRates.size() == 0) {
			return null;
		}
		
		return levelFeeRates.get(0);
	}

}

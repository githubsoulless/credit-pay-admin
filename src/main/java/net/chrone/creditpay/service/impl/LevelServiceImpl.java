package net.chrone.creditpay.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.mapper.LevelFeeRateMapper;
import net.chrone.creditpay.mapper.LevelMapper;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.LevelFeeRate;
import net.chrone.creditpay.model.LevelFeeRateDTO;
import net.chrone.creditpay.service.LevelService;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private LevelMapper levelMapper;
	@Autowired
	private LevelFeeRateMapper levelFeeRateMapper;

	@Override
	public List<Level> getLevelAll() {
		return levelMapper.selectByExample(null);
	}

	@Override
	public Level getLevelByLevelId(int levelId) {
		return levelMapper.selectByPrimaryKey(levelId);
	}

	@Override
	public void updateLevels(List<Level> levels) {
		for (Level level : levels) {
			if (0 == level.getProfitsType()) {
//				level.setDirectUserFee(level.getDirectUserFee() * 100);
				level.setIndirectUserFee(level.getIndirectUserFee() * 100);
				level.setUpUserFee(level.getUpUserFee() * 100);
				level.setDirectAgentFee(level.getDirectAgentFee() * 100);
				level.setIndirectAgentFee(level.getIndirectAgentFee() * 100);
				level.setUpAgentFee(level.getUpAgentFee() * 100);
			}
			if (1 == level.getProfitsType()) {
//				level.setDirectUserFee(level.getDirectUserFee() / 100);
				level.setIndirectUserFee(level.getIndirectUserFee() / 100);
				level.setUpUserFee(level.getUpUserFee() / 100);
				level.setDirectAgentFee(level.getDirectAgentFee() / 100);
				level.setIndirectAgentFee(level.getIndirectAgentFee() / 100);
				level.setUpAgentFee(level.getUpAgentFee() / 100);
			}
//			level.setFeeRate(level.getFeeRate() / 100);
			level.setTxRate(level.getTxRateFJ().multiply(new BigDecimal(100)).intValue());
			level.setPayMinAmt(level.getPayMinAmtFJ().multiply(new BigDecimal(100)).intValue());
			level.setPayMaxAmt(level.getPayMaxAmtFJ().multiply(new BigDecimal(100)).intValue());
			level.setTxMinAmt(level.getTxMinAmtFJ().multiply(new BigDecimal(100)).intValue());
			level.setTxMaxAmt(level.getTxMaxAmtFJ().multiply(new BigDecimal(100)).intValue());
			level.setUpgradeFee(level.getUpgradeFeeFJ().multiply(new BigDecimal(100)).intValue());
			levelMapper.updateByPrimaryKeySelective(level);
		}
	}

	@Override
	public List<Level> listLevel() {
		List<Level> list = getLevelAll();
		List<Level> levels = new ArrayList<>();
		for (Level level : list) {
			level.setFeeRate(level.getFeeRate()*100);
			if (0 == level.getProfitsType()) {
				level.setDirectUserFee(level.getDirectUserFee() / 100);
				level.setIndirectUserFee(level.getIndirectUserFee() / 100);
				level.setUpUserFee(level.getUpUserFee() / 100);
				level.setDirectAgentFee(level.getDirectAgentFee() / 100);
				level.setIndirectAgentFee(level.getIndirectAgentFee() / 100);
				level.setUpAgentFee(level.getUpAgentFee() / 100);
			}
			if (1 == level.getProfitsType()) {
				level.setDirectUserFee(level.getDirectUserFee() * 100);
				level.setIndirectUserFee(level.getIndirectUserFee() * 100);
				level.setUpUserFee(level.getUpUserFee() * 100);
				level.setDirectAgentFee(level.getDirectAgentFee() * 100);
				level.setIndirectAgentFee(level.getIndirectAgentFee() * 100);
				level.setUpAgentFee(level.getUpAgentFee() * 100);
			}
			levels.add(level);
		}
		return levels;
	}

	@Transactional
	@Override
	public void updateLevelFeeRate(LevelFeeRateDTO levelFeeRateDTO) {
		Level level = levelFeeRateDTO.getLevel();
		//level.setFeeRate(level.getFeeRate()/100);
		level.setRecUpdTs(new Date());
		levelMapper.updateByPrimaryKeySelective(level);
		for(LevelFeeRate levelFeeRate : levelFeeRateDTO.getLevelFeeRates()) {
			levelFeeRate.setFeeRate(levelFeeRate.getFeeRate().divide(new BigDecimal(100)));
			if(null != levelFeeRate.getUpperlimitFj())//完美还款没有上限
				levelFeeRate.setUpperlimit(levelFeeRate.getUpperlimitFj().multiply(new BigDecimal(100)).intValue());
			levelFeeRate.setPayFee(levelFeeRate.getPayFeeFj().multiply(new BigDecimal(100)).intValue());
			levelFeeRate.setRecUpdTs(new Date());
			levelFeeRate.setRecUpdUsr(level.getRecUpdUsr());
			levelFeeRateMapper.updateByPrimaryKeySelective(levelFeeRate);
		}
	}
}

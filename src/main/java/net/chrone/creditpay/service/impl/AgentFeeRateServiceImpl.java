package net.chrone.creditpay.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.mapper.AgentFeeRateMapper;
import net.chrone.creditpay.model.AgentFeeRate;
import net.chrone.creditpay.model.AgentFeeRateExample;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.LevelFeeRate;
import net.chrone.creditpay.model.LevelFeeRateDTO;
import net.chrone.creditpay.model.AgentFeeRateExample.Criteria;
import net.chrone.creditpay.service.AgentFeeRateService;

@Service
public class AgentFeeRateServiceImpl implements AgentFeeRateService {

	@Autowired
	private AgentFeeRateMapper agentFeeRateMapper;

	@Override
	public LevelFeeRate agentFeeRate(AgentFeeRate agentFeeRate) {

		AgentFeeRateExample agentFeeRateExample = new AgentFeeRateExample();
		Criteria criteria = agentFeeRateExample.createCriteria();
		criteria.andLevelIdEqualTo(agentFeeRate.getLevelId());
		
		if(StringUtils.isNotEmpty(agentFeeRate.getPayChnlCode())) {
			criteria.andPayChnlCodeEqualTo(agentFeeRate.getPayChnlCode());	
		}else {
			criteria.andPayChnlCodeNotEqualTo("");	
		}
		
		List<AgentFeeRate> levelFeeRates = agentFeeRateMapper.selectByExample(agentFeeRateExample);
		if(levelFeeRates.isEmpty() || levelFeeRates.size() == 0) {
			return null;
		}
		agentFeeRate = levelFeeRates.get(0);
		String json = JSON.toJSONString(agentFeeRate);
		return JSON.parseObject(json, LevelFeeRate.class);
	}
	
	@Override
	public List<AgentFeeRate> listLevelFeeRate(int levelId) {
		AgentFeeRateExample agentFeeRateExample = new AgentFeeRateExample();
		agentFeeRateExample.createCriteria().andLevelIdEqualTo(levelId).andTransTypeEqualTo(1).andPayChnlCodeNotEqualTo("");
		List<AgentFeeRate> agentFeeRates = agentFeeRateMapper.selectByExample(agentFeeRateExample);
		for (AgentFeeRate agentFeeRate : agentFeeRates) {
			agentFeeRate.setFeeRate(agentFeeRate.getFeeRate().multiply(new BigDecimal(100)));
		}
		return agentFeeRates;

	}

	@Transactional
	@Override
	public void updateLevelFeeRate(LevelFeeRateDTO levelFeeRateDTO) throws Exception {
		Level level = levelFeeRateDTO.getLevel();
		for(LevelFeeRate levelFeeRate : levelFeeRateDTO.getLevelFeeRates()) {
			if(levelFeeRate.getId() == null)//jsp是通过数组索引传递过来,有些索引是没有值的
				continue;
			String json = JSON.toJSONString(levelFeeRate);
			AgentFeeRate agentFeeRate = JSON.parseObject(json, AgentFeeRate.class);
			if(null != level.getFeeRate())
				agentFeeRate.setPayFeeRate(level.getFeeRate()/100);
			agentFeeRate.setFeeRate(levelFeeRate.getFeeRate().divide(new BigDecimal(100)));
			if(null != levelFeeRate.getUpperlimitFj())
				agentFeeRate.setUpperlimit(levelFeeRate.getUpperlimitFj().multiply(new BigDecimal(100)).intValue());
			agentFeeRate.setPayFee(levelFeeRate.getPayFeeFj().multiply(new BigDecimal(100)).intValue());
			agentFeeRate.setRecUpdTs(new Date());
			agentFeeRate.setRecUpdUsr(level.getRecUpdUsr());
			agentFeeRateMapper.updateByPrimaryKeySelective(agentFeeRate);
		}
	}

}
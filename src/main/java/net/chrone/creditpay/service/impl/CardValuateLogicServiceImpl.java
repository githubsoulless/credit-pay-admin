package net.chrone.creditpay.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CardValuateMapper;
import net.chrone.creditpay.model.CardValuate;
import net.chrone.creditpay.model.CardValuateExample;
import net.chrone.creditpay.service.CardValuateLogicService;
import net.chrone.creditpay.util.DateUtils;
@Service
public class CardValuateLogicServiceImpl implements CardValuateLogicService {

	@Autowired
	private CardValuateMapper cardValuateMapper;
	
	@Override
	public void addCardValuate(CardValuate cardValuate) {
		cardValuateMapper.insertSelective(cardValuate);
	}

	@Override
	public List<CardValuate> getCardValuateList() {
		
		return cardValuateMapper.selectByExample(null);
	}

	@Override
	public List<CardValuate> getCardValuateListDuring30day(String userId) {
		
		CardValuateExample cardValuateExample = new CardValuateExample();
		cardValuateExample.setOrderByClause("create_time desc");
		
		Date queryDate = DateUtils.addTime(new Date(), Calendar.DAY_OF_YEAR, -30);
		CardValuateExample.Criteria criteria = cardValuateExample.createCriteria();
		criteria.andCreateTimeGreaterThan(queryDate);
		criteria.andUserIdEqualTo(userId);
		
		return cardValuateMapper.selectByExample(cardValuateExample);
	}
	
	@Override
	public Map<String, Object> countCardValuate(CardValuate cardValuate) {
		
		return cardValuateMapper.countCardValuate(cardValuate);
	}
	@Override
	public List<CardValuate> listCardValuatePage(CardValuate cardValuate) {
		return cardValuateMapper.listCardValuatePage(cardValuate);
	}

}

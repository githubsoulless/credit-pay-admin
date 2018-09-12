package net.chrone.creditpay.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CardExtMapper;
import net.chrone.creditpay.model.CardExt;
import net.chrone.creditpay.model.CardExtExample;
import net.chrone.creditpay.service.CardExtService;

@Service
public class CardExtServiceImpl implements CardExtService {

	@Autowired
	private CardExtMapper cardExtMapper;
	
	@Override
	public CardExt getCardExtByCardNo(String cardNo, String chnlCode) {
		
		CardExtExample cardExtExample = new CardExtExample();
		CardExtExample.Criteria criteria = cardExtExample.createCriteria();
		criteria.andCardNoEqualTo(cardNo);
		criteria.andChnlCodeEqualTo(chnlCode);
		
		List<CardExt> list = cardExtMapper.selectByExample(cardExtExample);
		if(!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	@Override
	public void saveOrUpdateCardExt(CardExt cardExt) {

		CardExt exist = getCardExtByCardNo(cardExt.getCardNo(),cardExt.getChnlCode());
		if(exist != null) {
			cardExtMapper.updateByPrimaryKey(cardExt);
		}
		else {
			cardExtMapper.insert(cardExt);
		}
	}
}

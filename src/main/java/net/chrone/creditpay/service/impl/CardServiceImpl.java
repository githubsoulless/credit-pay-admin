package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CardMapper;
import net.chrone.creditpay.mapper.CreditRootBankMapper;
import net.chrone.creditpay.model.Card;
import net.chrone.creditpay.model.CreditRootBank;
import net.chrone.creditpay.service.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private CreditRootBankMapper creditRootBankMapper; 

	@Override
	public int getCardByPageCount(Card card) {
		return cardMapper.getCardByPageCount(card);
	}

	@Override
	public List<Card> getCardByPage(Card card) {
		List<Card> list = cardMapper.getCardByPage(card);
		for(Card l:list){
			CreditRootBank creditRootBank = creditRootBankMapper.selectByPrimaryKey(l.getBankNo());
			if(creditRootBank!=null){
				l.setBankNm(creditRootBank.getBankNm());
			}
		}
		return list;
	}

}

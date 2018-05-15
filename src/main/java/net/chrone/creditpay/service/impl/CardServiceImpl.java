package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CardMapper;
import net.chrone.creditpay.model.Card;
import net.chrone.creditpay.model.RootBank;
import net.chrone.creditpay.service.CardService;
import net.chrone.creditpay.service.RootBankService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private RootBankService rootBankService;

	@Override
	public int getCardByPageCount(Card card) {
		return cardMapper.getCardByPageCount(card);
	}

	@Override
	public List<Card> getCardByPage(Card card) {
		List<Card> list = cardMapper.getCardByPage(card);
		for(Card l:list){
			RootBank rootBank = rootBankService.getRootBankByBankNo(l.getBankNo());
			if(rootBank!=null){
				l.setBankNm(rootBank.getBankNm());
			}
		}
		return list;
	}

}

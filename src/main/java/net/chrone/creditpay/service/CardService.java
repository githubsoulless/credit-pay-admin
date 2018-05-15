package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Card;

public interface CardService {

	int getCardByPageCount(Card card);

	List<Card> getCardByPage(Card card);
	
	

}

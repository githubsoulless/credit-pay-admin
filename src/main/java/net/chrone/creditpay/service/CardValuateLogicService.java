package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.CardValuate;

public interface CardValuateLogicService {

	void addCardValuate(CardValuate cardValuate);
	
	List<CardValuate> getCardValuateList();
	
	List<CardValuate> getCardValuateListDuring30day(String userId);
	
	Map<String,Object> countCardValuate(CardValuate cardValuate);

	List<CardValuate> listCardValuatePage(CardValuate cardValuate);
}

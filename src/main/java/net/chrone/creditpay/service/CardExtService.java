package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Card;
import net.chrone.creditpay.model.CardExt;

public interface CardExtService {

	/**
	 * 使用卡ID和通道代码来查询卡的额外信息
	 * @param cardNo
	 * @param chnlCode
	 * @return
	 */
	public CardExt getCardExtByCardNo(String cardNo,String chnlCode); 
	
	public void saveOrUpdateCardExt(CardExt cardExt);

	
}

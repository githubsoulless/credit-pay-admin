package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.Card;
import net.chrone.creditpay.service.CardService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;

/**
 * 卡管理
 * @author aojiong
 * @date 2017年11月20日
 * @class CardController.java
 */
@Controller
@RequestMapping("card")
public class CardController {
	@Autowired
	private CardService cardService;
	
	
	@RequestMapping("list")
	public String list(Card card,String start,Model model){
		int starIndex = StringUtils.isEmpty(start)?0:Integer.valueOf(start);
		card.setStartRow(starIndex);
		int rowTotal = cardService.getCardByPageCount(card);
		List<Card>  list =new ArrayList<Card>();
		if(rowTotal>0){
			list = cardService.getCardByPage(card);
		}
		
		MyPage page=new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("start",starIndex);
		model.addAttribute("card",card);
		model.addAttribute("rowTotal",rowTotal);
		return "card/list"; 
	}

}

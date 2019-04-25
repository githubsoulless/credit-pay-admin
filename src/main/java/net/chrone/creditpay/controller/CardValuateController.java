package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.CardValuate;
import net.chrone.creditpay.service.CardValuateLogicService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.Utils;

/**
 * 
 * @author yasin
 * @date:   2019年4月12日 上午9:31:18
 */
@Controller
@RequestMapping("cardvaluate")
public class CardValuateController extends BaseController {
	@Autowired
	private  CardValuateLogicService cardValuateLogicService;
	private static Logger logger = Logger.getLogger(CardValuateController.class);
	
	@RequestMapping("list")
	public String list(CardValuate cardValuate,String start,Model model){
		int starIndex = StringUtils.isEmpty(start)?0:Integer.valueOf(start);
		cardValuate.setStartRow(starIndex);
		
		Map<String, Object> countMap = cardValuateLogicService.countCardValuate(cardValuate);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		
		List<CardValuate>  list =new ArrayList<CardValuate>();
		if(rowTotal>0){
			list = cardValuateLogicService.listCardValuatePage(cardValuate);
		}
		if(list != null && list.size()>0) {
			for(CardValuate cardValuate_:list) {
				cardValuate_.setCertId(Utils.hiddenCard(cardValuate_.getCertId(),6,4));
				cardValuate_.setCardNo(Utils.hiddenCard(cardValuate_.getCardNo(),4,4));
			}
		}
		
		MyPage page=new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("start",starIndex);
		model.addAttribute("cardValuate",cardValuate);
		model.addAttribute("rowTotal",rowTotal);
		return "cardvaluate/list"; 
	}

}

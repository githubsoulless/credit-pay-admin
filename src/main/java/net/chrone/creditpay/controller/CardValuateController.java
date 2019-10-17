package net.chrone.creditpay.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.CardValuateFee;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.CardValuateFeeService;
import net.chrone.creditpay.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Odd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.CardValuate;
import net.chrone.creditpay.service.CardValuateLogicService;
import sun.management.resources.agent;

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
	@Autowired
	private CardValuateFeeService cardValuateFeeService;
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
	@RequestMapping("updateCardValuate")
	public String updateCardValuate(CardValuateFee cardValuateFee,String type,Model model){

		int count = 0;
		String message = "";
		try {
			if ("updateCardValuate".equals(type)) {
				int fee = BigDecimal.valueOf(cardValuateFee.getFeeOfDouble()).multiply(new BigDecimal(100)).intValue();
				int oldFee = BigDecimal.valueOf(cardValuateFee.getOldFeeOfDouble()).multiply(new BigDecimal(100)).intValue();
				CardValuateFee cardValuateFee1 = new CardValuateFee();
				cardValuateFee1.setId(1);
				cardValuateFee1.setFee(fee);
				cardValuateFee1.setOldFee(oldFee);
				count =	cardValuateFeeService.updateCardValuateFeeByPrimaryKey(cardValuateFee1);
				if(count > 0){
					message = "success";
				}
			}else{
				CardValuateFee cardValuateFee2 = cardValuateFeeService.selectcardValuateFeeByPrimaryKey(1);
				model.addAttribute("cardValuateFee",cardValuateFee2);
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "cardvaluate/updateCardValuate";
	}

}

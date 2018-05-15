package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.LotteryDetail;
import net.chrone.creditpay.service.LotteryDetailService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: LotteryDetailController 
 * Description: 抽奖查询 
 * @author huoliang
 * @data 2017年11月28日 下午6:00:14
 *
 */

@Controller
@RequestMapping("lotteryDetail")
public class LotteryDetailController {
	
	@Autowired
	private LotteryDetailService lotteryDetailService;
	
	@RequestMapping("list")
	public String list(LotteryDetail lotteryDetail, Model model, String start) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		lotteryDetail.setStartRow(starIndex);
		if (StringUtils.isEmpty(lotteryDetail.getStartDate())) {
			lotteryDetail.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(),-6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(lotteryDetail.getEndDate())) {
			lotteryDetail.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		int rowTotal = lotteryDetailService.countLotteryDetail(lotteryDetail);
		List<LotteryDetail> list = new ArrayList<>();
		if(rowTotal > 0){
			list = lotteryDetailService.listLotteryDetail(lotteryDetail);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("lotteryDetail", lotteryDetail);
		
		return "lotteryDetail/list";
	}

}

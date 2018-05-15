package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.PayProfits;
import net.chrone.creditpay.model.PayProfitsDetail;
import net.chrone.creditpay.service.PayProfitsDetailService;
import net.chrone.creditpay.service.PayProfitsService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: PayProfitsController Description: 还款分润明细
 * 
 * @author huoliang
 * @data 2017年11月21日 下午5:03:06
 *
 */
@Controller
@RequestMapping("payProfits")
public class PayProfitsController {

	@Autowired
	private PayProfitsService payProfitsService;

	@Autowired
	private PayProfitsDetailService payProfitsDetailService;

	@RequestMapping("list")
	public String list(PayProfits payProfits, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		payProfits.setStartRow(starIndex);
		if (StringUtils.isEmpty(payProfits.getStartDate())) {
			payProfits.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(),-6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(payProfits.getEndDate())) {
			payProfits.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = payProfitsService.countPayProfits(payProfits);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<PayProfits> list = new ArrayList<PayProfits>();
		if (rowTotal > 0) {
			list = payProfitsService.listPayProfitsPage(payProfits);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("payProfits", payProfits);
		model.addAttribute("countMap", countMap);
		return "payProfits/list";
	}

	@RequestMapping("detail")
	public String detail(String profitsId, Model model) {
		PayProfits payProfits = payProfitsService.getPayProfitsByProfitsId(profitsId);
		List<PayProfitsDetail> list = payProfitsDetailService.listPayProfitsDetail(profitsId);
		model.addAttribute("list", list);
		model.addAttribute("payProfits", payProfits);
		return "payProfits/detail";
	}
}

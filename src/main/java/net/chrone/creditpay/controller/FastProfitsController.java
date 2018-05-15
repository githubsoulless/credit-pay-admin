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

import net.chrone.creditpay.model.FastProfits;
import net.chrone.creditpay.model.FastProfitsDetail;
import net.chrone.creditpay.service.FastProfitsDetailService;
import net.chrone.creditpay.service.FastProfitsService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 快捷分润明细
 * @author huoliang
 *
 */
@Controller
@RequestMapping("fastProfits")
public class FastProfitsController {

	@Autowired
	private FastProfitsService fastProfitsService;
	@Autowired
	private FastProfitsDetailService fastProfitsDetailService;

	@RequestMapping("list")
	public String list(FastProfits fastProfits, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		fastProfits.setStartRow(starIndex);
		if (StringUtils.isEmpty(fastProfits.getStartDate())) {
			fastProfits.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(),-6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(fastProfits.getEndDate())) {
			fastProfits.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = fastProfitsService.countFastProfits(fastProfits);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<FastProfits> list = new ArrayList<FastProfits>();
		if (rowTotal > 0) {
			list = fastProfitsService.listFastProfitsPage(fastProfits);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("fastProfits", fastProfits);
		model.addAttribute("countMap", countMap);
		return "fastProfits/list";
	}

	@RequestMapping("detail")
	public String detail(String profitsId, Model model) {
		FastProfits fastProfits = fastProfitsService.getFastProfitsByProfitsId(profitsId);
		List<FastProfitsDetail> list = fastProfitsDetailService.listFastProfitsDetail(profitsId);
		model.addAttribute("list", list);
		model.addAttribute("fastProfits", fastProfits);
		return "fastProfits/detail";
	}
}

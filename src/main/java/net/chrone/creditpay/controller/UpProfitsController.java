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

import net.chrone.creditpay.model.UpProfits;
import net.chrone.creditpay.model.UpProfitsDetail;
import net.chrone.creditpay.service.UpProfitsDetailService;
import net.chrone.creditpay.service.UpProfitsService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: UpProfitsController Description: 升级分润明细
 * 
 * @author huoliang
 * @data 2017年11月21日 下午5:03:06
 *
 */
@Controller
@RequestMapping("upProfits")
public class UpProfitsController {

	@Autowired
	private UpProfitsService upProfitsService;

	@Autowired
	private UpProfitsDetailService upProfitsDetailService;

	@RequestMapping("list")
	public String list(UpProfits upProfits, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		upProfits.setStartRow(starIndex);
		if (StringUtils.isEmpty(upProfits.getStartDate())) {
			upProfits.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(),-6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(upProfits.getEndDate())) {
			upProfits.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = upProfitsService.countUpProfits(upProfits);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<UpProfits> list = new ArrayList<UpProfits>();
		if (rowTotal > 0) {
			list = upProfitsService.listUpProfitsPage(upProfits);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("upProfits", upProfits);
		model.addAttribute("countMap", countMap);
		return "upProfits/list";
	}

	@RequestMapping("detail")
	public String detail(String profitsId, Model model) {
		UpProfits upProfits = upProfitsService.getUpProfitsByProfitsId(profitsId);
		List<UpProfitsDetail> list = upProfitsDetailService.listUpProfitsDetail(profitsId);
		model.addAttribute("list", list);
		model.addAttribute("upProfits", upProfits);
		return "upProfits/detail";
	}
}

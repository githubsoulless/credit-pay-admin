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

import net.chrone.creditpay.model.AccountDetail;
import net.chrone.creditpay.service.AccountDetailService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.PropertiseUtil;

/**
 * 
 * Title: PlatAccountDetailController Description: 平台账务
 * 
 * @author huoliang
 * @data 2017年11月24日 上午9:42:07
 *
 */
@Controller
@RequestMapping("platAccountDetail")
public class PlatAccountDetailController {

	@Autowired
	private AccountDetailService accountDetailService;

	@RequestMapping("list")
	public String list(AccountDetail accountDetail, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		accountDetail.setStartRow(starIndex);
		String platOwnerId = PropertiseUtil.getDataFromPropertiseFile("pay", "PLAT_OWNER_ID");
		accountDetail.setOwnerId(platOwnerId);
		if (StringUtils.isEmpty(accountDetail.getStartDate())) {
			accountDetail.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(accountDetail.getEndDate())) {
			accountDetail.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = accountDetailService.countAccountDetail(accountDetail);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<AccountDetail> list = new ArrayList<AccountDetail>();
		if (rowTotal > 0) {
			list = accountDetailService.listAccountDetailPage(accountDetail);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("accountDetail", accountDetail);
		model.addAttribute("countMap", countMap);
		return "platAccountDetail/list";
	}
}

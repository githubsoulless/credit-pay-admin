package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.VerifiedDetail;
import net.chrone.creditpay.service.VerifiedDetailService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: VerifiedDetailController Description: 实名认证记录
 * 
 * @author huoliang
 * @data 2017年11月29日 下午6:10:58
 *
 */
@Controller
@RequestMapping("verifiedDetail")
public class VerifiedDetailController {

	@Autowired
	private VerifiedDetailService verifiedDetailService;

	@RequestMapping("list")
	public String list(VerifiedDetail verifiedDetail, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		verifiedDetail.setStartRow(starIndex);
		if (StringUtils.isEmpty(verifiedDetail.getStartDate())) {
			verifiedDetail.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(),-6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(verifiedDetail.getEndDate())) {
			verifiedDetail.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		List<VerifiedDetail> list = new ArrayList<>();
		int rowTotal = verifiedDetailService.countVerifiedDetail(verifiedDetail);
		if(rowTotal > 0 ){
			list = verifiedDetailService.listVerifiedDetail(verifiedDetail);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("verifiedDetail", verifiedDetail);
		
		return "verifiedDetail/list";
	}

}

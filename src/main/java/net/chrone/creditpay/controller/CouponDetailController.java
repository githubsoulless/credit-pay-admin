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

import net.chrone.creditpay.model.CouponDetail;
import net.chrone.creditpay.service.CouponDetailService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: CouponDetailController 
 * Description: 优惠券明细 
 * @author huoliang
 * @data 2017年11月28日 下午5:57:18
 *
 */

@Controller
@RequestMapping("couponDetail")
public class CouponDetailController {
	
	@Autowired
	private CouponDetailService couponDetailService;
	
	@RequestMapping("list")
	public String list(CouponDetail couponDetail, Model model, String start) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		couponDetail.setStartRow(starIndex);
		if (StringUtils.isEmpty(couponDetail.getStartDate())) {
			couponDetail.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(),-6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(couponDetail.getEndDate())) {
			couponDetail.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = couponDetailService.countCouponDetail(couponDetail);
		int rowTotal = Integer.valueOf(countMap.get("count")+"");
		List<CouponDetail> list = new ArrayList<>();
		if(rowTotal > 0){
			list = couponDetailService.listCouponDetail(couponDetail);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("couponDetail", couponDetail);
		model.addAttribute("countMap", countMap);
		
		return "couponDetail/list";
	}

}

package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.util.DateUtils;

/**
 * 
 * Title: UserStatisticsController 
 * Description: 用户统计 
 * @author huoliang
 * @data 2017年12月7日 上午9:49:30
 *
 */

@Controller
@RequestMapping("userStatistics")
public class UserStatisticsController {
	
	@Autowired
	private AppUserService appUserService;
	
	/**
	 * 
	 * @param appUser
	 * @param type  1：等级分布   2日增统计
	 * @param dateType
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(AppUser appUser, String type, String dateType, String flag, HttpServletRequest request, Model model){
		//等级分布
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> listMap = new ArrayList<>();
		if(StringUtils.isEmpty(type) || "1".equals(type)){
			type = "1";
			Map<String, Object> map = appUserService.levelDistribution();
			int totalCount = Integer.valueOf(map.get("totalCount")+"");
			list = (List<Map<String, Object>>) map.get("levelDis");
			model.addAttribute("list", list);
			model.addAttribute("totalCount", totalCount);
			return "userStatistics/level";
		}else if("2".equals(type)){
			if(StringUtils.isEmpty(flag)){
				dateType = "3";
				flag = "1";
			}
			//按照下拉日期
			if("1".equals(flag)){
				if("1".equals(dateType)){//最近7天
					appUser.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
				}else if("2".equals(dateType)) {
					appUser.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -14), "yyyy-MM-dd"));
				}else if("3".equals(dateType)) {
					appUser.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -29), "yyyy-MM-dd"));
				}
				appUser.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
			}
			list = appUserService.listIncreasing(appUser);
			listMap = appUserService.listIncreasingForMap(appUser);
			model.addAttribute("list", list);
			model.addAttribute("listMap", listMap);
			model.addAttribute("dateType", dateType);
			model.addAttribute("appUser", appUser);
			return "userStatistics/increasing";
		}
		return "";
	}

}

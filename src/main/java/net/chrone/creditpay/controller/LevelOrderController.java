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

import net.chrone.creditpay.model.LevelOrder;
import net.chrone.creditpay.service.LevelOrderService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: LevelOrderController 
 * Description: 升级查询 
 * @author huoliang
 * @data 2017年11月24日 上午9:43:35
 *
 */
@Controller
@RequestMapping("levelOrder")
public class LevelOrderController {
	@Autowired
	private LevelOrderService levelOrderService;
	@Autowired
	private LevelService levelService;

	@RequestMapping("list")
	public String list(LevelOrder levelOrder, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		levelOrder.setStartRow(starIndex);
		if (StringUtils.isEmpty(levelOrder.getStartDate())) {
			levelOrder.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(levelOrder.getEndDate())) {
			levelOrder.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = levelOrderService.getLevelOrderByPageCount(levelOrder);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<LevelOrder> list = new ArrayList<LevelOrder>();
		if (rowTotal > 0) {
			list = levelOrderService.getLevelOrderByPage(levelOrder);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("levelOrder", levelOrder);
		model.addAttribute("levelList", levelService.getLevelAll());
		model.addAttribute("countMap", countMap);
		return "levelOrder/list";
	}
}

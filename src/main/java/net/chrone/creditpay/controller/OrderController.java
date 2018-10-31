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

import net.chrone.creditpay.model.Order;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.service.OrderService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: OrderController 
 * Description: 交易订单查询 
 * @author huoliang
 * @data 2017年11月24日 上午9:43:57
 *
 */

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private PayChannelService payChannelService;

	@RequestMapping("list")
	public String list(Order order, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		order.setStartRow(starIndex);
		if (StringUtils.isEmpty(order.getStartDate())) {
			order.setStartDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(order.getEndDate())) {
			order.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		order.setStartDate(order.getStartDate().replaceAll("-",""));
		order.setEndDate(order.getEndDate().replaceAll("-",""));
		Map<String, Object> countMap = orderService.getOrderByPageCount(order);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<Order> list = new ArrayList<Order>();
		if (rowTotal > 0) {
			list = orderService.getOrderByPage(order);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		List<PayChannel> channelList = payChannelService.listAllPayChannel();
		model.addAttribute("channelList", channelList);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("order", order);
		model.addAttribute("countMap", countMap);
		return "order/list";
	}
}

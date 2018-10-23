package net.chrone.creditpay.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.service.FastOrderService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;

@Controller
@RequestMapping("fastOrder")
public class FastOrderController {
	@Autowired
	private FastOrderService orderService;
	@Autowired
	private PayChannelService payChannelService;
	
	@RequestMapping("list")
	public String list(FastOrder order, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		order.setStartRow(starIndex);
		if (StringUtils.isEmpty(order.getStartDate())) {
			order.setStartDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(order.getEndDate())) {
			order.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = orderService.getOrderByPageCount(order);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<FastOrder> list = new ArrayList<FastOrder>();
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
		return "fastOrder/list";
	}
	
	/**
	 * 重发代付
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "fastOrder/agentpayManual";
	}
	
	/**
	 * 重发代付
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("updateState")
	public void reSendPay(HttpServletRequest request,HttpServletResponse response,String orderNo) throws Exception{
		String message="";
		try {
			if(StringUtils.isEmpty(orderNo)){
				throw new CHException("参数异常");
			}
			int result = orderService.reSendPay(orderNo);
			if(1 == result) {
				message="success";
			}else {
				message="代付重发失败";
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}
	
	/**
	 * 手动代付
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping("agentpayManual")
	public String agentpayManual(HttpServletRequest request,HttpServletResponse response,FastOrder order,Model model) throws Exception{
		String message="success";
		try {
			validate(order);
			orderService.handleAgentPayManual(order);
			model.addAttribute("message", message);
			
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} 
		
		return "fastOrder/agentpayManual";
	}
	
	/**
	 * 手动代付
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping("calcFee")
	public void calcFee(HttpServletRequest request,HttpServletResponse response,FastOrder order) throws Exception{
		String message="";
		try {
			validate(order);
			int[] fee = orderService.queryFee(order);
			message = fee[0]+","+fee[1]+","+fee[2]+","+fee[3];
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}
	
	
	private void validate(FastOrder order) {
		
		if(StringUtils.isEmpty(order.getUserId())) {
			throw new CHException("500","参数错误userId");
		}
		if(order.getAmount() ==0) {
			throw new CHException("500","参数错误amount");
		}
		if(StringUtils.isEmpty(order.getChannel())) {
			throw new CHException("500","参数错误channel");
		}
	}
}

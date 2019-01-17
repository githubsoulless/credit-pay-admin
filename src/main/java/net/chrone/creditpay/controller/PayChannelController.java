package net.chrone.creditpay.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: PayChannelController Description: 通道管理
 * 
 * @author huoliang
 * @data  2017年11月24日 上午11:39:52
 *
 */
@Controller
@RequestMapping("payChannel")
public class PayChannelController {

	@Autowired
	private PayChannelService payChannelService;
	@Autowired
	private LogConstant logConstant;

	@RequestMapping("list")
	public String list(PayChannel payChannel, Model model, String start) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		payChannel.setStartRow(starIndex);
		List<PayChannel> list = new ArrayList<>();
		int rowTotal = payChannelService.countPayChannel(payChannel);
		if (rowTotal > 0) {
			list = payChannelService.listPayChannel(payChannel);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("payChannel", payChannel);

		return "payChannel/list";
	}

	@RequestMapping("add")
	public String add(PayChannel payChannel, String type, Model model, HttpServletRequest request) {
		String message = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			if ("add".equals(type)) {
				PayChannel oPayChannel = payChannelService.getPayChannelByCodeAndType(payChannel);
				if(null != oPayChannel) {
					message = "通道信息已存在";
					model.addAttribute("message", message);
					return "payChannel/add";
				}
				payChannel.setId(new IdGen().nextId());
				payChannel.setCreated(new Date());
				payChannel.setCreatedUser(userInfSeesion.getLoginId());
				payChannel.setStatus(1);
				payChannel.setModifiedUser(userInfSeesion.getLoginId());
				
				String[] supBanks = request.getParameterValues("supBank");
				String[] supBankLimit = request.getParameterValues("supBankLimit");
				List<Map<String, String>> chnlRisk = new ArrayList<>();
				if(supBanks != null && supBankLimit != null) {
					for(int i=0;i<supBanks.length;i++) {
						Map<String, String> map = new HashMap<>();
						map.put(supBanks[i],supBankLimit[i]);
						chnlRisk.add(map);
					}
				}
				if(chnlRisk.size()>0) {
					payChannel.setChnlRisk(JSON.toJSONString(chnlRisk));
				}
				
				payChannelService.savePayChannel(payChannel);
				logConstant.createTweblog(userInfSeesion.getLoginId(), "新增通道，通道代码="+payChannel.getCode()+"，通道名称="+payChannel.getName(), 6, request);
				message = "success";
			} else {
				model.addAttribute("startDate", "00:00:00");
				model.addAttribute("endDate", "23:59:59");
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "payChannel/add";
	}

	@RequestMapping("update")
	public String update(PayChannel payChannel, String type, Model model, HttpServletRequest request) {
		String message = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			if ("update".equals(type)) {
				payChannel.setModified(new Date());
				payChannel.setModifiedUser(userInfSeesion.getLoginId());
				if(0 == payChannel.getFeeType()){
					payChannel.setFeeRate(payChannel.getFeeRate().divide(new BigDecimal(100)));
				}
				if(1 == payChannel.getFeeType()){
					payChannel.setFeeRate(payChannel.getFeeRate().multiply(new BigDecimal(100)));
				}
				payChannel.setMinAmount(BigDecimal.valueOf(payChannel.getMinAmountDou()*100).intValue());
				payChannel.setMaxAmount(BigDecimal.valueOf(payChannel.getMaxAmountDou()*100).intValue());
				payChannel.setDaySumAmount(BigDecimal.valueOf(payChannel.getDaySumAmtDou()*100).intValue());
				if(null != payChannel.getUpperlimitDou()) {
					payChannel.setUpperlimit((payChannel.getUpperlimitDou().multiply(new BigDecimal(100))).intValue());
				}
				if(null != payChannel.getPayFeeRate()) {
					payChannel.setPayFeeRate(payChannel.getPayFeeRate().multiply(new BigDecimal(100)));
				}
				try {
					if(StringUtils.isEmpty(payChannel.getStartDate())) {
						payChannel.setStartTime(DateUtils.parseDate("00:00:00", "HH:mm:ss"));
					}else {
						payChannel.setStartTime(DateUtils.parseDate(payChannel.getStartDate(), "HH:mm:ss"));
					}
					
					if(StringUtils.isEmpty(payChannel.getEndDate())){
						payChannel.setEndTime(DateUtils.parseDate("23:59:59", "HH:mm:ss"));
					}else {
						payChannel.setEndTime(DateUtils.parseDate(payChannel.getEndDate(), "HH:mm:ss"));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				String[] supBanks = request.getParameterValues("supBank");
				String[] supBankLimit = request.getParameterValues("supBankLimit");
				List<Map<String, String>> chnlRisk = new ArrayList<>();
				if(supBanks != null && supBankLimit != null) {
					for(int i=0;i<supBanks.length;i++) {
						Map<String, String> map = new HashMap<>();
						map.put(supBanks[i],supBankLimit[i]);
						chnlRisk.add(map);
					}
				}
				if(chnlRisk.size()>0) {
					payChannel.setChnlRisk(JSON.toJSONString(chnlRisk));
				}
				
				payChannelService.updatePayChannel(payChannel);
				logConstant.createTweblog(userInfSeesion.getLoginId(), "修改通道，通道代码="+payChannel.getCode()+"，通道名称="+payChannel.getName(), 6, request);
				message = "success";
			} else {
				PayChannel detail = payChannelService.getPayChannelByPrimaryKey(payChannel.getId());
				detail.setStartDate(DateUtils.formatDate(detail.getStartTime(), "HH:mm:ss"));
				detail.setEndDate(DateUtils.formatDate(detail.getEndTime(), "HH:mm:ss"));
				model.addAttribute("payChannel", detail);
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "payChannel/update";
	}
	
	@RequestMapping("close")
	public @ResponseBody String close(PayChannel payChannel, String type, Model model, HttpServletRequest request) {
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			String memo = "";
			payChannel.setModified(new Date());
			payChannel.setModifiedUser(userInfSeesion.getLoginId());
			if("0".equals(type)){
				payChannel.setStatus(0);
				memo = "关闭";
			}
			if("1".equals(type)){
				payChannel.setStatus(1);
				memo = "开启";
			}
			payChannelService.updatePayChannel(payChannel);
			logConstant.createTweblog(userInfSeesion.getLoginId(), memo + "通道，通道代码="+payChannel.getCode(), 6, request);
			
		} catch (CHException e) {
			e.printStackTrace();
			return "99";
		} catch (Exception e) {
			e.printStackTrace();
			return "99";
		}
		return "1";
	}

	@RequestMapping("detail")
	public String detail(String id, Model model) {
		PayChannel detail = payChannelService.getPayChannelByPrimaryKey(id);
		model.addAttribute("payChannel", detail);
		return "payChannel/detail";
	}

}

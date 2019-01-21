package net.chrone.creditpay.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.http.HttpResponse;
import net.chrone.creditpay.model.CreditRootBank;
import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.model.RoleInf;
import net.chrone.creditpay.model.RoleMenu;
import net.chrone.creditpay.model.RootBank;
import net.chrone.creditpay.service.CreditRootBankService;
import net.chrone.creditpay.service.MenuInfService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.service.RoleInfService;
import net.chrone.creditpay.service.RootBankService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.service.impl.SeqServiceImpl;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;


/**
 * 信用卡快捷通道规则
 * @author yasin
 *2019年1月18日
 */
@Controller
@RequestMapping("rootbank")
public class RootBankController extends BaseController {
	
	@Autowired 
	private CreditRootBankService creditRootBankService;
	@Autowired
	private PayChannelService payChannelService;
	

	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		
		List<CreditRootBank> list = creditRootBankService.geCreditRootBankAll();
		PayChannel payChannel = new PayChannel();
		payChannel.setStatus(1);
		payChannel.setChnlType(4);
		List<PayChannel> payChannels = payChannelService.listPayChannel(payChannel);
		List<PayChannel> retPayChannels = new ArrayList<>();
		for(PayChannel tmPayChannel: payChannels) {
			PayChannel retPayChannel = new PayChannel();
			retPayChannel.setCode(tmPayChannel.getCode());
			retPayChannel.setName(tmPayChannel.getName());
			retPayChannels.add(retPayChannel);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("chnls", JSON.toJSONString(retPayChannels));
		return "rootbank/list";
	}
	

	@RequestMapping("update")
	public void update(HttpServletRequest request,HttpServletResponse response,@RequestParam String bankNo,@RequestParam String chnlRule){
		Map<String, String> respMap = new HashMap<>();
		try {
			CreditRootBank creditRootBank = creditRootBankService.getCreditRootBankByBankNo(bankNo);
			if(creditRootBank == null) {
				respMap.put("respCode", "500");
				respMap.put("respMsg", "无效的bankNo");
				response(respMap, response);
				return;
			}else {
				creditRootBank.setChnlRule(chnlRule);
				creditRootBankService.updateCreditRootBank(creditRootBank);;
				respMap.put("respCode", "200");
				respMap.put("respMsg", "success");
			}
		} catch (CHException e) {
			respMap.put("respCode", "500");
			respMap.put("respMsg", e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			respMap.put("respCode", "500");
			respMap.put("respMsg", "系统异常");
			e.printStackTrace();
		}
		
		response(respMap, response);
	}	
	
	
}

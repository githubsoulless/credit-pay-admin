package net.chrone.creditpay.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.api.ChroneApi;
import net.chrone.creditpay.util.ConfigReader;

@Controller
@RequestMapping("payChnlBalance")
public class PayChnlBalanceController {

	@RequestMapping("detail")
	public String detail(Model model){
		Map<String, String> agentPayMap = ChroneApi.queryBalance(ConfigReader.getConfig("chronePayOrgId"), ConfigReader.getConfig("chronePayPriKey"));
		model.addAttribute("agentPayMap", agentPayMap);
		return "chnlQueryBalance/detail";
	}
	
}

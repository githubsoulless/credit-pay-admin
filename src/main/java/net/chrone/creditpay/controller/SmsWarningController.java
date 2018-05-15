package net.chrone.creditpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.SmsWarning;
import net.chrone.creditpay.model.SmsWarningDTO;
import net.chrone.creditpay.service.SmsWarningService;
import net.chrone.creditpay.util.CHException;

@Controller
@RequestMapping("smsWarning")
public class SmsWarningController {
	@Autowired
	private SmsWarningService smsWarningService;
	
	@RequestMapping("detail")
	public String detail(String type,SmsWarningDTO smsWarningDTO,Model model){
		String message="";
		try {
			if("update".equals(type)){
				smsWarningService.update(smsWarningDTO);
				message="success";
			}
			List<SmsWarning> smsWarningList = smsWarningService.getSmsWarningList();
			model.addAttribute("list", smsWarningList);
			model.addAttribute("listCount", smsWarningList.size());
		} catch (CHException e) {
			message=e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "smsWarning/detail";
	}

}

package net.chrone.creditpay.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.SysParamDTO;
import net.chrone.creditpay.service.SysParamService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.Constants;

/**
 * 
 * Title: SysParamController 
 * Description: 平台参数设置 
 * @author huoliang
 * @data 2017年11月22日 下午1:38:44
 *
 */
@RequestMapping("sysParam")
@Controller
public class SysParamController {

	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private LogConstant logConstant;
	
	@RequestMapping("detail")
	public String detail(Model model){
		Map<String, String> params =  sysParamService.listSysParam();
		model.addAttribute("params", params);
		return "sysParam/detail";
	}
	
	@RequestMapping("fastPayDetail")
	public String fastPayDetail(Model model){
		Map<String, String> params =  sysParamService.listSysParam();
		model.addAttribute("params", params);
		return "sysParam/fastPayDetail";
	}
	
	@RequestMapping("repayDetail")
	public String repayDetail(Model model){
		Map<String, String> params =  sysParamService.listSysParam();
		model.addAttribute("params", params);
		return "sysParam/repayDetail";
	}
	
	
	@RequestMapping("update")
	public String update(HttpServletRequest request, SysParamDTO sysParamDTO, Model model){
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		sysParamService.updateSysParam(sysParamDTO);
		logConstant.createTweblog(userInfSeesion.getLoginId(), "平台参数设置", 2, request);
		Map<String, String> params =  sysParamService.listSysParam();
		model.addAttribute("params", params);
		model.addAttribute("message", "success");
		String returnJsp = "sysParam/detail";
		switch (sysParamDTO.getType()) {
		case "0":
			returnJsp = "sysParam/detail";
			break;
		case "1":
			returnJsp = "sysParam/repayDetail";
			break;
		case "2":
			returnJsp = "sysParam/fastPayDetail";
			break;
		default:
			returnJsp = "sysParam/detail";
			break;
		}
		return returnJsp;
	}
	
}

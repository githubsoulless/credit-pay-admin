package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.SmsMessage;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.service.SmsMessageService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: MessageController 
 * Description: 短信管理
 * 
 * @author huoliang
 * @data 2017年12月1日 下午3:39:45
 *
 */

@Controller
@RequestMapping("smsMessage")
public class SmsMessageController {

	@Autowired
	private SmsMessageService smsMessageService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private LogConstant logConstant;

	@RequestMapping("list")
	public String list(SmsMessage smsMessage, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		smsMessage.setStartRow(starIndex);
		if (null != smsMessage.getPushRange() && 2 != smsMessage.getPushRange()) {
			smsMessage.setUserId(null);
		}
		if (StringUtils.isNotEmpty(smsMessage.getAgentId1())) {
			smsMessage.setAgentId(smsMessage.getAgentId1());
		}
		if (StringUtils.isNotEmpty(smsMessage.getAgentId2())) {
			smsMessage.setAgentId(smsMessage.getAgentId2());
		}
		if (StringUtils.isNotEmpty(smsMessage.getAgentId2())) {
			smsMessage.setAgentId(smsMessage.getAgentId2());
		}
		List<SmsMessage> list = new ArrayList<>();
		int rowTotal = smsMessageService.countSmsMessage(smsMessage);
		if (rowTotal > 0) {
			list = smsMessageService.listSmsMessage(smsMessage);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("smsMessage", smsMessage);
		List<Level> levelList = levelService.getLevelAll();
		List<Agent> agentList = agentService.getAgentAll();
		model.addAttribute("levelList", levelList);
		model.addAttribute("agentList", agentList);
		model.addAttribute("agentListJson", JSON.toJSONString(agentList));
		return "smsmessage/list";
	}

	@RequestMapping("add")
	public String add(SmsMessage smsMessage, String type, Model model, HttpServletRequest request) {
		String msg = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			if ("add".equals(type)) {
				smsMessage.setTloguserid(userInfSeesion.getLoginId());
				smsMessage.setId(new IdGen().nextId());
				smsMessageService.saveMessage(smsMessage);
				logConstant.createTweblog(userInfSeesion.getLoginId(), "短信管理，创建短信，短信内容=" + smsMessage.getContent(), 10,request);
				msg = "success";
			} else {
				List<Level> levelList = levelService.getLevelAll();
				List<Agent> agentList = agentService.getAgentAll();

				model.addAttribute("levelList", levelList);
				model.addAttribute("agentList", agentList);
				model.addAttribute("agentListJson", JSON.toJSONString(agentList));
			}
		} catch (CHException e) {
			msg = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			msg = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", msg);

		return "smsmessage/add";
	}

	@RequestMapping("detail")
	public String detail(String id, Model model) {
		SmsMessage smsMessage = smsMessageService.getSmsMessage(id);
		model.addAttribute("smsMessage", smsMessage);
		return "smsmessage/detail";
	}

}

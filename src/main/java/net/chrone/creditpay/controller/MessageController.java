package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.Message;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.UserMessage;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.service.MessageService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: MessageController Description: APP消息推送管理
 * 
 * @author huoliang
 * @data 2017年12月1日 下午3:39:45
 *
 */

@Controller
@RequestMapping("message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private LogConstant logConstant;

	@RequestMapping("list")
	public String list(Message message, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		message.setStartRow(starIndex);
		if (null != message.getPushRange() && 2 != message.getPushRange()) {
			message.setUserId(null);
		}
		if (StringUtils.isNotEmpty(message.getAgentId1())) {
			message.setAgentId(message.getAgentId1());
		}
		if (StringUtils.isNotEmpty(message.getAgentId2())) {
			message.setAgentId(message.getAgentId2());
		}
		if (StringUtils.isNotEmpty(message.getAgentId2())) {
			message.setAgentId(message.getAgentId2());
		}
		List<Message> list = new ArrayList<>();
		int rowTotal = messageService.countMessage(message);
		if (rowTotal > 0) {
			list = messageService.listMessage(message);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("message", message);
		List<Level> levelList = levelService.getLevelAll();
		List<Agent> agentList = agentService.getAgentAll();
		model.addAttribute("levelList", levelList);
		model.addAttribute("agentList", agentList);
		model.addAttribute("agentListJson", JSON.toJSONString(agentList));

		return "message/list";
	}

	@RequestMapping("add")
	public String add(Message message, String type, Model model, HttpServletRequest request) {
		String msg = "";
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		try {
			if ("add".equals(type)) {
				message.setTloguserid(userInfSeesion.getLoginId());
				messageService.saveMessage(message);
				logConstant.createTweblog(userInfSeesion.getLoginId(), "APP消息推送，新增消息，消息标题=" + message.getTitle(), 9,
						request);
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

		return "message/add";
	}

	@RequestMapping("detail")
	public String detail(String id, Model model) {
		Message message = messageService.getMessage(id);
		model.addAttribute("message", message);
		return "message/detail";
	}

	@RequestMapping("delete")
	@ResponseBody
	public String delete(Message message, Model model, HttpServletRequest request) {
		message.setState(1);
		messageService.deleteMessage(message);
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		logConstant.createTweblog(userInfSeesion.getLoginId(), "APP消息推送，删除消息，消息ID=" + message.getId(), 9, request);
		return "0";
	}
	
	@RequestMapping("istop")
	@ResponseBody
	public String istop(Message message,String type, Model model, HttpServletRequest request) {
		message.setIsTop(Integer.valueOf(type));
		messageService.update(message);
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		logConstant.createTweblog(userInfSeesion.getLoginId(), "APP消息推送，[置顶/取消]消息，消息ID=" + message.getId(), 9, request);
		return "0";
	}

	@RequestMapping("userlist")
	public String userlist(UserMessage userMessage, String type, String start, Model model,
			HttpServletRequest request) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		userMessage.setStartRow(starIndex);
		List<UserMessage> list = new ArrayList<>();
		int totalRow = 0;
		int notReadCount = 0;
		int totalCount = 0;
		totalRow = messageService.countUserMessage(userMessage);
		if (StringUtils.isEmpty(type) || "1".equals(type)) {
			type = "1";
			totalCount = totalRow;
			if (totalRow > 0) {
				list = messageService.listUserMessage(userMessage);
			}
			userMessage.setStatus(0);
			userMessage.setIsRead(0);
			notReadCount = messageService.countUserMessage(userMessage);
		}
		// 未读
		if ("2".equals(type)) {
			userMessage.setStatus(0);
			userMessage.setIsRead(0);
			notReadCount = messageService.countUserMessage(userMessage);
			if (notReadCount > 0) {
				list = messageService.listUserMessage(userMessage);
			}
			totalCount = notReadCount;
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, totalCount);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", start);
		model.addAttribute("notReadCount", notReadCount);
		model.addAttribute("totalRow", totalRow);
		Message oMessage = messageService.getMessage(userMessage.getMessageId());
		model.addAttribute("message", oMessage);
		model.addAttribute("type", type);
		return "message/userlist";
	}

}

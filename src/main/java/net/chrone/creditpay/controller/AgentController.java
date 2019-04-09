package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AgentUser;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.AgentUserService;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.service.impl.SeqServiceImpl;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.StringUtil;

/**
 * 
 * Title: AgentController 
 * Description: 代理管理 
 * @author huoliang
 * @data 2017年11月24日 上午9:42:59
 *
 */
@Controller
@RequestMapping("agent")
public class AgentController {
	@Autowired
	private AgentService agentService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AgentUserService agentUserService;
	@Autowired
	private LogConstant logConstant;
	@Autowired
	private SeqService seqService;
	

	@RequestMapping("list")
	public String list(Agent agent, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		agent.setStartRow(starIndex);
		if (StringUtils.isNotEmpty(agent.getAgentId1())) {
			agent.setAgentId3(agent.getAgentId1());
		}
		if (StringUtils.isNotEmpty(agent.getAgentId2())) {
			agent.setAgentId3(agent.getAgentId2());
		}
		int rowTotal = agentService.getAgentByPageCount(agent);
		List<Agent> list = new ArrayList<Agent>();
		if (rowTotal > 0) {
			list = agentService.getAgentByPage(agent);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("agent", agent);
		List<Agent> agentList = agentService.getAgentAll();
		model.addAttribute("agentList", agentList);
		model.addAttribute("agentListJson", JSON.toJSONString(agentList));
		model.addAttribute("rowTotal", rowTotal);
		return "agent/list";
	}

	@RequestMapping("add")
	public String add(Agent agent, String type, Model model, HttpServletRequest request) {
		String message = "";
		try {
			if ("add".equals(type)) {
				Agent oAgent = agentService.getAgentByAgentName(agent.getAgentName());
				if (null != oAgent) {
					throw new CHException("代理名称已存在,请重新输入");
				}
				if(StringUtils.isEmpty(agent.getCountyCd())) {
					agent.setCountyCd(agent.getCityId());
				}
				oAgent = agentService.getAgentByCountyCd(agent.getCountyCd());
				if (null != oAgent) {
					throw new CHException("此区县已有代理,请重新选择区县");
				}
				AppUser appUser = appUserService.getAppUserByUserId(agent.getUserId());
				if (appUser == null) {
					throw new CHException("绑定的用户账号不存在,请重新输入");
				}
				
//				AgentUser oAgentUser = agentUserService.getAgentUserByLoginId(agent.getAgentLoginId());
//				if (null != oAgentUser) {
//					throw new CHException("代理登录账号已存在,请重新输入");
//				}
				MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
				agent.setAgentId(StringUtil.getRandom8()+seqService.updateAndGetSequence(SeqServiceImpl.T_AGENT_INF, 2));
				agent.setRowCrtTs(new Date());
				agent.setRowCrtUsr(userInfSeesion.getLoginId());
				agent.setLevel(1);
				agentService.add(agent);
				message = "success";
			} else {
				model.addAttribute("agentList", agentService.getAgentAll());
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "agent/add";
	}

	@RequestMapping("update")
	public String update(Agent agent, String type, Model model, HttpServletRequest request) {
		String message = "";
		Agent agentInfo = agentService.getAgentBygAentId(agent.getAgentId());
		try {
			if ("update".equals(type)) {
//				AppUser appUser = appUserService.getAppUserByUserId(agent.getUserId());
//				if (appUser == null) {
//					throw new CHException("绑定的用户账号不存在,请重新输入");
//				}
				MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
				agent.setRecUpdTs(new Date());
				agent.setRecUpdUsr(userInfSeesion.getLoginId());
				agentService.update(agent);
				if(!agentInfo.getParentAgentId().equals(agent.getParentAgentId())){
					String pAgentId=agent.getParentAgentId();
					if(StringUtils.isEmpty(pAgentId)){
						pAgentId="平台";
					}
					String memo="代理ID："+agentInfo.getAgentId()+"，上级代理从ID："+agentInfo.getParentAgentId()+"迁移到ID："+pAgentId;
					logConstant.createTweblog(userInfSeesion.getLoginId(), memo, 11, request);
				}
				message = "success";
			} else {
				model.addAttribute("agent", agentInfo);
				model.addAttribute("agentList", agentService.getAgentAll());
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "agent/update";
	}

	@RequestMapping("resetPwd")
	public String resetPwd(AgentUser agentUser,String type, Model model, HttpServletResponse response, HttpServletRequest request) {
		String message = "";
		try {
			Agent agent = agentService.getAgentBygAentId(agentUser.getAgentId());
			model.addAttribute("agent", agent);
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			if ("resetpwd".equals(type)) {
				agentUser.setLoginPwd(DigestUtils.md5Hex(agentUser.getLoginPwd()));
				agentUser.setRecUpdTs(new Date());
				agentUser.setRecUpdUsr(userInfSeesion.getLoginId());
				agentUserService.updateAgentUser(agentUser);
				message = "success";
			}else{
				return "agent/resetPwd";
			}
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "agent/resetPwd";
	}
	
	@RequestMapping("getSubAgentList")
	public String getSubAgentList(String agentId,Model model) {
		Agent agent = new Agent();
		agent.setAgentId3(agentId);
		agent.setStartRow(0);
		agent.setPageSize(100000);
		List<Agent> list = agentService.getAgentByPage(agent);
		Agent agentInfo = agentService.getAgentBygAentId(agentId);
		model.addAttribute("list", list);
		model.addAttribute("agentInfo", agentInfo);
		return "agent/subAgentList";
	}
	
	@RequestMapping("updateAllAgentUser")
	public @ResponseBody String updateAllAgentUser() {
		int count = agentService.updateAllAgentUser();
		return "success:"+count;
	}
}

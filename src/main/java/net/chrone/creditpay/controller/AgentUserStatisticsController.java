package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AgentProfitVO;
import net.chrone.creditpay.service.AgentProfitService;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 代理商用户统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping("agentUserStatistics")
public class AgentUserStatisticsController {
	@Autowired
	private AgentService agentService;

	@RequestMapping("list")
	public String list(Agent agent, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		agent.setStartRow(starIndex);
		int rowTotal = agentService.getAgentUserStatisticsCount(agent);
		List<Agent> list = new ArrayList<Agent>();
		if (rowTotal > 0) {
			list = agentService.getAgentUserStatisticsByPage(agent);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("agent", agent);
		model.addAttribute("rowTotal", rowTotal);
		return "agentUserStatistics/list";
	}

}

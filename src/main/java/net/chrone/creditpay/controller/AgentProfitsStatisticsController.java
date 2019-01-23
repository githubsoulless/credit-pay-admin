package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.AgentProfitVO;
import net.chrone.creditpay.service.AgentProfitService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.MyPage;

/**
 * 代理商分润统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping("agentProfitsStatistics")
public class AgentProfitsStatisticsController {
	@Autowired
	private AgentProfitService agentProfitService;

	@RequestMapping("list")
	public String list(AgentProfitVO agentProfitVO, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		agentProfitVO.setStartRow(starIndex);
		if (StringUtils.isEmpty(agentProfitVO.getStartDate())) {
			agentProfitVO.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyyMMdd"));
		}
		if (StringUtils.isEmpty(agentProfitVO.getEndDate())) {
			agentProfitVO.setEndDate(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		}
		int rowTotal = agentProfitService.getAgentProfitsStatisticsCount(agentProfitVO);
		List<AgentProfitVO> list = new ArrayList<AgentProfitVO>();
		if (rowTotal > 0) {
			list = agentProfitService.getAgentProfitsStatisticsByPage(agentProfitVO);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("agentProfitVO", agentProfitVO);
		model.addAttribute("rowTotal", rowTotal);
		return "agentProfitsStatistics/list";
	}

}

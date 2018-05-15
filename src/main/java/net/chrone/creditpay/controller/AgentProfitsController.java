package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
 * 
 * Title: AgentProfitsController 
 * Description: 代理分润明细 
 * @author huoliang
 * @data 2017年11月21日 下午8:47:01
 *
 */
@Controller
@RequestMapping("agentProfits")
public class AgentProfitsController {
	
	@Autowired
	private AgentProfitService agentProfitService;
	
	@RequestMapping("list")
	public String list(AgentProfitVO agentProfitVO, String start, Model model, HttpServletRequest request){
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		agentProfitVO.setStartRow(starIndex);
		if (StringUtils.isEmpty(agentProfitVO.getStartDate())) {
			agentProfitVO.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(agentProfitVO.getEndDate())) {
			agentProfitVO.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		int rowTotal = 0;
		Map<String, Object> countMap = new HashMap<String, Object>();
		List<AgentProfitVO> list = new ArrayList<>();
		//还款+升级
		if(null == agentProfitVO.getProfitsType()){
			countMap = agentProfitService.countAgentProfits(agentProfitVO);
			rowTotal = Integer.valueOf(countMap.get("count")+"");
			if(rowTotal > 0){
				list = agentProfitService.listAgentProfits(agentProfitVO);
			}
		}else if(1 == agentProfitVO.getProfitsType()){
			//还款
			countMap = agentProfitService.countAgentPayProfits(agentProfitVO);
			rowTotal = Integer.valueOf(countMap.get("count")+"");
			if(rowTotal > 0){
				list = agentProfitService.listAgentPayProfits(agentProfitVO);
			}
		}else if(2 == agentProfitVO.getProfitsType()){
			//升级
			countMap = agentProfitService.countAgentUpProfits(agentProfitVO);
			rowTotal = Integer.valueOf(countMap.get("count")+"");
			if(rowTotal > 0){
				list = agentProfitService.listAgentUpProfits(agentProfitVO);
			}
		}else if(3 == agentProfitVO.getProfitsType()){
			//快捷
			countMap = agentProfitService.countAgentFastProfits(agentProfitVO);
			rowTotal = Integer.valueOf(countMap.get("count")+"");
			if(rowTotal > 0){
				list = agentProfitService.listAgentFastProfits(agentProfitVO);
			}
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("agentProfits", agentProfitVO);
		model.addAttribute("start", start);
		model.addAttribute("countMap", countMap);
		
		return "agentProfits/list";
	}

}

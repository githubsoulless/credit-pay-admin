package net.chrone.creditpay.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.AgentLevel;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.LevelDTO;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.AgentLevelService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.Constants;

/**
 * 
 * Title: LevelController Description: 用户等级设置
 * 
 * @author huoliang
 * @data 2017年11月22日 上午9:47:08
 *
 */

@RequestMapping("level")
@Controller
public class LevelController {

	@Autowired
	private LevelService levelService;
	@Autowired
	private LogConstant logConstant;
	@Autowired
	private AgentLevelService agentLevelService;
	
	@RequestMapping("list")
	public String list(Model model) {
		List<Level> list = levelService.listLevel();
		List<AgentLevel> agentLevelList = agentLevelService.getAgentLevelAll();
		model.addAttribute("list", list);
		model.addAttribute("agentLevelList", agentLevelList);
		return "level/list";
	}

	@RequestMapping("update")
	public String update(LevelDTO levelDTO, Model model, HttpServletRequest request) {
		writeLog(request, levelDTO);
		levelService.updateLevels(levelDTO.getLevels());
		agentLevelService.updateLevels(levelDTO.getAgentLevels());
		List<Level> list = levelService.listLevel();
		List<AgentLevel> agentLevelList = agentLevelService.getAgentLevelAll();
		model.addAttribute("list", list);
		model.addAttribute("agentLevelList", agentLevelList);
		model.addAttribute("message", "success");
		return "level/list";
	}
	
	public void writeLog(HttpServletRequest request, LevelDTO levelDTO){
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		List<Level> list = levelService.listLevel();
		for(Level level : list){
			for(Level uLevel : levelDTO.getLevels()){
				if(level.getLevelId().equals(uLevel.getLevelId())){
					logConstant.createTweblog(userInfSeesion.getLoginId(), "用户等级设置修改：原数据为：" + JSON.toJSONString(level) + " ，修改后的数据为：" + JSON.toJSONString(uLevel), 1, request);
				}
			}
		}
	}
	
}

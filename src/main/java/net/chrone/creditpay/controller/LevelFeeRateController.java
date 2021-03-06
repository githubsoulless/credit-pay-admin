package net.chrone.creditpay.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.AgentFeeRate;
import net.chrone.creditpay.model.AgentLevel;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.LevelDTO;
import net.chrone.creditpay.model.LevelFeeRate;
import net.chrone.creditpay.model.LevelFeeRateDTO;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.model.SysParam;
import net.chrone.creditpay.service.AgentFeeRateService;
import net.chrone.creditpay.service.AgentLevelService;
import net.chrone.creditpay.service.LevelFeeRateService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.service.SysParamService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.AmountUtil;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;


/**
 * 用户等级费率设置设置
 * @author huoliang
 *
 */
@RequestMapping("levelFeeRate")
@Controller
public class LevelFeeRateController {

	@Autowired
	private LevelService levelService;
	@Autowired
	private LevelFeeRateService levelFeeRateService;
	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private LogConstant logConstant;
	@Autowired
	private AgentFeeRateService agentFeeRateService;
	@Autowired
	private PayChannelService payChannelService;
	@Autowired
	private AgentLevelService agentLevelService;

	@RequestMapping("list")
	public String list(Model model) {
		List<Level> list = levelService.listLevel();
		getLeves(list);
		
		List<PayChannel> payChannels = payChannelService.listAllPayChannel();
		for(int i=payChannels.size()-1;i>=0;i--) {//除去禁用的,不是4快捷 7扫码的通道
			if(payChannels.get(i).getStatus() !=1 || !(payChannels.get(i).getChnlType() ==4 || payChannels.get(i).getChnlType() ==7)) {
				payChannels.remove(i);
			}
		}
		
		model.addAttribute("payChannels", payChannels);
		model.addAttribute("list", list);
		return "levelFeeRate/list";
	}
	
	/**
	 * 为层级增加费率还有 增加 代理层级
	 * @param list
	 */
	private void getLeves(List<Level> list) {
		SysParam sysParam = sysParamService.getSysParam("plan_df_fee");
		for(Level level : list) {
			if(null != sysParam) {
				level.setPlan_df_fee(new BigDecimal(AmountUtil.parseAmountStr2Long(sysParam.getValue())));
			}
			List<LevelFeeRate> listLevelFeeRate = levelFeeRateService.listLevelFeeRate(level.getLevelId());
			level.setListLevelFeeRate(listLevelFeeRate);
		}
		//一级代理
		List<AgentLevel> agentLevelList = agentLevelService.getAgentLevelAll();
		for(AgentLevel agentLevel:agentLevelList){
			list.add(getAgentLevelFeeRate(24-agentLevel.getLevelId(), sysParam,agentLevel.getLevelName()));
		}
	}
	
	private Level getAgentLevelFeeRate(int levelId,SysParam sysParam,String levelName){
		Level level = new Level();
		level.setLevelId(levelId);
		level.setLevelType(levelId);
		if(StringUtils.isNotEmpty(levelName)) {
			level.setLevelName(levelName);
		}else {
			if(levelId==1){
				level.setLevelName("一级代理");
			}else if(levelId==2){
				level.setLevelName("二级代理");
			}else if(levelId==3){
				level.setLevelName("三级代理");
			}
		}
		if(null != sysParam) {
			level.setPlan_df_fee(new BigDecimal(AmountUtil.parseAmountStr2Long(sysParam.getValue()))); 
		}
		List<AgentFeeRate> listLevelFeeRate = agentFeeRateService.listLevelFeeRate(levelId);
		//完美还款费率在费率列表中
		level.setListLevelFeeRate(listLevelFeeRate);
		return level;
	}

	@RequestMapping("update")
	public String update(LevelFeeRateDTO levelFeeRateDTO, String type, Model model, HttpServletRequest request) {
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		System.out.println("LevelFeeRateDTO:"+JSON.toJSONString(levelFeeRateDTO));
		String message = "";
		try {
			if("update".equals(type)) {
				levelFeeRateDTO.getLevel().setRecUpdUsr(userInfSeesion.getLoginId());
				if(levelFeeRateDTO.getLevel().getLevelType()==0){
					levelService.updateLevelFeeRate(levelFeeRateDTO);
				}else{
					agentFeeRateService.updateLevelFeeRate(levelFeeRateDTO);
				}
				List<Level> list = levelService.listLevel();
				getLeves(list);
				model.addAttribute("list", list);
				message = "success";
			}else {
				Level levelInfo = null;
				if(levelFeeRateDTO.getLevel().getLevelType()==0){
					levelInfo = levelService.getLevelByLevelId(levelFeeRateDTO.getLevel().getLevelId());
					levelInfo.setListLevelFeeRate(levelFeeRateService.listLevelFeeRate(levelFeeRateDTO.getLevel().getLevelId()));
					levelInfo.setFeeRate(levelInfo.getFeeRate()*100);
					SysParam sysParam = sysParamService.getSysParam("plan_df_fee");
					if(null != sysParam) {
						levelInfo.setPlan_df_fee(new BigDecimal(AmountUtil.parseAmountStr2Long(sysParam.getValue())));
					}
				}else{
					levelInfo =getAgentLevelFeeRate(levelFeeRateDTO.getLevel().getLevelId(), null,null);
				}
				List<PayChannel> payChannels = payChannelService.listAllPayChannel();
				for(int i=payChannels.size()-1;i>=0;i--) {//除去禁用的,不是4快捷 7扫码的通道
					if(payChannels.get(i).getStatus() !=1 || !(payChannels.get(i).getChnlType() ==4 || payChannels.get(i).getChnlType() ==7)) {
						payChannels.remove(i);
					}
				}
				
				model.addAttribute("payChannels", payChannels);
				model.addAttribute("level", levelInfo);
			}
			
		} catch (CHException e) {
			message = e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "levelFeeRate/update";
	}
	
//	public void writeLog(HttpServletRequest request, Level level){
//		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
//		List<Level> list = levelService.listLevel();
//		for(Level level : list){
//			for(Level uLevel : levelDTO.getLevels()){
//				if(level.getLevelId().equals(uLevel.getLevelId())){
//					logConstant.createTweblog(userInfSeesion.getLoginId(), "用户等级设置修改：原数据为：" + JSON.toJSONString(level) + " ，修改后的数据为：" + JSON.toJSONString(uLevel), 1, request);
//				}
//			}
//		}
//	}
	
}

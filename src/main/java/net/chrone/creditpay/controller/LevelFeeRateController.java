package net.chrone.creditpay.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.LevelDTO;
import net.chrone.creditpay.model.LevelFeeRate;
import net.chrone.creditpay.model.LevelFeeRateDTO;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.SysParam;
import net.chrone.creditpay.service.LevelFeeRateService;
import net.chrone.creditpay.service.LevelService;
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

	@RequestMapping("list")
	public String list(Model model) {
		List<Level> list = levelService.listLevel();
		getLeves(list);
		model.addAttribute("list", list);
		return "levelFeeRate/list";
	}
	
	public void getLeves(List<Level> list) {
		List<LevelFeeRate> listLevelFeeRate;
		SysParam sysParam;
		for(Level level : list) {
			sysParam = sysParamService.getSysParam("plan_df_fee");
			if(null != sysParam) {
				level.setPlan_df_fee(new BigDecimal(AmountUtil.parseAmountStr2Long(sysParam.getValue())));
			}
			listLevelFeeRate = levelFeeRateService.listLevelFeeRate(level.getLevelId());
			level.setListLevelFeeRate(listLevelFeeRate);
		}
	}

	@RequestMapping("update")
	public String update(LevelFeeRateDTO levelFeeRateDTO, String type, Model model, HttpServletRequest request) {
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
//		writeLog(request, level);
		String message = "";
		try {
			if("update".equals(type)) {
				levelFeeRateDTO.getLevel().setRecUpdUsr(userInfSeesion.getLoginId());
				levelService.updateLevelFeeRate(levelFeeRateDTO);
				List<Level> list = levelService.listLevel();
				getLeves(list);
				model.addAttribute("list", list);
				message = "success";
			}else {
				Level levelInfo = levelService.getLevelByLevelId(levelFeeRateDTO.getLevel().getLevelId());
				levelInfo.setListLevelFeeRate(levelFeeRateService.listLevelFeeRate(levelFeeRateDTO.getLevel().getLevelId()));
				levelInfo.setFeeRate(levelInfo.getFeeRate()*100);
				SysParam sysParam = sysParamService.getSysParam("plan_df_fee");
				if(null != sysParam) {
					levelInfo.setPlan_df_fee(new BigDecimal(AmountUtil.parseAmountStr2Long(sysParam.getValue())));
				}
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

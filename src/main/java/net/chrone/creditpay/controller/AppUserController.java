package net.chrone.creditpay.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.service.PmsBankInfService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.MyPage;


/**
 * 
 * Title: AppUserController 
 * Description: APP用户管理 
 * @author huoliang
 * @data 2017年11月24日 上午9:43:16
 *
 */
@Controller
@RequestMapping("appUser")
public class AppUserController {

	@Autowired
	private AppUserService appUserService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private PmsBankInfService pmsBankInfService;
	@Autowired
	private LogConstant logConstant;

	@RequestMapping("list")
	public String list(AppUser appuser, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		appuser.setStartRow(starIndex);

		int rowTotal = appUserService.getAppUserByPageCount(appuser);
		List<AppUser> list = new ArrayList<AppUser>();
		if (rowTotal > 0) {
			list = appUserService.getAppUserByPage(appuser);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("rowTotal", rowTotal);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("appuser", appuser);
		model.addAttribute("levelList", levelService.getLevelAll());
		List<Agent> agentList = agentService.getAgentAll();
		model.addAttribute("agentList", agentList);
		model.addAttribute("agentListJson", JSON.toJSONString(agentList));
		return "appUser/list";
	}

	@RequestMapping("detail")
	public String detail(AppUser appuser, Model model) {
		appuser = appUserService.getAppUserByUserId(appuser.getUserId());
		if (StringUtils.isNotEmpty(appuser.getPmsBankNo())) {
			appuser.setPmsBankName(pmsBankInfService.find(appuser.getPmsBankNo()).getFullName());
		}
		model.addAttribute("levelList", levelService.getLevelAll());
		model.addAttribute("agentList", agentService.getAgentAll());
		model.addAttribute("appuser", appuser);
		return "appUser/detail";
	}

	@RequestMapping("update")
	public String update(AppUser appuser, String type, Model model, HttpServletRequest request) {
		String message = "";
		try {
			if ("update".equals(type)) {
				MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
				writeLog(appuser, request);
				appuser.setRecUpdTs(new Date());
				appuser.setRecUpdUsr(userInfSeesion.getLoginId());
				appUserService.update(appuser);
				message = "success";
			} else {
				appuser = appUserService.getAppUserByUserId(appuser.getUserId());
				if (StringUtils.isNotEmpty(appuser.getPmsBankNo())) {
					appuser.setPmsBankName(pmsBankInfService.find(appuser.getPmsBankNo()).getFullName());
				}
				model.addAttribute("levelList", levelService.getLevelAll());
				model.addAttribute("agentList", agentService.getAgentAll());
				model.addAttribute("appuser", appuser);
			}
		} catch (Exception e) {
			message = "系统异常";
			e.printStackTrace();
		}
		model.addAttribute("message", message);
		return "appUser/update";
	}

	public void writeLog(AppUser appuser, HttpServletRequest request) {
		MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
		String loginId = userInfSeesion.getLoginId();
		AppUser oAppuser = appUserService.getAppUserByUserId(appuser.getUserId());
		// 基本信息修改
		if (!oAppuser.getAccountName().equals(appuser.getAccountName())
				|| !oAppuser.getCertNo().equals(appuser.getCertNo())) {
			logConstant.createTweblog(loginId, "账号：" + appuser.getUserId() +"，基本信息修改", 3, request);
		}
		// 用户等级调整
		if (!oAppuser.getLevelId().equals(appuser.getLevelId())) {
			logConstant.createTweblog(loginId, "账号：" + appuser.getUserId() +"，等级由" + 
						levelService.getLevelByLevelId(oAppuser.getLevelId()).getLevelName() + "调整为" 
						+ levelService.getLevelByLevelId(appuser.getLevelId()).getLevelName(), 4, request);
		}
//		// 推荐人修改
//		if (!oAppuser.getParentUserId().equals(appuser.getParentUserId())) {
//			logConstant.createTweblog(loginId, "账号：" + appuser.getUserId() +"，推荐人由" + 
//					oAppuser.getUserId() + "调整为" + appuser.getUserId(), 3, request);
//		}

		// 所属代理修改
//		if (!oAppuser.getAgentId().equals(appuser.getAgentId())) {
//			logConstant.createTweblog(loginId, "账号：" + appuser.getUserId() +"，所属代理由" + 
//					agentService.getAgentBygAentId(oAppuser.getAgentId()).getAgentName() + "，调整为" + agentService.getAgentBygAentId(appuser.getAgentId()).getAgentName(), 5, request);
//		}
	}
	
	/**
	 * 直属下级用户查询
	 * @param request
	 * @param model
	 * @param appuser
	 * @param start
	 * @return
	 */
	@RequestMapping("directUserList")
	public String directUserList(HttpServletRequest request, Model model, AppUser appuser, String start){
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		appuser.setStartRow(starIndex);
		String userId = appuser.getParentUserId();
		int rowTotal = appUserService.getAppUserByPageCount(appuser);
		List<AppUser> list = new ArrayList<AppUser>();
		if (rowTotal > 0) {
			list = appUserService.getAppUserByPage(appuser);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		
		appuser.setMerName(appUserService.getAppUserByUserId(userId).getMerName());
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("appuser", appuser);
		model.addAttribute("levelList", levelService.getLevelAll());
		List<Agent> agentList = agentService.getAgentAll();
		model.addAttribute("agentList", agentList);
		model.addAttribute("agentListJson", JSON.toJSONString(agentList));
		return "appUser/directUserList";
	}
	
	/**
	 * 所有下级用户查询
	 * @param request
	 * @param model
	 * @param appuser
	 * @param start
	 * @return
	 */
	@RequestMapping("subUserList")
	public String subUserList(HttpServletRequest request, Model model, AppUser appuser, String start){
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		appuser.setStartRow(starIndex);
		String userId = appuser.getParentUserId();
		List<AppUser> list = new ArrayList<AppUser>();
		
		int rowTotal = appUserService.countSubAppUser(appuser);
		if (rowTotal > 0) {
			appuser.setParentUserId(userId);
			list = appUserService.listSubAppUser(appuser);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		
		appuser.setMerName(appUserService.getAppUserByUserId(userId).getMerName());
		appuser.setParentUserId(userId);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("appuser", appuser);
		model.addAttribute("levelList", levelService.getLevelAll());
		List<Agent> agentList = agentService.getAgentAll();
		model.addAttribute("agentList", agentList);
		model.addAttribute("agentListJson", JSON.toJSONString(agentList));
		return "appUser/subUserList";
	}
	
	
	@RequestMapping("/img")
	public void downimg(String fileName, String type, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fileName)) {
				return;
			}
//			String fileName = "";
//			AppUser appUser = appUserService.getAppUserByUserId(userId);
//			if (null == appUser) {
//				return ;
//			}
//			//身份证正面
//			if("1".equals(type)) {
//				fileName = appUser.getCertCorrect();
//			}
//			//身份证背面
//			if("2".equals(type)) {
//				fileName = appUser.getCertOpposite();
//			}
//			//手持合照
//			if("3".equals(type)) {
//				fileName = appUser.getCertMeet();
//			}
			File file = new File(fileName);
			FileInputStream inputStream = new FileInputStream(file);
			int i = inputStream.available(); // 得到文件大小
			byte data[] = new byte[i];
			inputStream.read(data);
			response.setContentType("image/*");
			// byte[] data = new byte[(int) file.length()];
			OutputStream stream = response.getOutputStream();
			stream.write(data);
			stream.flush();
			stream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@RequestMapping("getUsersForMsg")
	@ResponseBody
	public List<AppUser> getUsersForMsg(String userId){
		List<AppUser> appUsers = new ArrayList<>();
		if(StringUtils.isEmpty(userId)) {
			return appUsers;
		}
		appUsers = appUserService.getUserLikeUserId(userId);
		return appUsers;
	}
	

}

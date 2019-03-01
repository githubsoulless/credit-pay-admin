package net.chrone.creditpay.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.UserTree;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.service.LevelService;
import net.chrone.creditpay.service.PmsBankInfService;
import net.chrone.creditpay.service.UserTreeService;
import net.chrone.creditpay.service.impl.LogConstant;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.ExcelUtil;
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
	@Autowired
	private UserTreeService userTreeService;
	@Autowired
	private AppUserMapper appUserMapper;
	private static Logger logger = Logger.getLogger(AppUserController.class);
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
				AppUser descAppUser = appUserService.getAppUserByUserId(appuser.getUserId());
				if(!descAppUser.getLoginId().equals(appuser.getLoginId())) {//更改了登录信息
					AppUser loginAppUser = appUserService.getAppUserByLoginId(appuser.getLoginId());
					if(loginAppUser!=null) {
						message = "登录ID已被占用";
						model.addAttribute("message", message);
						return "appUser/update";
					}
					AppUser userIdAppUser = appUserService.getAppUserByUserId(appuser.getLoginId());
					if(userIdAppUser!=null) {
						message = "登录ID已被占用";
						model.addAttribute("message", message);
						return "appUser/update";
					}
				}
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
		appuser.setPid(userId);
		appuser.setParentUserId(null);
		int rowTotal = appUserService.countSubAppUser(appuser);
		if (rowTotal > 0) {
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
	
	/**
	 * 生成用户关系
	 */
	@RequestMapping("genUserTree")
	public void genUserTree() {
		List<AppUser> list = appUserService.getAppUserAll();
		for(AppUser user:list) {
			List<UserTree> treeList = new ArrayList<>();
			List<String> zjuserList = getZJUserIdsBypUserId(user.getUserId());
			for(String zuser:zjuserList) {
				UserTree tree = new UserTree();
				tree.setUserId(user.getUserId());
				tree.setLowUserId(zuser);
				tree.setType(0);//直接
				treeList.add(tree);
			}
			if(!treeList.isEmpty()) {
				userTreeService.batchAdd(treeList);
				treeList = new ArrayList<>();
			}
			List<String> jjuserList = getJJUserIdsBypUserId(user.getUserId());
			for(String zuser:jjuserList) {
				UserTree tree = new UserTree();
				tree.setUserId(user.getUserId());
				tree.setLowUserId(zuser);
				tree.setType(1);//间接
				treeList.add(tree);
			}
			if(!treeList.isEmpty()) {
				userTreeService.batchAdd(treeList);
				treeList = new ArrayList<>();
			}
		}
	}
	
	

	 /**
    * 导出到excel -xls
    * @param request
    * @param response
    * @param withdrawaLog
    * @param model
    */
   @RequestMapping("/exportExcel")
   public void exportExcel(HttpServletRequest request, HttpServletResponse response, AppUser appUser, Model model) {
   		
	   	long currentTimeMillis = System.currentTimeMillis();
   		int rowTotal = appUserService.getAppUserByPageCount(appUser);
		List list = new ArrayList();
		 try {
			
           String[] titleNms = { "用户帐号","登录帐号", "真实姓名","昵称", "等级", "状态","实名认证", "注册时间", "最后登录时间", "信用卡数量","直接下级用户","所有下级用户","推荐人","所属直接代理","剩余体验计划次数"};
           String[] columMethodNms = { "getUserId","getLoginId","getAccountName", "getMerName", "getLevelNameFormat", "getStatusFormat","getCertStatusFormat","getRowCrtTs","getLastLoginTs","getCardNum","getDirectCount","getSubUserCount","getParentUserId","getAgentNameFormat","getTyCount"};
           Workbook workbook = ExcelUtil.createExcel(2007, "用户注册明细", titleNms, columMethodNms, list );
           
           int rowNum=Constants.MAX_EXPORT_NUM;
           if(rowTotal>0){
               for(int i=0;i<rowTotal;i+=rowNum){
            	   appUser.setStartRow(i);
            	   appUser.setPageSize(rowNum);
                   list = appUserService.getAppUserByPage(appUser);
                   formatList(list);
                   workbook = ExcelUtil.addDataToExcel(workbook, "用户注册明细", titleNms, columMethodNms, list, i);
               }
           }
           
           String fileName = "用户注册明细"+DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
   		ExcelUtil.workbook2InputStream(response, workbook, fileName, ".xlsx");
   		long l = System.currentTimeMillis() - currentTimeMillis;
   		 logger.info("-------------------------导出用时"+l+"毫秒");
       } catch (Exception e) {
       	e.printStackTrace();
           logger.error("导出文件异常",e);
       } 
   }
   
	 private void formatList(List list) {
			List<Level> levelList = levelService.getLevelAll();
			List<Agent> agentList = agentService.getAgentAll();
	    	List<AppUser> appUsers = (List<AppUser>)list;
	    	if(list != null) {
	    		for(AppUser appUser:appUsers) {
	    		
	    			for(Level level:levelList) {
	    				if(appUser.getLevelId() ==  level.getLevelId()) {
	    					appUser.setLevelNameFormat(level.getLevelName());
	    					break;
	    				}
	    			}
	    			for(Agent agent:agentList) {
	    				if(appUser.getAgentId().equals(agent.getAgentId())) {
	    					appUser.setAgentNameFormat(agent.getAgentName()+"("+agent.getAgentId()+")");
	    					break;
	    				}
	    			}
	    			if(appUser.getStatus() == 0) {
	    				appUser.setStatusFormat("正常");
	    			}
	    			else if(appUser.getStatus() == 1) {
	    				appUser.setStatusFormat("禁用");
	    			}
	    			
	    			if(appUser.getCertStatus() == 0) {
	    				appUser.setStatusFormat("未认证");
	    			}else if(appUser.getCertStatus() == 1) {
	    				appUser.setStatusFormat("己认证");
	    			}else if(appUser.getCertStatus() == 2) {
	    				appUser.setStatusFormat("认证失败");
	    			}
	    		}
	    	}
	    }
	
	
	/**
	 * 直推
	 * @param userId
	 * @return
	 */
	private List<String> getZJUserIdsBypUserId(String userId) {
		List<String> userIdList = new ArrayList<String>();
		List<String> allIdList = new ArrayList<String>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(userId);
		idMap.put("ids", ids);
		userIdList = appUserMapper.getAppUserByParentIdList(idMap);
		allIdList.addAll(userIdList);
		return allIdList;
	}
	
	/**
	 * 间接
	 * @param userId
	 * @return
	 */
	private List<String> getJJUserIdsBypUserId(String userId) {
		List<String> userIdList = new ArrayList<String>();
		List<String> allIdList = new ArrayList<String>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(userId);
		idMap.put("ids", ids);
		userIdList = appUserMapper.getAppUserByParentIdList(idMap);
        while (!userIdList.isEmpty()) {
            idMap.clear();
            idMap.put("ids", userIdList);
            userIdList = appUserMapper.getAppUserByParentIdList(idMap);
            allIdList.addAll(userIdList);
        }
        return allIdList;
	}
	
	
	
	

}

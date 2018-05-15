package net.chrone.creditpay.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.RoleInf;
import net.chrone.creditpay.service.MgrUserService;
import net.chrone.creditpay.service.RoleInfService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;

/**
 * 管理员
 * @author aojiong
 *
 */
@Controller
@RequestMapping("mgrUser")
public class MgrUserController {
	@Autowired
	private MgrUserService userInfService;
	@Autowired
	private RoleInfService roleInfService;

	/**
	 * 管理员列表
	 * @param mgrUserInf
	 * @param start
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(MgrUser mgrUserInf,String start,Model model){
		int starIndex = StringUtils.isEmpty(start)?0:Integer.valueOf(start);
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(mgrUserInf.getLoginId())){
			map.put("loginId", mgrUserInf.getLoginId());
		}
		if(StringUtils.isNotEmpty(mgrUserInf.getUserName())){
			map.put("userName", mgrUserInf.getUserName());
		}
		if(StringUtils.isNotEmpty(mgrUserInf.getUserSt())){
			map.put("userSt", mgrUserInf.getUserSt());
		}
		map.put("startRow", starIndex);
		map.put("pageSize", Constants.SHOW_NUM);
		int rowTotal = userInfService.getMgrUserByPageCount(map);
		List<MgrUser>  list =new ArrayList<MgrUser>();
		if(rowTotal>0){
			list = userInfService.getMgrUserByPage(map);
		}
		
		MyPage page=new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("start",starIndex);
		model.addAttribute("mgrUserInf",mgrUserInf);
		return "systemMgr/mgrUser"; 
	}
	
	/**
	 * 用户添加
	 * @return
	 */
	@RequestMapping("add")
	public String add(HttpServletRequest request,String type,MgrUser mgrUserInf,Model model){
		String message="";
		try {
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			MgrUser  userInf = null;
			if("toAdd".equals(type)){
				List<RoleInf> roleList = roleInfService.getRoleInfAll();
				model.addAttribute("roleList", roleList);
			}else{
				if(StringUtils.isEmpty(mgrUserInf.getLoginId())
						||StringUtils.isEmpty(mgrUserInf.getUserName())
						||StringUtils.isEmpty(mgrUserInf.getLoginPwd())
						||StringUtils.isEmpty(mgrUserInf.getRoleId())
						){
					throw new CHException("参数错误");
				}
				userInf = userInfService.getMgrUserByLoginId(mgrUserInf.getLoginId());
				if(userInf!=null){
					throw new CHException("用户已存在");
				}

				MgrUser user = new MgrUser();
				user.setLoginId(mgrUserInf.getLoginId());
				user.setUserName(mgrUserInf.getUserName());
				user.setMobileNo(StringUtils.isEmpty(mgrUserInf.getMobileNo())?null:mgrUserInf.getMobileNo());
				user.setRoleId(mgrUserInf.getRoleId());
				user.setEmail(StringUtils.isEmpty(mgrUserInf.getEmail())?null:mgrUserInf.getEmail());
				user.setLoginPwd(DigestUtils.md5Hex(mgrUserInf.getLoginPwd()));
				user.setRowCrtTs(new Date());
				user.setRowCrtUsr(userInfSeesion.getLoginId());
				user.setUserSt("1");
				if(userInfService.addMgrUser(user)==1){
					message="success";
				}else{
					message="系统异常";
				}
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			model.addAttribute("message", message);
		}
		return "systemMgr/mgrUser_add";
	}
	
	/**
	 * 校验登录ID是否已经存在
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("check_loginId")
	public void checkLoginId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String message="false";
		try {
			String loginId = request.getParameter("loginId");
			if(StringUtils.isEmpty(loginId)){
				throw new CHException("参数为空");
			}
			MgrUser userInf = userInfService.getMgrUserByLoginId(loginId);
			if(userInf!=null&&loginId.equals(userInf.getLoginId())){
				throw new CHException("账户登录名已存在,请重新输入");
			}
			message="true";
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error(e.getErrInfo());
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
		
	}
	
	/**
	 * 用户修改
	 * @return
	 */
	@RequestMapping("update")
	public String update(HttpServletRequest request,String type,MgrUser mgrUserInf,Model model){
		String message="";
		try {
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			MgrUser  userInf = null;
			if("toUpdate".equals(type)&&StringUtils.isNotEmpty(mgrUserInf.getLoginId())){
				userInf = userInfService.getMgrUserByLoginId(mgrUserInf.getLoginId());
				if(userInf==null){
					message="用户不存在";
				}
				List<RoleInf> roleList = roleInfService.getRoleInfAll();
				model.addAttribute("message", message);
				model.addAttribute("mgrUserInf", userInf);
				model.addAttribute("roleList", roleList);
			}else{
				if(StringUtils.isEmpty(mgrUserInf.getLoginId())
						||StringUtils.isEmpty(mgrUserInf.getUserName())
						||StringUtils.isEmpty(mgrUserInf.getMobileNo())
						||StringUtils.isEmpty(mgrUserInf.getRoleId())
					){
					message="参数错误";
				}
				MgrUser user = new MgrUser();
				user.setLoginId(mgrUserInf.getLoginId());
				user.setUserName(mgrUserInf.getUserName());
				if(StringUtils.isNotEmpty(mgrUserInf.getMobileNo())){
					user.setMobileNo(mgrUserInf.getMobileNo());
				}
				user.setRoleId(mgrUserInf.getRoleId());
				if(StringUtils.isNotEmpty(mgrUserInf.getEmail())){
					user.setEmail(mgrUserInf.getEmail());
				}
				if(StringUtils.isNotEmpty(mgrUserInf.getLoginPwd())){
					user.setLoginPwd(DigestUtils.md5Hex(mgrUserInf.getLoginPwd()));
				}
				user.setRecUpdTs(new Date());
				user.setRecUpdUsr(userInfSeesion.getLoginId());
				if(userInfService.updateMgrUser(user)==1){
					message="success";
				}else{
					message="系统异常";
				}
			}
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			model.addAttribute("message", message);
		}
		return "systemMgr/mgrUser_update";
	}
	
	/**
	 * 解冻用户
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("unfreeze")
	public void unfreeze(HttpServletRequest request,HttpServletResponse response,String loginId) throws Exception{
		String message="";
		try {
			if(StringUtils.isEmpty(loginId)){
				throw new CHException("参数异常");
			}
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			MgrUser mgrUserInf = userInfService.getMgrUserByLoginId(loginId);
			if(mgrUserInf==null){
				throw new CHException("用户不存在");
			}
			if(!"2".equals(mgrUserInf.getUserSt())){
				throw new CHException("用户状态不正确");
			}
			MgrUser userInf = new MgrUser();
			userInf.setLoginId(mgrUserInf.getLoginId());
			userInf.setUserSt("1");
			userInf.setRecUpdTs(new Date());
			userInf.setRecUpdUsr(userInfSeesion.getLoginId());
			if(userInfService.updateMgrUser(userInf)==1){
				message="success";
			}else{
				message="系统异常";
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}
	/**
	 * 冻结用户
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("freeze")
	public void freeze(HttpServletRequest request,HttpServletResponse response,String loginId) throws Exception{
		String message="";
		try {
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			if(StringUtils.isEmpty(loginId)){
				throw new CHException("参数异常");
			}
			MgrUser mgrUserInf = userInfService.getMgrUserByLoginId(loginId);
			if(mgrUserInf==null){
				throw new CHException("用户不存在");
			}
			if(!"1".equals(mgrUserInf.getUserSt())){
				throw new CHException("用户状态不正确");
			}
			MgrUser userInf = new MgrUser();
			userInf.setLoginId(mgrUserInf.getLoginId());
			userInf.setUserSt("2");
			userInf.setRecUpdTs(new Date());
			userInf.setRecUpdUsr(userInfSeesion.getLoginId());
			if(userInfService.updateMgrUser(userInf)==1){
				message="success";
			}else{
				message="系统异常";
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}
	
	/**
	 * 停用用户
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("disable")
	public void disable(HttpServletRequest request,HttpServletResponse response,String loginId) throws Exception{
		String message="";
		try {
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			if(StringUtils.isEmpty(loginId)){
				throw new CHException("参数异常");
			}
			MgrUser mgrUserInf = userInfService.getMgrUserByLoginId(loginId);
			if(mgrUserInf==null){
				throw new CHException("用户不存在");
			}
			MgrUser userInf = new MgrUser();
			userInf.setLoginId(mgrUserInf.getLoginId());
			userInf.setUserSt("3");
			userInf.setRecUpdTs(new Date());
			userInf.setRecUpdUsr(userInfSeesion.getLoginId());
			if(userInfService.updateMgrUser(userInf)==1){
				message="success";
			}else{
				message="系统异常";
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}
	
	
}

package net.chrone.creditpay.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.MgrUserService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.ClientSysEvnUtil;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.LogWriter;

@Controller
public class LoginController {
	
	@Autowired
	private MgrUserService mgrUserService;
	
	@RequestMapping("")
	public String toLogin(){
		
		return "login";
	}
	@RequestMapping("index")
	public String index(){
		
		return "index";
	}
	
	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @param securityCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="public/login")
	public String login(HttpServletRequest request, HttpServletResponse response,
			String username,String password,String securityCode,Model model){
		
		try {
			HttpSession session = request.getSession();
			model.addAttribute("username",username);
			if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)||StringUtils.isEmpty(securityCode)){
				LogWriter.error("登录信息不正确");
				model.addAttribute("errorMessage","登录信息不正确");
				return "login";
			}
			if(session.getAttribute("Urand")==null||!session.getAttribute("Urand").toString().trim().toLowerCase().equals(securityCode.toLowerCase())){
				LogWriter.error("验证码错误");
				model.addAttribute("errorMessage","验证码错误");
				return "login";
			}
			MgrUser mgrUser = mgrUserService.getMgrUserByLoginId(username);
			if(mgrUser==null){
				LogWriter.error("登录信息不正确");
				model.addAttribute("errorMessage","登录信息不正确");
				return "login";
			}
			if(!"1".equals(mgrUser.getUserSt())){
				LogWriter.error("用户状态已锁定");
				model.addAttribute("errorMessage","用户状态已锁定");
				return "login";
			}
			if(!mgrUser.getLoginPwd().equals(DigestUtils.md5Hex(password))){//校验密码
				LogWriter.error("登录信息不正确");
				model.addAttribute("errorMessage","登录信息不正确");
				return "login";
			}
			//更新用户登录信息
			mgrUser.setLastLoginIp(ClientSysEvnUtil.getIpAddr(request));
			mgrUser.setLastLoginTs(new Date());
			mgrUser.setLoginId(username);
			mgrUserService.updateMgrUser(mgrUser);
			
			//获取用户菜单权限
			List<MenuInf> menuInf = mgrUserService.getMenuByUserRole(mgrUser.getRoleId());
			session.setAttribute(Constants.LOGIN_SESSION, mgrUser);
			session.setAttribute(Constants.LOGIN_MENU, menuInf);
			response.sendRedirect(request.getContextPath() + "/index");
			return null;
		} catch (CHException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getErrInfo());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage","系统异常");
		}
		return "login";
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping("public/loginOut")
	public String loginOut(HttpServletRequest request){
		request.getSession().setAttribute(Constants.LOGIN_SESSION, null);
		return "login";	
	}
}

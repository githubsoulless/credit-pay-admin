package net.chrone.creditpay.controller;

import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.service.MgrUserService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.LogWriter;

/**
 * 管理员密码
 * @author aojiong
 *
 */
@Controller
public class MgrPasswordController {
	
	@Autowired
	private  MgrUserService userInfService;
	
	/**
	 * 管理员密码修改
	 * @param request
	 * @param type
	 * @param oldPassWord
	 * @param newPassWord
	 * @param model
	 * @return
	 */
	@RequestMapping("/mgrPwd_update")
	public String mdy(HttpServletRequest request,String type,String oldPassWord,String newPassWord,Model model){
		String message="";
		try {
			if("toUpdate".equals(type)){
				return "systemMgr/passwordMdy";
			}
			if(StringUtils.isEmpty(type)
				||StringUtils.isEmpty(oldPassWord)
				||StringUtils.isEmpty(newPassWord)){
				throw new CHException("参数异常");
			}
			if(oldPassWord.equals(newPassWord)){
				throw new CHException("新密码不能与旧密码一致");
			}
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			if(!userInfSeesion.getLoginPwd().equals(DigestUtils.md5Hex(oldPassWord))){//校验密码
				throw new CHException("原密码错误");
			}
			MgrUser mgrUserInf = new MgrUser();
			mgrUserInf.setLoginId(userInfSeesion.getLoginId());
			mgrUserInf.setLoginPwd(DigestUtils.md5Hex(newPassWord));
			mgrUserInf.setRecUpdUsr(userInfSeesion.getLoginId());
			mgrUserInf.setRecUpdTs(new Date());
			userInfService.updateMgrUser(mgrUserInf);
			message="success";
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error(e.getErrInfo());
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		}finally{
			model.addAttribute("message", message);
		}
		return "systemMgr/passwordMdy";
	}
	
	/**
	 * 校验登录密码
	 */
	@RequestMapping("/checkLoginPwd")
	public void checkPwd(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String message="";
		try {
			String oldPassWord = request.getParameter("oldPassWord");
			if(StringUtils.isEmpty(oldPassWord)){
				throw new CHException("参数为空");
			}
			MgrUser userInfSeesion = (MgrUser) request.getSession().getAttribute(Constants.LOGIN_SESSION);
			if(!userInfSeesion.getLoginPwd().equals(DigestUtils.md5Hex(oldPassWord))){//校验密码
				throw new CHException("原密码错误");
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
}

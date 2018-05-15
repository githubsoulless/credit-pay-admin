package net.chrone.creditpay.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.chrone.creditpay.model.MenuInf;


/**
 * 权限
 * @author aojiong
 *
 */
public class AuthUtil {
	
	public static boolean menuIsAuth(HttpServletRequest request,String AuthCode){
		@SuppressWarnings("unchecked")
		List<MenuInf> menuInfList = (List<MenuInf>) request.getSession().getAttribute(Constants.LOGIN_MENU);
		if(menuInfList == null){
            return false;
		}
		for(MenuInf menuInf:menuInfList){
			if(menuInf==null){
				continue;
			}
			if(AuthCode.equals(menuInf.getMenuId())){
				return true;
			}
		}
		return false;
	}
	
}

package net.chrone.creditpay.interceptor;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.LogWriter;



public class AccountInterceptor extends HandlerInterceptorAdapter  {
	
	public AccountInterceptor() {

	}
	
    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     *  
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  

//    	LogWriter.info("==============执行顺序: 1、preHandle================");  
        String url=request.getServletPath().substring(1);    
        System.out.println("url:"+url);
        
        if ("".equals(url)||url.startsWith("download")){//默认权限
        	return true;  
		}
        
        if ("".equals(url)||url.startsWith("public")){//默认权限
        	return true;  
		}else{
			MgrUser sessionUser = (MgrUser)request.getSession().getAttribute(Constants.LOGIN_SESSION);
			if(sessionUser==null){
				request.getRequestDispatcher("/sessionTimeOut.jsp").forward(request, response);
				return false;
			}
			if(defaultAuth().contains(url)){
				return true;
			}
			if(!urlIsAuth(request, url)){
	            request.getRequestDispatcher("/authError.jsp").forward(request, response);  
	            return false;
	        }  
	        return true;  
		}
    }  
  
    //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
//        LogWriter.info("==============执行顺序: 2、postHandle================");  
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
//    	LogWriter.info("==============执行顺序: 3、afterCompletion================");  
    }  
    
    /**
     *默认权限
     * @return
     */
    public List<String> defaultAuth(){
    	List<String> list = new ArrayList<String>();
    	list.add("mgrUser/check_loginId");
    	list.add("index");
    	list.add("checkLoginPwd");
    	list.add("mgrPwd_update");
    	list.add("mgrRole/check_roleNm");
    	list.add("operationLog/detail");
    	list.add("mobileVersion/toAdd");
    	list.add("mobileVersion/toUpdate");
    	list.add("shareImg/toUpload");
    	list.add("appUser/getUsersForMsg");
    	list.add("appUser/img");
    	list.add("taskStatistics/realTimeTask");
    	list.add("agent/updateAllAgentUser");
    	return list;
    }
    
    /**
 	 * url是否有访问权限
 	 * @return
 	 */
 	public boolean urlIsAuth(HttpServletRequest request,String url){
 		@SuppressWarnings("unchecked")
 		List<MenuInf> menuInfList = (List<MenuInf>) request.getSession().getAttribute(Constants.LOGIN_MENU);
 		for(MenuInf menuInf:menuInfList){
 			try {
 				//同权限多路径
 				if(menuInf==null){
 					continue;
 				}
 				String[] menuUrls = menuInf.getMenuUrl().split("\\|");
 				for(int i=0;i<menuUrls.length;i++){
 					if(url.equals(menuUrls[i])){
 						return true;
 					}
 				}
 			} catch (Exception e) {
 				LogWriter.error("异常菜单:"+menuInf);
 				e.printStackTrace();
 			}
 		}
 		return false;
 	}
}

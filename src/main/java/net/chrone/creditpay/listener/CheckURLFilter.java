package net.chrone.creditpay.listener;



import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.chrone.creditpay.util.ClientSysEvnUtil;
import net.chrone.creditpay.util.LogWriter;


public class CheckURLFilter implements Filter {


	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) servletrequest;  

		     
		String urlString = req.getRequestURI();
		
		
		if(servletrequest.getCharacterEncoding() == null || !servletrequest.getCharacterEncoding().toUpperCase().equals("UTF-8")){
			servletrequest.setCharacterEncoding("UTF-8");
		}
		long bt = System.currentTimeMillis();
		if(!(urlString.contains("/static/"))){
			LogWriter.debug("RemoteAddr:"+ClientSysEvnUtil.getIpAddr(req)+" 开始执行:"+urlString);
			Map<String, String[]> map = servletrequest.getParameterMap();
			Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, String[]> p= (Entry<String, String[]>) it.next();
				Object nString = p.getKey();
				if( ((String)nString).indexOf("password") != -1 || ((String)nString).indexOf("Password") != -1)
					continue;
				String[] v = (String[]) p.getValue();
				LogWriter.debug(nString+":"+v[0]);
			}
			
		}
		filterchain.doFilter(servletrequest, servletresponse);
		if(!(urlString.contains("/static/"))){
			long et = System.currentTimeMillis();
			LogWriter.debug(urlString+"执行完毕! 耗时："+(et-bt));
		}
	}

	@Override
	public void destroy() {

	}
}

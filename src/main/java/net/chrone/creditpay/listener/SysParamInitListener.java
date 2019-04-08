package net.chrone.creditpay.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.chrone.creditpay.util.ApplicationContextKeeper;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.RefreshCacheUtil;


public class SysParamInitListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		initContext(arg0);
		LogWriter.info("=========>初始数据开始");
		RefreshCacheUtil.refreshCacheAll();
		LogWriter.info("=========>初始数据结束");
	}

	private void initContext(ServletContextEvent event) {
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		ApplicationContextKeeper.init(ctx);
	}
	
	
}

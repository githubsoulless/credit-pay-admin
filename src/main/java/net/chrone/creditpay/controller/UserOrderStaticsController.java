package net.chrone.creditpay.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.Order;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.service.OrderService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.ExcelUtil;
import net.chrone.creditpay.util.Fen2YuanUtil;
import net.chrone.creditpay.util.MyPage;

/**
 * 用户交易统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping("userOrderStatics")
public class UserOrderStaticsController {
	
	private static Logger logger = Logger.getLogger(UserOrderStaticsController.class);
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping("list")
	public String list(AppUser appuser, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		appuser.setStartRow(starIndex);

		if(StringUtils.isEmpty(appuser.getMonthStr())) {
			appuser.setMonthStr(DateUtils.getCurrentDate().substring(0, 6));
		}
		appuser.setStartDate(appuser.getMonthStr()+"01");
		appuser.setEndDate(appuser.getMonthStr()+"31");
		int rowTotal = appUserService.getUserOrderStaticsPageCount(appuser);
		List<AppUser> list = new ArrayList<AppUser>();
		if (rowTotal > 0) {
			list = appUserService.getUserOrderStaticsByPage(appuser);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("rowTotal", rowTotal);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("appuser", appuser);
		return "userOrderStatics/list";
	}
	
	 /**
     * 导出到excel -xls
     * @param request
     * @param response
     * @param withdrawaLog
     * @param model
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(AppUser appuser ,HttpServletRequest request, HttpServletResponse response,Model model) {
        
    	long currentTimeMillis = System.currentTimeMillis();

		if(StringUtils.isEmpty(appuser.getMonthStr())) {
			appuser.setMonthStr(DateUtils.getCurrentDate().substring(0, 6));
		}
		appuser.setStartDate(appuser.getMonthStr()+"01");
		appuser.setEndDate(appuser.getMonthStr()+"31");
		int rowTotal = appUserService.getUserOrderStaticsPageCount(appuser);
		List<?> list = new ArrayList();
		 try {
			
            String[] titleNms = { "用户账户","用户姓名", "注册时间","认证状态", "当月交易总额"};
            String[] columMethodNms = { "getUserId","getAccountName","getRowCrtTsFormat", "getCertStatusFormat","getSumOrderFormat"};
            Workbook workbook = ExcelUtil.createExcel(2007, "用户月交易统计", titleNms, columMethodNms, list );
            
            int rowNum=Constants.MAX_EXPORT_NUM;
            if(rowTotal>0){
                for(int i=0;i<rowTotal;i+=rowNum){
                	appuser.setStartRow(i);
                	appuser.setPageSize(rowNum);
                    list = appUserService.getUserOrderStaticsByPage(appuser);
                    formatList(list);
                    workbook = ExcelUtil.addDataToExcel(workbook, "用户月交易统计", titleNms, columMethodNms, list, i);
                }
            }
            
            String fileName = "用户"+appuser.getMonthStr()+"月交易统计";
    		ExcelUtil.workbook2InputStream(response, workbook, fileName, ".xlsx");
    		long l = System.currentTimeMillis() - currentTimeMillis;
    		 logger.info("-------------------------导出用时"+l+"毫秒");
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("导出文件异常",e);
        } 
    }
    
    private void formatList(List<?> list) {
    	List<AppUser> appUsers = (List<AppUser>)list;
    	if(list != null) {
    		for(AppUser appUser:appUsers) {
    			
    			if(appUser.getCertStatus() == 0) {
    				appUser.setCertStatusFormat("未认证");
    			}else if(appUser.getCertStatus() == 1) {
    				appUser.setCertStatusFormat("己认证");
    			}else if(appUser.getCertStatus() == 2) {
    				appUser.setCertStatusFormat("认证失败");
    			}
    			
    			if(appUser.getRowCrtTs()!=null) {
    				appUser.setRowCrtTsFormat(DateUtils.formatDate(appUser.getRowCrtTs(), "yyyy-MM-dd HH:mm:ss"));
    			}
    			
    			if(appUser.getSumOrder()!=null) {
    				appUser.setSumOrderFormat(Fen2YuanUtil.formatAmt(appUser.getSumOrder()+""));
    			}else {
    				appUser.setSumOrderFormat("0.00");
    			}
    		}
    	}
    }


}

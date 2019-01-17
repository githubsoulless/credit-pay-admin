package net.chrone.creditpay.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.model.AccountDetail;
import net.chrone.creditpay.service.AccountDetailService;
import net.chrone.creditpay.taglibs.Fen2YuanTag;
import net.chrone.creditpay.util.ConfigReader;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.ExcelUtil;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;

/**
 * 
 * Title: AccountDetailController Description: 用户钱包明细
 * 
 * @author huoliang
 * @data 2017年11月24日 上午9:42:15
 *
 */
@Controller
@RequestMapping("accountDetail")
public class AccountDetailController {

	@Autowired
	private AccountDetailService accountDetailService;
	private static Logger logger = Logger.getLogger(AccountDetailController.class);

	@RequestMapping("list")
	public String list(AccountDetail accountDetail, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		accountDetail.setStartRow(starIndex);
		if (StringUtils.isEmpty(accountDetail.getStartDate())) {
//			accountDetail.setStartDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -6), "yyyy-MM-dd"));
			accountDetail.setStartDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(accountDetail.getEndDate())) {
			accountDetail.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		accountDetail.setStartDateStr(DateUtils.formatDate(accountDetail.getStartTime(), "yyyyMMdd"));
		accountDetail.setEndDateStr(DateUtils.formatDate(accountDetail.getEndTime(), "yyyyMMdd"));
		Map<String, Object> countMap = accountDetailService.countAccountDetail(accountDetail);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<AccountDetail> list = new ArrayList<AccountDetail>();
		if (rowTotal > 0) {
			list = accountDetailService.listAccountDetailPage(accountDetail);
		}

		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("accountDetail", accountDetail);
		model.addAttribute("countMap", countMap);
		model.addAttribute("rowTotal", rowTotal);
		return "accountDetail/list";
	}
	
	   /**
     * 导出到excel -xls
     * 
     * @param request
     * @param response
     * @param withdrawaLog
     * @param model
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, AccountDetail accountDetail, Model model) {
        
    	long currentTimeMillis = System.currentTimeMillis();
    	Map<String, Object> countMap = accountDetailService.countAccountDetail(accountDetail);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List list = new ArrayList();
		 try {
			
            String[] titleNms = { "记录ID","用户帐号", "真实姓名","变动类型", "账户类型", "变动金额", "变动后余额","变动时间", "关联订单", "备注"};
            String[] columMethodNms = { "getDtlId","getUserId","getAccountName", "getFormatTransType", "getFormatBalanceType", "getFormatChangeAmt","getFormatAvailable","getRowCrtTs",
                    "getAccountLogId","getMemo" };
            Workbook workbook = ExcelUtil.createExcel(2007, "钱包订单明细", titleNms, columMethodNms, list );
            
            int rowNum=Constants.MAX_EXPORT_NUM;
            if(rowTotal>0){
                for(int i=0;i<rowTotal;i+=rowNum){
                	accountDetail.setStartRow(i);
                	accountDetail.setPageSize(rowNum);
                    list = accountDetailService.listAccountDetailPage(accountDetail);
                    formatList(list);
                    workbook = ExcelUtil.addDataToExcel(workbook, "钱包订单明细", titleNms, columMethodNms, list, i);
                }
            }
            
            String fileName = "钱包明细"+DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
    		ExcelUtil.workbook2InputStream(response, workbook, fileName, ".xlsx");
    		long l = System.currentTimeMillis() - currentTimeMillis;
    		 logger.info("-------------------------导出用时"+l+"毫秒");
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("导出文件异常",e);
        } 
        
    }

    private void formatList(List list) {
    	
    	List<AccountDetail> accountDetails = (List<AccountDetail>)list;
    	if(list != null) {
    		for(AccountDetail accountDetail:accountDetails) {
    			
    			if(accountDetail.getTransType() == 0) {
    				accountDetail.setFormatTransType("升级分润");
    			}else if(accountDetail.getTransType() == 1) {
    				accountDetail.setFormatTransType("还款推广收益");
    			}
				else if(accountDetail.getTransType() == 2) {
					accountDetail.setFormatTransType("钱包提现");
				}
				else if(accountDetail.getTransType() == 3) {
					accountDetail.setFormatTransType("其他");
				}
				else if(accountDetail.getTransType() == 4) {
					accountDetail.setFormatTransType("快捷推广收益");
				}
    			
    			if(accountDetail.getBalanceType() == 0) {
    				accountDetail.setFormatBalanceType("可用");
    			}else if(accountDetail.getBalanceType() == 1) {
    				accountDetail.setFormatBalanceType("冻结");
    			}
    			if(accountDetail.getDcFlag() ==0) {
    				accountDetail.setFormatChangeAmt("+"+Fen2YuanTag.formatAmt(accountDetail.getAvalDebit()+""));
    			}else if(accountDetail.getDcFlag() ==1) {
    				accountDetail.setFormatChangeAmt("-"+Fen2YuanTag.formatAmt(accountDetail.getAvalCredit()+""));
    			}
    			accountDetail.setFormatAvailable(Fen2YuanTag.formatAmt(accountDetail.getAvailable()+""));
    		}
    	}
    }
}

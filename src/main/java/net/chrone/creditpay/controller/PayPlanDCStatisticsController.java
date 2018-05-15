package net.chrone.creditpay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.PayPlanDCStatisticsDTO;
import net.chrone.creditpay.service.PayPlanService;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.ExcelUtil;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;

/**
 * 计划收支统计
 * @author aojiong
 * @date 2018年1月3日
 * @class PayPlanDCStatisticsController.java
 */

@Controller
@RequestMapping("payPlanDCStatistics")
public class PayPlanDCStatisticsController {
	@Autowired
	private PayPlanService payPlanService;
	
	
	@RequestMapping("list")
	public String list(PayPlanDCStatisticsDTO dcStatisticsDTO,Model model,String start){
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		dcStatisticsDTO.setStartRow(starIndex);
		if(StringUtils.isEmpty(dcStatisticsDTO.getStartDate())){
			dcStatisticsDTO.setStartDate(DateUtils.formatDate(DateUtils.getLastDays(7), "yyyy-MM-dd"));
		}
		if(StringUtils.isEmpty(dcStatisticsDTO.getEndDate())){
			dcStatisticsDTO.setEndDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		}
		Map<String,Object> countMap = payPlanService.countPayPlayDCStatistics(dcStatisticsDTO);
		int rowTotal = Integer.valueOf(countMap.get("pageCount")+"");
		List<PayPlanDCStatisticsDTO> list = new ArrayList<>();
		if(rowTotal > 0){
			list = payPlanService.pagePayPlayDCStatistics(dcStatisticsDTO);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		model.addAttribute("rowTotal", rowTotal);
		model.addAttribute("list", list);
		model.addAttribute("dcStatisticsDTO", dcStatisticsDTO);
		model.addAttribute("start", starIndex);
		model.addAttribute("page", page);
		model.addAttribute("countMap", countMap);
		return "payPlanDCStatistics/list";
	}
	
	@RequestMapping("exportExcel")
    public void exportExcel(PayPlanDCStatisticsDTO dcStatisticsDTO,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		if(StringUtils.isEmpty(dcStatisticsDTO.getStartDate())){
			dcStatisticsDTO.setStartDate(DateUtils.formatDate(DateUtils.getLastDays(7), "yyyy-MM-dd"));
		}
		if(StringUtils.isEmpty(dcStatisticsDTO.getEndDate())){
			dcStatisticsDTO.setEndDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		}
		Map<String,Object> countMap = payPlanService.countPayPlayDCStatistics(dcStatisticsDTO);
		int rowTotal = Integer.valueOf(countMap.get("pageCount")+"");
		 if(rowTotal==0){
			 response.getWriter().println("<script>alert('没有可导出的数据');</script>");
			 return;
		 }
		int pageSize = Constants.MAX_EXPORT_NUM;
		 try {
			 	long currentTimeMillis = System.currentTimeMillis();
	            String[] titleNms={"还款计划单ID","计划状态","计划创建时间","计划总金额","服务费","代付费",
	            		"优惠金额","用户分润","代理分润","服务费退款","代付费退款","服务费成本","代付费成本","计划收益"};
	            String[] columMethodNms={"getPlan_id","transStatus","transRow_crt_ts","transPlan_amt","transFee","transDf_fee",
	            		"transYhAmt","transUser_profits","transAgent_profits","transRefund_fee","transRefund_dffee","transXf_chnl_amt"
	            		,"transDf_chnl_amt","transSyAmt"};
	    		List<PayPlanDCStatisticsDTO> list = new ArrayList<>();
	            Workbook workbook = ExcelUtil.createExcel(2007, "计划收支报表", titleNms, columMethodNms, list);
	            dcStatisticsDTO.setPageSize(pageSize);
	    		if(rowTotal>0){
	    			for(int i=0;i<rowTotal;i+=pageSize){
	    				dcStatisticsDTO.setStartRow(i);
	    				list = payPlanService.pagePayPlayDCStatistics(dcStatisticsDTO);
	    				workbook = ExcelUtil.addDataToExcel(workbook, "计划收支报表", titleNms, columMethodNms, list, i);
	    			}
	    		}
	    		String fileName = "planData"+DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
	    		fileName = ExcelUtil.processFileName(request, fileName);
	    		ExcelUtil.workbook2InputStream(response, workbook, fileName, ".xlsx");
	    		long l = System.currentTimeMillis() - currentTimeMillis;
	            LogWriter.info("-------------------------导出用时"+l+"毫秒");
	        } catch (Exception e) {
	        	response.getWriter().println("<script>alert('数据导出异常');</script>");
	            e.printStackTrace();
	        }
		
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtils.formatDate(DateUtils.getLastDays(7), "yyyy-MM-dd"));
	}

}

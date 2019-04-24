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

import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.model.Order;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.service.OrderService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.taglibs.Fen2YuanTag;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.ExcelUtil;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.StringUtil;

/**
 * 
 * Title: OrderController 
 * Description: 交易订单查询 
 * @author huoliang
 * @data 2017年11月24日 上午9:43:57
 *
 */

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private PayChannelService payChannelService;
	private static Logger logger = Logger.getLogger(OrderController.class);

	@RequestMapping("list")
	public String list(Order order, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		order.setStartRow(starIndex);
		if (StringUtils.isEmpty(order.getStartDate())) {
			order.setStartDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(order.getEndDate())) {
			order.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		order.setStartDate(order.getStartDate().replaceAll("-",""));
		order.setEndDate(order.getEndDate().replaceAll("-",""));
		Map<String, Object> countMap = orderService.getOrderByPageCount(order);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<Order> list = new ArrayList<Order>();
		if (rowTotal > 0) {
			list = orderService.getOrderByPage(order);
		}
		MyPage page = new MyPage(list, starIndex, Constants.SHOW_NUM, rowTotal);
		List<PayChannel> channelList = payChannelService.listAllPayChannel();
		model.addAttribute("channelList", channelList);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("start", starIndex);
		model.addAttribute("order", order);
		model.addAttribute("countMap", countMap);
		return "order/list";
	}
	
	 /**
     * 导出到excel -xls
     * @param request
     * @param response
     * @param withdrawaLog
     * @param model
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, Order order, Model model) {
        
    	long currentTimeMillis = System.currentTimeMillis();
    	if (StringUtils.isEmpty(order.getStartDate())) {
			order.setStartDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(order.getEndDate())) {
			order.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		order.setStartDate(order.getStartDate().replaceAll("-",""));
		order.setEndDate(order.getEndDate().replaceAll("-",""));
    	
    	Map<String, Object> countMap = orderService.getOrderByPageCount(order);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List list = new ArrayList();
		 try {
			
            String[] titleNms = { "内部流水号","所属代理", "所属计划ID","所属任务ID", "订单类型", "订单金额","平台服务费", "通道手续费", "总利润", "用户利润","代理利润","平台利润","银行名称","卡号","持卡人姓名","订单状态","订单生成时间","订单支付时间","交易通道","所属用户帐号","卡编号","备注"};
            String[] columMethodNms = { "getOrderNo","getAgentName","getPlanId", "getTaskId", "getOrderTpFormat", "getAmountFormat","getPlatFeeFormat","getFeeFormat","getTotalProfitFormat","getUserProfitFormat","getAgentProfitFormat","getPlantProfitFormat","getBankNm","getCardFormat","getCardName","getPayStFormat","getCreateTime","getPayTime","getChnlName","getUserId","getCardId","getRemark"};
            Workbook workbook = ExcelUtil.createExcel(2007, "交易订单明细", titleNms, columMethodNms, list );
            
            int rowNum=Constants.MAX_EXPORT_NUM;
            if(rowTotal>0){
                for(int i=0;i<rowTotal;i+=rowNum){
                	order.setStartRow(i);
                	order.setPageSize(rowNum);
                    list = orderService.getOrderByPage(order);
                    formatList(list);
                    workbook = ExcelUtil.addDataToExcel(workbook, "交易订单明细", titleNms, columMethodNms, list, i);
                }
            }
            
            String fileName = "交易订单明细"+DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
    		ExcelUtil.workbook2InputStream(response, workbook, fileName, ".xlsx");
    		long l = System.currentTimeMillis() - currentTimeMillis;
    		 logger.info("-------------------------导出用时"+l+"毫秒");
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("导出文件异常",e);
        } 
    }
    
	 private void formatList(List list) {
	    	
	    	List<Order> orders = (List<Order>)list;
	    	if(list != null) {
	    		for(Order order:orders) {
	    			if(order.getOrderTp()==0)
	    				order.setOrderTpFormat("消费");
	    			else if(order.getOrderTp()==1)
	    				order.setOrderTpFormat("还款");
	    			else if(order.getOrderTp()==2)
	    				order.setOrderTpFormat("提现");
	    			
	    			
	    			if(order.getPaySt()==1)
	    				order.setPayStFormat("待支付");
	    			else if(order.getPaySt()==2)
	    				order.setPayStFormat("支付成功");
	    			else if(order.getPaySt()==3)
	    				order.setPayStFormat("支付失败");
	    			
	    			order.setAmountFormat(Fen2YuanTag.formatAmt(order.getAmount()+""));
	    			order.setPlatFeeFormat(Fen2YuanTag.formatAmt(order.getPlatFee()+""));
	    			order.setFeeFormat(Fen2YuanTag.formatAmt(order.getFee()+""));
	    			order.setTotalProfitFormat(Fen2YuanTag.formatAmt((order.getPlatFee() - order.getFee())+""));
	    			order.setUserProfitFormat(Fen2YuanTag.formatAmt(order.getUserProfits()+""));
	    			order.setAgentProfitFormat(Fen2YuanTag.formatAmt(order.getAgentProfits()+""));
	    			order.setPlantProfitFormat(Fen2YuanTag.formatAmt((order.getPlatFee()-order.getFee()-order.getUserProfits()-order.getAgentProfits())+""));
	    			order.setCardFormat(StringUtil.hiddenCard(order.getCardNo(), 4, 4));
	    		}
	    	}
	    }
}

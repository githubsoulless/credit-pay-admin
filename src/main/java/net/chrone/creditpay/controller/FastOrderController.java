package net.chrone.creditpay.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.chrone.creditpay.model.AccountDetail;
import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.service.FastOrderService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.taglibs.Fen2YuanTag;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.Constants;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.ExcelUtil;
import net.chrone.creditpay.util.LogWriter;
import net.chrone.creditpay.util.MyPage;
import net.chrone.creditpay.util.StringUtil;

@Controller
@RequestMapping("fastOrder")
public class FastOrderController {
	@Autowired
	private FastOrderService orderService;
	@Autowired
	private PayChannelService payChannelService;
	private static Logger logger = Logger.getLogger(FastOrderController.class);
	@RequestMapping("list")
	public String list(FastOrder order, String start, Model model) {
		int starIndex = StringUtils.isEmpty(start) ? 0 : Integer.valueOf(start);
		order.setStartRow(starIndex);
		if (StringUtils.isEmpty(order.getStartDate())) {
			order.setStartDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		if (StringUtils.isEmpty(order.getEndDate())) {
			order.setEndDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		}
		Map<String, Object> countMap = orderService.getOrderByPageCount(order);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List<FastOrder> list = new ArrayList<FastOrder>();
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
		return "fastOrder/list";
	}
	
	 /**
     * 导出到excel -xls
     * @param request
     * @param response
     * @param withdrawaLog
     * @param model
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, FastOrder order, Model model) {
        
    	long currentTimeMillis = System.currentTimeMillis();
    	Map<String, Object> countMap = orderService.getOrderByPageCount(order);
		int rowTotal = Integer.valueOf(countMap.get("count") + "");
		List list = new ArrayList();
		 try {
			
            String[] titleNms = { "订单流水号","所属代理", "用户帐号","卡编号", "订单类型", "订单金额", "交易服务费","通道手续费", "总利润", "用户分润","代理分润","平台利润","卡号","持卡人姓名","订单状态","订单生成时间","订单支付时间","关联订单号","交易通道","描述"};
            String[] columMethodNms = { "getOrderNo","getAgentName","getUserId", "getCardId", "getOrderTpFormat", "getAmountFormat","getFeeFormat","getChnlFeeFormat","getTotalProfitFormat","getUserProfitFormat","getAgentProfitFormat","getPlantProfitFormat","getCardFormat","getCardName","getPayStFormat","getCreateTime","getPayTime","getSettleOrderNo","getChnlName","getMemo"};
            Workbook workbook = ExcelUtil.createExcel(2007, "快捷交易订单明细", titleNms, columMethodNms, list );
            
            int rowNum=Constants.MAX_EXPORT_NUM;
            if(rowTotal>0){
                for(int i=0;i<rowTotal;i+=rowNum){
                	order.setStartRow(i);
                	order.setPageSize(rowNum);
                    list = orderService.getOrderByPage(order);
                    formatList(list);
                    workbook = ExcelUtil.addDataToExcel(workbook, "快捷交易订单明细", titleNms, columMethodNms, list, i);
                }
            }
            
            String fileName = "快捷交易订单明细"+DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
    		ExcelUtil.workbook2InputStream(response, workbook, fileName, ".xlsx");
    		long l = System.currentTimeMillis() - currentTimeMillis;
    		 logger.info("-------------------------导出用时"+l+"毫秒");
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("导出文件异常",e);
        } 
    }
	
	
	/**
	 * 重发代付
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "fastOrder/agentpayManual";
	}
	
	/**
	 * 重发代付
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("updateState")
	public void reSendPay(HttpServletRequest request,HttpServletResponse response,String orderNo) throws Exception{
		String message="";
		try {
			if(StringUtils.isEmpty(orderNo)){
				throw new CHException("参数异常");
			}
			int result = orderService.reSendPay(orderNo);
			if(1 == result) {
				message="success";
			}else {
				message="代付重发失败";
			}
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}
	
	/**
	 * 手动代付
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping("agentpayManual")
	public String agentpayManual(HttpServletRequest request,HttpServletResponse response,FastOrder order,Model model) throws Exception{
		String message="success";
		try {
			validate(order);
			orderService.handleAgentPayManual(order);
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} 
		model.addAttribute("message", message);
		return "fastOrder/agentpayManual";
	}
	
	/**
	 * 手动代付
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping("calcFee")
	public void calcFee(HttpServletRequest request,HttpServletResponse response,FastOrder order) throws Exception{
		String message="";
		try {
			validate(order);
			int[] fee = orderService.queryFee(order);
			message = fee[0]+","+fee[1]+","+fee[2]+","+fee[3];
		} catch (CHException e) {
			message=e.getErrInfo();
			LogWriter.error("====>"+e.getErrInfo());
			e.printStackTrace();
		} catch (Exception e) {
			message="系统异常";
			e.printStackTrace();
		} finally{
			LogWriter.error("============>"+message);
			OutputStream out =response.getOutputStream();
			out.write(message.getBytes("UTF-8"));
			out.flush();
		}
	}
	
	
	private void validate(FastOrder order) {
		
		if(StringUtils.isEmpty(order.getUserId())) {
			throw new CHException("500","参数错误userId");
		}
		if(order.getAmount() ==0) {
			throw new CHException("500","参数错误amount");
		}
		if(StringUtils.isEmpty(order.getChannel())) {
			throw new CHException("500","参数错误channel");
		}
	}
	
	 private void formatList(List list) {
	    	
	    	List<FastOrder> fastOrders = (List<FastOrder>)list;
	    	if(list != null) {
	    		for(FastOrder fastOrder:fastOrders) {
	    			if(fastOrder.getOrderTp()==0)
	    				fastOrder.setOrderTpFormat("消费");
	    			else if(fastOrder.getOrderTp()==1)
	    				fastOrder.setOrderTpFormat("结算");
	    			else if(fastOrder.getOrderTp()==2)
	    				fastOrder.setOrderTpFormat("服务费");
	    			
	    			
	    			if(fastOrder.getPaySt()==0)
	    				fastOrder.setPayStFormat("待支付");
	    			else if(fastOrder.getPaySt()==1)
	    				fastOrder.setPayStFormat("处理中");
	    			else if(fastOrder.getPaySt()==2)
	    				fastOrder.setPayStFormat("支付成功");
	    			else if(fastOrder.getPaySt()==3)
	    				fastOrder.setPayStFormat("支付失败");
	    			else if(fastOrder.getPaySt()==4)
	    				fastOrder.setPayStFormat("已重发");
	    			
	    			fastOrder.setAmountFormat(Fen2YuanTag.formatAmt(fastOrder.getAmount()+""));
	    			fastOrder.setFeeFormat(Fen2YuanTag.formatAmt(fastOrder.getFee()+""));
	    			fastOrder.setChnlFeeFormat(Fen2YuanTag.formatAmt(fastOrder.getChnlFee()+""));
	    			fastOrder.setTotalProfitFormat(Fen2YuanTag.formatAmt((fastOrder.getFee() - fastOrder.getChnlFee())+""));
	    			fastOrder.setUserProfitFormat(Fen2YuanTag.formatAmt(fastOrder.getUserProfits()+""));
	    			fastOrder.setAgentProfitFormat(Fen2YuanTag.formatAmt(fastOrder.getAgentProfits()+""));
	    			fastOrder.setPlantProfitFormat(Fen2YuanTag.formatAmt((fastOrder.getFee()-fastOrder.getChnlFee()-fastOrder.getUserProfits()-fastOrder.getAgentProfits())+""));
	    			fastOrder.setCardFormat(StringUtil.hiddenCard(fastOrder.getCardNo(), 4, 4));
	    		}
	    	}
	    }
}

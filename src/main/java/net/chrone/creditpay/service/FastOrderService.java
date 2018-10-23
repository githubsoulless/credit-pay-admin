package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.model.Order;

public interface FastOrderService {

	Map<String, Object> getOrderByPageCount(FastOrder order);

	List<FastOrder> getOrderByPage(FastOrder order);

	/**
	 * 更新订单状态
	 * @param orderNo 订单号
	 * @param srcPaySt 订单原状态
	 * @param descPaySt 订单目标状态
	 */
	void updateState(String orderNo, int srcPaySt, int descPaySt);
	
	/**
	 * 重发代付
	 * @param orderNo
	 * @return
	 */
	int reSendPay(String orderNo);
	/**
	 * 
	 * @param order
	 */
	void handleAgentPayManual(FastOrder order);
	/**
	 * 
	 * @param fastOrder
	 * @return
	 * int[0] 用户费用
	 * int[1] 通道费用
	 * int[2] 用户快捷利润
	 * int[3] 用户实际输入代付金额
	 */
	 int[] queryFee(FastOrder fastOrder);
	

}

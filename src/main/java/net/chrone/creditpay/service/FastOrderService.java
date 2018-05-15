package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.FastOrder;

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

}

package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.PayChannel;

/**
 * 
 * Title: PayChannelService 
 * Description: 渠道路由管理 
 * @author huoliang
 * @data 2017年11月24日 上午10:48:26
 *
 */
public interface PayChannelService {
	
	int countPayChannel(PayChannel payChannel);
	
	List<PayChannel> listPayChannel(PayChannel payChannel);
	
	int savePayChannel(PayChannel payChannel);
	
	int updatePayChannel(PayChannel payChannel);
	
	PayChannel getPayChannelByPrimaryKey(String id);
	
	PayChannel getPayChannelByCodeAndType(PayChannel payChannel);
	
	List<PayChannel> listAllPayChannel();

}

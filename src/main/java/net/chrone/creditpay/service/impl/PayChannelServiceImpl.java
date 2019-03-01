package net.chrone.creditpay.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.PayChannelMapper;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.model.PayChannelExample;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.util.DateUtils;

/**
 * 
 * Title: PayChannelServiceImpl Description: 通道路由管理
 * 
 * @author huoliang
 * @data 2017年11月24日 上午10:50:40
 *
 */

@Service
public class PayChannelServiceImpl implements PayChannelService {

	@Autowired
	private PayChannelMapper payChannelMapper;

	@Override
	public int countPayChannel(PayChannel payChannel) {
		return payChannelMapper.countPayChannel(payChannel);
	}

	@Override
	public List<PayChannel> listPayChannel(PayChannel payChannel) {
		return payChannelMapper.listPayChannel(payChannel);
	}

	@Override
	public int savePayChannel(PayChannel payChannel) {
		if(0 == payChannel.getFeeType()){
			payChannel.setFeeRate(payChannel.getFeeRate().divide(new BigDecimal(100)));
		}
		if(1 == payChannel.getFeeType()){
			payChannel.setFeeRate(payChannel.getFeeRate().divide(new BigDecimal(100)));
		}
		payChannel.setMinAmount(BigDecimal.valueOf(payChannel.getMinAmountDou()*100).intValue());
		payChannel.setMaxAmount(BigDecimal.valueOf(payChannel.getMaxAmountDou()*100).intValue());
		payChannel.setDaySumAmount(BigDecimal.valueOf(payChannel.getDaySumAmtDou()*100).intValue());
		if(4 == payChannel.getChnlType()) {
			payChannel.setUpperlimit(BigDecimal.valueOf(payChannel.getUpperlimit()*100).intValue());
			payChannel.setPayFeeType(1);
			payChannel.setPayFeeRate(payChannel.getPayFeeRate().multiply(new BigDecimal(100)));
		}
		try {
			if(StringUtils.isEmpty(payChannel.getStartDate())) {
				payChannel.setStartTime(DateUtils.parseDate("00:00:00", "HH:mm:ss"));
			} else{
				payChannel.setStartTime(DateUtils.parseDate(payChannel.getStartDate(), "HH:mm:ss"));
			}
			if(StringUtils.isEmpty(payChannel.getEndDate())){
				payChannel.setEndTime(DateUtils.parseDate("23:59:59", "HH:mm:ss"));
			}else{
				payChannel.setEndTime(DateUtils.parseDate(payChannel.getEndDate(), "HH:mm:ss"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return payChannelMapper.insertSelective(payChannel);
	}

	@Override
	public int updatePayChannel(PayChannel payChannel) {
		return payChannelMapper.updateByPrimaryKeySelective(payChannel);
	}

	@Override
	public PayChannel getPayChannelByPrimaryKey(String id) {
		return payChannelMapper.selectByPrimaryKey(id);
	}

	@Override
	public PayChannel getPayChannelByCodeAndType(PayChannel payChannel) {
		PayChannelExample example = new PayChannelExample();
		example.createCriteria().andCodeEqualTo(payChannel.getCode()).andChnlTypeEqualTo(payChannel.getChnlType());
	    List<PayChannel> list = payChannelMapper.selectByExample(example);
	    if(list.size() == 0){
	    	return null;
	    }
		return list.get(0);
	}

	
	@Override
	public List<PayChannel> listAllPayChannel() {
		return payChannelMapper.selectByExample(null);
	}

}

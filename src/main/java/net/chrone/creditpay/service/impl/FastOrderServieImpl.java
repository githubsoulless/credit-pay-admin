package net.chrone.creditpay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.api.FastPayApi;
import net.chrone.creditpay.mapper.FastOrderMapper;
import net.chrone.creditpay.mapper.PayChannelMapper;
import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.model.FastOrderExample;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.service.FastOrderService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.util.DateUtils;

@Service
public class FastOrderServieImpl implements FastOrderService {
	@Autowired
	private FastOrderMapper fastOrderMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private PayChannelMapper payChannelMapper;

	@Override
	public Map<String, Object> getOrderByPageCount(FastOrder order) {
		return fastOrderMapper.getOrderByPageCount(order);
	}

	@Override
	public List<FastOrder> getOrderByPage(FastOrder order) {
		return fastOrderMapper.getOrderByPage(order);
	}

	@Override
	public void updateState(String orderNo, int srcPaySt, int descPaySt) {
		FastOrder record = new FastOrder();
		record.setPaySt(descPaySt);
		FastOrderExample example = new FastOrderExample();
		example.createCriteria()
		.andOrderNoEqualTo(orderNo)
		.andPayStEqualTo(srcPaySt);
		fastOrderMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int reSendPay(String orderNo) {
		FastOrder fastOrder = fastOrderMapper.selectByPrimaryKey(orderNo);
		if(null == fastOrder || 3!= fastOrder.getPaySt()) {
			return 0;
		}
		//将原交易记录更改为已重发
		FastOrder updFastOrder = new FastOrder();
		updFastOrder.setOrderNo(fastOrder.getOrderNo());
		updFastOrder.setPaySt(4);
		fastOrderMapper.updateByPrimaryKeySelective(updFastOrder);
		
		//生成一笔新的代付记录
		FastOrder agentPayOrder = new FastOrder();
		String agentPayOrderNo = new Date().getTime() + seqService.updateAndGetSequence(SeqServiceImpl.T_FAST_ORDER, 8);
		agentPayOrder.setOrderNo(agentPayOrderNo);
		agentPayOrder.setOrderDt(DateUtils.getCurrentDate());
		agentPayOrder.setPaySt(1);
		agentPayOrder.setOrderTp(1);
		agentPayOrder.setSettleOrderNo(fastOrder.getSettleOrderNo());
		agentPayOrder.setAmount(fastOrder.getAmount());
		agentPayOrder.setBankNo(fastOrder.getBankNo());
		agentPayOrder.setCardId(fastOrder.getCardId());
		agentPayOrder.setCardName(fastOrder.getCardName());
		agentPayOrder.setCardNo(fastOrder.getCardNo());
		agentPayOrder.setChnlFee(fastOrder.getChnlFee());
		agentPayOrder.setChnlId(fastOrder.getChnlId());
		agentPayOrder.setChnlOrderNo(fastOrder.getChnlOrderNo());
		agentPayOrder.setCreateTime(new Date());
		agentPayOrder.setUserId(fastOrder.getUserId());
		agentPayOrder.setFee(fastOrder.getFee());

		PayChannel payChannel = payChannelMapper.selectByPrimaryKey(fastOrder.getChnlId());
		if(null == payChannel) {
			return 0;
		}
		Map<String, String> resultMap = new HashMap<>();
		try {
			resultMap = FastPayApi.fastPay_df(agentPayOrder, payChannel.getCode());
		} catch (Exception e) {
			agentPayOrder.setPaySt(3);
			fastOrderMapper.insertSelective(agentPayOrder);
			e.printStackTrace();
			return 0;
		}
		if(!"1".equals(resultMap.get("status"))){//消费成功
			agentPayOrder.setPaySt(3);
		}
		fastOrderMapper.insertSelective(agentPayOrder);
		return 1;
	}
	

}

package net.chrone.creditpay.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.api.FastPayApi;
import net.chrone.creditpay.mapper.AgentMapper;
import net.chrone.creditpay.mapper.FastOrderMapper;
import net.chrone.creditpay.mapper.PayChannelMapper;
import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AgentFeeRate;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.FastOrder;
import net.chrone.creditpay.model.FastOrderExample;
import net.chrone.creditpay.model.LevelFeeRate;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.service.AgentFeeRateService;
import net.chrone.creditpay.service.AgentService;
import net.chrone.creditpay.service.AppUserService;
import net.chrone.creditpay.service.CardExtService;
import net.chrone.creditpay.service.FastOrderService;
import net.chrone.creditpay.service.LevelFeeRateService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.Fen2YuanUtil;

@Service
public class FastOrderServieImpl implements FastOrderService {
	@Autowired
	private FastOrderMapper fastOrderMapper;
	@Autowired
	private SeqService seqService;
	@Autowired
	private PayChannelMapper payChannelMapper;
	@Autowired
	private CardExtService cardExtService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private PayChannelService payChannelService;
	@Autowired
	private LevelFeeRateService levelFeeRateService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentFeeRateService agentFeeRateService;
	@Autowired
	private AgentMapper agentMapper;
	

	private final static Logger log = Logger.getLogger(FastOrderServieImpl.class);
	@Override
	public Map<String, Object> getOrderByPageCount(FastOrder order) {
		if(StringUtils.isNotEmpty(order.getAgentId())) {
			String agentIds = agentMapper.getSUBAgentIdByAgentId(order.getAgentId());
			order.setAgentIds(Arrays.asList(agentIds.split("\\,")));
		}
		return fastOrderMapper.getOrderByPageCount(order);
	}

	@Override
	public List<FastOrder> getOrderByPage(FastOrder order) {
		if(StringUtils.isNotEmpty(order.getAgentId())) {
			String agentIds = agentMapper.getSUBAgentIdByAgentId(order.getAgentId());
			order.setAgentIds(Arrays.asList(agentIds.split("\\,")));
		}
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
		
		AppUser appUser = appUserService.getAppUserByUserId(fastOrder.getUserId());
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
		agentPayOrder.setUserCertNo(appUser.getCertNo());
		
		PayChannel payChannel = payChannelMapper.selectByPrimaryKey(fastOrder.getChnlId());
		if(null == payChannel) {
			return 0;
		}
		agentPayOrder.setChannel(payChannel.getCode());
		Map<String, String> resultMap = new HashMap<>();
		try {
			resultMap = FastPayApi.fastPay_df(agentPayOrder, cardExtService,payChannel.getCode());
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
	
	@Override
	public int[] queryFee(FastOrder order) {

		PayChannel queryPayChannel = new PayChannel();
		queryPayChannel.setCode(order.getChannel());
		queryPayChannel.setChnlType(4);
		
		PayChannel payChannel = payChannelService.getPayChannelByCodeAndType(queryPayChannel);
		if(payChannel != null) {
			log.debug("找到快捷通道["+payChannel.getCode()+"]");	
		}else {
			throw new CHException("500","未找到快捷通道["+order.getChannel()+"]");
		}
		
		AppUser appUser = appUserService.getAppUserByUserId(order.getUserId());
		if(appUser == null) {
			throw new CHException("500","未找到用户["+order.getUserId()+"]");
		}
		
		String settleType = "0";//结算方式  默认D0
		String integralType = "0"; //有无积分  默认无积分
		LevelFeeRate userLevelFeeRate = getUserLevelFeeRate(settleType, integralType, appUser,payChannel.getCode());
		if(null == userLevelFeeRate) {
			throw new CHException("500","用户手续费规则未配置");
		}
		
		int userFee = calUserFee(order.getAmount(), userLevelFeeRate);
		int chnlFee = calChnlFee(order.getAmount(), payChannel);
		int userPayFee = userLevelFeeRate.getPayFee();
		int actualPayFee = order.getAmount() - userFee;
		if(payChannel.getCode().equals("reapalfast")) {
			//actualPayFee +=userLevelFeeRate.getPayFee();
		}
		
		return new int[] {userFee,chnlFee,userFee-chnlFee,actualPayFee};
	}
	
	
	@Override
	public void handleAgentPayManual(FastOrder order) {
		
		FastOrder fastOrder = assembleFastOrder(order);
		Map<String, String> resultMap = new HashMap<>();
		
		resultMap = FastPayApi.fastPay_df(fastOrder, cardExtService,fastOrder.getChannel());
		
		if(!"1".equals(resultMap.get("status"))){//消费成功
			order.setPaySt(3);
		}
		
		fastOrderMapper.insertSelective(fastOrder);
		
	}
	
	private FastOrder assembleFastOrder(FastOrder order) {
		
		PayChannel queryPayChannel = new PayChannel();
		queryPayChannel.setCode(order.getChannel());
		queryPayChannel.setChnlType(4);
		PayChannel payChannel = payChannelService.getPayChannelByCodeAndType(queryPayChannel);
		if(payChannel != null) {
			log.debug("找到快捷通道["+payChannel.getCode()+"]");	
		}else {
			throw new CHException("500","未找到快捷通道["+order.getChannel()+"]");
		}
		
		AppUser appUser = appUserService.getAppUserByUserId(order.getUserId());
		if(appUser == null) {
			throw new CHException("500","未找到用户["+order.getUserId()+"]");
		}
		
		//快捷消费手续费 要根据交易类型 用户等级 结算方式 有无积分来查询费率
		String settleType = "0";//结算方式  默认D0
		String integralType = "0"; //有无积分  默认无积分
		LevelFeeRate userLevelFeeRate = getUserLevelFeeRate(settleType, integralType, appUser,payChannel.getCode());
		if(null == userLevelFeeRate) {
			throw new CHException("500","用户手续费规则未配置");
		}
		
		//重新设置新值
		order.setCardName(appUser.getAccountName());
		order.setCardNo(appUser.getCardNo());
		order.setBankNo(appUser.getPmsBankNo());
		order.setChnlId(payChannel.getId());
		order.setChannel(payChannel.getCode());
		int payFeeRate = 0;
		if(null != payChannel.getPayFeeRate()) {
			payFeeRate = payChannel.getPayFeeRate().intValue();
		}
		order.setChnlFee(payFeeRate);
		int payFee = 0;
		if(null != userLevelFeeRate.getPayFee()) {
			payFee = userLevelFeeRate.getPayFee();
		}
		order.setFee(payFee);
		
		
		String agentPayOrderNo = new Date().getTime() + seqService.updateAndGetSequence(SeqServiceImpl.T_FAST_ORDER, 8);
		FastOrder agentPayOrder = new FastOrder();
		agentPayOrder.setOrderNo(agentPayOrderNo);
		agentPayOrder.setOrderDt(DateUtils.getCurrentDate());
		agentPayOrder.setPaySt(1);
		agentPayOrder.setOrderTp(1);
		agentPayOrder.setSettleOrderNo("999999999");
		agentPayOrder.setAmount(order.getAmount());
		agentPayOrder.setCardId(order.getCardId());
		agentPayOrder.setBankNo(order.getBankNo());
		agentPayOrder.setCardName(order.getCardName());
		agentPayOrder.setCardNo(order.getCardNo());
		agentPayOrder.setChnlFee(order.getChnlFee());
		agentPayOrder.setChnlId(order.getChnlId());
		agentPayOrder.setChannel(order.getChannel());
		agentPayOrder.setChnlOrderNo(order.getChnlOrderNo());
		agentPayOrder.setCreateTime(new Date());
		agentPayOrder.setUserId(order.getUserId());
		agentPayOrder.setFee(order.getFee());
		agentPayOrder.setUserCertNo(appUser.getCertNo());
		
		return agentPayOrder;
		
	}
	
	private LevelFeeRate getUserLevelFeeRate(String settleType, String integralType, AppUser appUser,String payChnlCode) {
		//判断用户身份为代理
		Agent agent = agentService.getAgentByUserId(appUser.getUserId());
		if(agent==null){//用户
			LevelFeeRate levelFeeRate = new LevelFeeRate();
			levelFeeRate.setLevelId(appUser.getLevelId());
			levelFeeRate.setPayChnlCode(payChnlCode);
			return levelFeeRateService.getLevelFeeRate(levelFeeRate);
		}else{//代理
			AgentFeeRate agentFeeRate = new AgentFeeRate();
			agentFeeRate.setLevelId(agent.getLevel());
			agentFeeRate.setPayChnlCode(payChnlCode);
			return agentFeeRateService.agentFeeRate(agentFeeRate);
		}
		
	}
	
	/**
	 * 计算手续费
	 * @param amt
	 * @param levelFeeRate
	 * @return
	 */
	private int calUserFee(int amt, LevelFeeRate levelFeeRate) {
		int tempAmt = Fen2YuanUtil.caclFee(amt, levelFeeRate.getFeeRate().doubleValue());
		if(0 < levelFeeRate.getUpperlimit() && tempAmt > levelFeeRate.getUpperlimit()) {
			tempAmt = levelFeeRate.getUpperlimit();
		}
		return tempAmt;
	}
	
	/**
	 * 计算快捷消费手续费
	 * @param amt
	 * @param payChannel
	 * @return
	 */
	private int calChnlFee(int amt, PayChannel payChannel) {
		int chnlFee=0;
		if(payChannel.getFeeType()==0){//百分比
			chnlFee = Fen2YuanUtil.caclFee(amt, payChannel.getFeeRate().doubleValue());
		}else{
			chnlFee = Fen2YuanUtil.caclFee(100, payChannel.getFeeRate().intValue()/100);
		}
		if(0 < payChannel.getUpperlimit() && chnlFee > payChannel.getUpperlimit()) {
			chnlFee = payChannel.getUpperlimit();
		}
		return chnlFee;
	}
	
	
}

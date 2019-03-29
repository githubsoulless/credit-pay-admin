package net.chrone.creditpay.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.SeqMapper;
import net.chrone.creditpay.model.Seq;
import net.chrone.creditpay.model.SeqExample;
import net.chrone.creditpay.service.RedisService;
import net.chrone.creditpay.service.SeqService;
import net.chrone.creditpay.util.IdGen;

/**
 * 取table_id
 * 
 * @author aojiong
 * 
 */
@Service
public class SeqServiceImpl implements SeqService {

	public static final String T_ROLE_INF = "T_ROLE_INF";
	public static final String T_AGENT_INF = "T_AGENT_INF";
	public static final String T_ACCOUNT = "T_ACCOUNT";
	public static final String T_CARD_INF = "T_CARD_INF";
	public static final String T_TX_RECORD = "T_TX_RECORD";
	public static final String T_ACCOUNT_DETAIL = "T_ACCOUNT_DETAIL";
	public static final String T_LEVEL_ORDER = "T_LEVEL_ORDER";
	public static final String T_UP_PROFITS = "T_LEVEL_ORDER";
	public static final String T_UP_PROFITS_DETAIL = "T_UP_PROFITS_DETAIL";
	public static final String T_PAY_PLAN = "T_PAY_PLAN";
	public static final String T_PAY_PLAN_TASK = "T_PAY_PLAN_TASK";
	public static final String T_PAY_PROFITS = "T_PAY_PROFITS";
	public static final String T_PAY_PROFITS_DETAIL = "T_PAY_PROFITS_DETAIL";
	public static final String T_LOTTERY_DETAIL = "T_LOTTERY_DETAIL";
	public static final String T_ORDER = "T_ORDER";
	public static final String T_FAST_ORDER = "T_FAST_ORDER";
	public static final String T_FAST_PROFITS = "T_FAST_PROFITS";
	public static final String T_FAST_PROFITS_DETAIL = "T_FAST_PROFITS_DETAIL";
	public static final String T_APP_MESSAGE = "T_APP_MESSAGE";
	@Autowired
	private SeqMapper seqMapper;
	@Autowired
	private RedisService redisService;

	public String format(Long num, int width) {
		if (num < 0)
			return "";
		StringBuffer sb = new StringBuffer();
		String s = "" + num;
		if (s.length() < width) {
			int addNum = width - s.length();
			for (int i = 0; i < addNum; i++) {
				sb.append("0");
			}
			sb.append(s);
		} else {
			return s.substring(s.length() - width, s.length());
		}
		return sb.toString();
	}

	@Override
	public String updateAndGetSequence(String id, int length) {
		long num = 0;
        try {      
        	num = redisService.genNextSeqNo(id);
        }catch (Exception e) {
        	String idtemp = new IdGen().nextId();
        	num = Long.valueOf(idtemp.substring(idtemp.length() - 8, idtemp.length()));
        	e.printStackTrace();
		}
       return format(num, length);
	}
	
	@Override
	public List<Seq> getSeqList() {
	
		SeqExample seqExample = new SeqExample();
		return seqMapper.selectByExample(seqExample);
	}

	@Override
	public int update(Seq seq) {
		return seqMapper.updateByPrimaryKeySelective(seq);
	}
	
}

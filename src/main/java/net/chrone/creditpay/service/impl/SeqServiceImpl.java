package net.chrone.creditpay.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.SeqMapper;
import net.chrone.creditpay.model.Seq;
import net.chrone.creditpay.service.SeqService;

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
	public static final String T_OPERATION_LOG = "T_OPERATION_LOG";
	public static final String T_AGENT_ROLE_INF = "T_AGENT_ROLE_INF";
	public static final String T_COUPON = "T_COUPON";
	public static final String T_FAST_ORDER = "T_FAST_ORDER";
	@Autowired
	private SeqMapper seqMapper;

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
		Long num = 1L;
		try {
			int row = seqMapper.tickCountSeq(id);
			if (row == 0) {
				Seq seq = new Seq();
				seq.setId(id);
				seq.setSeq(num);
				try {
					seqMapper.insertSelective(seq);
				} catch (Exception e) {
					e.printStackTrace();
					return "-1";
				}
				num = 1L;
			} else {
				Seq Seq = seqMapper.selectByPrimaryKey(id);
				num =Long.valueOf(Seq.getSeq());
				if (num > 999999998) { // 数据超长归零处理
					Seq.setSeq(0L);
					seqMapper.updateByPrimaryKeySelective(Seq);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format(num, length);
	}
}

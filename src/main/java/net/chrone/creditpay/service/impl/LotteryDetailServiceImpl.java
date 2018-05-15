package net.chrone.creditpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.LotteryDetailMapper;
import net.chrone.creditpay.model.LotteryDetail;
import net.chrone.creditpay.service.LotteryDetailService;


/**
 * 
 * Title: LotteryDetailServiceImpl 
 * Description: 中奖明细
 * @author huoliang
 * @data 2017年11月28日 下午5:45:47
 *
 */

@Service
public class LotteryDetailServiceImpl implements LotteryDetailService {
	
	@Autowired
	private LotteryDetailMapper lotteryDetailMapper;

	@Override
	public int countLotteryDetail(LotteryDetail lotteryDetail) {
		return lotteryDetailMapper.countLotteryDetail(lotteryDetail);
	}

	@Override
	public List<LotteryDetail> listLotteryDetail(LotteryDetail lotteryDetail) {
		return lotteryDetailMapper.listLotteryDetail(lotteryDetail);
	}

}

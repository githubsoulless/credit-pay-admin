package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.LotteryDetail;

/**
 * 
 * Title: LotteryDetailService 
 * Description: 抽奖记录 
 * @author huoliang
 * @data 2017年11月28日 下午5:44:45
 *
 */
public interface LotteryDetailService {
	
	int countLotteryDetail(LotteryDetail lotteryDetail);
	
	List<LotteryDetail> listLotteryDetail(LotteryDetail lotteryDetail);

}

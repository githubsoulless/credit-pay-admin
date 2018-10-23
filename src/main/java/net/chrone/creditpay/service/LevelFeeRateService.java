package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.LevelFeeRate;

public interface LevelFeeRateService {
	
	List<LevelFeeRate> listLevelFeeRate(int levelId);
	
	/**
	 * 查询用户快捷等级费率
	 * <b>新增了用户的快捷通道查询条件</b>
	 * @param levelFeeRate
	 * @param levelId  等级ID
	 * @param settleType 结算方式
	 * @param integralType 有无积分
	 * @param transType 交易类型
	 * @return
	 */
	LevelFeeRate getLevelFeeRate(LevelFeeRate levelFeeRate);
	
}

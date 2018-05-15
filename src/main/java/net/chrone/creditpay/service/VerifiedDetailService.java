package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.VerifiedDetail;

/**
 * 
 * Title: VerifiedDetailService 
 * Description: 实名认证记录表 
 * @author huoliang
 * @data 2017年11月29日 下午5:49:51
 *
 */

public interface VerifiedDetailService {
	
	int countVerifiedDetail(VerifiedDetail verifiedDetail);
	
	List<VerifiedDetail> listVerifiedDetail(VerifiedDetail verifiedDetail);

}

package net.chrone.creditpay.service;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.CouponDetail;

/**
 * 
 * Title: CouponDetailService 
 * Description: 优惠券明细 
 * @author huoliang
 * @data 2017年11月28日 下午5:44:45
 *
 */
public interface CouponDetailService {
	
	Map<String, Object> countCouponDetail(CouponDetail couponDetail);
	
	List<CouponDetail> listCouponDetail(CouponDetail couponDetail);
	
	Map<String, Object> countCouponDetailByStatus();

}

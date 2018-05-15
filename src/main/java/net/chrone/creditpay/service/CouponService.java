package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Coupon;

/**
 * 
 * Title: CouponService 
 * Description: 优惠券管理 
 * @author huoliang
 * @data 2017年11月27日 下午6:12:10
 *
 */
public interface CouponService {

	int saveCoupon(Coupon coupon);
	
	int updateCoupon(Coupon coupon);
	
	Coupon getCouponByPrimaayKey(String id);
	
	int countCoupons(Coupon coupon);
	
	List<Coupon> listcoupons(Coupon coupon);
	
	int sendCoupons(Coupon coupon);
	
}

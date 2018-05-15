package net.chrone.creditpay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.CouponDetailMapper;
import net.chrone.creditpay.model.CouponDetail;
import net.chrone.creditpay.service.CouponDetailService;


/**
 * 
 * Title: CouponDetailServiceImpl 
 * Description: 优惠券明细 
 * @author huoliang
 * @data 2017年11月28日 下午5:45:47
 *
 */

@Service
public class CouponDetailServiceImpl implements CouponDetailService {
	
	@Autowired
	private CouponDetailMapper couponDetailMapper;

	@Override
	public Map<String, Object> countCouponDetail(CouponDetail couponDetail) {
		return couponDetailMapper.countCouponDetail(couponDetail);
	}

	@Override
	public List<CouponDetail> listCouponDetail(CouponDetail couponDetail) {
		return couponDetailMapper.listCouponDetail(couponDetail);
	}

	@Override
	public Map<String, Object> countCouponDetailByStatus() {
		return couponDetailMapper.countCouponDetailByStatus();
	}

}

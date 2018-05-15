package net.chrone.creditpay.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.mapper.CouponDetailMapper;
import net.chrone.creditpay.mapper.CouponLevelMapper;
import net.chrone.creditpay.mapper.CouponMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.AppUserExample;
import net.chrone.creditpay.model.AppUserExample.Criteria;
import net.chrone.creditpay.model.Coupon;
import net.chrone.creditpay.model.CouponDetail;
import net.chrone.creditpay.model.CouponLevel;
import net.chrone.creditpay.model.CouponLevelExample;
import net.chrone.creditpay.service.CouponService;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.IdGen;

/**
 * 
 * Title: CouponServiceImpl Description: 优惠券管理
 * 
 * @author huoliang
 * @data 2017年11月27日 下午6:18:52
 *
 */

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private CouponLevelMapper couponLevelMapper;
	@Autowired
	private CouponDetailMapper couponDetailMapper;
	@Autowired
	private AppUserMapper appUserMapper;

	
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;

	@Override
	@Transactional
	public int saveCoupon(Coupon coupon) {
		coupon.setId(new IdGen().nextId());
		if (0 == coupon.getLimitAmountType()) {
			coupon.setLimitAmount(0);
		} else {
			coupon.setLimitAmount(coupon.getLimitAmount() * 100);
		}
		coupon.setAmount(coupon.getAmount() * 100);
		if (0 == coupon.getLotteryType()) {
			coupon.setProbabilityWinning(0.0);
		} else {
			coupon.setProbabilityWinning(coupon.getProbabilityWinning() / 100);
		}
		if (1 == coupon.getValidityType()) {
			try {
				coupon.setEffectStartTime(DateUtils.parseDate(coupon.getStartDate(), "yyyy-MM-dd"));
				coupon.setEffectEndTime(DateUtils.parseDate(coupon.getEndDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(1 == coupon.getLotteryType()){
			for (Integer levelId : coupon.getLevelIds()) {
				CouponLevel couponLevel = new CouponLevel();
				couponLevel.setId(coupon.getId());
				couponLevel.setLevelId(levelId);
				couponLevelMapper.insertSelective(couponLevel);
			}
		}
		return couponMapper.insertSelective(coupon);
	}

	@Override
	@Transactional
	public int updateCoupon(Coupon coupon) {
		CouponLevelExample example = new CouponLevelExample();
		example.createCriteria().andIdEqualTo(coupon.getId());
		couponLevelMapper.deleteByExample(example);
		if(1 == coupon.getLotteryType()){
			for (Integer levelId : coupon.getLevelIds()) {
				CouponLevel couponLevel = new CouponLevel();
				couponLevel.setId(coupon.getId());
				couponLevel.setLevelId(levelId);
				couponLevelMapper.insertSelective(couponLevel);
			}
		}
//		if (0 == coupon.getLimitAmountType()) {
//			coupon.setLimitAmount(0);
//		} else {
//			coupon.setLimitAmount(coupon.getLimitAmount() * 100);
//		}
//		coupon.setAmount(coupon.getAmount() * 100);
		if (0 == coupon.getLotteryType()) {
			coupon.setProbabilityWinning(0.0);
		} else {
			coupon.setProbabilityWinning(coupon.getProbabilityWinning() / 100);
		}
		if (1 == coupon.getValidityType()) {
			try {
				coupon.setEffectStartTime(DateUtils.parseDate(coupon.getStartDate(), "yyyy-MM-dd"));
				coupon.setEffectEndTime(DateUtils.parseDate(coupon.getEndDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			coupon.setFixedDays(0);
		}
		if(0  == coupon.getValidityType()) {
			coupon.setEffectStartTime(null);
			coupon.setEffectEndTime(null);
		}
		return couponMapper.updateByPrimaryKeySelective(coupon);
	}

	@Override
	public Coupon getCouponByPrimaayKey(String id) {
		Coupon coupon = couponMapper.getCouponByPrimaayKey(id);
		if (1 == coupon.getValidityType()) {
			coupon.setStartDate(DateUtils.formatDate(coupon.getEffectStartTime(), "yyyy-MM-dd"));
			coupon.setEndDate(DateUtils.formatDate(coupon.getEffectEndTime(), "yyyy-MM-dd"));
		}
		return coupon;
	}

	@Override
	public int countCoupons(Coupon coupon) {
		return couponMapper.countCoupons(coupon);
	}

	@Override
	public List<Coupon> listcoupons(Coupon coupon) {
		List<Coupon> coupons = couponMapper.listcoupons(coupon);
		for(Coupon c : coupons){
			c.setLevels(couponMapper.listLevels(c.getId()));
		}
		return coupons;
	}

	/**
	 * 手动发放优惠券 return 0 成功 1优惠券数量不足 2失败
	 */
	@Override
	public int sendCoupons(Coupon coupon) {
		try {
			Coupon oCoupon = couponMapper.getCouponByPrimaayKey(coupon.getId());
			AppUserExample appUserExample = new AppUserExample();
			Criteria criteria = appUserExample.createCriteria();
			List<AppUser> appusers = new ArrayList<>();
			// 按等级
			if (1 == coupon.getSendType()) {
				criteria.andLevelIdIn(coupon.getLevelIds());
				appusers = appUserMapper.selectByExample(appUserExample);
			}
			// 指定用户
			if (2 == coupon.getSendType()) {
				AppUser appuser;
				for (String userid : coupon.getUserIds().split("\\,")) {
					appuser = new AppUser();
					appuser.setUserId(userid);
					appusers.add(appuser);
				}
			}
			// 按代理
			if (3 == coupon.getSendType()) {
				criteria.andAgentIdEqualTo(coupon.getAgentId());
				appusers = appUserMapper.selectByExample(appUserExample);
			}
			// 当前目标用户下，没有查到用户
			if (appusers.isEmpty()) {
				return 0;
			}
			if (oCoupon.getCoupanCount() != -1 && oCoupon.getCoupanCount() < appusers.size()) {
				return 1;
			}
			Coupon updCoupon = new Coupon();
			updCoupon.setId(oCoupon.getId());
			if(oCoupon.getCoupanCount() != -1){
				updCoupon.setCoupanCount(oCoupon.getCoupanCount() - appusers.size());
			}
			updCoupon.setRecUpdTs(new Date());
			updCoupon.setRecUpdUsr(coupon.getRecUpdUsr());
			couponMapper.updateByPrimaryKeySelective(updCoupon);
			saveCouponDetail(appusers, oCoupon);
			return 0;
		} catch (Exception e) {
			return 2;
		}
	}

	public void saveCouponDetail(List<AppUser> appusers, Coupon oCoupon) throws ParseException{
		IdGen idGen = new IdGen();
		for(AppUser appuser : appusers){
			CouponDetail couponDetail = new CouponDetail();
			couponDetail.setId(idGen.nextId());
			couponDetail.setCouponId(oCoupon.getId());
			couponDetail.setStatus(0);
			couponDetail.setUserId(appuser.getUserId());
			couponDetail.setRowCrtTs(new Date());
			Date effectStartTime = null;
			Date effectEndTime = null;
			if(0 == oCoupon.getValidityType()){
				effectStartTime = DateUtils.parseDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
				effectEndTime = DateUtils.parseDate(DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), oCoupon.getFixedDays()), "yyyy-MM-dd")+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
			}
			if(1 == oCoupon.getValidityType()){
				effectStartTime = oCoupon.getEffectStartTime();
				effectEndTime = oCoupon.getEffectEndTime();
			}
			couponDetail.setEffectStartTime(effectStartTime);
			couponDetail.setEffectEndTime(effectEndTime);
			couponDetail.setOrderId("");
			task(couponDetail);
		}
		
	}
	
	public void task(CouponDetail couponDetail){
		try {
			taskExecutor.execute(new Runnable() {
				public void run() {
					couponDetailMapper.insertSelective(couponDetail);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

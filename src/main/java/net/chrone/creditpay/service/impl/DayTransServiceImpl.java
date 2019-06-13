package net.chrone.creditpay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.mapper.DayTransMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.DayTrans;
import net.chrone.creditpay.service.DayTransService;
import net.chrone.creditpay.util.DateUtils;


@Service
public class DayTransServiceImpl implements DayTransService {
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private DayTransMapper dayTransMapper;
	
	
	@Override
	public void dayUserOrderStatics(String orderDt, List<AppUser> userList) {
		
		List<Map<String, Object>> fastOrderMap = dayTransMapper.sumUserFastOrder(orderDt);
		List<Map<String, Object>> planOrderMap = dayTransMapper.sumUserPlanOrder(orderDt);
		
		Map<String, Long> foMap = new HashMap<>();
		for(Map<String, Object> fo:fastOrderMap) {
			foMap.put(fo.get("user_id").toString(), Long.valueOf(fo.get("sum_amt")+""));
		}
		
		Map<String, Long> poMap = new HashMap<>();
		for(Map<String, Object> po:planOrderMap) {
			poMap.put(po.get("user_id").toString(), Long.valueOf(po.get("sum_amt")+""));
		}
		
		List<DayTrans> dayTransList = new ArrayList<>();
		for(AppUser appUser:userList) {
			DayTrans dayTrans = new DayTrans();
			dayTrans.setTransDt(orderDt);
			dayTrans.setUserId(appUser.getUserId());
			if(foMap.get(appUser.getUserId())==null&&poMap.get(appUser.getUserId())==null) {
				continue;
			}
			if(foMap.get(appUser.getUserId())!=null) {
				dayTrans.setFastAmt(foMap.get(appUser.getUserId()).intValue());
			}else {
				dayTrans.setFastAmt(0);
			}
			if(poMap.get(appUser.getUserId())!=null) {
				dayTrans.setPlanAmt(poMap.get(appUser.getUserId()).intValue());
			}else {
				dayTrans.setPlanAmt(0);
			}
			dayTransList.add(dayTrans);
		}
		
		//入库
		List<DayTrans> tempList = new ArrayList<>();
		for(int i=0;i<dayTransList.size();i++) {
			tempList.add(dayTransList.get(i));
			if((i+1)%500==0) {
				dayTransMapper.batchAdd(tempList);
				tempList = new ArrayList<>();
			}
		}
		if(tempList.size()>0) {
			dayTransMapper.batchAdd(tempList);
		}
	}
	
	public static void main(String[] args) {
		String orderDt = DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -1), "yyyyMMdd");
		System.out.println(orderDt);
	}

}

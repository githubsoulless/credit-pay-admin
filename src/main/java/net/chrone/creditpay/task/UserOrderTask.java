package net.chrone.creditpay.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.service.DayTransService;
import net.chrone.creditpay.util.DateUtils;
import net.chrone.creditpay.util.LogWriter;

@Component
public class UserOrderTask {
	
	@Autowired
	private DayTransService dayTransService;
	@Autowired
	private AppUserMapper appUserMapper;

	@Scheduled(cron="0 0 04 * * ?")
	private void work() {
		LogWriter.info("用户交易统计 任务执行开始....");
		List<AppUser> userList = appUserMapper.selectByExample(null);
		String orderDt = DateUtils.formatDate(DateUtils.getAnyDayByNo(new Date(), -1), "yyyyMMdd");
		dayTransService.dayUserOrderStatics(orderDt,userList);
		LogWriter.info("用户交易统计 任务执行结束...");
	}
	
	public static void main(String[] args) {
	}
}

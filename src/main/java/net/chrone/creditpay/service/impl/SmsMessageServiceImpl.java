package net.chrone.creditpay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.mapper.SmsMessageLevelMapper;
import net.chrone.creditpay.mapper.SmsMessageMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.AppUserExample;
import net.chrone.creditpay.model.SmsMessage;
import net.chrone.creditpay.model.SmsMessageLevel;
import net.chrone.creditpay.service.SmsMessageService;
import net.chrone.creditpay.service.SmsService;
import net.chrone.creditpay.util.SendSmsThread;

/**
 * 
 * Title: SmsMessageServiceImpl Description: 短信管理
 * 
 * @author huoliang
 * @data 2017年12月4日 下午5:03:45
 *
 */
@Service
public class SmsMessageServiceImpl implements SmsMessageService {

	@Autowired
	private SmsMessageMapper smsMessageMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private SmsMessageLevelMapper smsMessageLevelMapper;
	@Autowired
	private SmsService smsService;
	@Autowired
	private TaskExecutor taskExecutor;

	@Override
	public int countSmsMessage(SmsMessage smsMessage) {
		return smsMessageMapper.countSmsMessage(smsMessage);
	}

	@Override
	public List<SmsMessage> listSmsMessage(SmsMessage smsMessage) {
		return smsMessageMapper.listSmsMessage(smsMessage);
	}

	@Override
	public int saveMessage(SmsMessage smsMessage) {
		smsMessage.setCreatTime(new Date());
		smsMessage.setMsgType(1);
		smsMessage.setUserId("");
		List<String> mobileNums = new ArrayList<>();

		// 按等级推送
		if (1 == smsMessage.getPushRange()) {
			AppUserExample example = new AppUserExample();
			example.createCriteria().andLevelIdIn(smsMessage.getLevelIds());
			List<AppUser> appusers = appUserMapper.selectByExample(example);
			for (AppUser appuser : appusers) {
				mobileNums.add(appuser.getLoginId());
			}

			for (Integer level : smsMessage.getLevelIds()) {
				SmsMessageLevel smsMessageLevel = new SmsMessageLevel();
				smsMessageLevel.setId(smsMessage.getId());
				smsMessageLevel.setLevelId(level);
				smsMessageLevelMapper.insertSelective(smsMessageLevel);
			}
		}
		// 按指定用户
		if (2 == smsMessage.getPushRange()) {
			for (String s : smsMessage.getMobileNum().split("\\,")) {
				mobileNums.add(s);
			}
		}

		// 按代理
		if (3 == smsMessage.getPushRange()) {
			AppUserExample example = new AppUserExample();
			example.createCriteria().andAgentIdEqualTo(smsMessage.getAgentId());
			List<AppUser> appusers = appUserMapper.selectByExample(example);
			for (AppUser appuser : appusers) {
				mobileNums.add(appuser.getLoginId());
			}
		}
		smsMessage.setSmsCount(Long.valueOf(mobileNums.size()));

		doSendSms(mobileNums, smsMessage);

		return smsMessageMapper.insertSelective(smsMessage);
	}

	public void doSendSms(List<String> mobileNums, SmsMessage smsMessage) {
		for (String mobileNum : mobileNums) {
			taskExecutor.execute(new SendSmsThread(mobileNum, smsMessage.getContent(), smsService));
		}
	}

	@Override
	public SmsMessage getSmsMessage(String id) {
		SmsMessage smsMessage = smsMessageMapper.getSmsMessage(id);
		if (1 == smsMessage.getPushRange()) {
			smsMessage.setLevelNames(smsMessageLevelMapper.getLevelNames(smsMessage.getId()));
		}
		return smsMessage;
	}

}

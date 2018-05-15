package net.chrone.creditpay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jpush.api.JPushClient;
import net.chrone.creditpay.mapper.AppUserMapper;
import net.chrone.creditpay.mapper.MessageMapper;
import net.chrone.creditpay.mapper.UserMessageMapper;
import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.AppUserExample;
import net.chrone.creditpay.model.Message;
import net.chrone.creditpay.model.UserMessage;
import net.chrone.creditpay.service.MessageService;
import net.chrone.creditpay.util.CHException;
import net.chrone.creditpay.util.ConfigReader;
import net.chrone.creditpay.util.IdGen;
import net.chrone.creditpay.util.MsgPushUtil;

/**
 * 
 * Title: MessageServiceImpl 
 * Description: APP消息推送管理 
 * @author huoliang
 * @data 2017年12月1日 下午3:34:59
 *
 */
@Service
public class MessageServiceImpl implements MessageService {
	
	 private Logger logger = Logger.getLogger(MessageService.class);
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private UserMessageMapper userMessageMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;

	@Override
	public int countMessage(Message message) {
		return messageMapper.countMessage(message);
	}

	@Override
	public List<Message> listMessage(Message message) {
		return messageMapper.listMessage(message);
	}

	@Override
	@Transactional(rollbackFor = CHException.class)
	public int saveMessage(Message message) {
		message.setCreatTime(new Date());
		message.setOperTime(new Date());
		message.setMsgType(1);
		message.setState(0);
		List<AppUser> appusers = new ArrayList<>();
		//按等级
		if(1 == message.getPushRange()){
			message.setUserId("");
			message.setAgentId("");
			//等级是否选择，如果未选择等级，等推送全部
			if(null == message.getLevelId()){
				message.setPushRange(0);
				message.setLevelId(-1);
				appusers = appUserMapper.selectByExample(null);
			}else{
				AppUserExample appUserExample = new AppUserExample();
				appUserExample.createCriteria().andLevelIdEqualTo(message.getLevelId());
				appusers = appUserMapper.selectByExample(appUserExample);
			}
		}
		//指定用户
		if(2 == message.getPushRange()){
			AppUserExample appUserExample = new AppUserExample();
			appUserExample.createCriteria().andUserIdEqualTo(message.getUserId());
			appusers = appUserMapper.selectByExample(appUserExample);
		}
		//按代理
		if(3 == message.getPushRange()){
			if(StringUtils.isNotEmpty(message.getAgentId1())){
				message.setAgentId(message.getAgentId1());
			}
			if(StringUtils.isNotEmpty(message.getAgentId2())){
				message.setAgentId(message.getAgentId2());
			}
			if(StringUtils.isNotEmpty(message.getAgentId3())){
				message.setAgentId(message.getAgentId3());
			}
			AppUserExample appUserExample = new AppUserExample();
			if(StringUtils.isEmpty(message.getAgentId())){
				appUserExample.createCriteria().andAgentIdIsNotNull();
			}else{
				appUserExample.createCriteria().andAgentIdEqualTo(message.getAgentId());
			}
			appusers = appUserMapper.selectByExample(appUserExample);
		}
		message.setId(new IdGen().nextId());
		int result = messageMapper.insertSelective(message);
		if(1 != result){
             throw new CHException("系统异常"); 
		}
		doPush(appusers, message);
		return 1;
	}
	
	public void doPush(List<AppUser> appusers, Message message){
		for(AppUser appuser : appusers){
			UserMessage userMessage = new UserMessage();
			userMessage.setIsRead(0);
			userMessage.setMessageId(message.getId());
			userMessage.setMsgType(1);
			userMessage.setStatus(0);
			userMessage.setUserId(appuser.getUserId());
			task(userMessage, message);
		}
	}
	
	public void task(UserMessage userMessage, Message message){
		try {
			taskExecutor.execute(new Runnable() {
				public void run() {
					userMessageMapper.insertSelective(userMessage);
					JPushClient jPushClient = new JPushClient(ConfigReader.getConfig("pushMasterSecret"), ConfigReader.getConfig("pushAppKey"), 3);
			        int pushToios = MsgPushUtil.pushAliasNotification(userMessage.getUserId(), message.getTitle(), null, message.getContent(),
			                jPushClient);
			        if (pushToios == -1) {
			            logger.info("自定义推送通知失败！");
			        }
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message getMessage(String id) {
		return messageMapper.getMessageByPrimaryKey(id);
	}

	@Override
	public int deleteMessage(String id) {
		return messageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int countUserMessage(UserMessage userMessage) {
		return userMessageMapper.countUserMessage(userMessage);
	}

	@Override
	public List<UserMessage> listUserMessage(UserMessage userMessage) {
		return userMessageMapper.listUserMessage(userMessage);
	}

	@Override
	public int deleteMessage(Message message) {
		return messageMapper.updateByPrimaryKeySelective(message);
	}

	@Override
	public void update(Message message) {
		messageMapper.updateByPrimaryKeySelective(message);
	}

}

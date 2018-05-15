package net.chrone.creditpay.service;

import java.util.List;

import net.chrone.creditpay.model.Message;
import net.chrone.creditpay.model.UserMessage;

/**
 * 
 * Title: MessageService 
 * Description: APP消息推送管理 
 * @author huoliang
 * @data 2017年12月1日 下午3:25:05
 *
 */
public interface MessageService {
	
	int countMessage(Message message);
	
	List<Message> listMessage(Message message);
	
	int saveMessage(Message message);
	
	Message getMessage(String id);
	
	int deleteMessage(String id);
	
	int countUserMessage(UserMessage userMessage);
	
	List<UserMessage> listUserMessage(UserMessage userMessage);
	
	int deleteMessage(Message message);

	void update(Message message);
	
}

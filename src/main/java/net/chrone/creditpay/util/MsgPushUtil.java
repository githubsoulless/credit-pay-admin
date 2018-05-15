package net.chrone.creditpay.util;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;



public class MsgPushUtil {
	private static final Logger logger = Logger.getLogger(MsgPushUtil.class);
	public static int pushAliasMsg(String alias, String msgContent, JPushClient jPushClient) {
		if (StringUtils.isEmpty(alias)) {
			logger.error("发送目标为空");
			return -1;
		}
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
		
		PushPayload pushPayLoad = buildPushObject_ios_audienceMore_messageWithExtras(alias, null, msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}

	public static int pushMsgToAndroid(String alias, String msgContent, JPushClient jPushClient) {
		if (StringUtils.isEmpty(alias)) {
			logger.error("发送目标为空");
			return -1;
		}
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
		
		PushPayload pushPayLoad = pushMsgToAndroid(alias, null, msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}
	
	public static int pushTagsMsg(Set<String> tags, String msgContent, JPushClient jPushClient) {
		if (tags==null || tags.size()==0) {
			logger.error("发送目标为空");
		}
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
		
		PushPayload pushPayLoad = buildPushObject_ios_audienceMore_messageWithExtras(null, tags, msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}
	
	public static int pushBroadcastMsg(String msgContent, JPushClient jPushClient) {
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
		
		PushPayload pushPayLoad = buildPushObject_ios_audienceMore_messageWithExtras(null, null, msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}
	public static PushPayload pushMsgToAndroid(String alias, Set<String> tags, String msgContent) {
	return PushPayload.newBuilder()
            .setPlatform(Platform.android_ios())
            .setAudience(Audience.newBuilder()
                    .addAudienceTarget(AudienceTarget.alias(alias))
                    .build())
            .setMessage(Message.newBuilder()
                    .setMsgContent(msgContent)
                    .addExtra("from", "JPush")
                    .build())
            .build();
	}
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String alias, Set<String> tags, String msgContent) {
		if (StringUtils.isEmpty(alias) && (tags==null || tags.size()==0)) {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.all())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(msgContent)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();
		}else if (StringUtils.isEmpty(alias) && (tags!=null && tags.size()>0)) {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag(tags))
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(msgContent)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();
		}else if (!StringUtils.isEmpty(alias) && (tags==null || tags.size()==0)) {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.alias(alias))
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(msgContent)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();
		}else {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.alias(alias))
	                        .addAudienceTarget(AudienceTarget.tag(tags))
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(msgContent)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();
		}
    }
	
	public static int pushMsgToServer(PushPayload pushPayLoad,JPushClient jPushClient) {
		if (pushPayLoad == null) {
			return -1;
		}
		try {
            PushResult result = jPushClient.sendPush(pushPayLoad);
            logger.info("Got result - " + result);
            return 0;

        } catch (APIConnectionException e) {
        	logger.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
        	logger.error("Should review the error, and fix the request", e);
        	logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
		return -1;
	}
	
	public static PushPayload buildPushObject_ios_audienceMore_NotificationWithExtras(String alias, Set<String> tags, 
			String title, Map<String,String> extras, String msgContent) {
//		Map<String,String> extras = new HashMap<String,String>();
//		extras.put("type", type);
		if (StringUtils.isEmpty(alias) && (tags==null || tags.size()==0)) {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.all())
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(msgContent)
	                                .setBadge(1)
	                                .setSound("happy.caf")
	                                .addExtras(extras)
	                                .build())
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                        		.setAlert(msgContent)
	                        		.setTitle(title)
	                        		.addExtras(extras)
	                        		.build())
	                        .build())
                     .setOptions(
                    		 Options.newBuilder()  
		                     .setApnsProduction(true)  
		                     .build())
	                .build();
		}else if (StringUtils.isEmpty(alias) && (tags!=null && tags.size()>0)) {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag(tags))
	                        .build())
	                        .setNotification(Notification.newBuilder()
	    	                        .addPlatformNotification(IosNotification.newBuilder()
	    	                                .setAlert(msgContent)
	    	                                .setBadge(1)
	    	                                .setSound("happy.caf")
	    	                                .addExtras(extras)
	    	                                .build())
	    	                        .addPlatformNotification(AndroidNotification.newBuilder()
	    	                        		.setAlert(msgContent)
	    	                        		.setTitle(title)
	    	                        		.addExtras(extras)
	    	                        		.build())
	    	                        .build())
	    	                 .setOptions(
	    	                		 Options.newBuilder()  
	    	                		 .setApnsProduction(true)  
	    	                		 .build())
	    	                .build();
		}else if (!StringUtils.isEmpty(alias) && (tags==null || tags.size()==0)) {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.alias(alias))
	                        .build())
	                        .setNotification(Notification.newBuilder()
	    	                        .addPlatformNotification(IosNotification.newBuilder()
	    	                                .setAlert(msgContent)
	    	                                .setBadge(1)
	    	                                .setSound("happy.caf")
	    	                                .addExtras(extras)
	    	                                .build())
	    	                        .addPlatformNotification(AndroidNotification.newBuilder()
	    	                        		.setAlert(msgContent)
	    	                        		.setTitle(title)
	    	                        		.addExtras(extras)
	    	                        		.build())
	    	                        .build())
	    	                 .setOptions(
	    	                		 Options.newBuilder()  
	    	                		 .setApnsProduction(true)  
	    	                		 .build())
	    	                .build();
		}else {
			return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.alias(alias))
	                        .addAudienceTarget(AudienceTarget.tag(tags))
	                        .build())
	                        .setNotification(Notification.newBuilder()
	    	                        .addPlatformNotification(IosNotification.newBuilder()
	    	                                .setAlert(msgContent)
	    	                                .setBadge(1)
	    	                                .setSound("happy.caf")
	    	                                .addExtras(extras)
	    	                                .build())
	    	                        .addPlatformNotification(AndroidNotification.newBuilder()
	    	                        		.setAlert(msgContent)
	    	                        		.setTitle(title)
	    	                        		.addExtras(extras)
	    	                        		.build())
	    	                        .build())
	    	                 .setOptions(
	    	                		 Options.newBuilder()  
	    	                		 .setApnsProduction(true)  
	    	                		 .build())
	    	                .build();
		}
	}
	
	public static PushPayload pushMessageToios(String alias, Set<String> tags, 
			String title, Map<String,String> extras, String msgContent){
		return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(alias))
                        .build())
                        .setNotification(Notification.newBuilder()
    	                        .addPlatformNotification(IosNotification.newBuilder()
    	                                .setAlert(msgContent)
    	                                .setBadge(1)
    	                                .setSound("happy.caf")
    	                                .addExtras(extras)
    	                                .build())
    	                        .addPlatformNotification(AndroidNotification.newBuilder()
    	                        		.setAlert(msgContent)
    	                        		.setTitle(title)
    	                        		.addExtras(extras)
    	                        		.build())
    	                        .build())
    	                .build();
	}
	
	public static int pushAliasNotification(String alias, String title, Map<String,String> extras, String msgContent, JPushClient jPushClient) {
		if (StringUtils.isEmpty(alias)) {
			logger.error("发送目标为空");
			return -1;
		}
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
		
		PushPayload pushPayLoad = buildPushObject_ios_audienceMore_NotificationWithExtras(alias, null, title,extras,msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}
	
	public static int pushToios(String alias, String title, Map<String,String> extras, String msgContent, JPushClient jPushClient) {
		if (StringUtils.isEmpty(alias)) {
			logger.error("发送目标为空");
			return -1;
		}
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
		
		PushPayload pushPayLoad = pushMessageToios(alias, null, title,extras,msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}
	
	public static int pushtagsNotification(Set<String> tags, String title, Map<String,String> extras, String msgContent, JPushClient jPushClient) {
		if (tags==null || tags.size()==0) {
			logger.error("发送目标为空");
		}
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
	
		PushPayload pushPayLoad = buildPushObject_ios_audienceMore_NotificationWithExtras(null, tags, title,extras,msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}
	
	public static int pushBroadcastNotification(String title, Map<String,String> extras, String msgContent, JPushClient jPushClient) {
		if (StringUtils.isEmpty(msgContent)) {
			logger.error("发送消息内容为空");
			return -1;
		}
		
		PushPayload pushPayLoad = buildPushObject_ios_audienceMore_NotificationWithExtras(null, null, title,extras,msgContent);
		return pushMsgToServer(pushPayLoad, jPushClient);
	}
}

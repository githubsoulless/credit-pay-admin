package net.chrone.creditpay.service.impl;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.chrone.creditpay.service.SMSMQService;

@Service
public class SMSMQServiceImpl  implements SMSMQService{
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired(required=true)
    private Destination destination;
    
    
    @Override
    public int produce(final Object text) {
        logger.debug("发送短信:" + JSON.toJSONString(text));
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage((Serializable)text);
                return message;
            }
        });
        return 0;
    }
}

package com.broad.mq.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "queueDestination")
    private Destination destination;

    @Resource

    @Override
    public void sendMessage(final TextMessage message) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("发送消息:" + message.getText());
                return session.createTextMessage(message.getText());
            }
        });
    }


}

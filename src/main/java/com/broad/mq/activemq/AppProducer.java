package com.broad.mq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;


public class AppProducer {

    private static final Logger logger = LoggerFactory.getLogger(AppProducer.class);
    private final static String MQ_URL = "tcp://localhost:61616";

    private static final String MQ_QUEUE_NAME = "test-queue";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(MQ_QUEUE_NAME);

        MessageProducer producer = session.createProducer(destination);


        for (int i = 0; i < 100; i++) {
            //创建消息 消息类型TextMessage
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("message:" + i);
            producer.send(textMessage);
            System.out.println("发散消息:" + textMessage.getText());

        }

        connection.close();
    }
}

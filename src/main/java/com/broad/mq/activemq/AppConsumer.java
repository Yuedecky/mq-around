package com.broad.mq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class AppConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AppProducer.class);
    private final static String MQ_URL = "tcp://localhost:61616";

    private static final String MQ_QUEUE_NAME = "test-queue";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(MQ_QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage tx = (TextMessage) message;
                try {
                    System.out.println("接受到消息：" + tx.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

//        connection.close();

    }
}

package com.broad.mq.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class TopicConsumer {


    private static final Logger LOG = LoggerFactory.getLogger(TopicProducer.class);

    private static final String TOPIC_QUEUE = "test-topic";

    private static final String TOPIC_URL = "tcp://localhost:61616";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(TOPIC_URL);
        Connection connection = connectionFactory.createConnection();

        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic(TOPIC_QUEUE);

        MessageConsumer consumer = session.createConsumer(destination);


        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage message1 = (TextMessage) message;
                    System.out.println("接受消息：" + message1.getText());
                } catch (Exception e) {
                    LOG.error("接受消息发生异常:" + e.getMessage());
                }

            }
        });
    }
}

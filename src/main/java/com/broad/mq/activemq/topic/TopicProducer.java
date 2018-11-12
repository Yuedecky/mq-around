package com.broad.mq.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class TopicProducer {


    private static final Logger LOG = LoggerFactory.getLogger(TopicProducer.class);

    private static final String TOPIC_QUEUE = "test-topic";

    private static final String TOPIC_URL = "tcp://localhost:61616";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(TOPIC_URL);
        Connection connection = connectionFactory.createConnection();

        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic(TOPIC_QUEUE);

        MessageProducer producer = session.createProducer(destination);

        for(int i=0;i<100;i++){
            TextMessage message = session.createTextMessage("topic:"+i);

            producer.send(message);

        }
    }
}

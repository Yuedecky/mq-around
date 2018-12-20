package com.broad.mq.activemq.ch02.jobs;

import ch.qos.logback.core.util.TimeUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class Producer {


    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private static String brokerUrl = "tcp://localhost:61616";

    private static transient ConnectionFactory connectionFactory;

    private transient Connection connection;

    private transient Session session;

    private MessageProducer producer;


    private static int total = 0;

    private static int count = 10;

    private static int id = 1000000;


    private String jobs[] = new String[]{"suspend", "delete"};


    public Producer() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(null);

    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws JMSException {
        Producer producer = new Producer();
        while (total < 1000) {
            for (int i = 0; i < count; i++) {
                producer.sendMessage();
            }
            total += count;
            LOGGER.info("Sent {} of {} job messages", count, total);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {

            }
        }

    }

    /**
     * 发送消息
     * @throws JMSException
     */
    public void sendMessage() throws JMSException {
        int idx = 0;
        while (true) {
            idx = (int) (Math.round(jobs.length * Math.random()));
            if (idx < jobs.length) {
                break;
            }
        }
        String job = jobs[idx];
        Destination destination = session.createQueue("JOBS." + job);
        Message message = session.createObjectMessage(id++);
        LOGGER.info("Sending id:{},on queue:{}", ((ObjectMessage) message).getObject(), destination);
        producer.send(destination, message);

    }
}

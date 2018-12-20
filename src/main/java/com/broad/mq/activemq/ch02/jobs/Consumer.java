package com.broad.mq.activemq.ch02.jobs;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Consumer {


    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);


    private static final String brokerUrl = "tcp://localhost:61616";

    private static transient ConnectionFactory connectionFactory;

    private transient Connection connection;

    private transient Session session;


    private String jobs[] = new String[]{"suspend", "delete"};

    public Consumer() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }


    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws JMSException {

        Consumer consumer = new Consumer();
        for (String job : consumer.jobs) {
            Destination destination = consumer.getSession().createQueue("JOBS." + job);
            MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
            messageConsumer.setMessageListener(new Listener(job));
        }
    }


    public Session getSession() {
        return session;
    }
}

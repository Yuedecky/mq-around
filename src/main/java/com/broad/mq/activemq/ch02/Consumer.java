package com.broad.mq.activemq.ch02;

import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Setter
@Getter
public class Consumer {


    private static final String brokerUrl = "tcp://localhost:61616";

    private transient ConnectionFactory connectionFactory;

    private transient Connection connection;


    private transient Session session;


    public Consumer() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }


    /**
     * 关闭JMS链接
     *
     * @throws JMSException
     */
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * test
     * @param args
     * @throws JMSException
     */
    public static void main(String[] args) throws JMSException {
        Consumer consumer = new Consumer();

        for (String stock : args) {
            Destination destination = consumer.getSession().createTopic("STOCKS." + stock);
            MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
            messageConsumer.setMessageListener(new Listener());
        }
    }

}

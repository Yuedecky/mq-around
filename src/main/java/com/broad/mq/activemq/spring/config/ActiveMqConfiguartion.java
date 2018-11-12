package com.broad.mq.activemq.spring.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;


@Configuration
public class ActiveMqConfiguartion {


    @Value("${spring.activemq.url}")
    private String activeMQUrl;

    @Value("${spring.activemq.user}")
    private String activeMQUser;

    @Value("${spring.activemq.password}")
    private String activeMQPassword;

    @Value("${spring.activemq.queue")
    private String queue;

    @Bean(name = "targetConnectionFactory")
    public ConnectionFactory targetConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(activeMQUrl);
        connectionFactory.setUserName(activeMQUser);
        connectionFactory.setPassword(activeMQPassword);
        return connectionFactory;
    }

    /**
     * spring 为我们提供的
     *
     * @return
     */
    @Bean("jmsTemplate")
    public JmsTemplate jmsTemplate() {
        ConnectionFactory factory = targetConnectionFactory();
        return new JmsTemplate(factory);

    }

    @Bean("queueDestination")
    public ActiveMQQueue queueDestination() {
        return new ActiveMQQueue(queue);
    }
}

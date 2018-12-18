package com.broad.mq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class MessageProducerConfiguration {

    @Resource
    private ProducerConfig producerConfig;



    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory(producerConfig.getBrokerUrl(),
                producerConfig.getUserName(), producerConfig.getPassword());
    }
}

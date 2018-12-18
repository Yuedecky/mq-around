package com.broad.mq.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.activemq.consumer")
public class ConsumerConfig {


    @Value("${broker-url}")
    private String brokerUrl;

    @Value("${userName}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${poolSize}")
    private int poolSize;

}

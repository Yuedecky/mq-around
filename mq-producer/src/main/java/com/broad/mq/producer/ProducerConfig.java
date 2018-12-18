package com.broad.mq.producer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.acrivemq")
public class ProducerConfig {


    //broker地址
    @Value("${broker-url}")
    private String brokerUrl;
    //用户名
    @Value("${userName}")
    private String userName;
    //密码
    @Value("${password}")
    private String password;
}

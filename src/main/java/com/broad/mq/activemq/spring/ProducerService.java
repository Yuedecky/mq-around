package com.broad.mq.activemq.spring;

import javax.jms.TextMessage;

public interface ProducerService {


    void sendMessage(TextMessage message);
}

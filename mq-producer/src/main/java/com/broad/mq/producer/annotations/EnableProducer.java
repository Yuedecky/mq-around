package com.broad.mq.producer.annotations;


import com.broad.mq.producer.MessageProducerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(MessageProducerConfiguration.class)
@EnableScheduling
public @interface EnableProducer {
}

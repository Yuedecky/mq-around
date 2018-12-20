package com.broad.mq.activemq.ch02.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class Listener implements MessageListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    private String job;

    public Listener(String job) {
        this.job = job;
    }

    @Override
    public void onMessage(Message message) {


        try {
            //do something here
            LOGGER.info(job + " id:" + ((ObjectMessage) message).getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.broad.mq.activemq.topic.loan;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Enumeration;

public class MetaData {

    public static void main(String[] args) {

        try {
            Context context = new InitialContext();

            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("QueueCF");

            Connection connection = connectionFactory.createQueueConnection();
            ConnectionMetaData metaData = connection.getMetaData();

            System.out.println("JMS Version:" + metaData.getJMSVersion());
            System.out.println("JMS Provider:" + metaData.getJMSProviderName());
            System.out.println("JMSX Provided:");
            Enumeration enumeration = metaData.getJMSXPropertyNames();
            while (enumeration.hasMoreElements()) {
                System.out.println(" " + enumeration.nextElement());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}

package com.broad.mq.activemq.topic.loan;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Enumeration;

public class LoanRequestBroswer {

    public static void main(String[] args) throws Exception {

        Context context = new InitialContext();

        QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("queueCF");
        QueueConnection connection = factory.createQueueConnection();

        connection.start();
        Queue queue = (Queue) context.lookup("LoanRequestQ");

        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        QueueBrowser browser = session.createBrowser(queue);

        Enumeration e = browser.getEnumeration();
        while (e.hasMoreElements()) {
            TextMessage msg = (TextMessage) e.nextElement();
            System.out.println("Browsing :" + msg.getText());
        }

        browser.close();
        connection.close();
        System.exit(0);
    }
}

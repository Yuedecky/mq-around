package com.broad.mq.activemq.topic.loan;

import com.sun.javafx.collections.MappingChange;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class QLender implements MessageListener {


    private QueueConnection connection;

    private QueueSession session;

    private Queue requestQ;


    public QLender(String queueCf, String requestQueue) {

        try {

            Context context = new InitialContext();

            QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(queueCf);
            this.connection = factory.createQueueConnection();

            this.session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            requestQ = (Queue) context.lookup(requestQueue);
            connection.start();

            QueueReceiver receiver = session.createReceiver(requestQ);

            receiver.setMessageListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMessage(Message message) {
        try {
            boolean accepted = false;
            if (message instanceof MapMessage) {
                MapMessage msg = (MapMessage) message;
                double salary = msg.getDouble("Salary");

                double loanAmount = msg.getDouble("LoanAmount");
                if (loanAmount < 20000) {
                    accepted = (salary / loanAmount) > .25;
                } else {
                    accepted = (salary / loanAmount) > .33;
                }
                System.out.println("%=" + (salary / loanAmount) + ",accepted?" + (accepted ? "Accepted!" : "Declined"));


                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(accepted ? "Accepted!" : "Declined");
                textMessage.setJMSCorrelationID(message.getJMSCorrelationID());


                QueueSender sender = session.createSender((Queue) message.getJMSReplyTo());
                sender.send(textMessage);


            }
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }


    public void exit() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

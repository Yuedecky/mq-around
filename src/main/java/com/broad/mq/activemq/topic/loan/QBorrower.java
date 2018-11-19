package com.broad.mq.activemq.topic.loan;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QBorrower {

    private QueueConnection connection;

    private QueueSession session;

    private Queue responseQ;

    private Queue requestQ;

    public QBorrower(String queueCf, String requestQ, String responseQ) {
        try {


            Context context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(queueCf);

            connection = connectionFactory.createQueueConnection();

            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            this.requestQ = (Queue) context.lookup(requestQ);
            this.responseQ = (Queue) context.lookup(responseQ);

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void sendLoanRequest(double salary, double loanAmount) {
        try {
            MapMessage message = this.session.createMapMessage();

            message.setDouble("Salary", salary);
            message.setDouble("LoanAmount", loanAmount);
            message.setJMSReplyTo(responseQ);

            QueueSender sender = session.createSender(requestQ);
            sender.send(message);

            String filter = "JMSCollelationID='" + message.getJMSCorrelationID() + "'";

            QueueReceiver receiver = session.createReceiver(responseQ, filter);
            TextMessage tms = (TextMessage) receiver.receive(3000);

            if (tms == null) {
                System.out.println("QueueLender not response");
            } else {
                System.out.println("Loan request was" + tms.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public void exit() {
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

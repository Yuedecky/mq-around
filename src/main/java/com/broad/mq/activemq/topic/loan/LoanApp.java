package com.broad.mq.activemq.topic.loan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LoanApp {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        String queueCf = null;
        String requestQ = null;
        String responseQ = null;
        if (args.length == 3) {
            queueCf = args[0];
            requestQ = args[1];
            responseQ = args[2];
        } else {
            System.err.println("usage: <queueConnectionFactory> <requestQueue> <responseQueue>");
            System.exit(0);
        }
        QBorrower borrower = new QBorrower(queueCf, requestQ, responseQ);
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("QBrorrower App started");
            System.out.println("Press enter to exit the app");
            System.out.println("Entry salary,loan_amount:");
            System.out.println("\n Eg:5000,2000");

            while (true) {
                System.out.println(">");
                String loadRequest = stdin.readLine();
                if (loadRequest == null || loadRequest.trim().length() <= 0) {
                    borrower.exit();
                }

                //解析交易说明
                StringTokenizer tokenizer = new StringTokenizer(loadRequest,",");

                double salary = Double.valueOf(tokenizer.nextToken().trim()).doubleValue();
                double amount = Double.valueOf(tokenizer.nextToken().trim()).doubleValue();

                borrower.sendLoanRequest(salary,amount);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

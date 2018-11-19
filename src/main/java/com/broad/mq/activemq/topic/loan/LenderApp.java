package com.broad.mq.activemq.topic.loan;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LenderApp {


    public static void main(String[] args) {
        String queueCf = null;

        String requestQ = null;
        if (args.length != 2) {
            System.err.println("Uasge <queueConfactory> <requestQueue>");
            System.exit(-1);
        } else {
            queueCf = args[0];
            requestQ = args[1];
        }


        QLender lender = new QLender(queueCf, requestQ);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
            lender.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

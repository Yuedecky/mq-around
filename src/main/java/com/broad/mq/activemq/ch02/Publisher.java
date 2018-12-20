package com.broad.mq.activemq.ch02;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Hashtable;
import java.util.Map;

public class Publisher {


    private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private static int MAX_DELTA_PERCENT = 1;
    private Map<String, Double> LAST_PRICES = new Hashtable<>();


    private static int count = 10;

    //记录下发送消息的总数
    private static int total = 0;


    private static String brokerUrl = "tcp://localhost:61616";


    private static transient ConnectionFactory connectionFactory;

    private transient Connection connection;

    private transient Session session;

    private transient MessageProducer messageProducer;

    public Publisher() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        messageProducer = session.createProducer(null);
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public void sendMessage(String[] stocks) throws JMSException {
        int idx = 0;
        while (true) {
            idx = (int) Math.round(stocks.length * Math.random());
            if (idx < stocks.length) {
                break;
            }
        }

        String stock = stocks[idx];

        Destination destination = session.createTopic("STOCKS." + stock);

        Message message = createStockMessage(stock, session);
        logger.info("Sending:{} on destination:{}", ((ActiveMQMapMessage) message).getContentMap(), destination);
        messageProducer.send(destination, message);
    }

    /**
     * 创建stock消息
     *
     * @param stock
     * @param session
     * @return
     * @throws JMSException
     */
    private Message createStockMessage(String stock, Session session) throws JMSException {
        Double value = LAST_PRICES.get(stock);

        //模拟上一次的随机的股票价格
        if (value == null) {
            value = new Double(Math.random() * 100);
        }

        double oldPrice = value.doubleValue();

        //获取最新的股票价格
        value = new Double(mutatePrice(oldPrice));

        LAST_PRICES.put(stock, value);

        double price = value.doubleValue();

        double offer = price * 1.001;
        boolean up = (price > oldPrice);

        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("stock", stock);
        mapMessage.setDouble("price", price);
        mapMessage.setDouble("offer", offer);

        mapMessage.setBoolean("up", up);
        return mapMessage;
    }


    /**
     * 模拟最新的股票价格
     *
     * @param price
     * @return
     */
    protected double mutatePrice(double price) {
        double percentChange = (2 * Math.random() - 1) * MAX_DELTA_PERCENT;

        return price * (100 + percentChange) / 100;
    }

    /**
     * Test main
     *
     * @param args
     * @throws JMSException
     */
    public static void main(String[] args) throws JMSException {

        //1.创建一个producer对象
        Publisher publisher = new Publisher();

        while (total < 1000) {

            for (int i = 0; i < count; i++) {
                publisher.sendMessage(args);
            }


            total += count;
            logger.info("Published '{}' of '{}' price messages ", count, total);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException x) {
            }
        }

        publisher.close();
    }
}

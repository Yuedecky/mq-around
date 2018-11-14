package com.broad.mq.activemq.topic.chat;

import javax.jms.*;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Chat implements MessageListener {


    private TopicSession topicSession;

    private TopicPublisher topicPublisher;

    private TopicConnection topicConnection;

    private String username;

    public Chat(String topicFactory, String topicName, String username) throws Exception {
        //使用jndi.properties获取一个jndi链接
        InitialContext context = new InitialContext();
        //查找jms链接工厂 并创建jms链接
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup(topicFactory);

        TopicConnection connection = connectionFactory.createTopicConnection();

        TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        //查找jms主题
        Topic chatTopic = (Topic) context.lookup(topicName);

        TopicPublisher publisher = pubSession.createPublisher(chatTopic);

        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic);

        //设置jms监听器
        subscriber.setMessageListener(this);

        //初始化程序链接
        this.topicConnection = connection;

        this.topicSession = pubSession;
        this.topicPublisher = publisher;
        this.username = username;


    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;

            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布创建消息
     *
     * @param text
     * @throws Exception
     */
    public void writeMessage(String text) throws Exception {
        TextMessage message = topicSession.createTextMessage();
        message.setText(username + ":" + text);
        topicPublisher.publish(message);


    }

    public void close() throws Exception {
        topicConnection.close();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("<Factory> <Topic> <username>");
            System.exit(-1);
        }
        Chat chat = new Chat(args[0], args[1], args[2]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = reader.readLine();
            if (s.equalsIgnoreCase("exit")) {
                chat.close();
                System.exit(0);
            } else {
                chat.writeMessage(s);
            }
        }
    }
}

package com.xxq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author xiaoqiang
 * @Title: JMSQueueProducer
 * @ProjectName activemq-producer
 * @Description: TODO
 * @date 2019-03-24 18:40
 */
public class JMSQueueProducer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.215.128:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            // 带事务性质
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 创建目的地
            Destination destination = session.createQueue("myQueue");
            // 创建发送者
            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage("hello world");
            // Test Map Bytes Stream Object
            producer.send(textMessage);
            session.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {

                }
            }
        }

    }
}

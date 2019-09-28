package cn.rumoss.study.activemq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TransSender {

    public static void main(String[] args) throws Exception {

        String username = "admin";
        String password = "admin";
        String brokerURL = "tcp://192.168.2.113:61616";
        // (1)
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username,password,brokerURL);
        // (2)
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // (3) Use Transaction
        //Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);// auto mode
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);// client mode
        // (4)
        String queueName = "myQueue";
        Destination destination = session.createQueue(queueName);
        // (5)
        MessageProducer messageProducer = session.createProducer(destination);
        // (6)
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // (7)
        //TextMessage textMessage = new ActiveMQTextMessage();
        //textMessage.setText("I am a ActiveMQ text message");
        TextMessage textMessage = session.createTextMessage("I am a ActiveMQ text message");
        messageProducer.send(textMessage);

        // Using Transaction need to commit, otherwise broker can not receive any message
        session.commit();

        if(connection!=null){
            connection.close();// if not close, main thread will be blocked
        }

    }

}

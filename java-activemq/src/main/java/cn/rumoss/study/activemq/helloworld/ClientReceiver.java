package cn.rumoss.study.activemq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ClientReceiver {

    public static void main(String[] args) throws Exception {

        String username = "admin";
        String password = "admin";
        String brokerURL = "tcp://192.168.2.113:61616";
        // (1)
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username,password,brokerURL);
        // (2)
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // (3)
        //Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);// auto mode
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);// client mode
        // (4)
        String queueName = "myQueue";
        Destination destination = session.createQueue(queueName);
        // (5)
        MessageConsumer messageConsumer = session.createConsumer(destination);

        while (true){
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if(textMessage==null)   break;
            System.out.println("Receive content: " + textMessage.getText());
            // Using another thread to send message ACK
            textMessage.acknowledge();
        }

        if(connection!=null){
            connection.close();// if not close, main thread will be blocked
        }

    }

}

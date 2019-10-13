package cn.rumoss.study.activemq.pub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publish {

    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public Publish(){
        try {

            factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                    ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    "tcp://192.168.2.113:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(null);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMessgae() throws Exception {
        Destination destination = session.createTopic("topic1");
        TextMessage textMessage = session.createTextMessage("I am the content ...");
        producer.send(destination, textMessage);
    }

    public static void main(String[] args) throws Exception {

        Publish pub = new Publish();
        pub.sendMessgae();

    }

}

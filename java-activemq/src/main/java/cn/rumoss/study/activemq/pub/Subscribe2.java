package cn.rumoss.study.activemq.pub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Subscribe2 {

    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    public Subscribe2(){

        try {

            factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                    ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    "tcp://192.168.2.113:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void receive() throws Exception{
        Destination destination = session.createTopic("topic1");
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if(message instanceof TextMessage){

                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("Receive message: " + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public static void main(String[] args) throws Exception {

        Subscribe2 sub = new Subscribe2();
        sub.receive();

    }

}

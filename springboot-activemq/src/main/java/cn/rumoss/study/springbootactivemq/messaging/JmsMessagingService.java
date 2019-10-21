package cn.rumoss.study.springbootactivemq.messaging;

import cn.rumoss.study.springbootactivemq.entity.Mail;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsMessagingService implements MessagingService {

    private JmsTemplate jms;
    private Destination mailQueue;

    @Autowired
    public JmsMessagingService(JmsTemplate jms, Destination mailQueue) {
        this.jms = jms;
        this.mailQueue = mailQueue;
    }

    @Override
    public void sendMessage(String message) {

        // (1)Default destination
        //jms.send(session -> session.createTextMessage(message));
        /*jms.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });*/

        // (2)With destination
        //jms.send(mailQueue, session -> session.createObjectMessage(mail));
        jms.convertAndSend(message);

    }

    @Override
    public void sendMail(Mail mail) {

        // (1)convertAndSend
        //jms.convertAndSend(mailQueue, mail);
        /*jms.convertAndSend(mailQueue, mail, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty("source", "mailtest");
                return message;
            }
        });*/

        // (2) MessagePostProcessor.postProcessMessage()
        jms.convertAndSend(mailQueue, mail, this::addMailSource);

    }

    private Message addMailSource(Message message) throws JMSException {
        message.setStringProperty("source", "mailtest");
        return message;
    }

    //////////////////////////////////////////////////////////////

    @Override
    public String receiveMessage() {
        return null;
    }

    @Override
    public Mail receiveMaill() {
        return (Mail) jms.receiveAndConvert(mailQueue);
    }

}

package cn.rumoss.study.springbootactivemq.listener;

import cn.rumoss.study.springbootactivemq.config.MailConfig;
import cn.rumoss.study.springbootactivemq.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private MailConfig mailConfig;

    @JmsListener(destination = "default.message.queue")
    public void receiveMessage(String message) {
        System.out.println("Receive message: " + message);
    }

    @JmsListener(destination = "mail.message.queue")
    public void receiveMaill(Mail mail) {
        System.out.println("Receive mail: " + mail);
//        simpleMailMessage.setFrom(mailConfig.getEmailFrom());
        simpleMailMessage.setFrom(mail.getFrom());
        simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getContent());
        mailSender.send(simpleMailMessage);
    }

}

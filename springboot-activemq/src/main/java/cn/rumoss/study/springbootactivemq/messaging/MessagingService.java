package cn.rumoss.study.springbootactivemq.messaging;

import cn.rumoss.study.springbootactivemq.entity.Mail;

public interface MessagingService {

    void sendMessage(String message);

    void sendMail(Mail mail);

    String receiveMessage();

    Mail receiveMaill();

}

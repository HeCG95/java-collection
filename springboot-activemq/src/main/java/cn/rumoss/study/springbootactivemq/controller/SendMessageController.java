package cn.rumoss.study.springbootactivemq.controller;

import cn.rumoss.study.springbootactivemq.entity.Mail;
import cn.rumoss.study.springbootactivemq.messaging.MessagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    private MessagingService messagingService;

    public SendMessageController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @GetMapping("/sendMessage")
    public String sendMessage() {
        messagingService.sendMessage("This is a message, date: " + new Date().toString());
        return "Send success";
    }

    @GetMapping("/sendMail")
    public String sendMail() {
        Mail mail = new Mail();
        mail.setFrom("xiaotang@9f0.net");
        mail.setTo("2656259928@qq.com");
        mail.setSubject("Mail send test ...");
        mail.setContent("Well, this is the mail content ...");
        messagingService.sendMail(mail);
        return "Send success";
    }

}

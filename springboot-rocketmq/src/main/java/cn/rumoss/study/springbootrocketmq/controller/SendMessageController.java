package cn.rumoss.study.springbootrocketmq.controller;

import cn.rumoss.study.springbootrocketmq.domain.OrderPaidEvent;
import cn.rumoss.study.springbootrocketmq.messaging.MessagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    @GetMapping("/sendMessageOrder")
    public String sendMessageOrder() {
        // May happen repeat consume message
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent("T_001", new BigDecimal("88.00"));
        messagingService.sendOrderPaidEvent(orderPaidEvent);
        return "Send orderPaidEvent success";
    }

    @GetMapping("/sendTransMessage")
    public String sendTransMessage() {
        messagingService.sendTransMessage();
        return "send transMessage success";
    }

}

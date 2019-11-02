package cn.rumoss.study.springbootrocketmq.messaging;

import cn.rumoss.study.springbootrocketmq.domain.OrderPaidEvent;

public interface MessagingService {

    void sendMessage(String message);

    void sendOrderPaidEvent(OrderPaidEvent orderPaidEvent);

    void sendTransMessage();

    String receiveMessage();

}

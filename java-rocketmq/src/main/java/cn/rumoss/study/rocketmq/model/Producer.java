package cn.rumoss.study.rocketmq.model;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class Producer {

    public static void main(String[] args) throws MQClientException,InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("message_producer");
        producer.setNamesrvAddr("192.168.2.182:9876;192.168.2.183:9876");

        producer.start();

        for(int i=0;i<10;i++){
//        for(int i=0;i<1;i++){
            try {
                Message message = new Message("Topic1",
                        "Tag1",
                        ("Message Content " + i).getBytes());
                SendResult sendResult = producer.send(message);
                System.out.println(sendResult);
            }catch (Exception e){
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();

    }

}

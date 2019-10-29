package cn.rumoss.study.rocketmq.quickstart;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class ConsumerRetryWork {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("quickstart_consumer");
        consumer.setNamesrvAddr("192.168.2.182:9876;192.168.2.183:9876");

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // Set message batch max size
        consumer.setConsumeMessageBatchMaxSize(8);

        consumer.subscribe("TopicQuickStart", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //@Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + " Receive message size: " + msgs.size());
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
                MessageExt msg = msgs.get(0);
                try {

                    String msgBody = new String(msg.getBody(), "UTF-8");
                    if("Hello RocketMQ 6".equals(msgBody)){
                        // Test Consume exception occur
                        int ans = 1%0;
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                    // Add here
                    int reconsumeTimes = msg.getReconsumeTimes();
                    System.out.println("Current reconsume times: " + reconsumeTimes);
                    if(reconsumeTimes==3){// After retry 3 times
                        // Add compensation ops
                        // adding logs and persist to database ...
                        System.out.println("adding logs and persist to database ...");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }else {
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.println("Consumer Started.");

    }

}

package cn.rumoss.study.rocketmq.quickstart;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PullConsumer {

    // key为指定的队列，value为这个队列拉取数据的最后位置
    final static Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws MQClientException {

        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("quickstart_pull_consumer");
        consumer.setNamesrvAddr("192.168.2.182:9876;192.168.2.183:9876");
        consumer.start();

        // 从TopicQuickStart这个主题去获取所有的队列(默认会有4个队列)
        Set<MessageQueue> msqs = consumer.fetchSubscribeMessageQueues("TopicQuickStart");
        // 遍历每一个队列，进行拉取数据
        System.out.println("Queue size: " + msqs.size());
        for (MessageQueue mq : msqs) {

            System.out.println("Consume from the queue: " + mq);
            SINGLE_MQ: while(true){
                try{

                    // 从queue中获取数据，从什么位置开始拉取数据，单次最多拉取32条记录 getMessageQueueOffset(mq)
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    System.out.println("Pull result: " + pullResult);
                    System.out.println("Pull status: " + pullResult.getPullStatus());
                    System.out.println();

                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());

                    switch (pullResult.getPullStatus()){
                        case FOUND:
                            List<MessageExt> list = pullResult.getMsgFoundList();
                            for(MessageExt msg : list){
                                System.out.println(new String(msg.getBody()));
                            }
                            break ;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            System.out.println("没有新消息 ...");
                            break;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

        System.out.println("Consumer Started.");

    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset){
        offsetTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq){
        Long offset = offsetTable.get(mq);
        if(offset!=null)
            return offset;
        return 0;
    }

}

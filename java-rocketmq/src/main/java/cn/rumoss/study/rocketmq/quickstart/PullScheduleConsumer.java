package cn.rumoss.study.rocketmq.quickstart;

import com.alibaba.rocketmq.client.consumer.*;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class PullScheduleConsumer {

    public static void main(String[] args) throws MQClientException{

        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService("schedule_consumer");

        scheduleService.getDefaultMQPullConsumer().setNamesrvAddr("192.168.2.182:9876;192.168.2.183:9876");
        scheduleService.setMessageModel(MessageModel.CLUSTERING);
        scheduleService.registerPullTaskCallback("TopicQuickStart", new PullTaskCallback() {
            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {

                MQPullConsumer consumer = context.getPullConsumer();
                try {

                    // 获取从哪里拉取
                    long offset = consumer.fetchConsumeOffset(mq, false);
                    if(offset<0)    offset=0;
                    PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                    switch (pullResult.getPullStatus()){
                        case FOUND:
                            List<MessageExt> list = pullResult.getMsgFoundList();
                            for(MessageExt msg : list){
                                System.out.println(new String(msg.getBody()));
                            }
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    // 存储Offset，客户端每隔5s会定时刷新到broker
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        scheduleService.start();

    }

}

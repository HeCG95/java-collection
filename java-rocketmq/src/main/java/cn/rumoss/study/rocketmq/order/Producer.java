/**
 * Copyright (C) 2010-2013 Alibaba Group Holding Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.rumoss.study.rocketmq.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;


/**
 * Producer，发送顺序消息
 */
public class Producer {
    public static void main(String[] args) {

        try {
            DefaultMQProducer producer = new DefaultMQProducer("order_producer");
            producer.setNamesrvAddr("192.168.2.182:9876;192.168.2.183:9876");

            producer.start();

            //String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);

            for (int i = 1; i <= 5; i++) {
                // 订单ID相同的消息要有序
                /*int orderId = i % 10;
                Message msg =
                        new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i,
                            ("Hello RocketMQ " + i).getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);

                System.out.println(sendResult);*/

                String body = dateStr + " Hello order_0 " + i;
                Message message = new Message("TopicTest", "order_0", "KEY" + i, body.getBytes());

                SendResult sendResult = producer.send(message,(mqs,msg,arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                },0);// 0是队列的下标，每个队列保证严格的顺序

                System.out.println(sendResult + " ,body: " + body);

            }
            for (int i = 1; i <= 5; i++) {
                String body = dateStr + " Hello order_1 " + i;
                Message message = new Message("TopicTest", "order_1", "KEY" + i, body.getBytes());

                SendResult sendResult = producer.send(message,(mqs,msg,arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                },1);// 1是队列的下标，每个队列保证严格的顺序
                System.out.println(sendResult + " ,body: " + body);
            }
            for (int i = 1; i <= 5; i++) {
                String body = dateStr + " Hello order_2 " + i;
                Message message = new Message("TopicTest", "order_2", "KEY" + i, body.getBytes());
                SendResult sendResult = producer.send(message,(mqs,msg,arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                },2);// 2是队列的下标，每个队列保证严格的顺序
                System.out.println(sendResult + " ,body: " + body);
            }

            producer.shutdown();
        }
        catch (MQClientException e) {
            e.printStackTrace();
        }
        catch (RemotingException e) {
            e.printStackTrace();
        }
        catch (MQBrokerException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

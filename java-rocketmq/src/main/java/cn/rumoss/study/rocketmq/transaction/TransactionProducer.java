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
package cn.rumoss.study.rocketmq.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;


/**
 * 发送事务消息例子
 * 
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {

        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer");
        producer.setNamesrvAddr("192.168.2.182:9876;192.168.2.183:9876");

        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);

        // 检查本地事务成功还是失败了
        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        producer.setTransactionCheckListener(transactionCheckListener);

        producer.start();

        String[] tags = new String[] { "TagA", "TagB"};
        TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();

        //for (int i = 0; i < 2; i++) {
        for (int i = 0; i < 1; i++) {
            try {
                Message msg =
                        new Message("TopicTransaction", tags[i % tags.length], "KEY" + i,
                            ("Hello RocketMQ " + i).getBytes());
                SendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, "TQ");
                System.out.println(sendResult);

                Thread.sleep(10);
            }
            catch (MQClientException e) {
                e.printStackTrace();
            }
        }

        /*for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }*/

        producer.shutdown();

    }
}

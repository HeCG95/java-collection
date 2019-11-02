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

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;


/**
 * 执行本地事务
 */
public class TransactionExecuterImpl implements LocalTransactionExecuter {

    //private AtomicInteger transactionIndex = new AtomicInteger(1);

    @Override
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, final Object arg) {
        //int value = transactionIndex.getAndIncrement();
        /*if (value == 0) {
            throw new RuntimeException("Could not find db");
        }
        else if ((value % 5) == 0) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        else if ((value % 4) == 0) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }*/
        String tags = msg.getTags();
        System.out.println("Local trans receive message: " + msg);
        System.out.println("arg: " + arg);
        if("TagA".equals(tags)){
            System.out.println("Received message, but local transaction failure, need to rollback ...");
//            return LocalTransactionState.ROLLBACK_MESSAGE;
            return LocalTransactionState.UNKNOW;
        }else
            return LocalTransactionState.COMMIT_MESSAGE;
    }
}

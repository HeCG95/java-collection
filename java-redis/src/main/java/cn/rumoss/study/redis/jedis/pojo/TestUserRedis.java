package cn.rumoss.study.redis.jedis.pojo;

import cn.rumoss.study.redis.jedis.util.FastJsonConvert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestUserRedis {

    // select * from user where age = 25
    // select * from user where age = 25 and sex = "W"
    // 500 w records
    // how take NOSQL-Redis use as RDB

    @Test
    public void testUser() {

        String host = "192.168.2.113";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);

        Map<String, String> map = new HashMap<String, String>();
        for(int i=0;i<10;i++){
            User user = new User();
            user.setId("user-" + i + "-" + UUID.randomUUID().toString());
            user.setName("HeCG-"+i);
            user.setAge(18+i);
            user.setSex(i%2==0?"M":"W");
            map.put(user.getId(), FastJsonConvert.convertObject2JSON(user));
        }

        jedis.hmset("users", map);

    }

}

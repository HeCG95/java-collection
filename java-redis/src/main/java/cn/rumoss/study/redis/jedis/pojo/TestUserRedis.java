package cn.rumoss.study.redis.jedis.pojo;

import cn.rumoss.study.redis.jedis.util.FastJsonConvert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TestUserRedis {

    final String SYS_USER_SEL_AGE_25 = "SYS_USER_SEL_AGE_25";
    final String SYS_USER_SEL_SEX_M = "SYS_USER_SEL_SEX_M";
    final String SYS_USER_SEL_SEX_W = "SYS_USER_SEL_SEX_W";
    final String SYS_USER_TABLE = "SYS_USER_TABLE";

    // select * from user where age = 25
    // select * from user where age = 25 and sex = "W"
    // 500 w records
    // how take NOSQL-Redis use as RDB

    // User Role Group Org --> View
    // hash and set with condition, ops with union inter

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

    // Solution: use type Set and Hash
    // Specific query business: SYS_USER_SEL_AGE_25
    // Specific query business: SYS_USER_SEL_SEX_M
    // Specific query business: SYS_USER_SEL_SEX_W
    @Test
    public void testUserWithSelect() {

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

            if(user.getAge()==25){
                jedis.sadd(SYS_USER_SEL_AGE_25, user.getId());
            }
            if("W".equals(user.getSex())){
                jedis.sadd(SYS_USER_SEL_SEX_W, user.getId());
            }else if("M".equals(user.getSex())){
                jedis.sadd(SYS_USER_SEL_SEX_M, user.getId());
            }

            map.put(user.getId(), FastJsonConvert.convertObject2JSON(user));
        }

        jedis.hmset(SYS_USER_TABLE, map);

    }
    /**
     * Redis result:
     127.0.0.1:6379> keys *
     1) "SYS_USER_SEL_SEX_W"
     2) "SYS_USER_SEL_AGE_25"
     3) "SYS_USER_SEL_SEX_M"
     4) "SYS_USER_TABLE"

     To achieve equals to - select * from user where age = 25 and sex = "W",
        just use sinter SYS_USER_SEL_SEX_W SYS_USER_SEL_AGE_25
     127.0.0.1:6379> sinter SYS_USER_SEL_SEX_W SYS_USER_SEL_AGE_25
     1) "user-7-2fe3fc16-ab15-49e3-8ada-b5f519464ee3"
     127.0.0.1:6379> sinter SYS_USER_SEL_SEX_M SYS_USER_SEL_AGE_25
     (empty list or set)
     *
     */

    @Test
    public void testSelectUserAge25AndSexW() {

        String host = "192.168.2.113";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);

        Set<String> ids = jedis.sinter(SYS_USER_SEL_AGE_25, SYS_USER_SEL_SEX_W);
        for(String id : ids){
            String  user = jedis.hget(SYS_USER_TABLE, id);
            System.out.println("Find user: " + user);
            User userObj = FastJsonConvert.convertJSON2Object(user, User.class);
            System.out.println("User object: " + userObj);
        }

    }

}

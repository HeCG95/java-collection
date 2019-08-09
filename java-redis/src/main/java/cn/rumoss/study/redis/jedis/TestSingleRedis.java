package cn.rumoss.study.redis.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.*;

public class TestSingleRedis {

    // (1) Single node
    private static Jedis jedis;
    // (2) Master Slave and sentinel
    private static ShardedJedis shardedJedis;
    // (3) Connection pools
    private static ShardedJedisPool jedisPool;

    @BeforeClass
    public static void initJedis() {

        String host = "192.168.2.113";
        int port = 6379;
        // Single node
        jedis = new Jedis(host, port);

        // Fragment
        List<JedisShardInfo> shardInfos = Arrays.asList(
                new JedisShardInfo(host, port)
        );
        shardedJedis = new ShardedJedis(shardInfos);

        int maxTotal = 100;
        int maxIdle = 20;
        // Pool
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(-1);
        config.setTestOnBorrow(true);
        jedisPool = new ShardedJedisPool(config, shardInfos);

    }

    @Test
    public void testJedis() {

//        System.out.println(jedis.keys("*"));
        List<String> list = jedis.mget("name", "p1", "p2", "p3");
        for(Iterator iterator=list.iterator();iterator.hasNext();){
            String value = String.valueOf(iterator.next());
            System.out.println(value);
        }

    }

    @Test
    public void testMap() {

        String key = "user";
        // add data
        Map<String, String> map = new HashMap<String, String>();
        map.put("name","HeCG");
        map.put("age","18");
        map.put("wechat","xxxxxxx");
        jedis.hmset(key, map);

        List<String> returnMap = jedis.hmget(key, "name", "age", "wechat");
        System.out.println(returnMap);

        jedis.hdel(key, "age");
        System.out.println(jedis.hmget(key, "name", "age"));

        System.out.println("jedis.hlen: " + jedis.hlen(key));
        System.out.println("jedis.exists: " + jedis.exists(key));
        System.out.println("jedis.hkeys: " + jedis.hkeys(key));
        System.out.println("jedis.hvals: " + jedis.hvals(key));

        Iterator<String> iterator = jedis.hkeys(key).iterator();
        while (iterator.hasNext()){
            String field = iterator.next();
            System.out.println(field + " : " + jedis.hget(key, field));
        }

    }
    /**
     * Result of the demo:testMap()
     [HeCG, 18, xxxxxxx]
     [HeCG, null]
     jedis.hlen: 2
     jedis.exists: true
     jedis.hkeys: [wechat, name]
     jedis.hvals: [xxxxxxx, HeCG]
     wechat : xxxxxxx
     name : HeCG
     *
     */

    @Test
    public void testShardedJedisPipeline() {

        ShardedJedisPipeline pipeline = shardedJedis.pipelined();
        long start = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            pipeline.set("sp"+i, "p"+i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("ShardedJedisPipeline SET: " + ((end-start)/1000.0) + " seconds");

    }

    /**
     * Result of the demo:testShardedJedisPipeline()
     ShardedJedisPipeline SET: 3.916 seconds
     *
     */

    @Test
    public void testShardedJedisPool() {

        ShardedJedis shardedJedis = jedisPool.getResource();

        long start = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            String result = shardedJedis.set("spn"+i, "n"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("ShardedJedisPool SET: " + ((end-start)/1000.0) + " seconds");

    }

}

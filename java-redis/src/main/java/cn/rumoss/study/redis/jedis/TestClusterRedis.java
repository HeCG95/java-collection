package cn.rumoss.study.redis.jedis;

import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class TestClusterRedis {

    private static JedisCluster jedisCluster;

    @BeforeClass
    public static void initJedisCluster() {

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        String host = "192.168.2.113";
        int port = 7000;
        for(int i=0;i<6;i++){// Port from 7000 to 7005
            jedisClusterNodes.add(new HostAndPort(host,port+i));
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        int maxTotal = 100;
        int maxIdle = 20;
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(-1);
        jedisPoolConfig.setTestOnBorrow(true);

        jedisCluster = new JedisCluster(jedisClusterNodes, 3000, 10, jedisPoolConfig);

    }

    @Test
    public void testGet(){

        System.out.println(jedisCluster.get("name"));
        System.out.println(jedisCluster.get("age"));
        System.out.println(jedisCluster.getClusterNodes());

    }
    /**
     * Result of the demo:testGet()
     hecg-7001
     18
     {192.168.2.113:7005=redis.clients.jedis.JedisPool@23223dd8, 192.168.2.113:7002=redis.clients.jedis.JedisPool@4ec6a292,
     192.168.2.113:7001=redis.clients.jedis.JedisPool@1b40d5f0, 192.168.2.113:7004=redis.clients.jedis.JedisPool@ea4a92b,
     192.168.2.113:7003=redis.clients.jedis.JedisPool@3c5a99da, 192.168.2.113:7000=redis.clients.jedis.JedisPool@47f37ef1}
     *
     */

}

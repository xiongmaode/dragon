package com.example.dragon.redis.conf;

import com.example.dragon.redis.entity.RedisProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassNAME JedisSingleConfig
 * @Description redis配置
 * @Author Xiongmao
 * @Date 2019-06-18
 */
@Configuration
public class JedisSingleConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 注意：
     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
     *
     * @return
     */
    @Bean
    public JedisCluster getJedisCluster() {
        String[] serverArray = redisProperties.getClusterNodes().split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        /** 密码Redis */
        if(redisProperties.isFlag()){
            return new JedisCluster(nodes, redisProperties.getCommandTimeout(), redisProperties.getCommandTimeout(), redisProperties.getMaxRedirections(), redisProperties.getPassword(), new GenericObjectPoolConfig());
        }
        return new JedisCluster(nodes, redisProperties.getCommandTimeout());
    }

}

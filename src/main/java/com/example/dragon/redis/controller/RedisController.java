package com.example.dragon.redis.controller;

import com.example.dragon.redis.conf.RedisJpaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNAME RedisController
 * @Description Redis 处理工具
 * @Author Xiongmao
 * @Date 2019-06-17
 */
@RestController("/redis")
public class RedisController {

    @Autowired
    private RedisJpaTemplate redisJpaTemplate;

    //获取redis数据
    @PostMapping("/getValue")
    public String redis(String prefix, String key){
        return redisJpaTemplate.get(prefix,key);
    }
}

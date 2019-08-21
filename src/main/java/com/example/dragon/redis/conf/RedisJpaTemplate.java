/**
 * 版权 @Copyright: 2017 www.sinosoft.com.cn. All rights reserved.
 * 项目名称：micro-service-redis<br/>
 * 文件名称： RedisJpaTemplate.java<br/>
 * 包名：cn.com.epicc.ecommerce.redis
 * 创建人：lidongsheng<br/>
 * 创建时间：2017年9月7日/下午3:28:01<br/>
 * 修改人：lidongsheng<br/>
 * 修改时间：2017年9月7日/下午3:28:01<br/>
 * 修改备注：<br/>
 */
package com.example.dragon.redis.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;

@Component
public class RedisJpaTemplate {

    @Autowired
    private JedisCluster jedisCluster;

    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值
    private static final String ROOT_KET = "DRAGON";
    private static final int EXPIRE_SECONDS = 120;

    /**
     * 设置缓存
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key    缓存key
     * @param value  缓存value
     */
    public void set(String prefix, String key, String value) {
        jedisCluster.set(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key, value);
    }

    /**
     * 设置缓存，并且自己指定过期时间
     * @param prefix
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public void setWithExpireTime(String prefix, String key, String value, int expireTime) {
        jedisCluster.setex(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key, expireTime, value);
    }

    /**
     * 设置缓存，并且由配置文件指定过期时间
     * @param prefix
     * @param key
     * @param value
     */
    public void setWithExpireTime(String prefix, String key, String value) {
        jedisCluster.setex(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key, EXPIRE_SECONDS, value);
    }

    /**
     * 获取指定key的缓存
     * @param prefix
     * @param key
     */
    public String get(String prefix, String key) {
    	return jedisCluster.get(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key);
    }

    /**
     * 删除指定key的缓存
     * @param prefix
     * @param key
     */
    public void deleteWithPrefix(String prefix, String key) {
        jedisCluster.del(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key);
    }

    /**
     * 方法名称: incr
     * 描述: redis 递增，一次加1
     * 参数: @param prefix
     * 参数: @param key
     * 返回值: void
     * @throws
     */
    public long incr(String prefix, String key){
    	return jedisCluster.incr(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key);
    }

    /**
     * 方法名称: incrBy
     * 描述: redis 递增，一次加 integer
     * 参数: @param prefix
     * 参数: @param key
     * 参数: @param integer
     * 返回值: void
     * @throws
     */
    public long incrBy(String prefix, String key, long integer){
    	return jedisCluster.incrBy(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key,integer);
    }

    /**
     * 方法名称: incrByFloat
     * 描述: redis 递增，一次加 double
     * 参数: @param prefix
     * 参数: @param key
     * 参数: @param value
     * 返回值: void
     * @throws
     */
    public double incrByFloat(String prefix, String key, double value){
    	return jedisCluster.incrByFloat(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key, value);
    }

    /**
     * 方法名称: decr
     * 描述: redis 递减，一次减1
     * 参数: @param prefix
     * 参数: @param key
     * 返回值: void
     * @throws
     */
    public long decr(String prefix, String key){
    	return jedisCluster.decr(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key);
    }


    /**
     * 方法名称: decrBy
     * 描述: redis 减 long
     * 参数: @param prefix
     * 参数: @param key
     * 参数: @param integer
     * 返回值: void
     * @throws
     */
    public long decrBy(String prefix, String key, long integer){
    	return jedisCluster.decrBy(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key, integer);
    }

    /**
     * map 存储
     * @param prefix
     * @param key
     */
    public void hmset(String prefix,String key,Map<String, String> map){
    	jedisCluster.hmset(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key, map);
    }

    /**
     * 获取map ，以list 方式返回
     * @param prefix
     * @param key
     */
    public List<String> hmget(String prefix,String key,String... fileds){
    	return jedisCluster.hmget(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key, fileds);
    }

    /**
     * 获取map ，以map 方式返回
     * @param prefix
     * @param key
     */
    public Map<String, String> hgetAll(String prefix,String key){
    	return jedisCluster.hgetAll(ROOT_KET+KEY_SPLIT+prefix + KEY_SPLIT + key);
    }

}

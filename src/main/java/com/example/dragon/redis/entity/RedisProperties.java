/**
 * 版权 @Copyright: 2017 www.sinosoft.com.cn. All rights reserved.
 * 项目名称：micro-service-redis<br/>
 * 文件名称： RedisProperties.java<br/>
 * 包名：cn.com.epicc.ecommerce.redis
 * 创建人：lidongsheng<br/>
 * 创建时间：2017年9月7日/下午3:28:10<br/>
 * 修改人：lidongsheng<br/>
 * 修改时间：2017年9月7日/下午3:28:10<br/>
 * 修改备注：<br/>
 */
package com.example.dragon.redis.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis.cache")
public class RedisProperties {

    /**
     * 默认失效时间
     */
    private int expireSeconds = 120;
    /**
     * 集群节点
     */
    private String clusterNodes;
    /**
     * 连接超时时间
     */
    private int connectionTimeout = 2000;
    /**
     *
     */
    private int soTimeout = 3000;
    /**
     *
     */
    private int maxRedirections = 5;
    /**
     *
     */
    private int commandTimeout = 3000;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(int maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    /**
     * commandTimeout
     *
     * @return the commandTimeout
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getCommandTimeout() {
        return commandTimeout;
    }

    /**
     * @param commandTimeout the commandTimeout to set
     */
    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }
}

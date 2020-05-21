package com.example.dragon.main.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

/**
 * 本类负责调用接口
 *
 * @version：1.0
 */
@Slf4j
public class HttpClientUtils {

    /**
     * 禁止实例化
     */
    private HttpClientUtils() {

    }

    /**
     * @Description post请求
     * @Param [uuid, url, data]
     * @Return java.lang.String
     */
    public static String postTemplate(String uuid,String url, Object data) throws ConnectException {
        return postTemplate(uuid,url,data,"utf-8",60000);
    }

    /**
     * restTemplate请求
     *
     * @return
     * @throws ConnectException
     */
    public static String postTemplate(String uuid, String url, Object data, String encodeType,int timeOut) throws ConnectException {
        log.info("【uuid:" + uuid + "】调用第三方接口地址:" + url);
        log.info("【uuid:" + uuid + "】请求报文:" + JsonUtils.bean2json(data));
        log.info("【uuid:" + uuid + "】请求开始，超时时间:" + timeOut);
        String resultStr = "";
        try {
            //HttpRequestFactory初始化请求参数
            SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
            httpRequestFactory.setReadTimeout(timeOut);
            httpRequestFactory.setConnectTimeout(timeOut);
            //创建请求对象
            RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
            //设置请求报文头参数
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept-Charset", encodeType);
            HttpEntity<Object> request = new HttpEntity<Object>(data, headers);
            Object resStr = restTemplate.postForObject(url, request, Object.class);
            resultStr = JsonUtils.bean2json(resStr);
        }catch (Exception e){
            log.error(uuid, e);
            throw new ConnectException("第三方接口连接失败");
        } finally {
            log.info("【uuid:" + uuid + "】发送请求结束，返回报文：" + resultStr);
            log.info("【uuid:" + uuid + "】调用第三方接口结束");
        }
        return resultStr;
    }

}

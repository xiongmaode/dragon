package com.example.dragon.main.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 项目名称：util
 * 类名称：JsonUtil
 * 类描述：json报文转换服务,所有转json报文都用这个服务,便于后期扩展微服务和替换转json工具
 * 创建人：lidongsheng
 * 创建时间：2017年9月13日 下午3:19:21
 * 修改人：lidongsheng
 * 修改时间：2017年9月13日 下午3:19:21
 * 修改备注：
 * @version 
 *
 */
public class JsonUtil {

	
	/**
	 * 禁止实例化
	 */
	private JsonUtil(){
		
	}
	
    /**
     * 
     * 方法名称: json2bean
     * 描述: json报文转model方法
     * 参数: @param json
     * 参数: @param clazz
     * 参数: @return 
     * 返回值: T
     * @throws
     */
    public static <T> T json2bean(String json, Class<T> clazz){
    	return JSONObject.toJavaObject(JSONObject.parseObject(json), clazz);
    }
    
    
    /**
     * 
     * 方法名称: json2JSONObject
     * 描述: json报文转Object(JSONObject)方法
     * 参数: @param json
     * 参数: @return 
     * 返回值: Object
     * @throws
     */
    public static Object json2JSONObject(String json){
    	return JSONObject.parseObject(json);
    }
    
    
    /**
     * 
     * 方法名称: bean2json
     * 描述: Tmodel转json方法
     * 参数: @param bean
     * 参数: @return 
     * 返回值: String
     * @throws
     */
    public static String bean2json(Object bean) {
    	return JSONObject.toJSONString(bean);
    }
    
    
    
}

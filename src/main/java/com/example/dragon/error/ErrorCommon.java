package com.example.dragon.error;


import com.example.dragon.error.common.ResCommon;

/**
 * @Deprecated 处理返回报文
 * @Author Songxinwei
 * @Date 2020/2/11
 */
public class ErrorCommon {

    /**
     * 组装返回公共信息
     *
     * @param errorMessage
     * @param common
     */
    public static void errorInfo(ErrorMessage errorMessage, ResCommon common) {
        common.setCode(errorMessage.getCode());
        common.setMsg(errorMessage.getMsg());
    }

    /**
     * 组装返回自定义提示
     *
     * @param message
     * @param common
     */
    public static void errorInfo(String message, ResCommon common) {
        common.setCode(ErrorMessage.COMMON_WARN_MSG.getCode());
        common.setMsg(message);
    }

}

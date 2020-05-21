package com.example.dragon.error;

/**
 * @Deprecated 错误信息封装
 * @Author Songxinwei
 * @Date 2020/2/11
 */
public enum ErrorMessage {

    /**
     * 成功
     */
    COMMON_SUCCESS_MSG("0000", "成功"),
    /**
     * 必传参数缺失
     */
    COMMON_MISS_MSG("0001", "缺少参数"),
    /**
     * 调用第三方接口失败
     */
    COMMON_TIMEOUT_MSG("0002", "调用第三方接口失败"),
    /**
     * 系统异常
     */
    COMMON_ERROR_MSG("0003", "系统异常"),
    /**
     * 逻辑异常
     */
    COMMON_WARN_MSG("0004", "自定义提示"),
    /**
     * 查询数据为空
     */
    COMMON_WARN_NULL("0005", "查询数据为空"),
    ;

    private String code;
    private String msg;

    ErrorMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }

}

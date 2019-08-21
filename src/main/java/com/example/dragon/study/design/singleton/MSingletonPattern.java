package com.example.dragon.study.design.singleton;

/**
 * @ClassNAME SingletonPattern
 * @Description 单例枚举
 * @Author Xiongmao
 * @Date 2019-7-23
 */
public enum MSingletonPattern {
    YEAR("2019"), MOTH("7"), DAY("23");

    private String singleton;

    MSingletonPattern(String singleton) {
        this.singleton = singleton;
    }

    public String getSingleton(){
        return singleton;
    }
}

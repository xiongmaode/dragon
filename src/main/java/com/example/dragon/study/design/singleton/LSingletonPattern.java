package com.example.dragon.study.design.singleton;


/**
 * @ClassNAME LSingletonPattern
 * @Description 单例双重判断懒汉（1、在需要的时候创建实例）
 * @Author Xiongmao
 * @Date 2019-7-23
 */
public class LSingletonPattern {

    private static LSingletonPattern singleton = null;

    private LSingletonPattern() {}

    public static LSingletonPattern getSingleton() {
        if (null == singleton) {
            synchronized (LSingletonPattern.class) {
                if (null == singleton) {
                    singleton = new LSingletonPattern();
                }
            }
        }
        return singleton;
    }
}

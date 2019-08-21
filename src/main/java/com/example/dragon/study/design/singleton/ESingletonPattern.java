package com.example.dragon.study.design.singleton;

/**
 * @ClassNAME ESingletonPattern
 * @Description 饿汉模式，在类加载的时候就创建实例
 * @Author Xiongmao
 * @Date 2019-7-23
 */
public class ESingletonPattern {

    private static ESingletonPattern singleton = new ESingletonPattern();

    private ESingletonPattern(){}

    public static ESingletonPattern getSingleton(){
        return singleton;
    }
}

package com.example.dragon.study.design.template;

/**
 * @ClassNAME HeiTemplatePattern
 * @Description 小黑上学的私有化方法
 * @Author XiongMao
 * @Date 2019-7-24
 */
public class HeiTemplatePattern extends GoSchoolTemplate {
    @Override
    void rise() {
        System.out.println("闹钟响了，小黑穿衣起床。。。");
    }

    @Override
    void shoes() {
        System.out.println("小黑开始穿鞋。。。");
    }

    @Override
    void school() {
        System.out.println("小黑去实验第二幼儿园上学。。。");
    }
}

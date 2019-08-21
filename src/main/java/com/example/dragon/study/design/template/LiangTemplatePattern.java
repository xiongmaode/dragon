package com.example.dragon.study.design.template;

/**
 * @ClassNAME LiangTemplatePattern
 * @Description 小亮上学的私有化方法
 * @Author XiongMao
 * @Date 2019-7-24
 */
public class LiangTemplatePattern extends GoSchoolTemplate {
    @Override
    void rise() {
        System.out.println("闹钟响了，小亮穿衣起床。。。（并玩了一会儿玩具）");
    }

    @Override
    void shoes() {
        System.out.println("小亮开始穿鞋。。。（并玩了一会儿玩具）");
    }

    @Override
    void school() {
        System.out.println("小亮去实验第一幼儿园上学。。。");
    }
}

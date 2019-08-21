package com.example.dragon.study.design.template;

/**
 * @ClassNAME TemplateMethodPattern
 * @Description 模版模式，父类定好模版中的主流程，私有化的东西交给子类完成
 * 当洗漱的作用于不是本类本身的时候子类可以进行重写，完成其他类型的模版模式
 * @Author XiongMao
 * @Date 2019-7-24
 */
public abstract class GoSchoolTemplate {
    //起床
    abstract void rise();
    //洗漱
    private void wash(){
        System.out.println("开始洗漱。。。");
    }
    //穿鞋
    abstract void shoes();
    //上学
    abstract void school();

    public final void goSchool(){
        rise();
        wash();
        shoes();
        school();
    }
}

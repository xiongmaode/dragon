package com.example.dragon.study.design;


import com.example.dragon.study.design.template.GoSchoolTemplate;
import com.example.dragon.study.design.template.HeiTemplatePattern;
import com.example.dragon.study.design.template.LiangTemplatePattern;
import com.example.dragon.study.design.singleton.ESingletonPattern;
import com.example.dragon.study.design.singleton.LSingletonPattern;
import com.example.dragon.study.design.singleton.MSingletonPattern;

/**
 * @ClassNAME DesignController
 * @Description 提供main方法，检测设计模式是否实现预期效果。每个方法是一个检测，方便代码块的区分。私有
 * 方法统一在main方法中调用，调用上使用注解标注知识点
 * @Author Xiongmao
 * @Date 2019-7-23
 */
public class DesignController {

    public static void main(String[] args) {

        /*
         *单例模式（1.防止实例被重复创建，节省资源；2.防止多个实例造成的数据不一致情况）
         *最佳操作应该是使用枚举类型，可解决通过反射和序列化造成的单例被破坏的问题
         *Spring中创建bean的时候采用的是单例模式，可用scope来控制
         *   缺省时采用的为饿汉模式，启动容器的时候就创建了bean的实例
         *   也可以设置为懒汉模式，在get的时候获取实例
         *   还有能设置为多实例模式prototype，每次获取都是一个新的实例
         */
//        singleton();

        /*
         * 模板模式（规定总体流程，开发只用关注具体实现，不用在意关键步骤）
         * Spring中的IOC、JdbcTemplate等一些模板中会使用到
         */
        template();
    }

    //单例模式
    private static void singleton() {
        LSingletonPattern l1 = LSingletonPattern.getSingleton();
        LSingletonPattern l2 = LSingletonPattern.getSingleton();
        ESingletonPattern e1 = ESingletonPattern.getSingleton();
        ESingletonPattern e2 = ESingletonPattern.getSingleton();
        String m1 = MSingletonPattern.DAY.getSingleton();
        String m2 = MSingletonPattern.DAY.getSingleton();
        System.out.println("l1:"+l1);
        System.out.println("l2:"+l2);
        System.out.println(l1 == l2);
        System.out.println("e1:"+e1);
        System.out.println("e2:"+e2);
        System.out.println(e1 == e2);
        System.out.println("m1:"+m1);
        System.out.println("m2:"+m2);
        System.out.println(m1 == m2);
    }

    //模板模式
    private static void template(){
        GoSchoolTemplate liang = new LiangTemplatePattern();
        liang.goSchool();
        GoSchoolTemplate hei = new HeiTemplatePattern();
        hei.goSchool();
    }
}

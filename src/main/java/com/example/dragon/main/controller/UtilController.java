package com.example.dragon.main.controller;


import com.example.dragon.main.dao.model.xmlutil.AgeAndSex;
import com.example.dragon.main.dao.model.xmlutil.Girl;
import com.example.dragon.main.service.ReadExcleToBeen;
import com.example.dragon.main.util.StringUtil;
import com.example.dragon.main.util.XmlUtil;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;

/**
 * @ClassNAME UtilController
 * @Description main方法
 * @Author Xiongmao
 * @Date 2019-06-17
 */
public class UtilController {

    public static void main(String[] args) {
        //agentinfo表配置方法
//        try {
//            AgentInfoService.agentInfo();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //判断字符串是否和数组中的值一致
//        System.out.println(StringUtil.strIsArry("01,02,03","0"));
//        List<String> list = new ArrayList<>();

        //把word中的接口信息换成javabean打印出来
        ReadExcleToBeen.getBeen();

        //测试XmlUtil工具类
        //组装值
//        Girl girl = new Girl();
//        girl.setName("小红");
//        List<AgeAndSex> ageAndSexs=new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            AgeAndSex ageAndSex = new AgeAndSex();
//            ageAndSex.setAge("18");
//            ageAndSex.setSex("女");
//            ageAndSexs.add(ageAndSex);
//        }
//        girl.setAgeAndSexs(ageAndSexs);
//        System.out.println("组装完成的对象值为："+girl);
//        //方法调用并输出
//        String xml;
//        try {
//            xml= XmlUtil.beanToXml(girl);
//            System.out.println("bean转成xml格式为："+XmlUtil.beanToXml(girl));
//            System.out.println("xml转成bean格式为："+XmlUtil.xmlToBean(xml,girl));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }

    }
}

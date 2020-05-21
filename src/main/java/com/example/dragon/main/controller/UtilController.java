package com.example.dragon.main.controller;


import com.example.dragon.main.dao.model.xmlutil.AgeAndSex;
import com.example.dragon.main.dao.model.xmlutil.Girl;
import com.example.dragon.main.service.ReadExcleToBeen;
import com.example.dragon.main.util.DateUtils;
import com.example.dragon.main.util.StringUtils;
import com.example.dragon.main.util.XmlUtils;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
//        System.out.println(StringUtil.strIsArry("01;02;03","0"));
//        List<String> list = new ArrayList<>();

        //把word中的接口信息换成javabean打印出来
//        ReadExcleToBeen.getBeen();

        //测试XmlUtil工具类
        //组装值
//        Girl girl = new Girl();
//        girl.setName("小红");
//        AgeAndSex ageAndSex = new AgeAndSex();
//        ageAndSex.setAge("18");
//        ageAndSex.setSex("女");
//        girl.setAgeAndSex(ageAndSex);
//        System.out.println("组装完成的对象值为：" + girl);
//        //方法调用并输出
//        Girl newGirl = new Girl();
//        String xml;
//        try {
//            xml = XmlUtil.beanToXml(girl, AgeAndSex.class);
//            System.out.println("bean转成xml格式为：" + xml);
//            newGirl = XmlUtil.xmlToBean(xml, newGirl, AgeAndSex.class);
//            System.out.println("xml转成bean格式为：" + newGirl);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }

        Date date = new Date();
        System.out.println(date);
        Date dat = DateUtils.getAfterTime(date,2);
        System.out.println(dat);

    }
}

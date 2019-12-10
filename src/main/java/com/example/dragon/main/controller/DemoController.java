package com.example.dragon.main.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassNAME DemoController
 * @Description 不涉及调用的main方法
 * @Author XiongMao
 * @Date 2019-8-14
 */
public class DemoController {

    public static void main(String[] args) throws ParseException {
        //calendar探索
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfM = new SimpleDateFormat("yyyy-MM");
        //当前时间
        System.out.println(sdfs.format(calendar.getTime()));
        //自定义时间
        calendar.setTime(sdfM.parse("2019-09"));
        //设置为1号
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("月初：" + sdfs.format(calendar.getTime()));
        //设置为0号
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        System.out.println("月末：" + sdfs.format(calendar.getTime()));

        //lombda探索
//        List<String> a = new ArrayList<>();
//        a.add("1");
//        a.add("1");
//        a.add("1");
//        a.add("1");
//        a.forEach(v -> {
//            System.out.println(v);
//        });

        //subList(包前不包后)
//        LinkedList<Integer> linkedList = new LinkedList<Integer>();
//        linkedList.add(0);
//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(3);
//        linkedList.add(4);
//        List<Integer> ints= linkedList.subList(1,2);
//        System.out.println(ints);
//        ints= linkedList.subList(2,3);
//        System.out.println(ints);

    }
}

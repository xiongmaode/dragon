package com.example.dragon.study.arraylist;


/**
 * @ClassNAME ArrayController
 * @Description main方法，测试arrayList是否成功
 * @Author XiongMao
 * @Date 2019-7-26
 */
public class ArrayController {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        System.out.println("总数：");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("list的大小："+list.size());
        System.out.println("判断是否为空："+list.isEmpty());
        System.out.println("删除数据1：");
        list.remove(1);
        System.out.println("获取第一个数："+list.get(0));
        System.out.println(""+list.get(0));
        System.out.println("list的大小："+list.size());
    }
}

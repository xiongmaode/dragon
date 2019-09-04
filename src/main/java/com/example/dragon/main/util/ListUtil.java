package com.example.dragon.main.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassNAME ListUtil
 * @Description List工具类
 * @Author XiongMao
 * @Date 2019-8-20
 */
public class ListUtil {

    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param <T>
     * @param list 需要分割的List
     * @param len 需要分割的长度，mybatis批量操作设置为995（超过1000in会报错）
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list,int len) {
        //判断非空
        if (list == null || list.isEmpty() || len < 1) {
            return Collections.emptyList();
        }
        //声明返回对象存值
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        //计算循环次数
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}

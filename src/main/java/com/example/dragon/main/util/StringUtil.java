package com.example.dragon.main.util;

/**
 * @ClassNAME StringUtil
 * @Description 字符串控制的工具方法
 * @Author XiongMao
 * @Date 2019-7-24
 */
public class StringUtil {

    /**
     * @Description 字符串是否包含对比，str：01;02 search：01
     * @Param [str, search]
     * @Return boolean
     */
    public static boolean strIsArry(String str, String search) {
        String[] strs = str.split(";");
        for (String s : strs) {
            if ((s).equals(search)) {
                return true;
            }
        }
        return false;
    }
}

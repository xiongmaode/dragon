package com.example.dragon.main.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassNAME StringUtil
 * @Description 字符串控制的工具方法
 * @Author XiongMao
 * @Date 2019-7-24
 */
public class StringUtils {

    /**
     * 校验多个字符串是否有空值
     *
     * @param strings 字符串组
     * @return 是否有空值
     */
    public static boolean checkEmpty(String... strings) {
        boolean flag = false;
        for (String str : strings) {
            if (null == str || "".equals(str) || "null".equals(str)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 大陆号码或香港号码都可以
     *
     * @param str
     * @return 符合规则返回true
     */
    public static boolean isPhoneLegal(String str) {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 145,147,149
     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
     * 166
     * 17+3,5,6,7,8
     * 18+任意数
     * 198,199
     *
     * @param str
     * @return 正确返回true
     */
    private static boolean isChinaPhoneLegal(String str) {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     *
     * @param str
     * @return 正确返回true
     */
    private static boolean isHKPhoneLegal(String str) {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}

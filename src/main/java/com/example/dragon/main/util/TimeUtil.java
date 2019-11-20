package com.example.dragon.main.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Descrjption 对于时间的处理工具类
 * @Author Songxinwei
 * @Date 2019/11/19 10:10
 */
public class TimeUtil {

    /**
     * 获取某个时间的下“step”个月的日期,因为calendar月份是从0开始的，所以+1
     *
     * @param date
     * @param step
     * @return
     */
    public static String skipMonth(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, step);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        return year + "-" + month + "-" + day;
    }
}

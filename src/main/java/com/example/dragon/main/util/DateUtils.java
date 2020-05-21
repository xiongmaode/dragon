package com.example.dragon.main.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Deprecated 日期处理类
 * @Author Songxinwei
 * @Date 2020/2/12
 */
@Slf4j
public class DateUtils {

    public static final String YEAR_MONTH_DAY_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YEAR_MONTH_DAY_HH_MM_DD = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY_POINT = "yyyy.MM.dd";

    /**
     * Date转String
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * String类型转Date类型
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    /**
     * 距离目标时间还有多久
     *
     * @param endTime
     * @return *天*小时
     */
    public static String lessTime(Date endTime) {
        Date now = new Date();
        long l = endTime.getTime() - now.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        return day + "天 " + hour + "小时";
    }

    /**
     * 距离目标时间还有多久
     *
     * @param startTime
     * @param endTime
     * @return *天
     */
    public static long lessTime(Date startTime, Date endTime) {
        long l = endTime.getTime() - startTime.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 获取N个月之后的日期
     *
     * @param startTime
     * @param number
     * @return
     */
    public static Date getAfterTime(Date startTime, int number) {
        //获得一个日历的实例
        Calendar c = Calendar.getInstance();
        //设置日历时间
        c.setTime(startTime);
        //在日历的月份上增加N个月
        c.add(Calendar.MONTH, number);
        //的到你想要得N个月后的日期
        Date afterTime = c.getTime();
        return afterTime;
    }

    /**
     * 获取当前日期周日的日期
     *
     * @param startTime
     * @return
     */
    public static Date getSundayTime(Date startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        //获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.add(Calendar.DATE, 6);
        return cal.getTime();
    }

    /**
     * 返回日时分秒
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        //转换天数
        long days = second / 86400;
        //剩余秒数
        second = second % 86400;
        //转换小时数
        long hours = second / 3600;
        //剩余秒数
        second = second % 3600;
        //转换分钟
        long minutes = second / 60;
        if (0 < days) {
            return days + "天" + hours + "小时" + minutes + "分";
        } else if (0 < hours) {
            return hours + "小时" + minutes + "分";
        } else if (0 < minutes) {
            return minutes + "分";
        } else {
            return second + "秒";
        }
    }

    /**
     * 某个时间的当月第一天或者最后一天
     * month = 0
     *
     * @param month   几个月之后的最后一天的时间
     * @param pattern 格式化时间
     * @param type    0:当月的第一天 1：当月最后一天
     * @return yyyy-MM-dd
     */
    public static String getFirstOrEndTime(Date date, int month, String pattern, int type) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String str = "";
        try {
            c.set(Calendar.DATE, 1);
            if (type == 1) {
                c.add(Calendar.MONTH, month + 1);
                c.add(Calendar.DATE, -1);
            }
            str = new SimpleDateFormat(pattern).format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取当前日期的年份
     *
     * @return yyyy
     */
    public static int getNowYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }
}

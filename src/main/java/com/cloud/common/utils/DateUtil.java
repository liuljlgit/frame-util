package com.cloud.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作公共类
 * @author lijun
 */
public class DateUtil {
    protected static final transient Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String DEFAUL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAUL_EXCEL_FORMAT = "yyyy/M/d HH:mm:ss";
    public static final String DEFAULT_NO_TIME_FROMAT = "yyyy-MM-dd";
    public static final String DEFAUL_EXCEL_NO_TIME_FORMAT = "yyyy/M/d";
    public static final String MONTH_FORMAT = "yyyyMM";
    public static final String TIME_FORMAT = "yyyyMMddHHmmss";
    public static final String CHINESE_DAY_FORMAT = "yyyy年MM月dd日";
    public static final String CHINESE_MONTH_FORMAT = "yyyy年MM月";
    public static final String WEEKEND = "EEEE";    //星期几

    /**
     * 获取时间的指定格式字符串
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 把指定格式的字符串转换成日期
     * @param time
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateStr(String time, String format) throws ParseException {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        return simple.parse(time);
    }

    /**
     * 增加时间
     * @param date
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date addTime(Date date, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.YEAR, year);
            ca.add(Calendar.MONTH, month);
            ca.add(Calendar.DATE, day);
            ca.add(Calendar.HOUR, hour);
            ca.add(Calendar.MINUTE, minute);
            ca.add(Calendar.SECOND, second);
            ca.add(Calendar.MILLISECOND, 0);
            return ca.getTime();
        }
    }

    /**
     * 增加年
     * @param date
     * @param year
     * @return
     */
    public static Date addYear(Date date, Integer year) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.YEAR, year);
            return ca.getTime();
        }
    }

    /**
     * 增加月
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, Integer month) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.MONTH, month);
            return ca.getTime();
        }
    }

    /**
     * 增加日
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, Integer day) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.DATE, day);
            return ca.getTime();
        }
    }

    /**
     * 增加时
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, Integer hour) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.HOUR, hour);
            return ca.getTime();
        }
    }

    /**
     * 增加分
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, Integer minute) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.MINUTE, minute);
            return ca.getTime();
        }
    }

    /**
     * 增加秒
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, Integer second) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.SECOND, second);
            return ca.getTime();
        }
    }

    /**
     * 得到年份
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {
        return Integer.valueOf(formatDate(date,"yyyy"));
    }

    /**
     * 得到年份
     * @return
     */
    public static Integer getYear() {
        Date date = new Date();
        return Integer.valueOf(formatDate(date,"yyyy"));
    }

    /**
     * 得到月份
     * @param date
     * @return
     */
    public static Integer getMonth(Date date) {
        return Integer.valueOf(formatDate(date,"MM"));
    }

    /**
     * 得到月份
     * @return
     */
    public static Integer getMonth() {
        Date date = new Date();
        return Integer.valueOf(formatDate(date,"MM"));
    }

    /**
     * 得到日期
     * @param date
     * @return
     */
    public static Integer getDay(Date date) {
        return Integer.valueOf(formatDate(date,"dd"));
    }

    /**
     * 得到月份
     * @return
     */
    public static Integer getDay() {
        Date date = new Date();
        return Integer.valueOf(formatDate(date,"dd"));
    }

    /**
     * 得到小时
     * @param date
     * @return
     */
    public static Integer getHour(Date date) {
        return Integer.valueOf(formatDate(date,"HH"));
    }

    /**
     * 得到小时
     * @return
     */
    public static Integer getHour() {
        Date date = new Date();
        return Integer.valueOf(formatDate(date,"HH"));
    }

    /**
     * 得到分钟
     * @param date
     * @return
     */
    public static Integer getMinute(Date date) {
        return Integer.valueOf(formatDate(date,"mm"));
    }

    /**
     * 得到分钟
     * @return
     */
    public static Integer getMinute() {
        Date date = new Date();
        return Integer.valueOf(formatDate(date,"mm"));
    }

    /**
     * 得到秒钟
     * @param date
     * @return
     */
    public static Integer getSecond(Date date) {
        return Integer.valueOf(formatDate(date,"ss"));
    }

    /**
     * 得到秒钟
     * @return
     */
    public static Integer getSecond() {
        Date date = new Date();
        return Integer.valueOf(formatDate(date,"ss"));
    }

    /**
     * 根据日期得到今天是星期几
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        return formatDate(date,WEEKEND);
    }

    /**
     * 得到一天的开始 yyyy-MM-dd 00:00:00
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(Calendar.DECEMBER, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MILLISECOND, 0);
            return ca.getTime();
        }
    }

    /**
     * 得到今天的开始 yyyy-MM-dd 00:00:00
     * @return
     */
    public static Date getDayStart() {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DECEMBER, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime();
    }

    /**
     * 得到一天的结束 yyyy-MM-dd 23:59:59
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(Calendar.DECEMBER, 23);
            ca.set(Calendar.MINUTE, 59);
            ca.set(Calendar.SECOND, 59);
            return ca.getTime();
        }
    }

    /**
     * 得到今天的结束 yyyy-MM-dd 23:59:59
     * @return
     */
    public static Date getDayEnd() {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DECEMBER, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        return ca.getTime();
    }

    /**
     * 得到这个月开始的第一天 yyyy-MM-dd 00:00:00
     * @return
     */
    public static Date getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(5, cal.getActualMinimum(5));
        return cal.getTime();
    }

    /**
     * 得到指定月开始的第一天 yyyy-MM-dd 00:00:00
     * @return
     */
    public static Date getMonthStart(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到指定月开始的最后一天 yyyy-MM-dd 23:59:59
     * @return
     */
    public static Date getMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.DECEMBER, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    /**
     * 得到这个月开始的最后一天 yyyy-MM-dd 23:59:59
     * @return
     */
    public static Date getMonthEnd(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.DECEMBER, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    /**
     * 得到一年的开始那一天 yyyy-MM-dd 00:00:00
     * @param date
     * @return
     */
    public static Date getYearStart(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(Calendar.MONTH, 0);
            ca.set(Calendar.DATE, 1);
            ca.set(Calendar.DECEMBER, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MILLISECOND, 0);
            return ca.getTime();
        }
    }

    /**
     * 得到今年的开始那一天 yyyy-MM-dd 00:00:00
     * @return
     */
    public static Date getYearStart() {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.MONTH, 0);
        ca.set(Calendar.DATE, 1);
        ca.set(Calendar.DECEMBER, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime();
    }

    /**
     * 得到一年的结束那一天 yyyy-MM-dd 23:59:59
     * @param date
     * @return
     */
    public static Date getYearEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.DECEMBER, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    /**
     * 得到今年的结束那一天 yyyy-MM-dd 23:59:59
     * @return
     */
    public static Date getYearEnd() {
        Date date  = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.DECEMBER, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }
}

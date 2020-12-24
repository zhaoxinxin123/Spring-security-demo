package com.study.two_security_jwt.common;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 *
 * @author zhuyushuo
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    private static final String YEAR_MONTH_DAY="yyyy-MM-dd";

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return DateUtil.getDateFormat(usDate, format);
    }

    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 日期相互加减
     * @param date  日期
     * @param day  加减日期
     * @param includeTime   是否包括时分秒
     * @return
     * @throws ParseException
     */
    public static  Date dateAdd(Date date,int day,boolean includeTime ) throws ParseException {
        if (date==null){
            date=new Date();
        }
        if (!includeTime){
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YEAR_MONTH_DAY);
            date = sdf.parse(sdf.format(date));
        }
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,day);
        return cal.getTime();
    }

    /**
     * 获取一天的开始时间
     * @param date
     * @return
     */
    public static Date getDateStart(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的结束始时间
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获得某天最大时间 2020-02-19 23:59:59
      * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2020-02-17 00:00:00
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 解析日器
     * @param datestr
     * @param template
     * @return
     */
    public static Date parse(String datestr,String template) {
        if (StringUtils.isBlank(datestr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(template);
        try {
            return format.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date localDateTime2Date( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        return date;//Tue Mar 27 14:17:17 CST 2018
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }
    public static String formatYYYYMMDD(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);
    }
    public static Integer parseDateToInt(Date date){
        if (date == null) {
            date =new Date();
        }
        return   Integer.parseInt(formatYYYYMMDD(date).replaceAll("-",""));
    }
    /**
     * yyyy-MM-dd HH:mm:ss
     * @param s
     * @return
     */
    public static LocalDateTime parseToLocalDateTime(String s) {
        Date date = parse(s,DateUtil.FULL_TIME_SPLIT_PATTERN);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static void main(String[] args) {
        Date dateStart = getDateStart(new Date());
        Date dateEnd = getDateEnd(new Date());
        System.out.println(dateStart+"000"+dateEnd);
    }
}

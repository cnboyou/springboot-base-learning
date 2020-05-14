package com.ehi.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: TestDate
 *
 * @Author: WangYiHai
 * @Date: 2020/5/14 11:34
 * @Description: TODO
 */
public class TestDate {

    public static void main(String[] args) throws ParseException {


        //------传统方式---------
        // SimpleDateFormat，不是线程安全的，多线程环境一定要注意使用安全
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date 转 字符串
        simpleDateFormat.format(new Date());
        // 字符串 转 Date
        simpleDateFormat.parse("2020-05-07 22:00:00");

        //-----------时间计算的相关功能-----------
        // Date 转化为字符串
        DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        // 字符串 转 Date
        DateUtils.parseDate("2020-05-07 22:00:00","yyyy-MM-dd HH:mm:ss");

        Date now = new Date();
        // Date 加 1 天
        Date addDays = DateUtils.addDays(now, 1);
        // Date 加 33 分钟
        Date addMinutes = DateUtils.addMinutes(now, 33);
        // Date 减去 233 秒
        Date addSeconds = DateUtils.addSeconds(now, -233);
        // 判断是否 Wie 同一天
        boolean sameDay = DateUtils.isSameDay(addDays, addMinutes);
        // 过滤时分秒,若 now 为 2020-05-07 22:13:00 调用 truncate 方法以后
        // 返回时间为 2020-05-07 00:00:00
        Date truncate = DateUtils.truncate(now, Calendar.DATE);

        //----------------JDK8 时间类---------------
        /*
        * JDK8 之后，Java 将日期与时间分为 LocalDate，LocalTime，功能定义更加清晰，当然其也提供一个 LocalDateTime，
        * 包含日期与时间。这些类相对于 Date 类优点在于，这些类与 String 类一样都是不变类型，不但线程安全，而且不能修改。
        * */

        //使用的是 Date 类型
        Date now1 = new Date();
        // Date-----> LocalDateTime 这里指定使用当前系统默认时区
        LocalDateTime localDateTime = now1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        // LocalDateTime------> Date 这里指定使用当前系统默认时区
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        //使用 LocalDateTime 进行字符串格式化
        // 按照 yyyy-MM-dd HH:mm:ss 转化时间
        LocalDateTime dateTime = LocalDateTime
                .parse("2020-05-07 22:34:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 将 LocalDateTime 格式化字符串
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);

        //获取当前时间年份，月份
        LocalDateTime now2 = LocalDateTime.now();
        // 年
        int year = now2.getYear();
        // 月
        int month = now2.getMonthValue();
        // 日
        int day = now2.getDayOfMonth();

        //使用 LocalDateTime 进行日期加减，获取下一天的时间
        LocalDateTime now3 = LocalDateTime.now();
        // 当前时间加一天
        LocalDateTime plusDays = now3.plusDays(1l);
        // 当前时间减一个小时
        LocalDateTime minusHours = now3.minusHours(1l);
        // 还有很多其他方法








    }

}
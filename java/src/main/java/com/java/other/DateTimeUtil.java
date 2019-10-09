package com.java.other;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoField.*;

/**
 * Java8的日期时间类
 * @author Ning
 * @date Create in 2019/1/14
 */
public class DateTimeUtil {

    private static void dateUtilTest() {

        // 获取当前日期, 格式: yyyy-MM-dd
        LocalDate localDate = LocalDate.now();

        // 以下两两结果相同
        int dayOfWeek = localDate.get(DAY_OF_WEEK);
        dayOfWeek = localDate.getDayOfWeek().getValue();

        int dayOfMonth = localDate.get(DAY_OF_MONTH);
        dayOfMonth = localDate.getDayOfMonth();

        int dayOfYear = localDate.get(DAY_OF_YEAR);
        dayOfYear = localDate.getDayOfYear();

        int monthOfYear = localDate.get(MONTH_OF_YEAR);
        monthOfYear = localDate.getMonthValue();

        int year = localDate.get(YEAR);
        year = localDate.getYear();

        System.out.printf("今天是%s, 这周的第%d天, 这个月的第%d天, 今年的第%d天, 今年的第%d个月, %d年%n", localDate, dayOfWeek, dayOfMonth, dayOfYear, monthOfYear, year);


        LocalDate customizeDate = LocalDate.of(2019, 2, 16);
        System.out.println("自定义的日期为: " + customizeDate);

        // 生日的判断, 还有仅包含年月的YearMonth类
        MonthDay birthday = MonthDay.of(customizeDate.getMonth(), customizeDate.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.now();
        if (birthday.equals(currentMonthDay)) {
            System.out.println("Happy birthday!");
        } else {
            System.out.println("birthday has not arrived");
        }

        System.out.println("自定义的日期一周后的日期为: " + customizeDate.plus(1L, ChronoUnit.WEEKS));

        System.out.println("今天是在自定义日期之前还是之后?"  + (LocalDate.now().isAfter(customizeDate) ? "之后" : "之前"));

        System.out.println("今年是闰年吗?" + (LocalDate.now().isLeapYear() ? "是" : "不是"));

        Period period = Period.between(localDate, customizeDate);
        System.out.println("今天距自定义日期还有" + period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天");

        String dayAfterTomorrow = "20180210";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parseResult = LocalDate.parse(dayAfterTomorrow, dateTimeFormatter);
        System.out.println("字符串解析结果" + parseResult);


        YearMonth yearMonth = YearMonth.now();
        System.out.println("现在是" + yearMonth + ", 这个月有" + yearMonth.lengthOfMonth() + "天");
        YearMonth customizeYearMonth = YearMonth.of(2018, Month.FEBRUARY);
        System.out.println("自定义年月为" + customizeYearMonth);
    }

    private static void timeUtilTest() {

        // 获取当前时间, 格式: hh:mm:ss.SSS
        LocalTime localTime = LocalTime.now();
        System.out.println("当前时间为: " + localTime);
        System.out.println("当前时间2小时后的时间为: " + localTime.plusHours(2L));
        System.out.println("当前时间2分钟前的时间为: " + localTime.minusMinutes(2L));

    }

    public static void main(String[] args) {

//        DateTimeUtil.dateUtilTest();
//        System.out.println();
//        DateTimeUtil.timeUtilTest();

        String a = "allll;l";
        int h = a.hashCode();
        System.out.println(3 << 2);
    }
}

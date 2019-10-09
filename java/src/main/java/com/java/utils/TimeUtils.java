package com.java.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ning
 */
public class TimeUtils {

//    public static String compareDate(Date stateDate, Date endDate) {
//
//
//        Calendar startCalender = Calendar.getInstance();
//        startCalender.setTime(stateDate);
//        Calendar endCalender = Calendar.getInstance();
//        endCalender.setTime(endDate);
//
//
//
//    }

    public static String cycle(long millisecond) {

        int[] arrays = new int[]{1000, 60, 60, 24, 365};
        String[] unit = new String[]{"毫秒", "秒", "分", "小时", "天"};

        long higherPart = millisecond;
        long currentPart ;
        long temp;
        List<String> result = new ArrayList<>(16);

        for (int index = 0; index < arrays.length; index++) {

            int value = arrays[index];
            temp = higherPart;
            higherPart = higherPart/value;
            currentPart = temp - higherPart*value;

            if (currentPart > 0) {
                result.add(currentPart + unit[index]);
            }

            if (higherPart == 0) {
                return timePartToString(result);
            }
        }

        result.add(higherPart + "年");
        return timePartToString(result);
    }

    private static String timePartToString(List<String> timeParts) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int index = timeParts.size() - 1; index > -1; index--) {
            stringBuilder.append(timeParts.get(index));
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {

        System.out.println(TimeUtils.cycle(82001455000L));
    }
}

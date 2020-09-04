package com.java.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ning
 */
public class TimeUtils {

    private static DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");


    public static String getLocalTime() {
        return formatter.format(new Date());
    }

    /**
     * 毫秒转年日时分秒毫秒
     * @param millisecond 毫秒
     * @return 年日时分秒
     */
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

        System.out.println(TimeUtils.cycle(82001455111L));
    }
}

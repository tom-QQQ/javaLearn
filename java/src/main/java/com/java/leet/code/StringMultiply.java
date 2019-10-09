package com.java.leet.code;



/**
 * @author Ning
 * @date Create in 2019/10/6
 */
public class StringMultiply {

    public String multiply(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        String result = "0";

        for (int index = num2.length() - 1; index > -1; index--) {

            StringBuilder multiplyResult = new StringBuilder(stringMultiplyNew(num1, (num2.charAt(index) - '0')));

            if (!"0".equals(multiplyResult.toString())) {
                for (int num = 0; num < num2.length() - 1 - index; num++) {
                    multiplyResult.append("0");
                }
            }

            result = stringPlusNew(result, multiplyResult.toString());
        }

        return result;
    }

    private String stringMultiplyNew(String num1, Integer num2) {

        if (num2 == 0) {
            return "0";

        } else if (num2 == 1) {
            return num1;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int addValue = 0;
        for (int index = num1.length() - 1; index > -1; index--) {

            int addResult = (num1.charAt(index) - '0') * num2 + addValue;

            stringBuilder.append(addResult%10);
            addValue = addResult/10;
        }

        if (addValue != 0) {
            stringBuilder.append(addValue);
        }

        return stringBuilder.reverse().toString();
    }

    private String stringPlusNew(String num1, String num2) {

        StringBuilder stringBuilder = new StringBuilder();

        int addValue = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i > -1 || j > -1; i--, j--) {

            int num1Num = i > -1 ? num1.charAt(i) - '0' : 0;
            int num2Num = j > -1 ? num2.charAt(j) - '0' : 0;

            int addResult = num1Num + num2Num + addValue;

            stringBuilder.append(addResult%10);
            addValue = addResult/10;

        }

        if (addValue != 0) {
            stringBuilder.append(addValue);
        }
        return stringBuilder.reverse().toString();
    }

    public String multiplyNew(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int[] resultArray = new int[num1.length() + num2.length()];

        for (int num1Index = num1.length() - 1; num1Index > -1; num1Index--) {

            int num1Num = num1.charAt(num1Index) - '0';

            for (int num2Index = num2.length() - 1; num2Index > -1; num2Index--) {

                int num2Num = num2.charAt(num2Index) - '0';

                int multiplyResult = num1Num * num2Num + resultArray[num1Index + num2Index + 1];

                if (multiplyResult > 9) {

                    resultArray[num1Index + num2Index + 1] = multiplyResult%10;
                    // 这里确实会让数组中的数字大于10, 但前面的计算multiplyResult时会在之后对数据进行修正, 自动进位, 进位到最后一位时, 由于两数相乘的最大位数为两数位数之和, 最后一位不可能再大于10
                    resultArray[num1Index + num2Index] += multiplyResult/10;

                } else {
                    resultArray[num1Index + num2Index + 1] = multiplyResult;
                }

            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < resultArray.length; index++) {

            if (index == 0 && resultArray[index] == 0) {
                continue;
            }
            stringBuilder.append(resultArray[index]);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        StringMultiply stringMultiply = new StringMultiply();
//        System.out.println(stringMultiply.multiply("9999999", "9999999"));
//        System.out.println(stringMultiply.multiplyNew("9999999", "9999999"));

        String str = "abc";
        System.out.println(str.concat("444"));


    }
}

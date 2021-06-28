package com.java.leet.code;

/**
 * 6. Z 字形变换
 */
public class ConvertZ {

    public static void main(String[] args) {
        System.out.println(convert2New("PAYPALISHIRING", 1));
    }

    /**
     * 使用数组替代StringBuilder
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 98.58% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 84.73% 的用户
     */
    public static String convert2New(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        char[] chars = new char[s.length()];
        int arrayIndex = 0;
        int groupCounts = (numRows << 1) -2;
        for (int index = 0; index < numRows; index++) {

            int startIndex = index;
            int times = 1;

            while (startIndex < s.length()) {

                if (index == 0 || index == numRows - 1) {
                    chars[arrayIndex] = s.charAt(startIndex);
                    ++arrayIndex;

                } else {
                    chars[arrayIndex] = s.charAt(startIndex);
                    ++arrayIndex;
                    if (times*groupCounts - index >= s.length()) {
                        break;
                    }
                    chars[arrayIndex] = s.charAt(times*groupCounts - index);
                    ++arrayIndex;
                    times++;
                }

                startIndex += groupCounts;
            }
        }

        return new String(chars);
    }

    /**
     * 出现的问题, 审题不清, 第一次的考虑方向与题目指定的不同
     * 找出规律, 使用StringBuilder拼接字符串
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 85.36% 的用户
     * 内存消耗： 8.5 MB , 在所有 Java 提交中击败了 87.58% 的用户
     */
    public static String convert2(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        StringBuilder result = new StringBuilder();
        int groupCounts = (numRows << 1) -2;
        for (int index = 0; index < numRows; index++) {

            int startIndex = index;
            int times = 1;

            while (startIndex < s.length()) {

                // 第一行和最后一行都是 startIndex, startIndex + groupCounts
                if (index == 0 || index == numRows - 1) {
                    result.append(s.charAt(startIndex));

                } else {
                    // 中间行是 startIndex和下一循环前index处
                    result.append(s.charAt(startIndex));
                    if (times*groupCounts - index >= s.length()) {
                        break;
                    }
                    result.append(s.charAt(times*groupCounts - index));
                    times++;
                }

                startIndex += groupCounts;
            }
        }

        return result.toString();
    }

    /**
     * 构建二维数组, 存放Z字变形数据, 然后读取
     * 执行用时： 25 ms 在所有 Java 提交中击败了 14.46% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 29.63% 的用户
     */
    public static String convert(String s, int numRows) {

        int length = s.length();

        if (numRows == 1) {
            return s;
        }

        // 完整循环次数
        int cycles;

        if (numRows == 2) {
            cycles = 2;

        } else {
            // 每个循环数量 = z的第一笔 + 斜线(行数-2)
            cycles = length / ((numRows << 1) - 2);
        }

        // 不到一个循环的剩余字符串数量
        int last = length - ((numRows << 1) - 2) * cycles;

        // 一个循环的高度 = Z横的1 + 斜线的行数-2
        int width = 1 + numRows - 2;

        // 先计算完整循环的高度
        int high = width * cycles;

        // 剩余数量为0, 高度恰好为完整循环的高度
        if (last == 0) {

          // 剩余数量小于等于numRows, 即结束到Z的第一笔高度加1
        } else if (last <= numRows) {
            high += 1;

            // 剩余数量小于等于numRows*2-2, 即结束到Z的第二笔
        } else {
            // 加Z第一笔的1
            high += 1;
            // 加Z第二笔的剩余数量-第一笔的数量
            high += last - numRows;
        }

        // 构建一个数组, 按照正常位置的Z进行构建
        char[][] charArray = new char[high][numRows];

        // 行
        int rows = 0;
        // 列
        int column = -1;
        boolean needBack = false;
        for (int index = 0; index < length; index++) {

            // 正常情况下向右移动写入字符
            if (!needBack) {
                ++column;

                // 后退状态下向当前位置的左下放格子写入字符
            } else {
                ++rows;
                --column;
            }

            charArray[rows][column] = s.charAt(index);

            // column等于实际列数时, 开始向左下角方向移动写入字符
            if (column == numRows-1) {
                needBack = true;
                continue;
            }

            if (column == 0 && needBack) {
                needBack = false;
            }
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numRows ; i++) {
            for (int j = 0; j < high; j++) {
                char c = charArray[j][i];
                if (c != '\u0000') {
                    builder.append(charArray[j][i]);
                }
            }
        }

        System.out.println(builder);

        return builder.toString();
    }
}

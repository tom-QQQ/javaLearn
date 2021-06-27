package com.java.leet.code;

/**
 * 6. Z 字形变换
 * 执行用时： 25 ms 在所有 Java 提交中击败了 14.46% 的用户
 * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 29.63% 的用户
 */
public class ConvertZ {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

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
//                System.out.print(charArray[j][i]);
            }
//            System.out.println();
        }

        System.out.println(builder);

        return builder.toString();
    }
}

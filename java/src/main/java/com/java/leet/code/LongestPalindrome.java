package com.java.leet.code;

/**
 * 最长回文子串
 */
public class LongestPalindrome {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("bb"));

    }

    public static String longestPalindrome(String s) {
        int[] longestStr = new int[]{0, 0};

        for (int index = 0; index < s.length() - 1; index++) {

            if (s.charAt(index) == s.charAt(index+1)) {
                int[] newLongStr = expandResult(index, index+1, s);
                if (strLength(newLongStr) > strLength(longestStr)) {
                    longestStr = newLongStr;
                }
            }

            if (index + 2 < s.length() && s.charAt(index) == s.charAt(index + 2)) {

                int[] newLongStr = expandResult(index, index + 2, s);
                if (strLength(newLongStr) > strLength(longestStr)) {
                    longestStr = newLongStr;
                }
            }
        }

        return s.substring(longestStr[0], longestStr[1] + 1);
    }

    private static int strLength(int[] longestStr) {

        return longestStr[1]- longestStr[0];
    }

    /**
     * 根据最基础的回文串, 形如aa或aba向外扩展寻找最长回文串
     * @param startIndex 起始索引, 含
     * @param endIndex 截止索引, 含
     * @param s 完整字符串
     * @return 扩展后的起始截止索引
     */
    private static int[] expandResult(int startIndex, int endIndex, String s) {

        while (true) {

            startIndex--;
            endIndex++;

            if (startIndex < 0 || endIndex > s.length()-1 || s.charAt(startIndex) != s.charAt(endIndex)) {
                return new int[]{startIndex+1, endIndex - 1};
            }
        }
    }
}

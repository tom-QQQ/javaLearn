package com.java.leet.code;

/**
 * 最长回文子串
 * 176 / 176 个通过测试用例
 * 执行用时：31 ms 在所有 Java 提交中击败了 87.81% 的用户
 * 内存消耗：38.5 MB 在所有 Java 提交中击败了 78.14% 的用户
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String[] a = {"abb", "ac", "bb", "abb", "cbbd", "aaaa", "babad", "a"};

        System.out.println(longestPalindromeNew("cbaabc"));

    }

    public static String longestPalindrome(String s) {

        int[] longestStr = new int[]{0, 1};

        if (s.length() > 1) {

            if (s.charAt(0) == s.charAt(1)) {
                longestStr = new int[]{0,2};

            } else if (s.charAt(s.length()-1) == s.charAt(s.length() - 2)) {
                longestStr = new int[]{s.length()-2, 2};
            }
        }

        for (int index = 1; index < s.length() - 1; index++) {

            if (s.charAt(index-1) == s.charAt(index)) {
                int[] newLongStr = expandResult(index-1, index, s);
                if (newLongStr[1] > longestStr[1]) {
                    longestStr = newLongStr;
                }
            }

            if (s.charAt(index-1) == s.charAt(index + 1)) {

                int[] newLongStr = expandResult(index-1, index + 1, s);
                if (newLongStr[1] > longestStr[1]) {
                    longestStr = newLongStr;
                }
            }
        }

        return s.substring(longestStr[0], longestStr[0] + longestStr[1]);
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
                return new int[]{startIndex+1, endIndex - 1 - startIndex};
            }
        }
    }

    public static String longestPalindromeNew(String s) {

        if (s.length() < 2) {
            return s;
        }

        int startIndex = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length();) {

            // 如果剩余子串长度小于目前查找到的最长回文子串的长度，直接终止循环
            // （因为即使他是回文子串，也不是最长的，所以直接终止循环，不再判断）
            if (s.length() - i <= maxLength/2) {
                break;
            }

            int left = i;
            int right = i;

            // 向右寻找和当前索引处相同的字符串, 即aba中间的b的部分
            while (right < s.length() -1 && s.charAt(right) == s.charAt(right+1)) {
                ++right;
            }
            // 下次在判断的时候从重复的下一个字符开始判断
            i = right + 1;

            // 向外寻找以中间相同部分对称的字符, 即aba两边b的部分
            while (left > 0 && right < s.length() - 1 && s.charAt(left-1) == s.charAt(right+1)) {
                ++left;
                ++right;
            }

            if (right - left + 1 > maxLength) {
                startIndex = left;
                maxLength = right - left + 1;
            }
        }

        return s.substring(startIndex, startIndex+maxLength);

    }
}

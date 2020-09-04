package com.java.leet.code;

import java.util.*;

/**
 * @author Ning
 * @date Create in 2019/10/8
 */
public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {

        List<Character> charList = new LinkedList<>();
        int size = 0;

        for (int index = 0; index < s.length(); index++) {

            char c = s.charAt(index);

            int minIndex = charList.indexOf(c);
            if (minIndex == -1 ) {
                charList.add(c);

            } else {
                charList.subList(0, minIndex + 1).clear();
                charList.add(c);
            }

            if (charList.size() > size) {
                size = charList.size();
            }
        }
        return size;
    }

    public int lengthOfLongestSubstringBest(String s) {

        // 用于标记当前不含有重复字符串字符的起始索引
        int i = 0;
        // 遍历整个字符串
        int j;
        // 用于遍历之前未重复的字符和新字符是否有重复
        int k;
        // 记录无重复字符串的最大长度
        int max = 0;
        char[] chars = s.toCharArray();

        for(j = 0; j < s.length(); j++){

            // 从新子串的第一个字符开始遍历, 查询是否有和新字符chars[j]重复
            for(k = i; k < j; k++){
                // 出现重复时, 将无重复的字符串索引指向重复的字符的下一个
                if(chars[k] == chars[j]){
                    i = k + 1;
                    break;
                }
            }

            if(j-i+1 > max) {
                max = j-i+1;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        LongestSubstring longestSubstring = new LongestSubstring();

        System.out.println(longestSubstring.lengthOfLongestSubstringBest("abcda"));

        ArrayList list = new ArrayList(500);
    }
}

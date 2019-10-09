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

    public static void main(String[] args) {

        LongestSubstring longestSubstring = new LongestSubstring();

        System.out.println(longestSubstring.lengthOfLongestSubstring("aab"));

        Map<String, Object> map = new HashMap<>();

        Iterator<Map.Entry<String, Object>> iterator =  map.entrySet().iterator();

        map.entrySet().removeIf(enter -> enter.getValue() == "xx");

        while (iterator.hasNext()) {
            iterator.next().getKey();;
            iterator.next().getValue();
        }
    }
}

package com.java.leet.code;

import com.java.utils.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * @author Ning
 * @date Create in 2019/10/5
 */
public class TwoNumSum {

    public int[] twoSumNew(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>(nums.length, 1);

        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if (index != null && index != i) {
                return new int[]{i, index};
            }

            map.put(nums[i], i);
        }

        return new int[2];
    }


    public int[] twoSum(int[] nums, int target) {


        int[] copyArray = new int[nums.length];
        System.arraycopy(nums, 0, copyArray, 0, copyArray.length);

        Arrays.sort(nums);

        for (int index = 0; index < nums.length; index++) {

            int needValue = target - nums[index];
            Integer needValueIndex = Utils.findTargetIndex(nums, needValue);

            if (needValueIndex != null) {

                return findValue(copyArray, nums[index], needValue);
            }
        }


        return null;
    }

    private int[] findValue(int[] nums, int targetA, int targetB) {

        int[] result = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};

        for (int index = 0; index < nums.length; index++) {

            if (result[0] == Integer.MAX_VALUE && nums[index] == targetA) {
                result[0] = index;

            } else if (result[1] == Integer.MAX_VALUE && nums[index] == targetB) {
                result[1] = index;
            }

            if (result[0] != Integer.MAX_VALUE && result[1] != Integer.MAX_VALUE) {
                return result;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        TwoNumSum twoNumSum = new TwoNumSum();

        int[] nums = new int[]{1, 2, 3, 4};

        Array.printArray(twoNumSum.twoSumNew(nums, 6));


    }
}

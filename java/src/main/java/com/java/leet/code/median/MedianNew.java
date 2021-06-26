package com.java.leet.code.median;

public class MedianNew {

    public static void main(String[] args) {

        int[] num1 = new int[]{1,3,3,4,5, 6};
        int[] num2 = new int[]{1,2,3,4,5};

        System.out.println(findMedianSortedArrays(num1, num2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 一个长度为m的数组数组的中间值 = 索引为(m + 1)/2的元素与索引为(m + 2)/2的元素的平均数
        int left = ((nums1.length + nums2.length) + 1) / 2;
        int right = ((nums1.length + nums2.length) + 2) / 2;

        if (left == right) {
            return findKth(nums1, 0 , nums2, 0, left);
        }

        return (findKth(nums1, 0 , nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2;
    }

    /**
     *
     * @param num1 第一个数组
     * @param i 第一个数组开始的索引
     * @param num2 第二个数组
     * @param j 第二个数组开始的索引
     * @param k 需要找到的2个数组合并后的第K个值
     * @return 第k个值
     */
    private static double findKth(int[] num1, int i, int[] num2, int j, int k) {


        // 当一个数组的起始索引大于长度, 即该数组为空时, 取另外一个数组的第K个值
        // 由于数组起始向右移动了j个位置, 第k个值也向右移动了j个位置, 即为j + k - 1, 减一是因为索引从0开始
        if (i >= num1.length) {
            return num2[j + k - 1];
        }

        if (j >= num2.length) {
            return num1[i + k - 1];
        }

        // 当k为1时, 即2个数组各只剩下一个值时                                         , 取小的值
        if (k == 1) {
            return Math.min(num1[i], num2[j]);
        }

        // 从起始索引开始, 分别找2个数组第k/2个值
        int kValue1 = (i + k /2 -1) < num1.length ? num1[i + k /2 -1] : Integer.MAX_VALUE;
        int kValue2 = (j + k/2 -1) < num2.length ? num2[j + k/2 - 1] : Integer.MAX_VALUE;

        // 2个数组中第k/2个值比较小的数组为a
        // 每次筛选a数组前k/2个数, 筛选掉另外一个数据后k/2个数
        // 即a数组起始索引加k/2, 并且k减去k/2
        if (kValue1 < kValue2) {
            return findKth(num1, i + k/2, num2, j, k - k/2);

        } else {
            return findKth(num1,  i, num2, j + k/2, k - k/2);
        }

    }

}

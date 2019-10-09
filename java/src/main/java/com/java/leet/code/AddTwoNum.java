package com.java.leet.code;

/**
 * @author Ning
 * @date Create in 2019/10/5
 */
public class AddTwoNum {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = null;
        ListNode node = null;
        int addValue = 0;

        while (true) {

            int newValue = l1.val + l2.val + addValue;
            addValue = 0;

            if (newValue >= 10) {
                addValue = newValue/10;
                newValue = newValue%10;
            }

            if (result == null) {
                result = new ListNode(newValue);
                node = result;

            } else {
                node.next = new ListNode(newValue);
                node = node.next;
            }

            l1 = l1.next;
            l2 = l2.next;

            if (l1== null && l2 == null && addValue == 0) {

                return result;

            } else {

                if (l1 == null) {
                    l1 = new ListNode(0);
                }

                if (l2 == null) {
                    l2 = new ListNode(0);
                }
            }
        }
    }

    public static void main(String[] args) {

        AddTwoNum addTwoNum = new AddTwoNum();

        ListNode l1 = addTwoNum.createListNode(5);
        ListNode l2 = addTwoNum.createListNode(5);

        ListNode result = addTwoNum.addTwoNumbers(l1, l2);

    }

    private ListNode createListNode(int... nums) {

        ListNode result = new ListNode(nums[0]);
        ListNode node = result;

        for (int index = 1; index < nums.length; index++) {

            node.next = new ListNode(nums[index]);
            node = node.next;
        }
        return result;
    }
}

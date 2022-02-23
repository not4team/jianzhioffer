/*
 * @Author: shixq
 * @Date: 2021-11-04 14:12:26
 * @LastEditors: shixq
 * @LastEditTime: 2022-02-23 16:46:13
 * @Description: 
 */
package jianzhioffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {
    public static MyObj test = null;

    private static void init(MyObj myTest) {
        myTest.setVal("2");
    }

    public static int findKthNum(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                queue.offer(nums[i]);
            } else {
                if (queue.peek() < nums[i]) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }
        return queue.peek();
    }

    public static ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static ListNode reverseListNode1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode newHead = reverseListNode1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reversKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode next = end.next;
            end.next = null;
            ListNode start = pre.next;
            pre.next = reverseListNode(start);
            start.next = next;
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    public static ListNode mergeListNode(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    public static void mergeArray(int nums1[], int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int p1 = 0, p2 = 0;
        int curr = 0;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                curr = nums2[p2++];
            } else if (p2 == n) {
                curr = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                curr = nums1[p1++];
            } else {
                curr = nums2[p2++];
            }
            result[p1 + p2 - 1] = curr;
        }
    }

    public static void bfsTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll().val);
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
        }
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null && !stack.isEmpty()) {
            // 遍历左子树
            while (node != null) {
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            }
            // 开始遍历右子树
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        for (int i = 0, pos = length - 1, j = length - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                result[pos] = nums[i] * nums[i];
                i++;
            } else {
                result[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }
        return result;
    }

    public static void main(String[] args) {
        MyObj test = new MyTest().new MyObj();
        test.setVal("1");
        init(test);
        System.out.println(test);
        int[] nums = new int[] { 1, 3, 9, 6, 11 };
        System.out.println(findKthNum(nums, 4));
    }

    class MyObj {
        private String val;

        public void setVal(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }
}
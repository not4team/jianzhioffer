package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}

	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		// if(listNode.next == null) {
		// System.out.println(listNode.val);
		// }else {
		// printListFromTailToHead(listNode.next);
		// System.out.println(listNode.val);
		//
		// }
		ArrayList<Integer> arrays = new ArrayList<Integer>();
		if (listNode == null)
			return arrays;
		arrays.add(listNode.val);
		while (listNode.next != null) {
			arrays.add(listNode.next.val);
			listNode = listNode.next;
		}
		Collections.reverse(arrays);
		System.out.println(arrays.toString());
		return (ArrayList<Integer>) arrays;
	}

	public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
		Stack<Integer> stack = new Stack<Integer>();
		while (listNode != null) {
			stack.push(listNode.val);
			listNode = listNode.next;
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}
		return list;
	}

	public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		if (listNode != null) {
			this.printListFromTailToHead2(listNode.next);
			arrayList.add(listNode.val);
		}
		return arrayList;
	}

	/**
	 * 两个指针，先让第一个指针和第二个指针都指向头结点，然后再让第一个指正走(k-1)步，到达第k个节点。然后两个指针同时往后移动，
	 * 当第一个结点到达末尾的时候，第二个结点所在位置就是倒数第k个节点了
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode FindKthToTail(ListNode head, int k) {
		if (head == null || k <= 0) {
			return null;

		}
		ListNode pre = head;
		ListNode last = head;
		for (int i = 1; i < k; i++) {
			if (pre.next != null) {
				pre = pre.next;
			} else {
				return null;
			}
		}
		while (pre.next != null) {
			pre = pre.next;
			last = last.next;
		}
		return last;
	}

	/**
	 * 反转链表
	 * 
	 * @param head
	 * @return
	 */
	public ListNode ReverseList(ListNode head) {
		if(head == null) {
			return head;
		}
		ListNode pre = null;
		ListNode next = null;
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		ListNode.printListFromTailToHead(l1);
	}
}

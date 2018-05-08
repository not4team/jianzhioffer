package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
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
		if (head == null) {
			return head;
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

	/**
	 * 一个链表中包含环，请找出该链表的环的入口结点。
	 * 
	 * 第一步，找环中相汇点。分别用p1，p2指向链表头部，p1每次走一步，p2每次走二步，直到p1==p2找到在环中的相汇点。
	 * 第二步，找环的入口。接上步，当p1==p2时，p2所经过节点数为2x,p1所经过节点数为x,设环中有n个节点,p2比p1多走一圈有2x=n+x; n=x;
	 * 可以看出p1实际走了一个环的步数，再让p2指向链表头部，p1位置不变，p1,p2每次走一步直到p1==p2; 此时p1指向环的入口。
	 */
	public ListNode EntryNodeOfLoop(ListNode pHead) {
		if (pHead == null || pHead.next == null || pHead.next.next == null) {
			return null;
		}

		ListNode fast = pHead.next.next;
		ListNode slow = pHead.next;
		// 先判断有没有环
		while (fast != slow) {
			if (fast.next != null && fast.next.next != null) {
				fast = fast.next.next;
				slow = slow.next;
			} else {
				// 没有环,返回
				return null;
			}
		}
		// 循环出来的话就是有环，且此时fast==slow.
		fast = pHead;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}

	/**
	 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5
	 * 处理后为 1->2->5
	 */
	public ListNode deleteDuplication(ListNode pHead) {
		//新建一个节点，防止头结点要被删除
		ListNode first = new ListNode(-1);
		first.next = pHead;
		ListNode p = pHead;
		ListNode last = first;
		while (p != null && p.next != null) {
			//如果当前节点的值和下一个节点的值相等
			if (p.val == p.next.val) {
				int val = p.val;
				//向后重复查找
				while (p != null && p.val == val) {
					p = p.next;
				}
				last.next = p;
			} else { //如果当前节点和下一个节点值不等，则向后移动一位
				last = p;
				p = p.next;
			}
		}
		//返回头结点的下一个节点
		return first.next;
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

package jianzhioffer;

/**
 * 链表
 * 
 * @author shixq
 *
 */
public class LinkedList {
	class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	/**
	 * 反转链表
	 * 
	 * @param head
	 * @return
	 */
	public ListNode ReverseList(ListNode head) {
		if (head == null)
			return null;
		ListNode pre = null;
		ListNode next = null;
		// 当前节点是head，pre为当前节点的前一节点，next为当前节点的下一节点
		// 需要pre和next的目的是让当前节点从pre->head->next1->next2变成pre<-head next1->next2
		// 即pre让节点可以反转所指方向，但反转之后如果不用next节点保存next1节点的话，此单链表就此断开了
		// 所以需要用到pre和next两个节点
		// 1->2->3->4->5
		// 1<-2<-3 4->5
		while (head != null) {
			// 做循环，如果当前节点不为空的话，始终执行此循环，此循环的目的就是让当前节点从指向next到指向pre
			// 如此就可以做到反转链表的效果
			// 先用next保存head的下一个节点的信息，保证单链表不会因为失去head节点的原next节点而就此断裂
			next = head.next;
			// 保存完next，就可以让head从指向next变成指向pre了，代码如下
			head.next = pre;
			// head指向pre后，就继续依次反转下一个节点
			// 让pre，head，next依次向后移动一个节点，继续下一次的指针反转
			pre = head;
			head = next;
		}
		// 如果head为null的时候，pre就为最后一个节点了，但是链表已经反转完毕，pre就是反转后链表的第一个节点
		// 直接输出pre就是我们想要得到的反转后的链表
		return pre;
	}

	/**
	 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public ListNode mergeList(ListNode list1, ListNode list2) {

		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		if (list1.val <= list2.val) {
			list1.next = mergeList(list1.next, list2);
			return list1;
		} else {
			list2.next = mergeList(list1, list2.next);
			return list2;
		}
	}

	public ListNode mergeList1(ListNode list1, ListNode list2) {

		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		ListNode mergeHead = null;
		ListNode current = null;
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				if (mergeHead == null) {
					mergeHead = current = list1;
				} else {
					current.next = list1;
					current = current.next;
				}
				list1 = list1.next;
			} else {
				if (mergeHead == null) {
					mergeHead = current = list2;
				} else {
					current.next = list2;
					current = current.next;
				}
				list2 = list2.next;
			}
		}
		if (list1 == null) {
			current.next = list2;
		} else {
			current.next = list1;
		}
		return mergeHead;
	}
	
	/**
	 * 输入两个链表，找出它们的第一个公共结点。(公共节点后的next都是一样的)
	 * 
	 * 长度相同有公共结点，第一次就遍历到；没有公共结点，走到尾部NULL相遇，返回NULL
	 * 长度不同有公共结点，第一遍差值就出来了，第二遍一起到公共结点；没有公共，一起到结尾NULL。
	 * @param pHead1
	 * @param pHead2
	 * @return
	 */
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		ListNode p1 = pHead1, p2 = pHead2;
		while (p1 != p2) {
			p1 = (p1 == null ? pHead2 : p1.next);
			p2 = (p2 == null ? pHead1 : p2.next);
		}
		return p1;
	}
}

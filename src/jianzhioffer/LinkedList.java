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
     * 反转链表递归
     * 
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 最小子问题，结束
        if (head == null || head.next == null)
            return head;
        // 递的过程，一次次拆解问题
        ListNode newHead = reverseList(head.next);
        // 归的过程，反转
        head.next.next = head;
        head.next = null;
        return newHead;
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
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 定义一个假的节点。
        ListNode dummy = new ListNode(0);
        // 假节点的next指向head。
        // dummy->1->2->3->4->5
        dummy.next = head;
        // 初始化pre和end都指向dummy。pre指每次要翻转的链表的头结点的上一个节点。end指每次要翻转的链表的尾节点
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            // 循环k次，找到需要翻转的链表的结尾,这里每次循环要判断end是否等于空,因为如果为空，end.next会报空指针异常。
            // dummy->1->2->3->4->5 若k为2，循环2次，end指向2
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
            if (end == null) {
                break;
            }
            // 先记录下end.next,方便后面链接链表
            ListNode next = end.next;
            // 然后断开链表
            end.next = null;
            // 记录下要翻转链表的头节点
            ListNode start = pre.next;
            // 翻转链表,pre.next指向翻转后的链表。1->2 变成2->1。 dummy->2->1
            pre.next = ReverseList(start);
            // 翻转后头节点变到最后。通过.next把断开的链表重新链接。
            start.next = next;
            // 将pre换成下次要翻转的链表的头结点的上一个节点。即start
            pre = start;
            // 翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end = start;
        }
        return dummy.next;
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

    public ListNode mergeList1(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    /**
     * 输入两个链表，找出它们的第一个公共结点。(公共节点后的next都是一样的)
     * 
     * 长度相同有公共结点，第一次就遍历到；没有公共结点，走到尾部NULL相遇，返回NULL
     * 长度不同有公共结点，第一遍差值就出来了，第二遍一起到公共结点；没有公共，一起到结尾NULL。
     * 
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

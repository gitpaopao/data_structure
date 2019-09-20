package pp.linked_list;

/**
 * Created by pkpm on 2019/9/20
 * 查找两个单链表相交的第一个节点（可能相交，也可能不相交）
 * 链表可能有环，也可能无环
 *
 * 时间复杂度O(N+M)
 * 额外空间O(1)
 */
public class FindFirstIntersectNode {

    /**
     * 一个有环，一个无环，是不可能相交的
     * 所以分两大类：均无环 / 均有环
     */
    public static Node getIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }
        if (loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    /**
     * 入环点
     *
     * 头结点head，入环点loop，首次相遇点meet
     * head 到loop的距离是 d1，loop到meet的距离是s1，meet到loop的距离是s2，s1+s2是一圈
     *
     * 首次相遇时，快指针走的路程是慢指针的2倍，d1+s1+n(s1+s2) = 2(d1+s1), 得到 d1=(n-1)(s1+s2)+s2
     *      即从头到入环点的距离，等于 首次相遇点经过n-1圈回到入环点的距离
     *      所以，一个指针从head，一个指针从meet，下一次相遇点就是入环点
     */
    public static Node getLoopNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow){
//          链表奇数/偶数长度  无环的判断
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
//        两指针相遇后，快指针回到开头，慢指针在相遇点，一次一步，再次相遇时就是入环点
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 两个链表都无环
     *  ①不相交
     *  ②若相交就是Y型相交，长链表先走差值步，然后一起走，节点相等时即为相交点
     */
    public static Node noLoop(Node head1,Node head2){
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1 != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null){
            n--;
            cur2 = cur2.next;
        }
//        尾节点不一样，一定不相交
        if (cur1 != cur2){
            return null;
        }
//       此时，n是两个链表长度的差值
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个链表都有环：
     *  ① 两个链表的入环点相同时，Y型相交
     *  ② 入环点不同，loop2不出现在链表1的环上，即不相交
     *  ③ loop2 出现在链表1的环上，在环上相交
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        Node cur1;
        Node cur2;
        if (loop1 == loop2){
            int n = 0;
            cur1 = head1;
            cur2 = head2;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args){
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next;
        // 1-- 6
        System.out.println("1-- " + getIntersectNode(head1, head2).data);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next.next = head1.next.next.next;

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next;
        // 2-- 2
        System.out.println("2-- " + getIntersectNode(head1, head2).data);

//        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next;
        // 3-- 4
        System.out.println("3-- " + getIntersectNode(head1, head2).data);
    }
    private static class Node{
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
        }
    }
}

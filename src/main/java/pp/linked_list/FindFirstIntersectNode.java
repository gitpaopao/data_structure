package pp.linked_list;

/**
 * Created by pkpm on 2019/9/20
 * 查找两个单链表相交的第一个节点（可能相交，也可能不相交）
 * 链表可能有环，也可能无环
 * <p>
 * 时间复杂度O(N+M)
 * 额外空间O(1)
 */
public class FindFirstIntersectNode {

    /**
     * 通过入环点判断是否有环
     * ①一个有环，一个无环：不相交
     * ②均无环
     * ③均有环
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 获取入环点
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 入环点
     * <p>
     * 头结点head，入环点loop，首次相遇点meet
     * head 到loop的距离是 d1，loop到meet的距离是s1，meet到loop的距离是s2，s1+s2是一圈
     * <p>
     * 首次相遇时，快指针走的路程是慢指针的2倍，d1+s1+n(s1+s2) = 2(d1+s1), 得到 d1=(n-1)(s1+s2)+s2
     * 即从头到入环点的距离，等于 首次相遇点经过n-1圈回到入环点的距离
     * 所以，一个指针从head，一个指针从meet，下一次相遇点就是入环点
     */
    public static Node getLoopNode(Node head) {
       /* if (head == null || head.next == null || head.next.next == null){
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
        return slow;*/
        // 找到meet节点
        Node fast = head;
        Node slow = head;
        Node meet = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                meet = fast;
                break;
            }
        }
        // 不会相遇,即无环
        if (meet == null) {
            return null;
        }
        while (head != meet) {
            head = head.next;
            meet = meet.next;
        }
        return meet;
    }

    /**
     * 两个链表都无环
     * ①不相交, 尾节点不同
     * ②Y型相交,长链表先走差值步,然后一起走,节点相等时即为相交点
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 计算两个链表长度的差值 n
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
//        尾节点不一样，一定不相交
        if (cur1 != cur2) {
            return null;
        }
//       cur1指向长链表的头节点
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        // 长链表先走差值步,然后和短链表一起遍历
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 拼接链表消除差值法
     * 路程相同,速度相同,终点一致,一定同时到达岔路口,到达终点
     */
    public static Node noLoop2(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node node1 = head1;
        Node node2 = head2;
        while (node1 != node2) {
            node1 = node1 == null ? head2 : node1.next;
            node2 = node2 == null ? head1 : node2.next;
        }
        return node1;
    }

    /**
     * 两个链表都有环：
     * ① 两个链表的入环点相同时，Y型相交
     * ② 入环点不同，loop2不出现在链表1的环上，即不相交
     * ③ loop2 出现在链表1的环上，在环上相交
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1;
        Node cur2;
        // 入环点相同
        if (loop1 == loop2) {
            int n = 0;
            cur1 = head1;
            cur2 = head2;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
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
        if (getIntersectNode(head1, head2) == null) {
            System.out.println("不相交");
        } else {
            System.out.println("1-- " + getIntersectNode(head1, head2).data);
        }

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

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}

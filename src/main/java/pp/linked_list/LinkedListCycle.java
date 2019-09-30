package pp.linked_list;

/**
 * Created by paopao on 2019/9/3
 *
 * 判断链表是否有环
 *
 * 借助两个指针，慢的每次移动一个，快的每次移动2个，如果有环，总会相遇
 */
public class LinkedListCycle {

    /**
     * 链表节点
     */
    private static class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 判断是否有环
     * @param head  链表头节点
     */
    public static boolean isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println(isCycle(node1));
    }
}

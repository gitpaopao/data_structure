package pp.linked_list;

/**
 * Created by pkpm on 2019/9/12
 *
 * 打印两个有序链表的公共部分
 *   类似外排的方式
 */
public class PrintCommonPart {

    /**
     * 链表节点
     */
    private static class Node{
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 遍历链表
     * @param head 头节点
     */
    public static void printLinkedList(Node head){
        while (head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    /**
     * 打印公共部分
     */
    public static void printCommonPart(Node head1, Node head2){
        while (head1 != null && head2 != null){
            if (head1.data < head2.data){
                head1 = head1.next;
            }else if (head1.data == head2.data){
                System.out.print(head1.data + " ");
                head1 = head1.next;
                head2 = head2.next;
            }else {
                head2 = head2.next;
            }
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printLinkedList(node1);
        System.out.println();
        printLinkedList(node2);
        System.out.println();
        printCommonPart(node1, node2);
    }
}

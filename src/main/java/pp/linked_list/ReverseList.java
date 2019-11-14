package pp.linked_list;

import java.util.Stack;

/**
 * Created by pkpm on 2019/11/14
 * 反转链表与逆序输出
 */
public class ReverseList {

    /**
     * 借助栈实现逆序输出
     */
    private static void output1(Node head) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data);
        }
    }

    /**
     * 递归逆序输出
     */
    private static void output2(Node head) {
        if (head == null) {
            return;
        }
        output2(head.next);
        System.out.println(head.data);
    }

    /**
     * 反转链表,每次改变的是当前节点的next, 指向它的prev
     * 所以要记录prev, 另外记录next是暂存下次遍历的节点
     *
     * @return 返回头节点
     */
    private static Node reverse1(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node prev = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 递归反转链表
     *   从右向左反转
     * @return 头节点，原尾节点
     */
    private static Node reverse2(Node head) {
        if (head == null || head.next == null){
            return head;
        }
        Node rear = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return rear;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = null;
        print(reverse1(head));
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
        }
    }
}

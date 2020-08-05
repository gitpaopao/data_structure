package pp.linked_list;

import java.util.Stack;

/**
 * Created by pkpm on 2019/9/12
 *
 * 判断链表是否是回文结构  1 2 3 2 1
 *    实质就是判断 逆序和正序是否一致
 */
public class PalindromeList {

    /**
     * 解法1：
     * 使用栈，需要 n 个额外空间
     */
    public static boolean isPalindrome1(Node head){
        if (head == null || head.next == null){
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node tmp = head;
//        全部入栈
        while (head != null){
            stack.push(head);
            head = head.next;
        }
//        一边出栈，一边遍历，不同则false；
        while (!stack.isEmpty()){
            if (stack.pop().data != tmp.data){
                return false;
            }
            tmp = tmp.next;
        }
        return true;
    }

    /**
     * 解法2：
     * 只将后半部分入栈，出栈，和前半部分比较
     * n/2 个额外空间
     *
     * 重点是找中点的位置：快指针走两步，慢指针走一步，快指针到尾的时候，慢指针到达中点
     */
    public static boolean isPalindrome2(Node head){
        if (head == null || head.next == null){
            return true;
        }
//        先找中点的位置s
//        这里是处理一个边界的问题，这样慢指针始终落在右区第一个
        Node s = head.next;
        Node q = head;
        while (q.next != null && q.next.next != null){
            q = q.next.next;
            s = s.next;
        }
//        右区入栈
        Stack<Node> stack = new Stack<>();
        while (s != null){
            stack.push(s);
            s = s.next;
        }
//        出栈
        while (!stack.isEmpty()){
            if (stack.pop().data != head.data){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 解法3：
     * in-place:
     *    时间复杂度O(N),空间复杂度O(1)
     *    找到右区前一个位置,将右区逆序,两个指针遍历,比较左区和右区是否相等,遍历完成后,将链表恢复
     */
    public static boolean isPalindrome3(Node head){
        if (head == null || head.next == null){
            return true;
        }
        // 1.找中间节点
        Node quick = head;
        Node slow = head;
        while (quick.next != null && quick.next.next != null){
            quick = quick.next.next;
            slow = slow.next;
        }
        // 2.将右半部分逆序
        Node prev = reverse(slow.next);
        //暂存尾节点
        Node cur = prev;
        boolean flag = true;
        while (head != null && prev != null){
            if (head.data != prev.data){
                flag = false;
                break;
            }
            head = head.next;
            prev = prev.next;
        }
        // 3.逆序回来
       reverse(cur);
        return flag;
    }

    public static void main(String[] args){
        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        System.out.println();

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

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
     */
    public static void printLinkedList(Node head){
        while (head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static Node reverse(Node node){
        if (node == null || node.next == null){
            return node;
        }
        Node prev = null;
        Node next;
        while (node != null){
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}

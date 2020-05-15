import com.sun.javafx.css.parser.StopConverter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by pkpm on 2020/5/12
 */
public class TestMyLinkedList {

    private Node first;
    private Node last;
    private int size;

    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 单链表插入操作
     */
    public void insert(int ele,int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("非法插入");
        }
        Node newNode = new Node(ele);
        if (size == 0){
            first = newNode;
            last = newNode;
        }else if (index == 0){
        //    头插
            newNode.next = first;
            first = newNode;
        }else if (index == size){
        //    尾插
            last.next = newNode;
            last = newNode;
        }else {
            Node prev = get(index-1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    /**
     * 单链表查询操作
     */
    private Node get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("下标越界");
        }
        Node node = first;
        for (int i = 0;i<index;i++){
            node = node.next;
        }
        return node;
    }

    /**
     * 单链表删除操作
     */
    public int delete(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("下标越界");
        }
        Node delete = null;
        if (index == 0){
        //    删头
            delete = first;
            first = first.next;
        }else if (index == size-1){
        //    删尾
            delete = last;
            last = get(index-1);
        }else {
        //    删中间
           Node prev = get(index-1);
           delete = prev.next;
           prev.next = delete.next;
        }
        size--;
        return delete.data;
    }

    public static void output(Node first){
        Node node = first;
        while (node != null){
            System.out.print(node.data + "  ");
            node = node.next;
        }
        System.out.println();
    }

    @Test
    public void test(){
        TestMyLinkedList myLinkedList = new TestMyLinkedList();
//        index 等于0，在头部插入
        myLinkedList.insert(3,0);
        myLinkedList.insert(4,0);
        myLinkedList.insert(9,2);
        myLinkedList.insert(5,3);
        myLinkedList.insert(6,1);
        System.out.println("构建链表");
        myLinkedList.output(myLinkedList.first);
        // myLinkedList.delete(0);
        System.out.println("逆序输出");
        reverseOutput1(myLinkedList.first);
        // reverseOutput2(myLinkedList.first);
        System.out.println();
        System.out.println("反转链表");
        Node node = reverse1(myLinkedList.first);
        myLinkedList.output(node);
        System.out.println("原链表");
        myLinkedList.output(myLinkedList.first);
        System.out.println("再反转链表");
        Node node1 = reverse2(myLinkedList.first);
        myLinkedList.output(node1);
    }

    /**
     * 单链表逆序输出
     */
    public void reverseOutput1(Node first){
        Stack<Node> stack = new Stack<>();
        while (first != null){
            stack.push(first);
            first = first.next;
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop().data + "  ");
        }
        System.out.println();
    }

    public void reverseOutput2(Node first){
        if (first == null){
            return;
        }
        reverseOutput2(first.next);
        System.out.print(first.data + "  ");
    }

    /**
     * 反转单链表
     */
    public Node reverse1(Node first){
        if (first == null || first.next == null){
            return first;
        }
        Node prev = null;
        Node cur = first;
        Node next;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public Node reverse2(Node first){
        if (first == null || first.next == null){
            return first;
        }
        Node last = reverse2(first.next);
        first.next.next = first;
        first.next = null;
        return last;
    }

    /**
     * 判断单链表是否有环
     */
    public boolean isCycle(Node first){
        Node slow = first;
        Node quick = first;
        while (quick != null && quick.next != null){
            slow = slow.next;
            quick = quick.next.next;
            if (slow == quick){
                return true;
            }
        }
        return false;
    }

    @Test
    public  void test2() {
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

    /**
     * 判断链表是否是回文结构
     */
    public boolean palindromeList(Node first){
        if (first == null || first.next == null){
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = first;
        while (first != null){
            stack.push(first);
            first = first.next;
        }
        while (!stack.isEmpty()){
            if (cur.data != stack.pop().data){
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 只将右半部分入栈
     * 快慢指针查找中间位置：quick总是落在偶数下标上
     *                    奇数长度时,quick可到达尾节点,步数为 length/2,slow到达下标为 length/2的位置,即中间位置
     *                    偶数长度时,quick到达倒数第二个,或者 null的位置,到达倒数第二个位置时,经过length/2-1步,slow到达下标为length/2-1的位置,即左区最后一个
     *                    综上：quick达到尾节点或倒数第二位置时,slow的下一个位置是右区第一
     */
    public boolean palindromeList2(Node first){
        if (first == null || first.next == null){
            return true;
        }
        Node slow = first;
        Node quick = first;
        while (quick.next != null &&quick.next.next != null){
            slow = slow.next;
            quick = quick.next.next;
        }
        slow = slow.next;
        Stack<Node> stack = new Stack<>();
        while (slow != null){
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()){
            if (stack.pop().data != first.data){
                return false;
            }
        }
        return true;
    }

    public boolean palindromeList3(Node first){
        if (first == null || first.next == null){
            return true;
        }
    //    找中间节点
        Node quick = first;
        Node slow = first;
        while (quick.next != null && quick.next.next != null){
            quick = quick.next.next;
            slow = slow.next;
        }
        //将右半部分逆序
        Node prev = slow;
        Node cur = slow.next;
        prev.next = null;
        Node next;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        //暂存尾节点
        cur = prev;
        boolean flag = true;
        while (first != null && prev != null){
            if (first.data != prev.data){
                flag = false;
                break;
            }
            first = first.next;
            prev = prev.next;
        }
       // 逆序回来
        prev = null;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return flag;
    }

    @Test
    public void test3(){
        Node head;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        output(head);
        System.out.println(palindromeList(head));
        System.out.println(palindromeList3(head));
       output(head);
    }


    public static void printCommonPart(Node head1,Node head2){
        while (head1 != null && head2 != null){
            if (head1.data < head2.data){
                head1 = head1.next;
            }else if (head1.data == head2.data){
                System.out.println(head1.data);
                head1 = head1.next;
                head2 = head2.next;
            }else {
                head2 = head2.next;
            }
        }
    }

    @Test
   public void test4(){
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        output(node1);
        System.out.println();
        output(node2);
        System.out.println();
        printCommonPart(node1, node2);
   }

   public static class RandNode{
        private int data;
        private RandNode next;
        private RandNode rand;

       public RandNode(int data) {
           this.data = data;
       }
   }

    public static void printList(RandNode head){
        RandNode cur = head;
        System.out.print("random: ");
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();

        cur = head;
        System.out.print("rand:   ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

   public RandNode copyListWithRandom(RandNode first){
       Map<RandNode,RandNode> map = new HashMap<>();
       RandNode tmp = first;
       while (tmp != null){
           RandNode node = new RandNode(tmp.data);
           map.put(tmp,node);
           tmp = tmp.next;
       }
       tmp = first;
       while (tmp != null){
           RandNode node = map.get(tmp);
           node.next = map.get(tmp.next);
           node.rand = map.get(tmp.rand);
           tmp = tmp.next;
       }
       return map.get(first);
   }

   @Test
    public void test5(){
        RandNode head;
        head = new RandNode(1);
        head.next = new RandNode(2);
        head.next.next = new RandNode(3);
        head.next.next.next = new RandNode(4);
        head.next.next.next.next = new RandNode(5);
        head.next.next.next.next.next = new RandNode(6);

        // 1 -> 6
        head.rand = head.next.next.next.next.next;
        // 2 -> 6
        head.next.rand = head.next.next.next.next.next;
        // 3 -> 5
        head.next.next.rand = head.next.next.next.next;
        // 4 -> 3
        head.next.next.next.rand = head.next.next;
        // 5 -> null
        head.next.next.next.next.rand = null;
        // 6 -> 4
        head.next.next.next.next.next.rand = head.next.next.next;

        printList(head);
        System.out.println("--------------------------------");
        RandNode node1 = copyListWithRandom(head);
        printList(node1);
        System.out.println("--------------------------------");
        RandNode node2 = inPlaceCopy(head);
        printList(node2);
    }

    private RandNode inPlaceCopy(RandNode head) {
        if (head == null){
            return null;
        }
        RandNode cur = head;
        RandNode next;
        while (cur != null){
            next = cur.next;
            cur.next = new RandNode(cur.data);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        RandNode copy;
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            copy.rand = cur.rand == null?null:cur.rand.next;
            cur = next;
        }

        cur = head;
        RandNode newHead = head.next;
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next == null?null:next.next;
            cur = next;
        }
        return newHead;
    }
    
    public static Node getIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }else if (loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }else {
            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2){
            return null;
        }
        // cur1指向长链表的头节点
        cur1 = n > 0? head1 : head2;
        cur2 = cur1==head1 ? head2 : head1;
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

    private static Node bothLoop(Node head1,Node loop1, Node head2,Node loop2) {
        Node cur1 = head1;
        Node cur2 = head2;
        if (loop1 == loop2){
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0?head1:head2;
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

    private static Node getLoopNode(Node head) {
        // 找到meet节点
        Node fast = head;
        Node slow = head;
        Node meet = null;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                meet = fast;
                break;
            }
        }
        // 不会相遇,即无环
        if (meet == null){
            return null;
        }
        while (head != meet){
            head = head.next;
            meet = meet.next;
        }
        return meet;
    }

    @Test
    public void test6(){
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
        // head2.next.next.next = head1.next.next.next.next.next;
        // 1-- 6
        if (getIntersectNode(head1,head2) == null){
            System.out.println("不相交");
        }else {
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


      public static class ListNode {
          int val;
          ListNode next;

          ListNode(int x) {
              val = x;
          }
      }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head;
        if(l1.val < l2.val){
            head = new ListNode(l1.val);
            l1 = l1.next;
        }else if(l1.val == l2.val){
            head = new ListNode(l1.val);
            l1 = l1.next;
        }else{
            head = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode cur = head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }else if(l1.val == l2.val){
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }else{
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1==null?l2:l1;
        return head;
    }

    @Test
    public void test7(){

    }
}

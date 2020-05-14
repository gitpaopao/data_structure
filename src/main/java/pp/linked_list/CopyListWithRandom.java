package pp.linked_list;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pkpm on 2019/9/20
 *
 * 复制含有随机指针节点的链表
 *
 * 无环单链表
 */
public class CopyListWithRandom {
//    节点
    private static class Node{
        int data;
        Node next;
        Node rand;
        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 借助哈希表
     */
    public static Node copyWithHash(Node head){
        Map<Node,Node> map = new HashMap<>();
//        利用hash保存节点与copy节点的对应关系
        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.data));
            cur = cur.next;
        }
//        重新遍历一遍，设置copy节点的next和rand 指针
//        copy节点的next = 原节点的next的copy节点，通过hash找copy节点
        cur = head;
        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * in-place：利用hash是保存与copy节点的关系，不用hash，就每次拿出两个节点，不需要再去hash中获取关系
     * @return
     */
    public static Node inPlaceCopy(Node head){
        if (head == null){
            return null;
        }
        Node cur = head;
        Node next;
//        将每个节点的copy节点放在它的后边，即原来的两个节点间插入一个copy节点
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.data);
            cur.next.next = next;
            cur = next;
        }

//        设置 copy节点的rand指针
        cur = head;
        Node copy;
        while (cur != null){
//            每次拿出两个节点，下一次循环是原链表的节点，暂存一下next关系
            next = cur.next.next;
            copy = cur.next;
            copy.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }

//        分离两个链表，调整next指针（删除原来的两个节点间的copy节点）
        cur = head;
        Node result = head.next;
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next == null ? null : next.next;
            cur = next;
        }
        return result;
    }

    public static void printList(Node head){
        Node cur = head;
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

    public static void main(String[] args){
        Node head;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

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
        Node node1 = copyWithHash(head);
        printList(node1);
        System.out.println("--------------------------------");
        Node node2 = inPlaceCopy(head);
        printList(node2);
    }
}

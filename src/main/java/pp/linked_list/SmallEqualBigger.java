package pp.linked_list;

/**
 * Created by pkpm on 2019/9/12
 *
 * 在链表上实现荷兰国旗问题:
 *
 * 进阶：左中右三个部分的内部也做顺序要求，与原链表中节点的先后次序一致
 *      时间复杂度O(N),空间复杂度O(1)
 */
public class SmallEqualBigger {

    /**
     * (借助容器数组，在数组上patition，然后再串起来)
     *
     *      链表改变了，要返回头节点
     */
    public static Node partiton1(Node head, int pivot){
        if (head == null){
            return head;
        }
//        确定链表长度，初始化数组
        Node cur = head;
        int i = 0;
        while (cur != null){
            i++;
            head = head.next;
        }
        Node[] arr = new Node[i];

//        节点放进数组
        cur = head;
        for (i=0; i<arr.length; i++){
            arr[i] = cur;
            cur = cur.next;
        }
        //    荷兰国旗问题
        int more = arr.length - 1;
        int less = -1;
        i = 0;
        while (i < more){
            if (arr[i].data < pivot){
                swap(arr,++less,i++);
            }else if (arr[i].data == pivot){
                i++;
            }else {
                swap(arr,--more,i);
            }
        }
//        数组串成链表
        for (i=1; i<arr.length; i++){
            arr[i-1].next = arr[i];
        }
        // 尾节点
        arr[i-1].next = null;
        return arr[0];
    }

    /**
     * 借助6个指针，遍历链表，找到第一个小于pivot的less，等于pivot的mid，大于的more
     * 再遍历链表，小于pivot的不是less，挂在less下，同理……
     * 每个区域用两个变量记录头指针和尾指针，然后将三个区域串起来
     */
    public static Node partition2(Node head, int pivot){
        Node sh = null;
        Node st = null;
        Node eh = null;
        Node et = null;
        Node bh = null;
        Node bt = null;
        while (head != null){
//            改变链表关系，每次访问的是一个独立的节点，next置为空，并暂存下一个节点
            Node next = head.next;
            head.next = null;
            if (head.data < pivot){
                if (sh == null){
                    sh = head;
                    st = head;
                }else {
                    st.next = head;
                    st = head;
                }
            }else if (head.data == pivot){
                if (eh == null){
                    eh = head;
                    et = head;
                }else {
                    et.next = head;
                    et = head;
                }
            }else {
                if (bh == null){
                    bh = head;
                    bt = head;
                }else {
                    bt.next = head;
                    bt = head;
                }
            }
            head = next;
        }
//        将small、equal、bug 连接起来
        if (st != null){
            st.next = eh;
            et = et==null ? st : et;
        }
        if (et != null){
            et.next = bh;
        }
//        返回头节点
        return sh != null ? sh : eh != null ? eh : bh;
    }

    public static void main(String[] args){
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        System.out.println();
        head1 = partition2(head1, 5);
        printLinkedList(head1);
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


    public static void swap(Node[] arr,int i,int j){
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

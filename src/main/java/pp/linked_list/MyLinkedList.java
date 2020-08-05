package pp.linked_list;

/**
 * Created by paopao on 2019/9/3
 */
public class MyLinkedList {

    //   头指针
    private Node head;
    //    尾指针
    private Node last;
    private int size;

    //    节点结构
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    /**
     * 链表插入元素
     *
     * @param element 插入元素
     * @param index   插入位置
     */
    public void insert(int element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("非法插入");
        }
        Node node = new Node(element);
        if (size == 0) {
//            空链表,头、尾节点均为
            head = node;
            last = node;
        } else if (index == 0) {
//            头部插入
            node.next = head;
            head = node;
        } else if (index == size) {
//            尾部插入
            last.next = node;
            last = node;
        } else {
//           中间插入
            Node pre = get(index - 1);
            node.next = pre.next;
            pre.next = node;
        }
        size++;
    }

    /**
     * 链表查找元素
     *
     * @param index 查找的位置
     */
    public Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 链表删除元素
     *
     * @param index 删除的位置
     */
    public Node delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        Node delete;
        if (index == 0) {
//            删除头结点
            delete = head;
            head = head.next;
        } else if (index == size - 1) {
//            删除尾节点
            Node prev = get(index - 1);
            delete = prev.next;
//            记得设置尾节点的next为null
            prev.next = null;
            last = prev;
        } else {
//            中间删除
            Node prev = get(index - 1);
            delete = prev.next;
            prev.next = prev.next.next;
        }
        size--;
        return delete;
    }

    /**
     * 输出链表
     */
    public void output() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
//        index 等于0，在头部插入
        myLinkedList.insert(3, 0);
        myLinkedList.insert(4, 0);
        myLinkedList.insert(9, 2);
        myLinkedList.insert(5, 3);
        myLinkedList.insert(6, 1);
        myLinkedList.delete(0);
        myLinkedList.delete(3);
        myLinkedList.output();
    }
}

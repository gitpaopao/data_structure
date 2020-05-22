package pp.queue;

/**
 * Created by paopao on 2019/9/3
 *
 * 队列最大容量比数组长度小1
 * 牺牲一个空间，区分队空与队满
 * rear指向尾元素的下一个元素
 */
public class MyQueue {

    private int[] array;
    private int rear;
    private int front;

    public MyQueue(int capacity){
        this.array = new int[capacity];
    }

    /**
     * 入队
     */
    public void enQueue(int element) throws Exception {
        if ((rear+1)%array.length == front){
            throw new Exception("队列满");
        }
        array[rear] = element;
        rear = (rear+1)%array.length;
    }

    /**
     * 出队
     */
    public int deQueue() throws Exception {
        if (rear == front){
            throw new Exception("队列空");
        }
        int delete = array[front];
        front = (front+1)%array.length;
        return delete;
    }

    /**
     * 输出队列
     */
    public void output(){
        for (int i=front; i!=rear; i=(i+1)%array.length){
            System.out.println(array[front]);
        }
    }

    public static void main(String[] args) throws Exception {
        MyQueue myQueue = new MyQueue(6);
        myQueue.enQueue(3);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(8);
        myQueue.enQueue(1);
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.enQueue(2);
        myQueue.enQueue(4);
        myQueue.enQueue(9);
        myQueue.output();
    }
}

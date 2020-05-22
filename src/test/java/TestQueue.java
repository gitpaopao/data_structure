/**
 * Created by pkpm on 2020/5/22
 */
public class TestQueue {
    private int[] array;
    private int front;
    private int rear;

    public TestQueue(int size) {
        array = new int[size];
    }

    public void enQueue(int ele) throws Exception {
        if ((rear+1)%array.length == front){
            throw new Exception("队列满");
        }
        array[rear] = ele;
        rear = (rear+1)%array.length;
    }


    public int deQueue() throws Exception {
        if (rear == front){
            throw new Exception("队列空");
        }
        int ele = array[front];
        front = (front+1)%array.length;
        return ele;
    }

    public void output(){
        for (int i = front; i != rear; i=(i+1)%array.length){
            System.out.println(array[i]);
        }
    }
}

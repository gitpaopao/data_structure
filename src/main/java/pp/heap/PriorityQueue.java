package pp.heap;

import java.util.Arrays;

/**
 * Created by pkpm on 2019/9/29
 * 优先队列(实质就是堆)
 */
public class PriorityQueue {
    private int size;
    private int[] array;
    public PriorityQueue() {
        this.array = new int[32];
    }

    public void enQueue(int ele){
        if (size > array.length){
//            resize
            resize();
        }
        array[size++] = ele;
        upAdjust();
    }

    public int deQueue() throws Exception {
        if (size <= 0){
            throw new Exception("the queue is empty");
        }
        int delete = array[0];
        array[0] = array[--size];
        downAdjust();
        return delete;
    }

    public void resize(){
        int newSize = 2 *size;
        this.array = Arrays.copyOf(array,newSize);
    }
//  上浮
    public void upAdjust(){
        int cindex = size-1;
        int pindex = (cindex-1)/2;
        int tmp = array[cindex];
        while (cindex > 0){
            if (tmp > array[pindex]){
                array[cindex] = array[pindex];
                cindex = pindex;
                pindex = (cindex-1)/2;
            }else {
                break;
            }
        }
        array[cindex] = tmp;
    }
//    下沉
    public void downAdjust(){
        int pindex = 0;
        int tmp = array[pindex];
        int cindex = 2*pindex + 1;
        while (cindex < size){
            if (cindex+1 < size && array[cindex+1] > array[cindex]){
                cindex = cindex + 1;
            }
            if (tmp < array[cindex]){
                array[pindex] = array[cindex];
                pindex = cindex;
                cindex = 2*pindex + 1;
            }else {
                break;
            }
        }
        array[pindex] = tmp;
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("出队元素：" + priorityQueue.deQueue());
        System.out.println("出队元素：" + priorityQueue.deQueue());
    }
}

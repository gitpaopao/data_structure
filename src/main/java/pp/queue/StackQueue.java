package pp.queue;

import java.util.Stack;

/**
 * Created by paopao on 2019/9/3
 *
 * 用栈结构实现队列
 * 使用两个栈，栈A的全部元素倒进栈B中，可实现栈B出栈的顺序和栈A入栈的顺序一致，先进先出
 *
 */
public class StackQueue {

    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();

    public void enQueue(Integer ele){
        stackA.push(ele);
        transfer();
    }

    public Integer deQueue(){
        if (stackA.isEmpty() && stackB.isEmpty()){
            throw new RuntimeException("empty");
        }
        transfer();
        return stackB.pop();
    }

    /**
     * 两个原则：
     *  ① B中有数据时，不可移动
     *  ② 元素要一次性倒完
     *
     *  transfer()在入队、出队时调用都是可以的
     */
    public void transfer(){
        if (stackB.isEmpty() && !stackA.isEmpty()){
            while (!stackA.isEmpty()){
                stackB.push(stackA.pop());
            }
        }
    }

//    1 2 3 4
    public static void main(String[] args){
        StackQueue stackQueue = new StackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        stackQueue.enQueue(4);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
    }
}

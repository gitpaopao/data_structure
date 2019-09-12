package pp.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by pkpm on 2019/9/11
 *
 * 用队列结构实现栈
 *
 * 两个队列
 */
public class QueueStack {

    private Queue<Integer> queueA = new LinkedList<>();
    private Queue<Integer> queueB = new LinkedList<>();

    /**
     * 入栈，直接放队列A中
     */
    public void push(Integer ele){
        queueA.add(ele);
    }

    /**
     * 出栈
     * ①队列A中只有一个元素，直接出队
     * ②队列A中对于一个元素，将其他元素放入队列B中，只留最后一个元素，然后出队
     *      将队列B中元素再移到队列A中
     * @return
     */
    public Integer pop() {
        if (queueA.size() == 1) {
            return queueA.poll();
        } else {
            while (queueA.size() != 1) {
                queueB.add(queueA.poll());
            }
            Integer tem = queueA.poll();
            while (!queueB.isEmpty()) {
                queueA.add(queueB.poll());
            }
            return tem;
        }
    }

//    1 4 3 2
    public static void main(String[] args){
        QueueStack stack = new QueueStack();
        stack.push(1);
        System.out.println(stack.pop());
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

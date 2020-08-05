import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by pkpm on 2020/5/20
 */
public class TestStack {
    private int[] array;
    private int index;

    public TestStack(int size) {
        if (size <= 0){
            throw new IllegalArgumentException();
        }
        array = new int[size];
        index = 0;
    }

    public void push(int ele){
        if (index == array.length){
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index++] = ele;
    }

    public int pop(){
        if (index == 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[--index];
    }

    public int peek(){
        return array[index-1];
    }

    public static class QueueStack{
        private Queue<Integer> queueA = new LinkedList<>();
        private Queue<Integer> queueB = new LinkedList<>();

        public void push(Integer ele){
            queueA.offer(ele);
        }

        public Integer pop(){
            if (queueA.size() == 1){
                return queueA.poll();
            }else {
                while (queueA.size() != 1){
                    queueB.add(queueA.poll());
                }
                Integer ele = queueA.poll();
                while(!queueB.isEmpty()){
                    queueA.offer(queueB.poll());
                }
                return ele;
            }
        }
    }

    public static class MinStack{
        private Stack<Integer> mainStack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public void push(Integer ele){
            mainStack.push(ele);
            if (minStack.isEmpty() || ele < minStack.peek()){
                minStack.push(ele);
            }
        }

        public Integer pop(){
            if (mainStack.peek().equals(minStack.peek())){
                minStack.pop();
            }
            return mainStack.pop();
        }

        public Integer peek() throws Exception {
            if (mainStack.isEmpty()){
                throw  new Exception("stack is empty");
            }
            return minStack.peek();
        }
    }

    public void reverseStack(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        Integer tail = getAndRemoveTail(stack);
        reverseStack(stack);
        stack.push(tail);
    }

    private Integer getAndRemoveTail(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()){
            return pop;
        }else {
            Integer integer = getAndRemoveTail(stack);
            stack.push(pop);
            return integer;
        }
    }


    public static class StackQueue{
        private Stack<Integer> stackA = new Stack<>();
        private Stack<Integer> stackB = new Stack<>();

        public void push(Integer ele){
            stackA.push(ele);
            transfer(stackA,stackB);
        }

        public Integer pop(){
            if (stackA.isEmpty() && stackB.isEmpty()){
                throw new RuntimeException("empty");
            }
            transfer(stackA,stackB);
            return stackB.pop();

        }

        private void transfer(Stack<Integer> stackA,Stack<Integer> stackB) {
            if (!stackA.isEmpty() && stackB.isEmpty()){
                while(!stackA.isEmpty()){
                    stackB.push(stackA.pop());
                }
            }
        }
    }

}

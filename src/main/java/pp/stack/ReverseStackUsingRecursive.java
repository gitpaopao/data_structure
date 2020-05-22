package pp.stack;

import java.util.Stack;

/**
 * Created by pkpm on 2019/12/30
 * 逆序一个栈，不申请额外的数据结构，只能使用递归函数
 * <p>
 * 递归取出栈底元素，然后再一次入栈，顺序就已逆转
 */
public class ReverseStackUsingRecursive {


    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int tail = getAndRemoveStackTail(stack);
        reverseStack(stack);
        stack.push(tail);
    }

    /*
     获取并删除栈底元素
     ①出栈,如果栈为空了,就是栈底元素
     ②如果不是,再继续获取剩下栈的栈底元素,然后将当前元素放进去
       就是一个个元素出栈,删除掉栈底后,再按顺序一个个入栈
      */
    public static int getAndRemoveStackTail(Stack<Integer> stack) {
        Integer top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        } else {
            int i = getAndRemoveStackTail(stack);
            stack.push(top);
            return i;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        reverseStack(stack);
        System.out.println(stack.peek());
    }
}

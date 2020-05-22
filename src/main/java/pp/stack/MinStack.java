package pp.stack;

import java.util.Stack;

/**
 * Created by paopao on 2019/9/3
 *
 * 栈:入栈、出栈、获取最小值，保证时间复杂度都是O(1)
 * 借助一个辅助栈，最小栈
 */
public class MinStack {

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    /**
     * 入栈
     */
    public void push(Integer ele){
        mainStack.push(ele);
        // 最小栈为空 或 当前元素小于最小栈的栈顶时,最小栈入栈
        if (minStack.isEmpty() || ele < minStack.peek()){
            minStack.push(ele);
        }
    }

    /**
     * 出栈
     */
    public Integer pop(){
        // 如果出栈元素和最小栈栈顶元素值相等,最小栈也要出栈
        if (mainStack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        return mainStack.pop();
    }

    /**
     * 最小值
     */
    public Integer getMin() throws Exception {
        if (mainStack.isEmpty()){
            throw new Exception("stack is empty");
        }
        return minStack.peek();
    }

    public static void main(String[] args) throws Exception {
        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
    }
}

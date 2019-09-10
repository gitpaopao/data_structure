package pp.stack;

/**
 * Created by pkpm on 2019/9/10
 *
 * 用数组结构实现固定大小的栈
 */
public class ArrayStack {
    private Integer[] arr;
    private Integer index;

    public ArrayStack(int size) {
        if (size < 0){
            throw new IllegalArgumentException();
        }
        arr = new Integer[size];
        index = 0;
    }

    public Integer peek(){
        if (index == 0){
            return null;
        }
        return arr[index-1];
    }

    public void push(int obj){
        if (index == arr.length){
            throw new ArrayIndexOutOfBoundsException();
        }
        arr[index++] = obj;
    }

    public Integer pop(){
        if (index == 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[--index];
    }
}

import lombok.Data;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by pkpm on 2020/5/12
 */
@Data
public class TestMyArray {
    private int[] array;
    private int size; // 数组中实际元素的个数

    public TestMyArray(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    /**
     * insert
     */
    public void insert(int index, int item) {
        if (index < 0 || index > size) {
            System.out.println("非法插入");
            return;
        }
        if (size >= array.length) {
            resize();
        }
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = item;
        size++;
    }

    /**
     * 扩容
     */
    private void resize() {
        int[] newArray = Arrays.copyOf(array, array.length * 2);
        array = newArray;
    }

    public int delete(int index) {
        if (index < 0 || index >= size) {
            System.out.println("非法删除");
            return 0;
        }
        int delete = array[index];
        for (int i = index; i <= size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return delete;
    }

    public void output(){
        for (int i=0; i<size; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Test
    public void test01(){
        TestMyArray array = new TestMyArray(8);
        array.insert(0,0);
        array.insert(1,1);
        array.insert(2,2);
        array.insert(3,3);
        array.insert(4,4);
        array.output();
        array.delete(3);
        array.output();
    }


    /**
     * 随机数组
     */
    public static int[] generateRandomArray(int maxLen,int maxValue){
        int length = (int)Math.random() * maxLen + 1;
        int[] arr = new int[length];
        for (int i=0; i<length; i++){
            arr[i] = (int)Math.random() * maxValue;
        }
        return arr;
    }


}

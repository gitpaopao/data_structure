package pp.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 非递归实现快排
 * <p>
 * 存储Map类型参数的栈，存放每一次排序的起始下标和结束下标
 * 入栈，出栈，分成两部分，两部分的起止下标参数入栈...
 */
public class QuickSortWithStack {

    public static void quickSort(int[] array) {
        //   准备一个函数栈
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", 0);
        map.put("endIndex", array.length - 1);
        stack.push(map);

        // 栈为空时结束，表示已全部排完序
        while (!stack.isEmpty()) {
            Map<String, Integer> pop = stack.pop();
            Integer startIndex = pop.get("startIndex");
            Integer endIndex = pop.get("endIndex");
            int partition = partition(array, startIndex, endIndex);
            if (startIndex < partition - 1) {
                Map<String, Integer> leftMap = new HashMap<>();
                leftMap.put("startIndex", startIndex);
                leftMap.put("endIndex", partition - 1);
                stack.push(leftMap);
            }
            if (partition + 1 < endIndex) {
                Map<String, Integer> rightMap = new HashMap<>();
                rightMap.put("startIndex", partition + 1);
                rightMap.put("endIndex", endIndex);
                stack.push(rightMap);
            }
        }
    }

    public static int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            while (left < right && array[right] > pivot) {
                right--;
            }
            while (left < right && array[left] <= pivot) {
                left++;
            }
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }
        //    两指针重合
        array[startIndex] = array[left];
        array[left] = pivot;
        return left;
    }

    public static void main(String[] args){
        int[] arr = new int[] {4,7,6,5,3,2,8,1};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

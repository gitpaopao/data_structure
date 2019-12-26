package pp.sort;

import java.util.Arrays;

/**
 * 鸡尾酒排序、双向冒泡排序
 * <p>
 * 优化前边有序，后边无序的数组，例 2 3 4 5 1
 * <p>
 * 第i趟比较交换区间是（i ~ length-1-i） 左右两头都空出 i 个
 * <p>
 * 时间复杂度，O(n^2) O(n^2)
 * 空间复杂度，O(1)
 * 稳定
 */
public class CockTailSort {

    public static void sort(int[] array) {
        int tmp = 0;
        //  每趟排序比较2次，总趟数减少一半
        for (int i = 0; i < array.length / 2; i++) {
            //   先从左到右比较,升序，最大的冒到最后一个
            boolean isSorted = true;
            for (int j = i; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

            //  从右向左比较，降序，最小到冒到第一个
            isSorted = true;
            for (int j=array.length-1-i; j>i;j--){
                if (array[j] < array[j-1]){
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,5,1};

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

package pp.sort;

import java.util.Arrays;

/**
 * Created by paopao on 2019/9/3
 *
 * 冒泡排序(和数据状况无关)
 *
 * 时间复杂度 O(N^2)
 * 稳定
 */
public class BubbleSort {

    public static void sort1(int[] array){
//        n-1 趟, 0~n-2
        for (int i=0; i<array.length-1; i++){
            //  遍历未排序的所有元素(第一趟时：j的下标是0~n-2<n-1,j+1是1~n-1，所以j<length-1-i)
            for (int j=0;j<array.length-1-i; j++){
                if (array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    public static void sort2(int[] array){
        for (int i=0; i<array.length-1; i++){
            boolean sorted = true;
            for (int j=0;j<array.length-1-i; j++){
                if (array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    sorted = false;
                }
            }
            if (sorted){
                break;
            }
        }
    }

    /*
        记录每一趟后无序数列的边界
        每一趟最后一个交换的位置就是无序数组的边界
        后面没有交换,就说明后边是有序的,不需要每趟都走到最后位置
      */
    public static void sort3(int[] array){
        int lastExchange = 0;
        int border = array.length-1;
        boolean sorted = true;
        for (int i=0; i< array.length-1; i++){
            for (int j=0; j<border; j++){
                if (array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    sorted = false;
                    lastExchange = j;
                }
            }
            if (sorted){
                break;
            }
            border = lastExchange;
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{3,4,2,1,5,6,7,8};
        sort3(array);
        System.out.println(Arrays.toString(array));
    }
}

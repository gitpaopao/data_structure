package pp.sort;

/**
 * Created by paopao on 2019/9/3
 *
 * 冒泡排序
 */
public class BucketSort {

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

    // 记录每一趟后无序数列的边界
    public static void sort3(int[] array){
        int lastExchange = 0;
        int border = array.length-1;
        for (int i=0; i< array.length-1; i++){
            boolean sorted = true;
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
}

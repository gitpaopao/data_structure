package pp.sort;

import java.util.Arrays;

/**
 * 计数排序
 * 时间复杂度 O(n+m) O(n+m)
 * 空间复杂度O(m)
 */
public class CountSort {

    /**
     * 维护一组计数器，如原始数组中有值为i,统计数组下标为i的位置+1
     * 统计数组的长度是原始数组最大值max+1，保证最后的下标是max
     */
    public static int[] countSort(int[] array){
        // 1.获取数组最大值
        int max = array[0];
        for (int i=0;i<array.length;i++){
            if (array[i] > max){
                max = array[i];
            }
        }
        // 2.统计数组，记录每个值出现了几次
        int[] countArray = new int[max+1];
        for (int i=0;i<array.length;i++){
            countArray[array[i]]++;
        }
        // 3.遍历统计数组
        int[] sortedArray = new int[array.length];
        int index = 0;
        for (int i=0; i<countArray.length;i++){
            for (int j=0;j<countArray[i];j++){
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    /**
     * 优化版(假设数字取值都较大，会浪费很多小值的空间)
     * 统计数组的长度为 max-min+1
     * 对统计数组的值进行变形，数值标识下标对应的原始数组的值排序后排在第几位
     */
    public static int[] countSort2(int[] array){
        // 1.获取数组最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i=0;i<array.length;i++){
            if (array[i] > max){
                max = array[i];
            }
            if (array[i] < min){
                min = array[i];
            }
        }

    //    2.计算统计数组
        int[] countArray = new int[max-min+1];
        for (int i=0;i<array.length;i++){
            countArray[array[i] - min]++;
        }
        /*  3. 统计数组变形，计算每个值的最终位置
               后边的元素等于前边的元素之和
         */
        for (int i=1;i<countArray.length;i++){
            countArray[i] += countArray[i-1];
        }
          /*4.倒序遍历原始数组(为保证稳定性),在统计数组中找到最终位置
               假设统计数组中array[8] = 10,表示与min+8的那个数在排序后是第10位,可能出现多次,最后一次在第10位
               倒序遍历,最右出现的，在排序后也在最右，所以能保证稳定性

               countArray[array[i]-min]表示排在第几位,下标还要-1
           */
        int[] sortedArray = new int[array.length];
        for (int i=array.length-1;i>=0;i--){
            sortedArray[countArray[array[i]-min] - 1] = array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;
    }

    public static void main(String[] args){
        int[] array = new int[] {4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));

        array = new int[] {95,94,91,98,99,90,99,93,91,92};
        sortedArray = countSort2(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}

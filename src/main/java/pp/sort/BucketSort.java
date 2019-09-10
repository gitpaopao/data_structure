package pp.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by paopao on 2019/9/3
 *
 * 桶排序
 * 桶的个数num = 数组的长度
 * 最后一个桶只存放数组的最大值，其他桶的区间范围跨度span = (max-min)/(num-1)
 *
 * 确定数a 在哪个桶中：(a-min)/span，得到的整数是桶的下标，例如等于2.3，应该在第三个桶中，桶下标为2
 *
 * 时间复杂度O(n) O(logn)
 * 空间复杂度O(n)
 */
public class BucketSort {

    public static double[] bucketSort(double[] arr){

        //1.得到数列的最大值和最小值，并算出差值d
        double min = arr[0];
        double max = arr[0];
        for (int i=0; i<arr.length; i++){
            min = Math.min(min,arr[i]);
            max = Math.max(max,arr[i]);
        }
        double d = max - min;

        //2.初始化桶，每个桶是一个list，多个桶是一个泛型为list的集合
        int bucketNum = arr.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>();
        for (int i=0; i<bucketNum; i++){
            bucketList.add(new LinkedList<Double>());
        }

        //3.遍历原始数组，将每个元素放入桶中
        for (int i=0; i<arr.length; i++){
            int bid = (int)((arr[i]-min)*(bucketNum-1)/d);
            bucketList.get(bid).add(arr[i]);
        }

        //4.对每个桶内部进行排序
        for (int i=0; i<bucketNum; i++){
            Collections.sort(bucketList.get(i));
        }

        //5.输出全部元素
        double[] newArray = new double[arr.length];
        int index = 0;
//        list 是有序的
        for (LinkedList<Double> list : bucketList){
            for (double ele : list){
                newArray[index++] = ele;
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        double[] array = new double[] {4.12,6.421,0.0023,3.0,2.123,8.122,4.12, 10.09};
        double[] sortedArray = bucketSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }

}

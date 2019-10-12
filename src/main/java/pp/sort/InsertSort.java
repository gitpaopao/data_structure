package pp.sort;

import java.util.Arrays;

/**
 * Created by pkpm on 2019/9/6
 *
 * 插入排序(和数据状况有关)
 *
 *   时间复杂度 O(N^2)
 *   依次与有序数列的数字比较、往前插入
 *   稳定
 */
public class InsertSort {



    public static void insertSort2(int[] array){
        if (array == null || array.length <2){
            return;
        }
        // from small to big
        for(int i = 1; i< array.length; i++){

            for(int j = i-1; j>=0; j--){
                if(array[j] > array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }
    }



    public static void insertSort(int[] array){
        if (array == null || array.length <2){
            return;
        }
//        第一趟，有序数列只有第一个数字
        for (int i=1; i<array.length;i++){
//            j是有序数列的最右
            for (int j=i-1; j>=0 && array[j]>array[j+1]; j--){
                swap(array,j,j+1);
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

//    for test
    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

//    生成随机样本
    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int)((maxValue+1)*Math.random()) - (int)(maxValue*Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        if (arr == null){
            return null;
        }
        int[] arr2  = new int[arr.length];
        for (int i=0;i<arr.length;i++){
            arr2[i] = arr[i];
        }
        return arr2;
    }

/**
 *  对数器的应用
 *
 *  一个测试方法a，一个绝对正确的方法b
 *  生成随机样本产生器
 *  多次生成样本，验证方法a 和 方法b 的结果
  */
    public static void main(String[] args){
       int testTime = 500000;
       int maxSize = 100;
       int maxValue = 100;
       boolean succeed = true;
       for (int i=0; i<testTime;i++){
           int[] arr1 = generateRandomArray(maxSize, maxValue);
           int[] arr2 = copyArray(arr1);
           insertSort2(arr1);
           comparator(arr2);
           if (!isEqual(arr1,arr2)){
               succeed = false;
               break;
           }
       }
       System.out.println(succeed);
    }

    public static boolean isEqual(int[] arr1,int[] arr2){
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if (arr1 == null &&arr2 == null){
            return true;
        }
        if (arr1.length != arr2.length){
            return false;
        }
        for (int i=0; i<arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }
}

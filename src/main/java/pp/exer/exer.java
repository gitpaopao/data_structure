package pp.exer;

import java.util.Arrays;

/**
 * Created by pkpm on 2019/9/11
 */
public class exer {
    /**
     * 1. 猫狗队列
     *
     * 可以用add方法将猫或狗加入队列；
     * 可以用pollAll方法，将队列中所有实例按照先后顺序输出；
     * pollDog，将dog的实例按照先后顺序输出；
     * pollCat
     * isEmpty
     * isDogEmpty
     * isCatEmpty
     *
     *
     * 方案：使用两个队列，每个实例根据入队的先后顺序加一个索引或时间戳记录一下
     */

    /**
     * 2. 转圈打印矩阵，额外空间复杂度O(1)
     *  知道左上和右下可打印一圈，打印一圈后，左上角向右下移动，右下角向左上移动
     */

    /**
     * 3. 正方形矩阵旋转90度，额外空间O(1)
     *
     * 根据左上和右下确定一圈，每圈旋转
     */

    /**
     * 4. 之字打印矩阵，额外空间O(1)
     *
     *  分解为打印对角线的值
     *  确定对角线借助两个点A，B：A像右走，到最后一个向下；B向下走，到最下一个向右
     */

    /**
     * 5. 在排好序的矩阵中查找数
     * 矩阵matrix的每一行、每一列都是排好序的，判断k是否在矩阵中
     * 时间复杂度O(N+M),空间复杂度O(1)
     *
     * 从左下或右上开始遍历
     */


    /**
     * 8. 在链表上实现荷兰国旗问题
     *   (借助容器数组，在数组上patition，然后再串起来)
     *
     * 进阶：左中右三个部分的内部也做顺序要求，与原链表中节点的先后次序一致
     *      时间复杂度O(N),空间复杂度O(1)
     *
     *      借助6个指针，遍历链表，找到第一个小于pivot的less，等于pivot的mid，大于的more
     *      再次遍历链表，小于pivot的不是less，挂在less下，同理……
     *      每个区域用两个变量记录头指针和尾指针，然后将三个区域串起来
     */

    public static void quick(int[] arr, int l, int r){
        if (l >= r){
            return;
        }
        int index = partition(arr, l, r);
        quick(arr,l,index-1);
        quick(arr,index+1,r);
    }

    public static int partition(int[] arr, int l, int r){
        int left = l;
        int right = r;
        int pivot = arr[left];
        while (left != right){
            while (left < right && arr[right] > pivot){
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr,l,left);
        return left;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
//        if (i == j){
//            return;
//        }
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args){
        int[] arr = new int[]{5,1,2,1,9};
        quick(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}

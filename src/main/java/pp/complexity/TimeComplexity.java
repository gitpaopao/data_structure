package pp.complexity;

/**
 * Created by ljx on 2019/8/6
 *
 * 1.1  时间复杂度
 */
public class TimeComplexity {

    /**
     * 递归查找一个数组中最大的值（分治）
     * @param array  数组
     * @return  int
     *
     * 递归时间复杂度估算master公式：N 父样本的规模，a 子过程发生的次数(只看一次)，N/b 子过程样本量，O(N^d) 额外的时间复杂度
     *      T(N) = aT(N/b) + O(N^d)
     *      log(b,a) > d 时，O(N^log(b,a))
     *      log(b,a) = d 时，O(N^d*logN)
     *      log(b,a) < d 时，O(N^d)
     */
    public static int getMax(int[] array){
        return process(array,0,array.length-1);
    }

//      T(N) = 2T(N/2) + O(N^0)
    public static int process(int[] arr, int l, int r){
//        先写base case，问题分解的最小单位
        if (l == r){
            return arr[l];
        }
        int middle = (l + r)/2;
        int left = process(arr,l,middle);
        int right = process(arr,middle+1,r);
//        return left>right?left:right;
        return Math.max(left,right);
    }
}

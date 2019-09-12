package pp.exer;

/**
 * Created by paopao on 2019/9/3
 *
 * 无序数组排序后的最大相邻差
 *  (给定一个数组，求如果排序后，相邻的两数的最大差值，时间复杂度O(N)，不能借助基于比较的排序)
 *
 *  桶思想的应用
 */
public class MaxSortedDistance {

    public static void main(String[] args) {
        int[] array = new int[] {2,6,3,4,5,10,9};
        System.out.println(maxGap(array));
    }


    /**
     * 解法1：借助计数排序的思想
     *      将数放在统计数组arr后,统计出arr中最大连续出现0的次数+1,即最大相邻差值
     *      数值相差悬殊的时候,效率低
     */
//    ------------------------------------------------------------

    /**
     * 解法2：借助桶的思想
     *
     * 准备n+1个桶，最小值放第一个桶，最大值放最后一个桶，空桶至少有一个
     * 某空桶左边找一个最近的非空桶a，右侧最近的非空桶b，a的最大值和b的最小值在排序后一定是相邻的
     * 设置空桶是排除桶内差值的可能性(空桶两侧的差值>桶的范围，又一定存在空桶，所以最大差值不会出现在同一桶内)
     * 最大差值可能来自空桶两侧，也可能来自两个相邻的非空桶
     */
    public static int maxGap(int[] nums){
        if (nums == null || nums.length < 2){
            return 0;
        }
        int len = nums.length;
//        取出数组的最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i=0; i<len;i++){
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        if (max == min){
            return 0;
        }
        boolean[] hasNum = new boolean[len+1];
        int[] maxs = new int[len+1];
        int[] mins = new int[len+1];
        int bid = 0;

//        初始化桶
        for (int i=0; i<len;i++){
//            得到桶号，并更新桶的最大值、最小值
            bid = bucket(nums[i],len,min,max);
            mins[bid] = hasNum[bid]?Math.min(mins[bid],nums[i]):nums[i];
            maxs[bid] = hasNum[bid]?Math.max(maxs[bid],nums[i]):nums[i];
            hasNum[bid] = true;
        }
//        最大相邻差
        int res = 0;
//        前一个桶的最大值
        int lastMax = maxs[0];
        int i = 1;
//        遍历计算每个非空桶的最小值 与 它前边相邻的非空桶的最大值的差值
        for (; i<=len;i++){
            if (hasNum[i]){
                res = Math.max(res,mins[i]-lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(long num,long len,long min,long max){
        return (int)((num-min)*len/(max-min));
    }
}

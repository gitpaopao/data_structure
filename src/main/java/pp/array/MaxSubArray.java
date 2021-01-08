package pp.array;

/**
 * Created by pkpm on 2020/12/17
 * 和最大的数组子序列
 */
public class MaxSubArray {

    public static int  maxSubArray(int[] nums) {

        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int sum = Integer.MIN_VALUE;
        int curSum = 0;

        for (int num : nums) {
            if (curSum <= 0) {
                curSum = num;
            } else {
                curSum += num;
            }

            if (curSum > sum) {
                sum = curSum;
            }
        }
        return sum;
    }


    public static int dp(int[] nums) {

        int sum = 0;
        // int[] dp = new int[nums.length];
        // dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i-1],0);
            sum = Math.max(nums[i], sum);
        }
        return sum;
    }

    public static void main(String[] args){
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
        System.out.println(dp(arr));
    }
}

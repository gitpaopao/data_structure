package pp.dynamic_planning;

/**
 * Created by pkpm on 2019/11/14
 * 给你一个数组arr,一个整数aim。如果可以任意选择arr中的数字，能不能累加得到aim,返回true或者false.
 * 遍历每个值时，都有两种可能，取或不取
 */
public class MoneyProblem {
    private MoneyProblem() {
    }
    // 从第一个数字开始遍历，sum初始为0

    static boolean money1(int[] arr, int aim) {
        return process(arr, 0, 0, aim);
    }

    /**
     * 递归
     * 正推, 计算能否到达base case的位置
     * @param i   第i个位置
     * @param sum 到第i个位置时的累加和,i-1后的累加和
     */
    private static boolean process(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }
        if (i == arr.length) {
            return false;
        }
        // 遍历第i位置后的两种可能,取/不取第i个位置的值arr[i]
        return process(arr, i + 1, sum, aim) || process(arr, i + 1, sum + arr[i], aim);
    }

    /**
     * 动态递归
     * 可变参数是i和sum, 二维表, 0~arr.length  0~aim
     * 从后向前计算,(i,j)位置是否为true, 取决于sum 加/不加arr[i] == aim, 列j表示当前的sum和
     * @return (0,0)位置,已知为true的位置, 倒推(0,0)位置能否到达base case位置
     */
     static boolean money2(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        // aim列是true
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        // 倒推填满二维表
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                // 当 j+arr[i]大于aim时，肯定是不加arr[i]的
                dp[i][j] = dp[i+1][j];
                if (j+arr[i] <= aim){
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }
}

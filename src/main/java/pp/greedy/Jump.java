package pp.greedy;

import pp.array.Logarithm;

/**
 * Created by pkpm on 2019/11/8
 * 给定一个非负整数数组，最初位于第一个位置，
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度，
 * 求：使用最少的跳跃次数到达数组的最后一个位置。
 */
public class Jump {
    /**
     * 从最右向左查找能到达它，且离它最远的点
     */
    public static int jump1(int[] arr) {
        int right = arr.length - 1;
        int sum = 0;
        while (right > 0) {
            int cur = right - 1;
            for (int i = right - 2; i >= 0; i--) {
                if (i + arr[i] >= right) {
                    cur = i;
                }
            }
            right = cur;
            sum++;
        }
        return sum;
    }

    /**
     * 脑补贪心策略：我们要跳跃的那个点，可以使得上一次 + 下一次的跳跃总距离最远。
     */
    public static int jump2(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int right = arr.length - 1;
        int sum = 0;
        //  这一步能跳到的最远
        int end = 0;
        //  下一步能跳到的最远
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, i + arr[i]);
            if (i == end) {
                end = max;
                sum++;
            }
            if (end == right) {
                break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        boolean error = false;
        int testTimes = 5;
        for (int i = 0; i != testTimes; i++) {
            int len = 10;
            int maxValue = 100;
            int[] arr = Logarithm.getRandomArray(len, maxValue);
            int res1 = jump1(arr);
            int res2 = jump2(arr);
            if (res1 != res2) {
                error = true;
            }
            System.out.println(i + "" + error);
            if (error){
                Logarithm.printArray(arr);
            }
        }
    }


}

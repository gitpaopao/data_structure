package pp.array;

/**
 * Created by pkpm on 2019/11/8
 * 对数器
 */
public class Logarithm {
    /**
     * 生成随机数组
     */
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    /**
     * 打印数组
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

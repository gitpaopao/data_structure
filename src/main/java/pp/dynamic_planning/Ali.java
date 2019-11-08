package pp.dynamic_planning;

/**
 * Created by pkpm on 2019/11/8
 * 四个整数参数：n p k m
 * n： 一共有1~n 个位置
 * p：最终要停在 p 的位置
 * k：一共可以走 k 步
 * m：开始的位置
 * <p>
 * 求从m开始，走k步，落在p的位置，一共有多少种走法
 */
public class Ali {
    public static int getWays(int n, int p, int m, int k) {
        // 排除无效的值
        if (n < 2 || p < 0 || p > n || k < 0 || m < 0 || m > n) {
            return 0;
        }
        return process(n, p, m, k);
    }

    /**
     * 暴力递归
     *
     * @param n           1~n 个位置  (固定)
     * @param p           最终要停在 p 的位置  (固定)
     * @param curPosition 当前所在位置  (可变)
     * @param restStep    剩余步数  (可变)
     * @return 从curPosition开始，走restStep步，到达p位置，一共有多少种走法
     */
    public static int process(int n, int p, int curPosition, int restStep) {
        // base case,只剩0步
        if (restStep == 0) {
            return curPosition == p ? 1 : 0;
        }
        // 如果在1位置,下一步一定是走到2
        if (curPosition == 1) {
            return process(n, p, 2, restStep - 1);
        } else if (curPosition == n) {
            // 如果在n位置,下一步一定是走到n-1
            return process(n, p, n - 1, restStep - 1);
        } else {
            // 如果在中间位置,下一步有两种情况,所以是两边的加和
            return process(n, p, curPosition - 1, restStep - 1)
                    + process(n, p, curPosition + 1, restStep - 1);
        }
    }

    public static void main(String[] args){

    }
}

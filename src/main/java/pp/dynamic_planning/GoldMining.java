package pp.dynamic_planning;

/**
 * 有一个国家发现了5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人数也不同。
 * 参与挖矿工人的总数是10人。每座金矿要么全挖，要么不挖，不能派出一半人挖取一半金矿。
 * 要想得到尽可能多的黄金，应该选择挖取哪几座金矿？
 */
public class GoldMining {
    /**
     * 递归：存在许多重复的运算
     * <p>
     * 获得金矿最优收益
     *
     * @param w 工人数量
     * @param n 可选金矿数量
     * @param p 金矿开采所需工人数量
     * @param g 金矿储量
     */
    public static int getBestGoldMining(int w, int n, int[] p, int[] g) {
        // base case 问题边界F(n,w)=0(n=0或w=0)
        if (w == 0 || n == 0) {
            return 0;
        }
        // w<p[n-1]时，这个矿挖不了,只有一种最优子结构 F(n,w)=F(n-1,w)
        if (w < p[n - 1]) {
            return getBestGoldMining(w, n - 1, p, g);
        }
        // 常规情况下,存在两种最优子结构,每个矿有两种选择：不挖和挖
        return Math.max(getBestGoldMining(w, n - 1, p, g), getBestGoldMining(w - p[n - 1], n - 1, p, g) + g[n - 1]);
    }

    /**
     * 动态规划
     * 双循环填充二维数组
     * 时间复杂度、空间复杂度：O(nw)
     * <p>
     * 获得金矿最优收益
     *
     * @param w 工人数量
     * @param n 可选金矿数量
     * @param p 金矿开采所需工人数量
     * @param g 金矿储量
     */
    public static int getBestGoldMining2(int w, int n, int[] p, int[] g) {
        // n+1行，w+1列，每一个需要用到n-1行的数据，所以n作为行数，一行一行计算
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        return dp[n][w];
    }

    /**
     * 空间优化：只保留一行的数据,计算下一行时,推导所需要的数据在上一行它的左侧,从右向左依次把旧的数据替换
     * 空间复杂度：O(n)
     * <p>
     * 获得金矿最优收益
     *
     * @param w 工人数量
     * @param n 可选金矿数量
     * @param p 金矿开采所需工人数量
     * @param g 金矿储量
     */
    public static int getBestGoldMining3(int w, int n, int[] p, int[] g) {
        int[] dp = new int[w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = w; j >= 1; j--) {
                // 当前人数小于需要的人数时，该矿不挖，还是等于它自身的值
                if (j >= p[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        return dp[w];
    }

    public static void main(String[] args) {
        int w = 10;
        int n = 5;
        int[] p = {5, 5, 3, 4 ,3};
        int[] g = {400, 500, 200, 300 ,350};
        System.out.println("递归：" + getBestGoldMining(w, 5, p, g));
        System.out.println("动态规划1：" + getBestGoldMining2(w, 5, p, g));
        System.out.println("动态规划2：" + getBestGoldMining3(w, 5, p, g));

    }
}

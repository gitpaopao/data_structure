package pp.dynamic_planning;

/**
 * Created by pkpm on 2019/11/11
 * 给你一个二维数组，二维数组中的每个数都是正数，要求从左上角走到右下角，每一步只能向右或向下。
 * 沿途经过的数字累加起来，求返回的最小的路径和。
 */
public class MinPath {

    public static int minPath1(int[][] matrix) {
        return process(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    /**
     * 只能往下走或往右走，所以到ij位置时的路径和，与它上一个位置和左一个位置的路径和有关
     *
     * @return 到达int[i][j]位置时的路径和
     */
    public static int process(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        //  base case,不能再递归的情况，int[0][0]位置，没有上一个和左一个位置
        if (i == 0 && j == 0) {
            return res;
        }
        //  第一行，没有上一个位置，都依赖左一个位置的路径和 + 自身的值(一定是从它左一个位置走来)
        if (i == 0 && j != 0) {
            return res + process(matrix, i, j - 1);
        }
        //  第一列，依赖上一个位置
        if (i != 0 && j == 0) {
            return res + process(matrix, i - 1, j);
        }
        //  中间位置，可能从上边走来，或左边走来，取决于哪个路径和更小
        return res + Math.min(process(matrix, i, j - 1), process(matrix, i - 1, j));
    }

    /**
     * 动态规划
     * 两个变化的值 i j，用二维数组搞，正序计算，记录子过程的结果
     * int[0][0]位置的值知道,将整个数组计算完毕，最终取int[i][j]位置的结果
     */
    public static int minPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix[0] == null) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }

}

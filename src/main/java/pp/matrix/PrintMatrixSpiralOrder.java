package pp.matrix;

/**
 * 顺时针打印矩阵
 * Created by pkpm on 2020/12/14
 */
public class PrintMatrixSpiralOrder {

    /**
     * 一圈圈打印：从左上-》右上，右上-》右下，右下-左下，左下-》左上
     * 打印完一圈，左上向右下移动一个格子，右下向右上移动一个格子，缩小一圈
     */
    public static void spiralOrderPrint(int[][] matrix) {

        int tr = 0, tc = 0;
        int dr = matrix.length - 1;
        int dc = matrix[0].length - 1;
        while (tr <= dr && tc <= dc) {
            printEdage(matrix, tr++, tc++, dr--, dc--);
        }
    }

    private static void printEdage(int[][] matrix, int tr, int tc, int dr, int dc) {
        //    只有一行
        if (tr == dr) {
            for (int i = tc; i <= dc; i++) {
                System.out.print(matrix[tr][i] + " ");
            }
        } else if (tc == dc) {
            for (int i = tr; i <= dr; i++) {
                System.out.print(matrix[i][tc] + " ");
            }
        } else {
            int curC = tc;
            int curR = tr;
            while (curC != dc) {
                System.out.print(matrix[curR][curC] + " ");
                curC++;
            }
            while (curR != dr) {
                System.out.print(matrix[curR][curC] + " ");
                curR++;
            }
            while (curC != tc) {
                System.out.print(matrix[curR][curC] + " ");
                curC--;
            }
            while (curR != tr) {
                System.out.print(matrix[curR][curC] + " ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        spiralOrderPrint(matrix);
    }
}

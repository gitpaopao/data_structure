package pp.hash;

/**
 * Created by pkpm on 2019/10/22
 * 岛问题
 *  一个矩阵中只有0和1，每个位置可以和自己的上、下、左、右相连，如果有一片1连在一起，就叫一个岛。
 *  求一个矩阵中有几个岛
 *  例：0 0 1 0 1 0
 *     1 1 1 0 1 0
 *     1 0 0 1 0 0
 *     0 0 0 0 0 0    矩阵中有三个岛
 *
 *  遍历矩阵：遇到1，即岛+1，然后1的位置变为2，递归访问它的上下左右，1的位置都变为2；接着原位置继续遍历矩阵
 */
public class Islands {

    private static int countLand(int[][] array){
        if (array == null || array[0] == null){
            return 0;
        }
        int num = 0;
        int m = array.length;
        int n = array[0].length;
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (array[i][j] == 1){
                    num++;
                    infect(array,i,j,m,n);
                }
            }
        }
        return num;
    }

    /**
     * 感染函数：把可以连在一片的1都置为2
     */
    private static void infect(int[][] array, int i, int j, int m, int n){
        if (i<0 || i>=m || j<0 || j>=n || array[i][j] != 1){
            return;
        }
        array[i][j] = 2;
        infect(array,i-1,j,m,n);
        infect(array,i+1,j,m,n);
        infect(array,i,j-1,m,n);
        infect(array,i,j+1,m,n);
    }


    public static void main(String[] args){
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                        { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countLand(m1));
        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                        { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                        { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countLand(m2));
    }

    /**
     * 利用并查集优化
     *  将一个大矩阵化成多个小矩阵，然后不同的CPU处理不同的矩阵，最后将不同的矩阵合并。
     *
     *  边界问题，将边界上连成一片的1合并：
     *      在解决边界时，若两个1碰到一起了(属于一个岛)，就检查两个1的代表结点(感染它的节点)是不是一样的，
     *      不一样的话，就合并两个集合，同时岛的个数-1；
     *      一样的话，证明之前已经合并过了，岛的个数不变。
     */
}

package pp.dynamic_planning;

/**
 * Created by pkpm on 2019/11/8
 * n!
 */
public class Factorial {
    /**
     *  暴力递归:
     *     1)把问题转化为规模缩小了的同类问题的子问题
     *     2)有明确的不需要继续进行递归的条件(base case)
     *     3)有当得到了子问题的结果之后的决策过程
     *     4)不记录每一个子问题的解
     *
     *  动态规划:
     *     1)从暴力递归中来
     *     2)将每一个子问题的解记录下来，避免重复计算
     *     3)把暴力递归的过程，抽象成了状态表达
     *     4)并且存在化简状态表达，使其更加简洁的可能
     *
     *  暴力递归转动态规划：
     *     1)寻找可变参数可以代表一个递归的状态，即哪些参数一旦确定，返回值就确定了;
     *     2)把可变参数的所有组合映射为一张表：1个可变参数就是一维表；2个可变参数就是二维表;
     *     3)最终答案要的是表中的哪个位置;
     *     4)根据递归过程的 base case,把表的最简单、不需要依赖其他位置的那些位置填好;
     *     5)根据递归的非 base case 部分，也就是表中普通位置需要怎么计算得到;
     */

//    暴力递归
    public static long getFactorial1(int n){
        if (n == 1){
            return 1;
        }
        return n * getFactorial1(n-1);
    }

//    动态规划
    public static long getFactorial2(int n){
        int res = 1;
        for (int i=0; i<n; i++){
            res *= n;
        }
        return res;
    }
}
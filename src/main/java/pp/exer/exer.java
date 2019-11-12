package pp.exer;

/**
 * Created by pkpm on 2019/9/11
 */
public class exer {
    /**
     * 1. 猫狗队列
     *
     * 可以用add方法将猫或狗加入队列；
     * 可以用pollAll方法，将队列中所有实例按照先后顺序输出；
     * pollDog，将dog的实例按照先后顺序输出；
     * pollCat
     * isEmpty
     * isDogEmpty
     * isCatEmpty
     *
     *
     * 方案：使用两个队列，每个实例根据入队的先后顺序加一个索引或时间戳记录一下
     */
    /**
     * 2. 转圈打印矩阵，额外空间复杂度O(1)
     *  知道左上和右下可打印一圈，打印一圈后，左上角向右下移动，右下角向左上移动
     */
    /**
     * 3. 正方形矩阵旋转90度，额外空间O(1)
     *
     * 根据左上和右下确定一圈，每圈旋转
     */
    /**
     * 4. 之字打印矩阵，额外空间O(1)
     *
     *  分解为打印对角线的值
     *  确定对角线借助两个点A，B：A像右走，到最后一个向下；B向下走，到最下一个向右
     */
    /**
     * 5. 在排好序的矩阵中查找数
     * 矩阵matrix的每一行、每一列都是排好序的，判断k是否在矩阵中
     * 时间复杂度O(N+M),空间复杂度O(1)
     *
     * 从左下或右上开始遍历
     */
    /**
     * 哈希函数：
     *      ① 输入域是无穷大的
     *      ② 输出域是有限的
     *      ③ 相同的输入，输出是一样的
     *      ④ 不同的输入，可能会有相同的输出(碰撞)
     *      ⑤ 散列性
     */
    /**
     * 6. 布隆过滤器(存在失误率)
     *  假设有一个100亿个URL的黑名单，来一个URL，判断是否在黑名单中
     *      准备一个长度为m的bit数组，k 个哈希函数，100亿个URL经过k个哈希函数，然后%m，得到k个值，然后将bit数组中的这k个位置置1.
     *      判断的过程同上，判断URL对应的 k个位置是否都是1。
     *
     *      int[] arr = new it[10];
     *      bit数组中第i个位置置1：arr[i/32] | (1 << (i%32))   i/32:来自第几个整数    i%32:是该整数的第几位
     *      取出第i个位置的值：arr[i/32] & (1 << (i%32)),如果得到0，则是0,；得到1，则是1.
     *  m 的设置取决于不同样本的数量和失误率p多少
     *  m = -(n*lnp) / (ln2)^2
     *  k = 0.7 * m/n
     *  p = (1-e^-n*k/m)^k
     */
    /**
     * 7.一致性哈希
     *  虚拟节点技术：
     */
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
    /**
     * 10. 给你一个数组arr,一个整数aim。如果可以任意选择arr中的数字，能不能累加得到aim,返回true或者false.
     */
    /**
     * 11. 给定两个数组w 和 v,两个数组长度相等，w[i]表示第i件商品的重量，v[i]表示第i件商品的价值。
     * 再给定一个整数bag，要求你挑选商品的重量加起来一定不能超过bag，返回满足这个条件下，你能获得的最大价值。
     */
    /**
     * 12.给你一个栈，逆序这个栈，不申请额外的数据结构，只能使用递归函数。
     */
    /**
     * 13.打印一个字符串的全部子序列，包括空字符串。
     */
    /**
     * 14.打印一个字符串的全部排列
     * 进阶：要求不要出现重复的排列
     */
    /**
     * 15.汉诺塔问题
     *  打印n 层汉诺塔从最左边移动最右边的全部过程
     */
}

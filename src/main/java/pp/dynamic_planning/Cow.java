package pp.dynamic_planning;

/**
 * Created by pkpm on 2019/11/11
 * 母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只母牛，假设不会死。
 * 求N年后，母牛的数量
 * 进阶：(如果每只母牛只能活10年，求N年后，母牛的数量)
 */
public class Cow {

    /**
     * 递归
     * 前一年每年的数量 + 3年前母牛的数量 * 1
     */
    public static int cowNumber1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber1(n - 1) + cowNumber1(n - 3);
    }

    /**
     * 动态规划
     * 正序计算，暂存子问题的结果 prepre pre res
     */
    public static int cowNumber2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        // 前一年的
        int res = 3;
        int pre = 2;
        // 3年前的
        int prepre = 1;
        int tmp;
        for (int i = 4; i <= n; i++) {
            tmp = res;
            res += prepre;
            prepre = pre;
            pre = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(cowNumber1(n));
        System.out.println(cowNumber2(n));
    }
}

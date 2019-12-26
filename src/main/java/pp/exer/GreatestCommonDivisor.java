package pp.exer;

/**
 *
 */
public class GreatestCommonDivisor {
    /**
     * 暴力解法
     * 时间复杂度：O(min(a,b))
     */
    public static int greatestCommonDivisor(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        if (big % small == 0) {
            return small;
        }
        for (int i = small / 2; i > 1; i--) {
            if (big % i == 0 && small % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法，欧几里得算法
     * a,b的最大公约数=  a,b的余数与b的最大公约数
     * 时间复杂度近似为：O(log(max(a,b)))
     * 取模运算性能差
     */
    public static int greatestCommonDivisor2(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        if (big % small == 0) {
            return small;
        }
        return greatestCommonDivisor2(big % small, small);
    }

    /**
     * 更相减损法
     * a,b的最大公约数=  a,b的差值与b的最大公约数
     * 最坏时间复杂度：O(max(a,b))
     */
    public static int greatestCommonDivisor3(int a, int b) {
        if (a == b) {
            return a;
        }
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        return greatestCommonDivisor2(big - small, small);
    }

    /**
     * 更相减损与移位结合
     * 时间复杂度：O(log(max(a,b)))
     */
    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        // a,b都是偶数,gcd(a,b)=2*gcd(a/2,b/2)
        if ((a & 1) == 0 && (b & 1) == 0) {
            return gcd(a >> 1, b >> 1) << 1;
        }
        // a是偶数,b是奇数,gcd(a,b)=gcd(a/2,b)
        else if ((a & 1) == 0 && (b & 1) != 0) {
            return gcd(a >> 1, b);
        }
        // a是奇数,b是偶数,gcd(a,b)=gcd(a,b/2)
        else if ((a & 1) != 0 && (b & 1) == 0) {
            return gcd(a, b >> 1);
        }
        // a,b都是奇数,进行一次相减操作,差值就是偶数了
        else {
            int big = a > b ? a : b;
            int small = a < b ? a : b;
            return gcd(big - small, small);
        }
    }

    public static void main(String[] args) {
        System.out.println(greatestCommonDivisor2(100, 80));
        System.out.println(greatestCommonDivisor3(100, 80));
        System.out.println(gcd(100, 80));
    }
}

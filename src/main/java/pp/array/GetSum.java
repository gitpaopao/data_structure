package pp.array;

/**
 * Created by pkpm on 2020/12/23
 * 1+2+……+n
 */
public class GetSum {

    public static int getSum(int n) {
       boolean flag = n>0 && (n+=getSum(n-1)) > 0;
        return n;
        // if (n == 1) {
        //     return 1;
        // }
        // return getSum(n - 1) + n;
    }

    public static void main(String[] args) {
        System.out.println(getSum(4));
    }
}

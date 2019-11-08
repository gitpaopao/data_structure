package pp.dynamic_planning;

/**
 * Created by pkpm on 2019/11/8
 * n!
 */
public class Factorial {

//    暴力递归
    public static long getFactorial1(int n){
        if (n == 1){
            return 1;
        }
        return n * getFactorial1(n-1);
    }

    public static long getFactorial2(int n){
        int res = 1;
        for (int i=0; i<n; i++){
            res *= n;
        }
        return res;
    }
}

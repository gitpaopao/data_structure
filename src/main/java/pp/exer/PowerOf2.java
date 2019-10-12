package pp.exer;

/**
 * Created by paopao on 2019/10/09
 * 判断一个数是否是2的整数次幂
 * 时间复杂度：O(logn)
 */
public class PowerOf2 {

   public static boolean m1(int number){
        int tmp = 1;
        while (tmp <= number){
            if (tmp == number){
                return true;
            }
            tmp<<=1;
        }
        return false;
   }

    /**
     * 2的整数次幂,转为二进制后,只有最高位是1,其余为0
     * 2的整数次幂-1,转化为二进制,都是1
     * 所以,如果n是2的整数次幂，则有 n&(n-1) = 0
     */
   public static boolean m2(int number){
       if ((number & (number-1)) == 0){
           return true;
       }
       return false;
   }

    public static void main(String[] args) {
        System.out.println(m1(32));
        System.out.println(m2(18));
    }
}

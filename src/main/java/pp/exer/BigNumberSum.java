package pp.exer;

/**
 * Created by paopao on 2019/9/3
 * 大整数求和
 *   将大整数拆分成数组来存储、计算
 */
public class BigNumberSum {

    /**
     * 把两个大整数用数组逆序存储，数组长度等于较大整数位数+1（长度+1是预留给进位用的,逆序存储是方便从左向右访问数组）
     */
    public static String bigNumberSum(String bigNumberA, String bigNumberB) {
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        int[] arrA = new int[maxLength+1];
        for (int i=0; i<bigNumberA.length(); i++){
            // 字符-'0'得到相应的整数
            arrA[i] = bigNumberA.charAt(bigNumberA.length()-1-i)-'0';
        }
        int[] arrB = new int[maxLength+1];
        for (int i=0; i<bigNumberB.length(); i++){
            arrB[i] = bigNumberB.charAt(bigNumberB.length()-1-i)-'0';
        }
        int[] result = new int[maxLength+1];
        for (int i=0; i<result.length; i++){
            int tmp = result[i];
            tmp += arrA[i];
            tmp += arrB[i];
            if (tmp >= 10){
                tmp = tmp - 10;
                result[i+1] = 1;
            }
            result[i] = tmp;
        }

        StringBuilder builder = new StringBuilder();
        //  是否找到大整数的最高有效位
        boolean first = false;
        for (int i=result.length-1; i>=0; i--){
            if (!first){
               if (result[i] == 0){
                   continue;
               }else {
                   first = true;
               }
            }
            builder.append(result[i]);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(bigNumberSum("426709752318", "95481253129"));
    }
}

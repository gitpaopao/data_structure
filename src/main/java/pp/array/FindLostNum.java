package pp.array;

/**
 * Created by paopao on 2019/9/29
 * 一个数组中,有2个数出现了奇数次,其余数出现偶数次,找出出现奇数次的两个数 A和 B
 *
 * 分治法：
 *
 * 依次异或运算,最终结果不为0,且等于 A和 B的异或结果,至少有一个二进制位为1,
 * 则若A在这个二进制位为0, B这个二进制位一定就是1,
 * 所以根据这位二进制位的不同,分成两个数组（即A和B被分到不同数组）
 */
public class FindLostNum {
    public static int[] findLostNum(int[] arr){
        //用于存储两个出现奇数次的整数
        int[] result = new int[2];
        //第一次整体异或
        int xorResult = 0;
        for (int i=0; i<arr.length; i++){
         xorResult^=arr[i];
        }
        //如果异或结果为0，说明输入数组不符合题目
        if (xorResult == 0){
            return null;
        }
        //确定两个整数的不同位，以此来做分组,从低位向高位找第一个等于1的位置
        int seperator = 1;
        while ((seperator & xorResult) == 0){
            seperator<<=1;
        }
        //第二次分组异或
        for (int i=0; i<arr.length; i++){
            // 分组：假设找到separator是第k为1,若array[i]&separator=0,则array[i]的第k为是0,则划为第一组
            if ((arr[i] & seperator) == 0){
                result[0]^=arr[i];
            }else {
                result[1]^=arr[i];
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] array = {4,1,2,2,5,1,4,3};
        int[] result = findLostNum(array);
        System.out.println(result[0] + "," + result[1]);
    }
}

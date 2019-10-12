package pp.exer;

import java.util.Arrays;

/**
 *  字典序算法：
 * 寻找全排列的下一个数
 * 输入：正整数a                                                 12345-12354    12354-12435   12435-12453
 * 输出：整数a 所包含数字的全部组合中，大于且仅大于a 的新整数
 *
 * 时间复杂度：O(n)
 */
public class FindNearestNumber {
    public static int[] findNearestNumber(int[] numbers){
        int index = 0;
        for (int i=numbers.length-1; i>0; i--){
            if (numbers[i] > numbers[i-1]){
                index = i;
                break;
            }
        }
        if (index == 0){
            return null;
        }
        int head = numbers[index-1];
        for (int i=numbers.length-1; i>0; i--){
            if (numbers[i] > head){
                numbers[index-1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        for (int i=index,j=numbers.length-1; i<j; i++,j--){
            int tmp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = tmp;
        }
        return numbers;
    }

    public static void main(String[] args){
        int[] num = {1,2,3,5,4};
        System.out.println(Arrays.toString(findNearestNumber(num)));
    }

}

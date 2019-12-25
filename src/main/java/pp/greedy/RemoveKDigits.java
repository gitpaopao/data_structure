package pp.greedy;

/**
 * Created by paopao on 2019/12/7
 * 给定一个整数，从该整数中去掉k个数字后，要求剩下掉数字形成的新整数尽可能小。
 * <p>
 * 从左向右比较，如果一位数字大于它后面的数字，删除该数字后，由比它小的数字代替它。
 * 如果没有找到，说明是升序的，删除最后一位。
 * 贪心：依次求得局部最优解，最终得到全局最优解
 */
public class RemoveKDigits {

    /*
     * 时间复杂度O(kn)
     * 每次都从头遍历，重复遍历
     */
    public static String removeKDigists1(String num, int k) {
        String newNum = num;
        for (int i = 0; i < k; i++) {
            boolean hasCut = false;
            for (int j = 0; j < newNum.length(); j++) {
                if (newNum.charAt(j) > newNum.charAt(j + 1)) {
                    newNum = newNum.substring(0, j) + newNum.substring(j + 1, newNum.length());
                    hasCut = true;
                    break;
                }
            }
            if (!hasCut) {
                newNum = newNum.substring(0, newNum.length() - 1);
            }
        }
        //        去掉左侧的0
        newNum = removeZero(newNum);
        if (newNum.length() == 0) {
            return "0";
        }
        return newNum;
    }

    public static String removeZero(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(0) != '0') {
                break;
            }
//            todo  string只有一个字符时，会越界吗
            num = num.substring(1, num.length());
        }
        return num;
    }

    /*
        遍历数字作为外层循环，k作为内层循环，实际只遍历一次
        避免每次删除都调用一次subString()，用一个数组记录字符串
     */
    public static String removeKDigists2(String num, int k) {
        char[] stack = new char[num.length()];
        //      栈顶
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            //  避免每次从头开始遍历，从上一次删除的位置继续比较
            while (k > 0 && top > 0 && stack[top - 1] > num.charAt(i)) {
                top--;
                k--;
            }
            stack[top++] = num.charAt(i);
        }
        int newLength = num.length() - k;
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }
}

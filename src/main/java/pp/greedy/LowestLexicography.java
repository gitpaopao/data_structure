package pp.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by pkpm on 2019/11/6
 * 给定一个字符串类型的数组strs，找到一种拼接方式，使得把所有字符串
 *  拼起来之后形成的字符串具有最低的字典序。
 *
 *  每个字符相当于k进制的数，当字符串长度不同时，后面补0(字典序最小)
 */
public class LowestLexicography {

    /**
     *  规则：单纯按字典序排的话，会有问题，ba 与 b 拼接，b < ba，但bab < bba
     *      如果 ab < ba，则a在前，按ab进行拼接
     */
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    /**
     *  将字符串按某种规则排序，然后进行拼接
     */
    public static String lowestString(String[] strs){
        if (strs == null || strs.length == 0){
            return "";
        }
        String res = "";
        Arrays.sort(strs,new MyComparator());
        for (int i=0; i< strs.length; i++){
            res += strs[i];
        }
        return res;
    }

    /**
     * 证明：
     *  1. 证明这种排序是正确的，即证明这种比较具有传递性。
     *     若 a.b < b.a, b.c < c.b , 则有a.c < c.a
     *          假设a,b,c 都是k 进制的数，a.b 相当于a 向左移动b 的长度位，然后加上b('12'＋'34'  = 12 * 10^2 + 34)
     *          用函数表示为，a*m(b)+b < b*m(a)+a ①, b*m(c)+c < c*m(b)+b ②, 证 a*m(c)+c < c*m(a)+a ③
     *          ①-b*c  ②-b*a，即可得证③
     *
     *  2. 证明这种贪心策略是对的
     *      交换任意两个字符串a,b ， 得到的字典序比原来大
     */
}

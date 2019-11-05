package pp.greedy;

import java.util.PriorityQueue;

/**
 * Created by pkpm on 2019/11/5
 * 切金条，一块金条切成两半，花费的铜板和长度数值一样。
 * 输入一个数组，返回切割的最小代价。
 * 例：长度60的金条, {10,20,30}
 * ① 先切成 10 和 50，花费60，然后切成20 和 30，花费 50；
 * ② 先切成30 和 30，花费60，然后切成10 和 20，花费30.
 */
public class LessMoney {

    /**
     * 实质是哈夫曼编码(贪心算法)
     *  借助优先队列，每次取两个最小的，然后将加和放进队列，再取两个最小的
     */
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int i=0; i<arr.length; i++){
            queue.add(arr[i]);
        }
        int cur;
        int sum = 0;
        while (queue.size() > 1){
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

    public static void main(String[] args){
        int[] arr = new int[]{10,20,30};
        int money = lessMoney(arr);
        System.out.println(money);
    }
}

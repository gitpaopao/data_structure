package pp.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by pkpm on 2019/11/7
 * 输入：参数1，正数数组costs  参数2，正数数组profits  参数3，正数k   参数4，正数m
 * costs[i]表示i号项目的花费  profits[i]表示i号项目在扣除花费之后还能挣到的利润
 * k表示你不能并行、只能串行的最多做k个项目    m表示你初始的资金
 * 说明:你做完一个项目，马上获得收益，可以支持你去做下一个项目。
 * 输出：你最后获得的最大钱数
 */
public class IPO {
    private static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    private static class MaxComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    private static class MinComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    /**
     * 借助两个堆，小根堆根据花费排，大根堆根据利润排
     * 每次取出小于资金的项目，放进大根堆，选择堆顶的项目做
     *
     * @param k  只能做k个项目
     * @param w  初始资金
     * @param profits  利润数组
     * @param costs    花费数组
     * @return
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] costs) {
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            Node node = new Node(profits[i], costs[i]);
            nodes[i] = node;
        }
        PriorityQueue<Node> minCostQueue = new PriorityQueue<>(new MinComparator());
        PriorityQueue<Node> maxProQueue = new PriorityQueue<>(new MaxComparator());
        for (int i = 0; i < nodes.length; i++) {
            minCostQueue.add(nodes[i]);
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().c <= w) {
                maxProQueue.add(minCostQueue.poll());
            }
//            大根堆为空时，说明没有当前资金满足的项目，直接返回
            if (maxProQueue.isEmpty()){
                return w;
            }
            w += maxProQueue.poll().p;
        }
        return w;
    }

    public static void main(String[] args){
        int[] profits = new int[]{2,3,4,5};
        int[] costs = new int[]{4,10,8,5};
        int capital = findMaximizedCapital(2, 6, profits, costs);
        System.out.println(capital);
    }
}

package pp.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by pkpm on 2019/11/7
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。给你每一个项目开始的时间和结束的时间，
 *   来安排宣讲的日程，要求会议室进行的宣讲的场次最多，返回最多的宣讲次数。
 */
public class BestArrange {
    private static class Program {
        public int start;
        public int end;
        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    private static class MyComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * 脑补一种贪心策略：按结束时间排序，优先安排结束最早的宣讲会。
     * @param start 上一场宣讲会的结束时间，即可以安排下一场的时间
     */
    public static int bestArrange(Program[] programs, int start) {
        int res = 0;
        Arrays.sort(programs,new MyComparator());
        for (int i=0; i<programs.length; i++){
            if (start <= programs[i].start){
                res++;
                start = programs[i].end;
            }
        }
        return res;
    }

    public static void main(String[] args){
        int arrange = bestArrange(new Program[]{new Program(6, 9), new Program(7, 10), new Program(1, 5)
                , new Program(11, 7), new Program(6, 8)}, 0);
        System.out.println(arrange);
    }
}

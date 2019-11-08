package pp.greedy;

import pp.array.Logarithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by pkpm on 2019/11/7
 * 一个数据流中，随时可以取得中位数(奇数个时，中间的那个数；偶数个时，中间两个数的平均值)
 * 借助两个堆，前N/2存在大根堆中，后N/2存在小根堆中，两个堆顶正好压中数组中间的值
 */
public class MedianQuick {

    private static class MedianHolder {
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>(new MinHeapComparator());
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());

        public void addNum(int num) {
            if (this.maxHeap.isEmpty()) {
                this.maxHeap.add(num);
                return;
            }
            if (num <= this.maxHeap.peek()) {
                this.maxHeap.add(num);
            } else {
                this.minHeap.add(num);
            }
//            添加数字后，修正两个堆的长度，保证两边是平均的
            modifyTwoHeapsSize();
        }

        public void modifyTwoHeapsSize() {
            if (this.maxHeap.size() - this.minHeap.size() == 2) {
                this.minHeap.add(this.maxHeap.poll());
            }
            if (this.minHeap.size() - this.maxHeap.size() == 2) {
                this.maxHeap.add(this.minHeap.poll());
            }
        }

        public Integer getMedian() {
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if (minHeapSize + maxHeapSize == 0) {
                return null;
            }
            int maxHeapHead = this.maxHeap.peek();
            int minHeapHead = this.minHeap.peek();
//            总个数为偶数个时(偶数最后一位一定是0)
            if (((maxHeapSize + minHeapSize) & 1) == 0) {
                return (maxHeapHead + minHeapHead) / 2;
            }
            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
        }
    }

    /**
     * 大根堆比较器
     */
    private static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o2 > o1) {
                return 1;
            }
            return -1;
        }
    }

    /**
     * 小根堆比较器
     */
    private static class MinHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return 1;
            }
            return -1;
        }
    }

    /**
     * 暴力解
     */
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    /**
     * 使用对数器验证
     */
    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 2;
        for (int i = 0; i != testTimes; i++) {
            int len = 10;
            int maxValue = 100;
            int[] arr = Logarithm.getRandomArray(len, maxValue);
            MedianHolder holder = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                holder.addNum(arr[j]);
            }
            if (holder.getMedian() != getMedianOfArray(arr)) {
                err = true;
                break;
            }
            if (err) {
                Logarithm.printArray(arr);
            }
            System.out.println(err);
            System.out.println("---------------------------");
        }
    }

}

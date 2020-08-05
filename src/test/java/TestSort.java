import java.util.Arrays;

/**
 * Created by pkpm on 2020/5/22
 */
public class TestSort {

    public static void bubbleSort(int[] arr) {
        boolean sorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    // 记录每一趟后无序数组的边界
    public static void bubbleSort2(int[] arr) {
        int lastExchange = 0;
        int border = arr.length - 1;
        boolean sorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < border; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    sorted = false;
                    lastExchange = j;
                }
            }
            if (sorted) {
                break;
            }
            border = lastExchange;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivotIndex = patition1(arr, l, r);
        quickSort1(arr, l, pivotIndex - 1);
        quickSort1(arr, pivotIndex + 1, r);
    }

    private static int patition1(int[] arr, int l, int r) {
        int pivot = arr[l];
        int left = l;
        int right = r;
        while (left != right) {
            while (left < right && arr[right] > pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, l, left);
        return left;
    }


    public static int partition2(int[] arr, int l, int r) {
        int pivot = arr[l];
        int mark = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, ++mark);
            }
        }
        swap(arr, l, mark);
        return mark;
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, r, more);
        return new int[]{less + 1, more};
    }

    /**
     * 归并排序
     * 时间复杂度O(NlogN)
     * 左区和右区相等时,先拷贝左区,可实现稳定性
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[(r - l + 1)];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= r){
            help[i++] = arr[p1] < arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1 <= mid){
            help[i++] = arr[p1++];
        }
        while(p2 <= r){
            help[i++] = arr[p2++];
        }

    //    将已排序的拷贝回原数组
        for (i=0;i<help.length;i++){
            arr[l+1] = help[i];
        }
    }

    /**
     * 构建堆
     * 所有的叶节点进行下沉操作
     *
     * ①将数组元素假设为一颗完全二叉树,构建堆是从最后一个非叶节点开始到根节点,依次下沉
     *   这样可以保证到根节点时,下面的每个分支都是符合堆的
     *
     *   如果从上向下依次调整,根节点与左孩子互换，右边分支是未调整的，会忽略右分支的元素
     *
     * ②依次将数组中的元素插入堆中
     */
    public static void buildHeap(int[] array){
        // for (int i= )
    }

}

package pp.sort;

import java.util.Arrays;

/**
 * Created by paopao on 2019/9/3
 *
 * 构建堆的时间复杂度，O(n)
 * 堆排时间复杂度，O(nlogn)（n-1趟循环，每次循环调用一次heapify）   O(nlogn)
 * 空间复杂度O(1)
 *
 * 升序
 * 删除的堆顶是堆中最大的元素，即大根堆
 *
 * 不稳定
 */
public class HeapSort {

    /**
     * 下沉
     */
    public static void heapify(int[] arr,int pindex,int length){
        int cindex = 2*pindex + 1;
//        要暂存开始时父节点的值，因为下面是单向赋值
        int temp = arr[pindex];
        while (pindex < length){
//            比较左右孩子
            if (cindex+1 < length && arr[cindex+1] > arr[cindex]){
                cindex++;
            }
//            父节点和孩子节点比较
            if (cindex < length && arr[cindex] > temp){
                arr[pindex] = arr[cindex];
                pindex = cindex;
                cindex = 2*pindex + 1;
            }else {
                break;
            }
        }
        arr[pindex] = temp;
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 堆排
     *
     * 大根堆，堆顶是最大值，堆顶和最后一个值交换，堆的长度减1
     */
    public static void heapSort(int[] arr){
        //  构建堆
        for (int i= (arr.length-2)/2; i>=0; i--){
            heapify(arr,i,arr.length);
        }
        System.out.println("建堆："+Arrays.toString(arr));

//     循环交换集合尾部元素到堆顶，并调节堆产生新的堆顶
        for (int i=arr.length-1; i>0; i--){
            swap(arr,0,i);
//          新的堆顶元素下沉，堆的长度是i
            heapify(arr,0,i);
        }

      /* int size = arr.length;
//        堆尾元素和堆顶交换，堆的长度-1
        swap(arr,0,--size);
        while (size > 0){
            heapify(arr,0,size);
            swap(arr,0,--size);
        }*/
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,3,2,6,5,7,8,9,10,0};
        heapSort(arr);
        System.out.println("堆排："+Arrays.toString(arr));
    }
}

package pp.sort;

/**
 * Created by pkpm on 2019/9/6
 *
 * 归并排序
 * 时间复杂度 O(NlogN)
 */
public class MergeSort {

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        mergeSort(arr,0,arr.length-1);
    }

//    T(N) = 2T(N/2) + O(N)
    public static void mergeSort(int[] arr,int L, int R){
        if (L == R){
            return;
        }
//        int mid = L + ((R - L)>>1);
        int mid = (L+R)/2;
        mergeSort(arr,L,mid);
        mergeSort(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    public static void merge(int[] arr,int L, int mid, int R){
        int[] help = new int[(R-L+1)];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1<=mid && p2<=R){
            help[i++] = arr[p1] < arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1 <= mid){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
//        将有序的部分拷贝回原数组
        for (i=0; i<help.length;i++){
            arr[L+i] = help[i];
        }
    }
}

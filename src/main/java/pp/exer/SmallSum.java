package pp.exer;

/**
 * 归并排序的应用：
 *
 *  小和问题、逆序对问题
 *
 *  一个数组中，每个数左边比它小的数累加，形成小和
 */
public class SmallSum {
    public static int smallSum(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        return mergeSort(arr,0,arr.length-1);
    }

    public static int mergeSort(int[] arr,int l, int r){
        if (l == r){
            return 0;
        }
//        避免 l+r越界溢出，l+(r-l)/2
        int mid = l + ((r-l)>>1);
//        左边归并排序的过程中产生的小和
        return mergeSort(arr,l,mid)
                //  右边归并排序的过程中产生的小和
                +mergeSort(arr,mid+1,r)
//                合并过程中产生的小和
                +merge(arr,1,mid,r);
    }

//    合并后不会重复产生小和，就是一个整体，和另一部分进行合并，原小组内不会产生小和
    public static int merge(int[] arr,int l, int m,int r){
        int[] help = new int[r-l+1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        while(p1 <= m && p2 <= r){
//            小和:左边比其小的数的和，可以转化为求出右侧比其大的个数
            res += arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;
            help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (i=0; i<help.length;i++){
            arr[l+i] = help[i];
        }
        return res;
    }
}

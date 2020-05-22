package pp.sort;

import java.util.Arrays;

/**
 * Created by paopao on 2019/9/3
 *
 * 快排
 *
 *  分治法：
 *      将原问题划分成n个规模较小而结构与原问题相似的子问题，递归的解决这些子问题，
 *    然后合并其结果，得到原问题的解。
 *
 *  时间复杂度最好是O(nlogn),logn趟循环；最坏是O(n^2)
 *  空间复杂度最好是O(logn),函数栈，取决于递归的深度；最坏是O(n),每一次排完都只在一边
 *  额外空间复杂度是用来记录断点的位置，只有记录断点，才能知道右半个区域在哪
 *
 *  不稳定（做交换的时候，会破坏稳定性）
 */
public class QuickSort {

//    随机快排，在数组里随机取一个，和最后一个交换

    public static void process1(int[] arr,int l,int r){
        if (l < r){
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            int[] p = partition(arr,l,r);
            process1(arr,l,p[0]-1);
            process1(arr,p[1]+1,r);
        }
    }

    /**
     * 利用荷兰国旗问题优化的快排
     *    (荷兰国旗问题：
     *      给定一个数组arr，和一个数num，
     *      把小于num的数放在数组的左边，等于num的放在中间，大于num的放在右边，
     *      额外空间O(1),时间O(N)
     *      )
     * 单边循环法
     */
    public static int[] partition(int[] arr,int l, int r){
//        左区边界
        int less = l-1;
//        右区边界
        int more = r;
//        每次迭代访问 l 处的数字，基准值是arr[r]
        while (l<more){
//          < 和左区下一个交换++less，左区右扩一个，访问L下一个
            if (arr[l] < arr[r]) {
                swap(arr,++less,l++);
//          >  和右区前一个交换，右区左扩一个，L不变，仍然访问L处
            }else if (arr[l] > arr[r]){
                swap(arr,--more,l);
//          =  访问下一个
            }else {
                l++;
            }
        }
//        跳出循环是，l=more
//        一直是以r处为基准，arr[r]是属于中间区的，和右区第一个交换，即交换完，右区第一个more位置是属于中间区的
        swap(arr,more,r);
//        返回中间区的位置，左区下一个，到more的位置
        return new int[]{less+1,more};
    }

    public static void swap(int[] arr,int i,int j){
        if (i != j){
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

//    -------------------------------------------------------

    public static void quickSort(int[] arr,int l,int r){
        if (l >= r){
            return;
        }
        int pivotIndex = patition2(arr,l,r);
        quickSort(arr,l,pivotIndex-1);
        quickSort(arr,pivotIndex+1,r);
    }

    /**
     * 双边循环法
     *
     * 需要注意的点：
     *     ① 从基准值对面的哨兵可以移动(哨兵在左侧,从左侧开始走的话,停的位置是大于哨兵的,与哨兵进行交换是不符合要求的)
     *     ② 注意判断条件，不能卡在基准值的位置
     */
    public static int patition1(int[] arr,int l,int r){
        // 取第一个位置的元素作为基准元素
        int pivot = arr[l];
        int left = l;
        int right = r;
        while(left != right){
            // 控制right指针比较并左移
            while (left < right && arr[right] > pivot){
                right--;
            }
            // 控制left指针比较并右移
            while (left < right && arr[left] <= pivot){
                left++;
            }
//          左侧找到>pivot的值，右侧找到<pivot的值，交换两个指针的值
            if (left < right){
                swap(arr,left,right);
            }
        }

        /*
             指针重合处,就是基准值的位置：右侧都比基准大,左侧比基准小
             两个指针会重合：1⃣️ j停下，i走到j，j停下是遇到了小于pivot的值
                           2⃣️ j走到i，i位置的值一定小于等于pivot
        */
        swap(arr,left,l);
        return left;
    }

//    --------------------------------------------------------------------

    /**
     * 单边循环法
     *
     * mark作为小于pivot的边界，当遍历到小于pivot的值时，mark后移一个，然后与新mark交换，相当于小区扩大一个位置
     * 所以mark所在位置以及左边，都是小于pivot
     * 遍历到最后时，pivot与mark交换，以保证左边都是小于pivot的，右侧是大于等于pivot的
     */
    public static int patition2(int[] arr,int l,int r){
        int pivot = arr[l];
//        左区边界
        int mark = l;

        for (int i=l+1; i<=r; i++){
//          小于基准值时，和左区下一个交换，左区扩一个
            if (arr[i] < pivot){
                swap(arr,++mark,i);
            }
        }
//      最终，基准值放在左区最右一个
        swap(arr,mark,l);
        return mark;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {3,4,2,1,5,6,7,8,5};
        // quickSort(arr, 0, arr.length-1);
       process1(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}

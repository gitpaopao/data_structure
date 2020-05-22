import java.util.Arrays;

/**
 * Created by pkpm on 2020/5/22
 */
public class TestSort {

    public static void bubbleSort(int[] arr){
        boolean sorted = true;
        for (int i=0; i<arr.length-1; i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                    sorted = false;
                }
            }
            if (sorted){
                break;
            }
        }
    }

    // 记录每一趟后无序数组的边界
    public static void bubbleSort2(int[] arr){
        int lastExchange = 0;
        int border = arr.length - 1;
        boolean sorted = true;
        for (int i=0;i<arr.length-1;i++){
            for (int j = 0;j<border;j++){
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                    sorted = false;
                    lastExchange = j;
                }
            }
            if (sorted){
                break;
            }
            border = lastExchange;
        }
    }

    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args){
        int[] array = new int[]{3,4,2,1,5,6,7,8};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort1(int[] arr,int l, int r){
        if (l >= r){
            return;
        }
        int pivotIndex = patition1(arr,l,r);
        quickSort1(arr,l,pivotIndex-1);
        quickSort1(arr,pivotIndex+1,r);
    }

    private static int patition1(int[] arr, int l, int r) {
        int pivot = arr[l];
        int left = l;
        int right = r;
        while(left != right){
            while (left < right && arr[right] > pivot){
                right--;
            }
            while (left < right && arr[left] <= pivot){
                left++;
            }
            if (left < right){
                swap(arr,left,right);
            }
        }
        swap(arr,l,left);
        return left;
    }


    public static int partition2(int[] arr,int l,int r){
        int pivot = arr[l];
        int mark = l;
        for (int i=l+1;i<=r;i++){
            if (arr[i] < pivot){
                swap(arr,i,++mark);
            }
        }
        swap(arr,l,mark);
        return mark;
    }

    public static int[] partition(int[] arr,int l,int r){
        int less = l-1;
        int more = r;
        while(l<more){
            if (arr[l] < arr[r]){
                swap(arr,++less,l++);
            }else if (arr[l] > arr[r]){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        swap(arr,r,more);
        return new int[]{less+1,more};
    }


}

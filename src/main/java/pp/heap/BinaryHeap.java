package pp.heap;

import java.util.Arrays;

/**
 * Created by paopao on 2019/9/3
 *
 * 小根堆
 *
 * 用数组来存储，父节点下标为 i,则左右孩子的下标分别为 2i+1,2i+2
 */
public class BinaryHeap {

    /**
     * 上浮调整(插入节点)
     */
    public static void upAdjust(int[] array){
        int cindex = array.length - 1;
        int pindex = (cindex-1)/2;
        //        temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[cindex];
        while (cindex>0 && temp<array[pindex]){
            //            单向赋值
            array[cindex] = array[pindex];
            cindex = pindex;
            pindex = (cindex-1)/2;
        }
        array[cindex] = temp;
    }

    /**
     * 下沉调整(删除节点)
     * @param array     待调整的堆
     * @param pindex    要下沉的父节点
     * @param length    堆的有效大小
     */
    public static void downAdjust(int[] array, int pindex, int length){
        //        保存父节点的值，用于最后的赋值
        int temp = array[pindex];
        int cindex = 2*pindex+1;
        while (cindex < length){
            //    比较左右孩子，如果右孩子<左孩子的值，定位到右孩子
            if (cindex+1 < length && array[cindex+1] < array[cindex]){
                cindex++;
            }
            //            父节点小于子节点的值，无需交换调整
            if (temp < array[cindex]){
                break;
            }
            //无需真正交换，单向赋值即可，即交换过后，childIndex的位置应该是temp的值
            //一轮循环后，确定父节点temp的值最终下沉到哪里，再进行最终赋值
            array[pindex] = array[cindex];
            pindex = cindex;
            cindex = 2*pindex+1;
        }
        array[pindex] = temp;
    }

    /**
     * 构建堆
     * 所有非叶节点进行下沉调整
     * @param array     待调整的堆
     *
     *  构建堆是从最后一个非叶节点开始到根节点，依次下沉
     *  这样可以保证到根节点时，下面的每个分支是符合堆的
     *
     *  如果从上到下，根节点与左孩子交换，因右边分支是未调整的，所以会忽略后分支子树中存在比根节点要小的值（小根堆）
     */
    public static void buildHeap(int[] array){
        // 从最后一个非叶子节点开始，依次下沉调整
        for (int i=(array.length-2)/2; i>=0; i--){
            downAdjust(array,i,array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[] {7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}

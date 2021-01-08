package pp.sort;

/**
 * Created by pkpm on 2020/12/28
 */
public class ReversePairs {

    public static int reversePairs(int[] nums) {

        int length = nums.length;
        if (length < 2) {
            return 0;
        }

        // 保存一份原数组结构
        int[] copy = new int[length];
        for (int i = 0; i < length; i++) {
            copy[i] = nums[i];
        }

        // 归并辅助数组
        int[] tmp = new int[length];
        return recur(nums, 0, length - 1, tmp);
    }

    private static int recur(int[] nums, int left, int right, int[] tmp) {

        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftPairs = recur(nums, left, mid, tmp);
        int rightPairs = recur(nums, mid + 1, right, tmp);

        // nums是有序的,合并不会产生逆序对了
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int count = mergeAndCount(nums, left, mid, right, tmp);
        return leftPairs + rightPairs + count;
    }

    // 归并排序并计算逆序对
    private static int mergeAndCount(int[] nums, int left, int mid, int right, int[] tmp) {

        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = tmp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = tmp[i];
                i++;
            } else if (tmp[i] <= tmp[j]) {
                nums[k] = tmp[i];
                i++;
            } else {
                nums[k] = tmp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{7, 5, 6, 4}));
    }
}

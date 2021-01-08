package pp.array;

/**
 * Created by pkpm on 2020/12/29
 */
public class ConstructArr {

    public static int[] constructArr(int[] nums) {

        if (nums == null || nums.length < 1) {
            return new int[0];
        }

        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = right[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = left[i] * right[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] ints = constructArr2(new int[]{1, 2, 3, 4, 5});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] constructArr2(int[] nums){

        int[] b = new int[nums.length];
        b[0] = 1;
        int tmp = 1;
        for (int i = 1; i < nums.length; i++) {
            b[i] = b[i - 1] * nums[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            b[i] *= tmp;
        }
        return b;
    }
}

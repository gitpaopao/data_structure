
/**
 * Created by pkpm on 2020/5/15
 */
public class Test {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 1, 2, 2, 3, 3, 4, 5, 6, 6};
        int[] ints = singleNumbers(nums);
        System.out.println(ints[0] + "……" + ints[1]);

        System.out.println(singleNumber(new int[]{3, 4, 3, 3}));
    }

    public static int[] singleNumbers(int[] nums) {

        int res = 0;
        for (int num : nums) {
            res ^= num;
        }

        int sep = 1;
        while ((sep & res) != 1) {
            sep <<= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & sep) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    public static int huo() {
        int res = 0;
        int[] nums = new int[]{1, 0, 0, 1, 1, 1, 0};

        for (int num : nums) {
            res <<= 1;
            res += num;
        }
        return res;
    }

    public static int huo1() {
        int res = 0;
        int[] nums = new int[]{1, 0, 0, 1, 1, 1, 0};

        for (int num : nums) {
            res <<= 1;
            res |= num;
        }
        return res;
    }

    public static int huo2() {
        int res = 0;
        int[] nums = new int[]{1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= nums[31 - i];
        }
        return res;
    }

    public static int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>>= 1;
            }
        }

        int res = 0, m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= count[31 - i] % 3;
        }
        return res;
    }

    public boolean twoSum(int n) {


        return false;
    }
}

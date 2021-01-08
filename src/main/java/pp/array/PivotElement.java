package pp.array;

/**
 * Created by pkpm on 2020/12/22
 * 在数组中找到满足条件的数：比它左边的都大,比它右边的都小
 */
public class PivotElement {

    public static void printPivotElement(int[] data, int len) {

        int[] rightMin = new int[data.length];
        int min = data[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            if (data[i] < min) {
                min = data[i];
            }
            rightMin[i] = min;
        }

        int max = data[0];
        for (int i = 1; i < len - 1; i++) {
            if (data[i] > max) {
                max = data[i];
                if (data[i] < rightMin[i + 1]) {
                    System.out.println(data[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6};
        printPivotElement(array,array.length);
    }
}

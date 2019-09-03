package pp.array;

/**
 * Created by paopao on 2019/9/3
 */
public class MyArray {
    private int[] array;
    private int size;

    private MyArray(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 数组插入元素
     * @param element  插入的元素
     * @param index  插入的位置
     *
     * size 是数组中实际元素的数量
     * 若尾部插入，index==size
     * 若中间或头部插入，index<size
     */
    private void insert(int element,int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("非法输入");
        }
//        扩容
        if (size >= array.length){
            resize();
        }
//      -> 右移
        for (int i=size-1; i>=index; i--){
            array[i+1] = array[i];
        }
        array[index] = element;
        size++;
    }

    /**
     * 扩容
     */
    public void resize(){
        int[] arrayNew = new int[array.length*2];
        System.arraycopy(array,0,arrayNew,0,array.length);
        array = arrayNew;
    }


    /**
     * 数组删除元素
     * @param index  删除的位置
     */
    public int delete(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("下标越界");
        }
        int element = array[index];
        for (int i=index; i<=size-1; i++){
            array[i] = array[i+1];
        }
        size--;
        return element;
    }

    /**
     * 输出数组
     */
    public void output(){
        for (int i=0; i<size; i++){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args){
        MyArray myArray = new MyArray(4);
        myArray.insert(3,0);
        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,1);
        myArray.insert(8,5);
        myArray.delete(3);
        myArray.output();
    }
}

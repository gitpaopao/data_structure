package pp.hash;

import java.util.HashMap;

/**
 * Created by pkpm on 2019/9/27
 * 设计RandomPool结构
 *  ① insert(key)：将key加入该结构，不重复加入
 *  ② delete(key): 将某个key移除
 *  ③ getRandom(): 等概率返回结构中的任何一个key
 *  时间复杂度都是O(1)
 */
public class RandomPool {

    /**
     * 给每个key加一个下标
     * 当删除元素时，将最后一个元素与其交换下标，保证下标的连续性，等概率返回元素
     * key-index 与 index-key的映射，通过两个哈希表实现
     */
    public static class Pool<K>{
        private HashMap<K,Integer> keyIndexMap;
        private HashMap<Integer,K> indexKeyMap;
        int size;
        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key){
            if (!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key,size);
                this.indexKeyMap.put(size,key);
                size++;
            }
        }

        public void delete(K key){
            if (this.keyIndexMap.containsKey(key)){
                Integer deleteIndex = this.keyIndexMap.get(key);
                K lastEle = this.indexKeyMap.get(--this.size);
                this.keyIndexMap.put(lastEle,deleteIndex);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.put(deleteIndex,lastEle);
                this.indexKeyMap.remove(this.size);
            }
        }

        public K getRandom(){
            if (this.size == 0){
                return null;
            }
//            0 ~ size-1
            int index = (int)(Math.random()*this.size);
            return this.indexKeyMap.get(index);
        }
    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<>();
        pool.insert("li");
        pool.insert("pao");
        pool.insert("fei");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
    }
}

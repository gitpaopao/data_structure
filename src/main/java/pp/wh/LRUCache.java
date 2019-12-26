package pp.wh;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现LRU
 * 使用LinkedHashMap实现
 * LinkedHashMap底层就是用的【HashMap 加 双链表】实现的，而且本身已经实现了按照访问顺序的存储。
 * 此外，LinkedHashMap中本身就实现了一个方法removeEldestEntry用于判断是否需要移除最不常读取的数，
 * 方法默认是直接返回false，不会移除元素，所以需要重写该方法,即当缓存满后就移除最不常用的数。
 */

public class LRUCache<K, V>  extends LinkedHashMap<K, V> {

    private final int MAX_CACHE_SIZE;

    public LRUCache(int cacheSize){
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;

    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<K, V> entry : entrySet()){
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<>(3);
        lruCache.put("政治", 5);
        lruCache.put("语文", 1);
        lruCache.put("英文", 3);
        System.out.println(lruCache.toString());
        lruCache.get("政治");
        lruCache.put("地理", 6);
        System.out.println(lruCache.toString());
    }



}

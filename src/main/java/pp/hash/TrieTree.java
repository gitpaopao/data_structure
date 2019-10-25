package pp.hash;

/**
 * Created by pkpm on 2019/10/25
 * 前缀树:哈希树的变种
 *    有两个字符串数组arr1,arr2
 *      ① arr2中有哪些字符串，在arr1中出现过
 *      ② arr2中有哪些字符串，在arr1中作为某个字符串前缀出现
 *      ③ arr2中出现次数最多的前缀
 */
public class TrieTree {
    /**
     * 节点信息：path:该节点到达过的次数
     *         end:以该节点结尾的次数
     *         nexts:下级的路(一共是26个字母)
     */
    private static class TrieNode{
        private int path;
        private int end;
        private TrieNode[] nexts;
        private TrieNode() {
            path = 0;
            end = 0;
        //  nexts[0] == null：该节点没有走向'a'的路
        //  nexts[0] != null：该节点有走向'a'的路
            nexts = new TrieNode[26];
        }
    }

    private static class Trie{
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        /**
         * 加入字符串
         */
        public void insert(String word) {
            if (word == null){
                return;
            }
            TrieNode node = root;
            char[] chars = word.toCharArray();
            int index;
            for (int i=0; i<chars.length; i++){
                index = chars[i] - 'a';
                if (node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.path++;
            }
            node.end++;
        }

        /**
         * 查询word出现过几次
         */
        public int search(String word) {
            if (word == null){
                return 0;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index;
            for(int i=0; i<chars.length; i++){
                index = chars[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }else {
                    node = node.nexts[index];
                }
            }
            return node.end;
        }

        /**
         * 查询加入过的字符串多少个以pre开头
         */
        public int prefixNumber(String pre) {
            if (pre == null){
                return 0;
            }
            TrieNode node = root;
            char[] chars = pre.toCharArray();
            int index;
            for (int i=0; i<chars.length; i++){
                index = chars[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }else {
                    node = node.nexts[index];
                }
            }
            return node.path;
        }
        /**
         * 删除字符串
         */
        public void delete(String word) {
            if (search(word) > 0){
                TrieNode node = root;
                int index;
                char[] chars = word.toCharArray();
                for (int i=0; i<chars.length; i++){
                    index = chars[i] - 'a';
//                   当下级节点的到达次数减为0后，则下级节点置为空，后面的也不需要处理了，直接返回
                    if(--node.nexts[index].path == 0){
                        node.nexts[index] = null;
                        return;
                    }else {
                        node = node.nexts[index];
                    }
                }
                node.end--;
            }
        }
    }
    public static void main(String[] args){
        Trie trie = new Trie();
        System.out.println(trie.search("zuo") + "-- 0");
        trie.insert("zuo");
        System.out.println(trie.search("zuo") + "-- 1");
        trie.delete("zuo");
        System.out.println(trie.search("zuo") + "-- 0");
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo") + "-- 1");
        trie.delete("zuo");
        System.out.println(trie.search("zuo") + "-- 0");
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa") + "-- 0");
        System.out.println(trie.prefixNumber("zuo") + "-- 3");
    }
}

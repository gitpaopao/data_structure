package pp.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by pkpm on 2019/10/25
 * 哈希并查集结构
 *  由两个哈希表组成，一个记录每个元素所在集合的代表元素，另一个记录集合的长度。
 *  ① isSameSet(A, B)：非常快的检查两个元素是否属于一个集合
 *  ② union(A, B)：将两个元素各自所在的集合，合并在一起()
 *
 *  优化（扁平化处理）：当从链上某个结点往上找它的代表结点时，找到之后，沿途所经过的所有结点，在找完之后统统指向代表结点(直接挂接在代表节点上)
 */
public class UnionFind {

    private static class Node{ }

    private static class UnionFindSet{
        public HashMap<Node,Node> fatherMap;
        public HashMap<Node,Integer> sizeMap;

        /**
         * 初始化
         *  集合中的每个元素看做是单个的集合，代表元素是自己
         */
        public UnionFindSet(List<Node> nodes) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes){
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        /**
         * 比较A, B所在集合的代表元素是否相同
         */
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        private Node findHead(Node node) {
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            Node father = fatherMap.get(cur);
            while (cur != father){
                stack.push(cur);
                cur = father;
                father = fatherMap.get(cur);
            }
//            扁平化优化
            while (!stack.isEmpty()){
                fatherMap.put(stack.pop(),father);
            }
            return father;
        }

        /**
         * 将两个代表结点合并即可，结点数少的挂到结点数多的上面
         */
        public void union(Node a, Node b){
            if (a == null || b == null){
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead){
                Integer aSize = sizeMap.get(aHead);
                Integer bSize = sizeMap.get(bHead);
                if (aSize < bSize){
                    fatherMap.put(aHead,bHead);
                    sizeMap.put(bHead,aSize+bSize);
                }else {
                    fatherMap.put(bHead,aHead);
                    sizeMap.put(aHead,aSize+bSize);
                }
            }
        }
    }
}

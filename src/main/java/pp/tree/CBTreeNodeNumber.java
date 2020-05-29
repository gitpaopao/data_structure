package pp.tree;

/**
 * Created by pkpm on 2019/9/27
 * 求一颗完全二叉树节点的个数，时间复杂度低于O(N)
 *       ① 首先获取最左节点所处的层数
 *       ② 判断右子树的最左节点是否在最后一层，若在，说明左子树是满二叉树；若不在，说明右子树是满二叉树，比左子树高度小1
 *       ③ 递归
 */
public class CBTreeNodeNumber {

    public static int getNumber(Node head){
        if (head == null){
            return 0;
        }
        return process(head,1,getMostLeftLavel(head,1));
    }

    /**
     * @param node 当前子树的根节点
     * @param level 根节点所在的层级
     * @param height 树的高度
     * @return 当前子树的节点个数
     */
    public static int process(Node node, int level, int height){
//        若节点所在层和树的高度相同，说明只有这一个节点
        if (level == height){
            return 1;
        }
        if (getMostLeftLavel(node.right,level+1) == height){
            // 左子树是满二叉树
            return (1 << (height-level)) + process(node.right,level+1,height);
        }else {
            // 右子树是满二叉树
            return (1 << (height-level-1)) +process(node.left,level+1,height);
        }
    }

    public static int getMostLeftLavel(Node node, int level){
        while (node != null){
            level++;
            node = node.left;
        }
        return level-1;
    }

    public static void main(String[] args){
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(getNumber(head));
    }
    public static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }
}

package pp.tree;

/**
 * Created by pkpm on 2019/9/26
 *
 * 平衡二叉树：任何一颗子树，左子树和右子树的高度差的绝对值不大于1
 *           递归(每一颗子树返回它的高度以及是否平衡，一颗子树的高度等于左右子树最大高度+1)
 */
public class IsBalancedTree {

    public static ReturnData process(Node head){
        if (head == null){
            return new ReturnData(true,0);
        }
        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);
        if (!leftData.isBalanced || !rightData.isBalanced){
            return new ReturnData(false,-1);
        }
        if (Math.abs(leftData.height - rightData.height) > 1){
            return new ReturnData(false,-1);
        }
        return new ReturnData(true,Math.max(leftData.height,rightData.height) + 1);
    }

    public static void main(String[] args){
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.left.left = new Node(6);
        head.left.left.right = new Node(7);

        System.out.println(process(head).isBalanced);
    }
    public static class ReturnData{
        public boolean isBalanced;
        public int height;

        public ReturnData(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    private static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }
}

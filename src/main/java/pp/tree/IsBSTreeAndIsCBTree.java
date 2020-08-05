package pp.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by pkpm on 2019/9/26
 * 判断是否是搜索二叉树、完全二叉树
 */
public class IsBSTreeAndIsCBTree {
    /**
     * 搜索二叉树：一颗树中是没有相同值的节点的，任何一颗子树满足，左子树小于根节点的值，右子树大于根节点的值
     *           判断：中序遍历是升序的
     */
    private static boolean isBSTree(TreeNode head){

        if (head == null){
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        TreeNode pre = null;
        while (cur != null){
            stack.push(cur);
            cur = cur.left;
        }

        while (!stack.isEmpty()){

            TreeNode pop = stack.pop();
//            访问该元素的时候，就与前驱节点比较
            if (pre != null && pop.data < pre.data){
                return false;
            }
            if (pop.right != null){
                cur = pop.right;
                while (cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
//          更新前驱节点为当前节点
            pre = pop;
        }
        return true;
    }

    /**
     * 完全二叉树：层次遍历判断每个节点
     *           ① 有右无左一定不是
     *           ② 若一个节点不是左右孩子都有(无左无右/有左无右)，则这个节点后的都是叶子节点,开启叶子模式
     */
    private static boolean isCBTree(TreeNode head){
        if (head == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        boolean leaf = false;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.right != null && node.left == null){
                return false;
            }
//            叶子模式下，但凡有一个孩子就不对
            if (leaf && (node.left != null || node.right != null)){
                return false;
            }
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }else {
//                无右时，开启叶子模式
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args){
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        System.out.println("isBSTree： " + isBSTree(head));
        System.out.println("isCBTree： " + isCBTree(head));
    }

    private static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;
        public TreeNode(int data) {
            this.data = data;
        }
    }
}

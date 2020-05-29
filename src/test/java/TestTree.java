import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import pp.tree.CBTreeNodeNumber;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by pkpm on 2020/5/27
 */
public class TestTree {

    @Data
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> input) {
        if (CollectionUtils.isEmpty(input)) {
            return null;
        }
        TreeNode node = null;
        Integer integer = input.removeFirst();
        if (integer != null) {
            node = new TreeNode(integer);
            node.setLeft(createBinaryTree(input));
            node.setRight(createBinaryTree(input));
        }
        return node;
    }

    public static void preOrderTravesal(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getData());
        preOrderTravesal(node.getLeft());
        preOrderTravesal(node.getRight());
    }

    public static void inOrderTravesal(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderTravesal(node.getLeft());
        System.out.println(node.getData());
        preOrderTravesal(node.getRight());
    }

    public static void postOrderTravesal(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderTravesal(node.getLeft());
        preOrderTravesal(node.getRight());
        System.out.println(node.getData());
    }

    public static void levelOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.println(poll.getData());
            if (poll.getLeft() != null) {

                queue.offer(poll.getLeft());
            }
            if (poll.getRight() != null) {
                queue.offer(poll.getRight());
            }
        }
    }

    public static void preOrderWithStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.getData() + " ");
                stack.push(node);
                node = node.getLeft();
            }
            while (!stack.isEmpty()) {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

    public static void inOrderWithStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null && !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            while (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println(node.getData() + " ");
                node = node.getRight();
            }
        }
    }

    public static void postOrderWithStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = null;

        while (cur != null) {
            stack.push(cur);
            cur = cur.getLeft();
        }

        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur.getRight() == null || cur.getRight() == last) {
                System.out.println(cur.getData() + " ");
                last = cur;
            } else {
                stack.push(cur);
                cur = cur.getRight();
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.getLeft();
                }
            }
        }
    }

    public static class ReturnData {
        public boolean isBalanced;
        public int height;

        public ReturnData(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /**
     * 是否是平衡二叉树
     */
    public static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(true, 0);
        }
        ReturnData left = process(head.getLeft());
        ReturnData right = process(head.getRight());
        if (!left.isBalanced || !right.isBalanced) {
            return new ReturnData(false, -1);
        }
        if (Math.abs(left.height - right.height) > 1) {
            return new ReturnData(false, -1);
        }
        return new ReturnData(true, Math.max(left.height, right.height));
    }

    /**
     * 是否是完全二叉树
     */
    private static boolean isConpleteBinaryTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.getLeft() == null && node.getRight() != null) {
                return false;
            }
            if (leaf && (node.getLeft() != null || node.getRight() != null)) {
                return false;
            }
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            } else {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 是否是搜索二叉树
     */
    public static boolean isBinarySearchTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        TreeNode pre = null;
        while(cur != null){
            stack.push(cur);
            cur = cur.getLeft();
        }
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (pre != null && node.getData() < pre.getData()){
                return false;
            }
            if (node.getRight() != null){
                cur = node.getRight();
                while (cur != null){
                    stack.push(cur);
                    cur = cur.getLeft();
                }
            }
            pre = node;
        }
        return true;
    }

    /**
     * 求一颗完全二叉树的节点的个数
     */
    public static int getNumber(TreeNode head){
        if(head == null){
            return 0;
        }
        return process(head,1,getMostLeftLevel(head,1));
    }


    public static int process(TreeNode node, int level, int height){
        if (level == height){
            return 1;
        }
        if (getMostLeftLevel(node.getRight(),level+1) == height){
            return (1<<(height-level)) + process(node.getRight(),level+1,height);
        }else {
            return (1<<(height-level)) + process(node.getLeft(),level+1,height);
        }
    }

    private static int getMostLeftLevel(TreeNode node, int level) {
        while(node != null){
            level++;
            node = node.getLeft();
        }
        return level-1;
    }
}

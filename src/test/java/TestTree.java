import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by pkpm on 2020/5/27
 */
public class TestTree {

    @Data
    public static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> input){
        if (CollectionUtils.isEmpty(input)){
            return null;
        }
        TreeNode node = null;
        Integer integer = input.removeFirst();
        if (integer != null){
            node = new TreeNode(integer);
            node.setLeft(createBinaryTree(input));
            node.setRight(createBinaryTree(input));
        }
        return node;
    }

    public static void  preOrderTravesal(TreeNode node){
        if (node == null){
            return;
        }
        System.out.println(node.getData());
        preOrderTravesal(node.getLeft());
        preOrderTravesal(node.getRight());
    }

    public static void  inOrderTravesal(TreeNode node){
        if (node == null){
            return;
        }
        preOrderTravesal(node.getLeft());
        System.out.println(node.getData());
        preOrderTravesal(node.getRight());
    }

    public static void  postOrderTravesal(TreeNode node){
        if (node == null){
            return;
        }
        preOrderTravesal(node.getLeft());
        preOrderTravesal(node.getRight());
        System.out.println(node.getData());
    }

    public static void levelOrderTravel(TreeNode node){
        if (node == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.println(poll.getData());
            if (poll.getLeft() != null){

                queue.offer(poll.getLeft());
            }
            if (poll.getRight() != null){
                queue.offer(poll.getRight());
            }
        }
    }

    public static void preOrderWithStack(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

    }
}

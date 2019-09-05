package pp.tree;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by paopao on 2019/9/3
 */
public class BinaryTreeTraversalLevel {

    /**
     * 二叉树节点
     */
    @Data
    private static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 构建二叉树
     * @param inputList   输入序列 链表
     * 链表节点的顺序是前序遍历的顺序
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        if (CollectionUtils.isEmpty(inputList)){
            return null;
        }
        TreeNode node = null;
        Integer integer = inputList.removeFirst();
        if(integer != null){
            node = new TreeNode(integer);
            node.setLeftChild(createBinaryTree(inputList));
            node.setRightChild(createBinaryTree(inputList));
        }
        return node;
    }

    /**
     * 二叉树层序遍历
     * @param root   二叉树根节点
     */
    public static void levelOrderTraversal(TreeNode root){
        if (root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
//            根节点出队列
            TreeNode poll = queue.poll();
            System.out.println(poll.getData());
//            子节点入队列
            if(poll.getLeftChild() != null){
                queue.offer(poll.getLeftChild());
            }
            if (poll.getRightChild() != null){
                queue.offer(poll.getRightChild());
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4,}));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("层序遍历：");
        levelOrderTraversal(treeNode);
    }
}

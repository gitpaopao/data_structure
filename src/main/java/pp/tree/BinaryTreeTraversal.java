package pp.tree;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by paopao on 2019/9/3
 *
 * 二叉树递归遍历
 */
public class BinaryTreeTraversal {

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
     * 二叉树前序遍历
     * @param node   二叉树节点
     */
    public static void preOrderTravesal(TreeNode node){
        if (node == null){
            return;
        }
        System.out.println(node.getData());
        preOrderTravesal(node.getLeftChild());
        preOrderTravesal(node.getRightChild());
    }

    /**
     * 二叉树中序遍历
     * @param node   二叉树节点
     */
    public static void inOrderTravesal(TreeNode node){
        if (node == null){
            return;
        }
        inOrderTravesal(node.getLeftChild());
        System.out.println(node.getData());
        inOrderTravesal(node.getRightChild());
    }

    /**
     * 二叉树后序遍历
     * @param node   二叉树节点
     */
    public static void postOrderTraversal(TreeNode node){
        if (node == null){
            return;
        }
        postOrderTraversal(node.getLeftChild());
        postOrderTraversal(node.getRightChild());
        System.out.println(node.getData());
    }

    /**
     * 二叉树的序列化和反序列化
     *      保留一棵树的结构：利用空占位
     *      恢复一颗二叉树：根据序列化的顺序进行反序列化
     */
    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4,}));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历：");
        preOrderTravesal(treeNode);
        System.out.println("中序遍历：");
        inOrderTravesal(treeNode);
        System.out.println("后序遍历：");
        postOrderTraversal(treeNode);
    }
}

package pp.tree;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by paopao on 2019/9/3
 *
 * 非递归实现二叉树遍历
 */
public class BinaryTreeTraversalStack {
    /**
     * 二叉树节点
     */
    @Data
    public static class TreeNode{
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
     * 前序遍历
     */
    public static void preOrderTravesal(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
//        当左右孩子都为空时，栈不为空，是连续两次出栈
        while (node != null || !stack.isEmpty()) {
//            迭代访问左孩子，入栈
            while (node != null) {
                System.out.print(node.getData() + " ");
                stack.push(node);
                node = node.getLeftChild();
            }
//            左孩子为空时，父节点出栈，迭代访问右子树
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.getRightChild();
            }
        }
    }


    public static void preOrderTravesal2(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.data + " ");
            if (node.rightChild != null){
                stack.push(node.rightChild);
            }
            if (node.leftChild != null){
                stack.push(node.leftChild);
            }
        }
    }
    /**
     * 中序遍历
     */
    public static void inOrderTravesal(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null){
                stack.push(node);
                node = node.getLeftChild();
            }
            //  左孩子为空时，父节点出栈，迭代访问右子树
            if(!stack.isEmpty()){
                node = stack.pop();
                System.out.print(node.getData() + " ");
                node = node.getRightChild();
            }
        }
    }

    /**
     * 后序遍历
     *
     */
    public static void postOrderTravesal(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = null;

        while (cur != null){
            stack.push(cur);
            cur = cur.getLeftChild();
        }
//       cur 为空时
        while (!stack.isEmpty()){
            cur = stack.pop();
//            没有右孩子或右孩子访问过
            if (cur.getRightChild() == null || cur.getRightChild() == last){
//                访问该父节点
                System.out.print(cur.getData() + " ");
                last = cur;
//              出栈
            }else {
//                右子树没有被访问过，父节点要重新入栈
                stack.push(cur);
                cur = cur.getRightChild();
                while (cur != null){
                    stack.push(cur);
                    cur = cur.getLeftChild();
                }
            }
        }
    }

//    左右中   中右左，该打印的时候就入栈
    public static void postOrderTravesal2(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        stackA.push(root);
        while (!stackA.isEmpty()){
            TreeNode node = stackA.pop();
            stackB.push(node);
            if (node.leftChild != null){
                stackA.push(node.leftChild);
            }
            if (node.rightChild != null){
                stackA.push(node.rightChild);
            }
        }
        while (!stackB.isEmpty()){
            System.out.print(stackB.pop().data + " ");
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4,}));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.print("前序遍历1： ");
        preOrderTravesal(treeNode);
        System.out.println();

        System.out.print("前序遍历2： ");
        preOrderTravesal2(treeNode);
        System.out.println();

        System.out.print("中序遍历 ： ");
        inOrderTravesal(treeNode);
        System.out.println();

        System.out.print("后序遍历1： ");
        postOrderTravesal(treeNode);
        System.out.println();

        System.out.print("后序遍历2： ");
        postOrderTravesal2(treeNode);
        System.out.println();

        System.out.println("直观的打印二叉树：");
        PrintBinaryTree.printTree(treeNode);
    }
}

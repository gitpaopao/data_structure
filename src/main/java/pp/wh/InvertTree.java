package pp.wh;

import java.util.Objects;

/**
 * 反转二叉树
 */
public class InvertTree {

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void preTraverse(TreeNode head){
        if (Objects.isNull(head)) return ;
        System.out.println(head.id);
        preTraverse(head.left);
        preTraverse(head.right);
    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode();
        TreeNode left = new TreeNode();
        TreeNode right = new TreeNode();
        node.setId(1);
        left.setId(2);
        right.setId(3);
        node.setLeft(left);
        node.setRight(right);
        System.out.println("反转前:");
        preTraverse(node);
        TreeNode treeNode = invertTree(node);
        System.out.println("反转后:");
        preTraverse(treeNode);


    }


}

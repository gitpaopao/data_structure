package pp.tree;

import lombok.Data;

public class SuccessorNode {

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

    public TreeNode getSuccessorNode(TreeNode root,TreeNode node){

        if (root == null){
            return null;
        }

        if (node.data >= root.data){
            return getSuccessorNode(root.rightChild,node);
        }else {

            TreeNode node1 = getSuccessorNode(root.leftChild,node);
            if (node1 == null){
                return root;
            }else {
                return node1;
            }
        }
    }
}

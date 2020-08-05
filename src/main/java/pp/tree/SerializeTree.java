package pp.tree;


import apple.laf.JRSUIUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeTree {

    /**
     * Encodes a tree to a single string.
     *
     * dfs 前序序列化
     * <p>
     * 递归前序遍历二叉树
     * <p>
     * 不需要将前序遍历序列放在 list 中，然后再转成 String
     * <p>
     * 在遍历过程中，直接拼接字符串
     */
    public String inOrderSerialize(TreeNode root) {

        String str = "[";
        str = inOrder(root, str);
        return str + "]";
    }

    public String inOrder(TreeNode node, String str) {

        if (node == null) {
            str += "null,";
        } else {
            str += str + node.val + ",";
            str = inOrder(node.left, str);
            str = inOrder(node.right, str);
        }
        return str;
    }

    /**
     * Decodes your encoded data to tree.
     *
     * 根据前序遍历序列，递归构建二叉树
     *
     */
    public TreeNode inOrderSeserialize(String data) {

        String[] split = data.substring(1, data.length() - 1).split(",");
        List<String> list = new LinkedList<>(Arrays.asList(split));

        return createNode(list);
    }


    public TreeNode createNode(List<String> list) {

        String s = list.get(0);
        if (s.equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(s));
        list.remove(0);
        node.left = createNode(list);
        node.right = createNode(list);
        return node;
    }


    /**
     * Encodes a tree to a single string.
     *
     * bfs 层次遍历
     *
     * 借助 queue 来记录父节点，循环父节点出队，访问节点，左右孩子节点入队
     */
    public String serialize(TreeNode root) {

        String string = "[";

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){

            TreeNode poll = queue.poll();

            if (poll == null){
                string+="null,";
            }else {
                string+=poll.val + ",";
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
        }
        return string + "]";
    }

    /**
     * Decodes your encoded data to tree.
     *
     * 根据层次遍历序列构建二叉树
     *
     * 和层次遍历一样，需要借助 queue 来记录父节点
     */
    public TreeNode deserialize(String data) {

        TreeNode root = null;

        //层次遍历序列
        String[] split = data.substring(1, data.length() - 1).split(",");
        List<String> list = new LinkedList<>(Arrays.asList(split));


        //得到 根节点
        if (list.get(0).equals("null")){
            return null;
        }else {
            root = new TreeNode(Integer.parseInt(list.get(0)));
        }

        Queue<TreeNode> parents = new LinkedList<>();

        //记录每一次的父节点
        TreeNode parent = root;

        //标识构建的是左孩子，还是右孩子（当构建完右孩子后，需要更新父节点的值，下一节点）
        boolean isLeft = true;

        for (int i=1;i<list.size();i++){

            //每一次遍历，就是构建一个节点
            TreeNode node = null;

            String s = list.get(i);
            if (!"null".equals(s)){
                node = new TreeNode(Integer.parseInt(s));
            }

            if (isLeft){
                parent.left = node;
            }else {
                parent.right = node;
            }

            //如果当前节点不为空，就入队暂存，它会是后面节点的父节点
            if (node != null){
                parents.offer(node);
            }

            isLeft = !isLeft;

            //右孩子构建完毕，开始构建下一节点的左右孩子
            if (isLeft){
                parent = parents.poll();
            }
        }

        return root;
    }
}

package pp.tree;

/**
 * Created by pkpm on 2019/9/27
 * 在二叉树中找到一个节点的后继节点(中序遍历中，node的下一个节点为node的后继节点)
 *      二叉树节点多一个指向父节点的parent指针
 */
public class SuccessorNode {

    /**
     *  ① 如果节点有右子树，后继为右子树最左的节点
     *  ② 没有右子树：当前是父节点的左孩子节点，后继是它的父节点
     *               当前a是父节点的右孩子节点，往上遍历，找到是其父节点左孩子的节点，后继就是这个父节点
     *               综上，就是找到这个a是哪个节点左子树最右的节点
     */
    public static Node getSuccessorNode(Node node){
        if (node == null){
            return null;
        }
        if (node.right != null){
           return getMostLeft(node.right);
        }else {
            while (node.parent != null && node.parent.left != node){
                node = node.parent;
            }
            return node.parent;
        }
    }
    //      获取最左的节点
    public static Node getMostLeft(Node node){
        if (node == null){
            return null;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(4);
        head.right.left = new Node(8);
        head.right.right = new Node(10);
        head.left.left.right = new Node(2);
        head.left.right.right = new Node(5);
        head.right.left.left = new Node(7);
        head.left.right.right.parent = head.left.right;
        head.right.left.parent = head.right;
        head.left.left.right.parent = head.left.left;
        head.left.left.parent = head.left;
        head.left.right.parent = head.left;
        head.left.parent = head;
        head.right.parent = head;
        head.right.left.left.parent = head.right.left;
        head.right.right.parent = head.right;

//        1   2
        Node test = head.left.left;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data);
//        2  3
        test = head.left.left.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data);
//        5  6
        test = head.left.right.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data);
//        6  7
        test = head;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data);
        test = head.right.left.left;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data);
        test = head.right.left;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data);
        test = head.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data);
        test = head.right.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test));
    }
        public static class Node{
            int data;
            Node left;
            Node right;
            Node parent;
            public Node(int data) {
                this.data = data;
            }
        }
    }

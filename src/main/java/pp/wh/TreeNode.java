package pp.wh;


/**
 * Created by pkpm on 2019/9/24
 */
public class TreeNode {
    public Integer id;
    public TreeNode left;
    public TreeNode right;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }



    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

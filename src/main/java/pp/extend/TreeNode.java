package pp.extend;

import lombok.Data;

/**
 * Created by pkpm on 2019/9/24
 */
@Data
public class TreeNode {
    public Integer id;
    public String name;
    public TreeNode left;
    public TreeNode mid;
    public TreeNode right;
    public boolean full;

    public Integer leftId;
    public Integer midId;
    public Integer rightId;

    public TreeNode() {
    }

    public TreeNode(Integer id, String name, Integer leftId, Integer midId, Integer rightId, boolean full) {
        this.id = id;
        this.name = name;
        this.full = full;
        this.leftId = leftId;
        this.midId = midId;
        this.rightId = rightId;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", left=" + left +
                ", mid=" + mid +
                ", right=" + right +
                ", full=" + full +
                '}';
    }
}

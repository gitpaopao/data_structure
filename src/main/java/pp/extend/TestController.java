package pp.extend;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TestController {
    /**
     * map模拟下数据库数据
     */
    public static Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>() {
        private static final long serialVersionUID = 8101266986222000075L;

        {
            put(7, new TreeNode(7, "g", null, null, null, false));
            put(6, new TreeNode(6, "f", null, null, null, false));
            put(5, new TreeNode(5, "e", null, null, null, false));
            put(4, new TreeNode(4, "d", null, null, null, false));
            put(3, new TreeNode(3, "c", 7, null, null, false));
            put(2, new TreeNode(2, "b", 5, 6, null, false));
            put(1, new TreeNode(1, "a", 2, 3, 4, true));
        }
    };

    /**
     * leader 发展了 person
     */
    public static void add(TreeNode leader, TreeNode person) {
//        将person入库得到id
        map.put(person.id, person);
        Integer personId = person.id;
//        获取leader的子树
        TreeNode root = createTree(leader);
//        层次遍历
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
//        根节点入队列
        queue.offer(root);
        while (!queue.isEmpty()) {
//            节点出队列
            TreeNode poll = queue.poll();
            if (!poll.full) {
//                节点不满
                if (poll.left == null) {
                    poll.leftId = personId;
                } else if (poll.mid == null) {
                    poll.midId = personId;
                } else {
                    poll.rightId = personId;
                    poll.full = true;
                }
//               更新
                map.put(poll.id, new TreeNode(poll.id, poll.name, poll.leftId, poll.midId, poll.rightId, poll.full));
                break;
            } else {
//              满，则继续层次遍历，孩子节点入队
                queue.offer(poll.left);
                queue.offer(poll.mid);
                queue.offer(poll.right);
            }
        }
    }

    /**
     * 获取root为根节点的子树关系
     *
     * @return 根节点
     */
    public static TreeNode createTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = getDetail(root.id);
        node.setLeft(createTree(node.left));
        node.setMid(createTree(node.mid));
        node.setRight(createTree(node.right));
        return node;
    }

    private static TreeNode getDetail(Integer id) {
        TreeNode treeNode = map.get(id);
        treeNode.setLeft(map.get(treeNode.leftId));
        treeNode.setMid(map.get(treeNode.midId));
        treeNode.setRight(map.get(treeNode.rightId));
        return treeNode;
    }

    public static void main(String[] args) {
        System.out.println("打印现有的树结构： ");
        TreeNode tree = createTree(map.get(1));
        System.out.println(tree.toString());

        addTest(3, new TreeNode(8, "h", null, null, null, false));
        addTest(2, new TreeNode(9, "i", null, null, null, false));
        addTest(2, new TreeNode(10, "j", null, null, null, false));
        addTest(1, new TreeNode(11, "k", null, null, null, false));
        addTest(4, new TreeNode(12, "l", null, null, null, false));

        System.out.println("新增后的树结构：");
        TreeNode tree2 = createTree(map.get(1));
        System.out.println(tree2.toString());
    }

    public static void addTest(Integer id, TreeNode person) {
        TreeNode leader = map.get(id);
        add(leader, person);
    }

   /* @GetMapping("/tree/create")
    public ResponseResult<?> testCreate(){
        TreeNode tree = createTree(map.get(1));
        return ((ResponseResult<TreeNode>) ResponseResult.getSuccessResult()).setData(tree);
    }

    @PostMapping("/tree/add/{id}")
    public ResponseResult<?> add(@PathVariable("id") Integer id, @RequestBody TreeNode person){
        TreeNode leader = map.get(id);
        add(leader, person);
        TreeNode tree = createTree(map.get(1));
        return ((ResponseResult<TreeNode>) ResponseResult.getSuccessResult()).setData(tree);
    }*/
}

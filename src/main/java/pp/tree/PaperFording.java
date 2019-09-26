package pp.tree;

/**
 * Created by pkpm on 2019/9/26
 * 一张纸从下向上对折一次，折痕是凹下去的，成为下折痕；对折两次，从上向下依次是下折痕、下折痕、上折痕
 *
 *    打印对折 N 次，从上向下所有折痕的方向
 *
 *    实质是二叉树的中序遍历，根节点、左孩子节点都是下，右孩子节点是上
 */
public class PaperFording {

    private static void printAllForders(int n){
        process(1,n,true);
    }
    private static void process(int level, int height, boolean down){
        if (level > height){
            return;
        }
        process(level+1, height,true);
        System.out.println(down?"下":"上");
        process(level+1, height,false);
    }

    public static void main(String[] args){
        printAllForders(3);
    }
}

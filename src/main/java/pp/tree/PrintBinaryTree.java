package pp.tree;

/**
 * 直观的打印二叉树
 */
public class PrintBinaryTree {

	public static void printTree(BinaryTreeTraversalStack.TreeNode head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(BinaryTreeTraversalStack.TreeNode head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.rightChild, height + 1, "v", len);
		String val = to + head.data + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.leftChild, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

}

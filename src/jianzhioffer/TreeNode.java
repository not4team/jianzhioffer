package jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * 先序遍历：根节点->左子树->右子树
 * 中序遍历：左子树->根节点->右子树
 * 后序遍历：左子树->右子树->根节点
 * 
 * 二叉搜索树，也称有序二叉树,排序二叉树，是指一棵空树或者具有下列性质的二叉树：
 * 1. 若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 2. 若任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 3. 任意节点的左、右子树也分别为二叉查找树。
 * 4. 没有键值相等的节点。
 * 
 * @author shixq
 *
 */
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
	
	public static void preOrder(TreeNode root) {
		if(root != null) {
			System.out.println(root.val);
			if(root.left != null) {
				System.out.println("it's left child is " + root.left.val);
			}
			if(root.right != null) {
				System.out.println("it's right child is " + root.right.val);
			}
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	/**
	 * 非递归先序遍历
	 * @param root
	 */
	public static void preOrder1(TreeNode root) {
		// 用来暂存节点的栈
		Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
		// 新建一个游标节点为根节点
		TreeNode node = root;
		// 当遍历到最后一个节点的时候，它的左右子树都为空，并且栈也为空
		// 所以，只要不同时满足这两点，都需要进入循环
		while (node != null || !treeNodeStack.isEmpty()) {
			// 若当前考查节点非空，则输出该节点的值
			// 由考查顺序得知，需要一直往左走
			while (node != null) {
				System.out.print(node.val + " ");
				// 为了之后能找到该节点的右子树，暂存该节点
				treeNodeStack.push(node);
				node = node.left;
			}
			// 一直到左子树为空，则开始考虑右子树
			// 如果栈已空，就不需要再考虑
			// 弹出栈顶元素，将游标等于该节点的右子树
			if (!treeNodeStack.isEmpty()) {
				node = treeNodeStack.pop();
				node = node.right;
			}
		}
	}

	public static void inOrder(TreeNode root) {
		if(root != null) {
			inOrder(root.left);
			System.out.println(root.val);
			inOrder(root.right);
		}
	}
	
	public static void inOrder1(TreeNode root) {
		Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
		TreeNode node = root;
		while (node != null || !treeNodeStack.isEmpty()) {
			while (node != null) {
				treeNodeStack.push(node);
				node = node.left;
			}
			if (!treeNodeStack.isEmpty()) {
				node = treeNodeStack.pop();
				System.out.print(node.val + " ");
				node = node.right;
			}
		}
	}
	
	public static void postOrder(TreeNode root) {
		if(root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.val);
		}
	}
	
	public static void postOrder1(TreeNode root) {
		Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
		TreeNode node = root;
		TreeNode lastVisit = root;
		while (node != null || !treeNodeStack.isEmpty()) {
			while (node != null) {
				treeNodeStack.push(node);
				node = node.left;
			}
			// 查看当前栈顶元素
			node = treeNodeStack.peek();
			// 如果其右子树也为空，或者右子树已经访问
			// 则可以直接输出当前节点的值
			if (node.right == null || node.right == lastVisit) {
				System.out.print(node.val + " ");
				treeNodeStack.pop();
				lastVisit = node;
				node = null;
			} else {
				// 否则，继续遍历右子树
				node = node.right;
			}
		}
	}
	
	/**
	 * 深度优先搜索
	 * 深度优先搜索二叉树是先访问根结点，然后遍历左子树接着是遍历右子树，
	 * 因此我们可以利用堆栈的先进后出的特点，先将右子树压栈，再将左子树压栈，
	 * 这样左子树就位于栈顶，可以保证结点的左子树先与右子树被遍历。
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> depthFirstSearch(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<>();
		if(root == null) {
			return result;
		}
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode treeNode = stack.pop();
			System.out.println(treeNode.val);
			result.add(treeNode.val);
			if(treeNode.right != null) {
				stack.push(treeNode.right);
			}
			if(treeNode.left != null) {
				stack.push(treeNode.left);
			}
		}
		return result;
	}

	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入
	 * 前序遍历序列{1,2,4,7,3, 5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
	 * 前序遍历第一个元素一定是根节点，中序遍历根节点左边是左子树右边是右子树。
	 * 
	 * 
	 */
	public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre.length == 0 || in.length == 0) {
			return null;
		}
		TreeNode node = new TreeNode(pre[0]);
		for (int i = 0; i < in.length; i++) {
			if (pre[0] == in[i]) {
				node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
				node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
						Arrays.copyOfRange(in, i + 1, in.length));
			}
		}
		TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
		return root;
	}

	private static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn,
			int endIn) {

		if (startPre > endPre || startIn > endIn)
			return null;
		TreeNode root = new TreeNode(pre[startPre]);

		for (int i = startIn; i <= endIn; i++)
			if (in[i] == pre[startPre]) {
				root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
				root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
			}
		return root;
	}

	/**
	 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null) {
			return false;
		}

		boolean result = false;
		// 如果找到了对应Tree2的根节点的点
		if (root1.val == root2.val) {
			// 以这个根节点为为起点判断是否包含Tree2
			result = isSame(root1, root2);
		}
		// 如果找不到，那么就再去root的左儿子当作起点，去判断时候包含Tree2
		if (!result) {
			result = HasSubtree(root1.left, root2);
		}

		// 如果还找不到，那么就再去root的右儿子当作起点，去判断时候包含Tree2
		if (!result) {
			result = HasSubtree(root1.right, root2);
		}
		return result;
	}

	/**
	 * 判断两颗树是否一样
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static boolean isSame(TreeNode node1, TreeNode node2) {
		// 如果Tree2已经遍历完了都能对应的上，返回true
		if (node2 == null) {
			return true;
		}
		// 如果Tree2还没有遍历完，Tree1却遍历完了。返回false
		if (node1 == null) {
			return false;
		}
		// 如果其中有一个点没有对应上，返回false
		if (node1.val != node2.val) {
			return false;
		}

		// 如果根节点对应的上，那么就分别去子节点里面匹配
		return isSame(node1.left, node2.left) && isSame(node1.right, node2.right);
	}

	/**
	 * 操作给定的二叉树，将其变换为源二叉树的镜像。
	 * 二叉树的镜像定义：源二叉树 
    	    8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11
    	镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
	 * @param root
	 */
	public void Mirror(TreeNode root) {
		if(root != null) {
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;
			Mirror(root.left);
			Mirror(root.right);
		}
	}
	
	/**
	 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
	 * 广度优先搜索(Breadth First Search)
	 * 借助队列先入先出特点
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode treeNode = queue.poll();
			if(treeNode.left != null) {
				queue.add(treeNode.left);
			}
			if(treeNode.right != null) {
				queue.add(treeNode.right);
			}
			result.add(treeNode.val);
		}
		return result;
    }
	
	/**
	 * 输入一棵二叉树，求该树的深度。
	 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
	 * 
	 * 广度优先搜索(Breadth First Search)
	 * @param root
	 * @return
	 */
	public static int TreeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int depth = 0, count = 0, nextCount = 1;
		while (!queue.isEmpty()) {
			TreeNode treeNode = queue.poll();
			count++;
			if (treeNode.left != null) {
				queue.add(treeNode.left);
			}
			if (treeNode.right != null) {
				queue.add(treeNode.right);
			}
			//nextCount每层元素的个数，利用count来记录该层的元素是否全部弹出，如果全部弹出就depth+1
			if (count == nextCount) {
				nextCount = queue.size();
				count = 0;
				depth++;
			}
		}
		return depth;
	}
	
	public int TreeDepth1(TreeNode pRoot) {
		if (pRoot == null) {
			return 0;
		}
		int left = TreeDepth1(pRoot.left);
		int right = TreeDepth1(pRoot.right);
		return Math.max(left, right) + 1;
	}
	
	/**
	 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
	 * 假设输入的数组的任意两个数字都互不相同。
	 * 
	 * 二叉搜索树（二叉排序树，二叉查找树）具有以下性质：
	 * 1. 若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
	 * 2. 若任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
	 * 3. 任意节点的左、右子树也分别为二叉查找树。
	 * 4. 没有键值相等的节点。
	 * 
	 * 非递归也是一个基于递归的思想：
	 * 左子树一定比右子树小，因此去掉根后，数字分为left，right两部分，right部分的
	 * 最后一个数字是右子树的根他也比左子树所有值大，因此我们可以每次只看右子树是否符合条件
	 * 即可，即使到达了左子树左子树也可以看出由左右子树组成的树还像右子树那样处理
	 * 对于左子树回到了原问题，对于右子树，左子树的所有值都比右子树的根小可以暂时把他看出右子树的左子树
	 * 只需看看右子树的右子树是否符合要求即可
	 * 
	 * @param sequence
	 * @return
	 */
	public boolean VerifySquenceOfBST(int[] sequence) {

		int size = sequence.length;
		if (0 == size)
			return false;

		int i = 0;
		while (--size > 0) {
			while (sequence[i] < sequence[size]) {
				i++;
			}
			while (sequence[i] > sequence[size]) {
				i++;
			}
			if (i < size) {
				return false;
			}
			i = 0;
		}
		return true;
	}
	
	/**
	 * BST的后序序列的合法序列是，对于一个序列S，最后一个元素是x （也就是根），如果去掉最后一个元素的序列为T，
	 * 那么T满足：T可以分成两段，前一段（左子树）小于x，后一段（右子树）大于x，
	 * 且这两段（子树）都是合法的后序序列。完美的递归定义 。
	 * @param sequence
	 * @return
	 */
	public boolean VerifySquenceOfBST1(int[] sequence) {
		if(sequence.length == 0) {
			return false;
		}
		return isPostOrder(sequence, 0, sequence.length - 1);
	}
	
	public boolean isPostOrder(int[] a, int left, int right) {

		//left==right对应的是叶子结点，left>right对应的是空树，这两种情况都是true
		if (left >= right) {
			return true;
		}
		int i = right;
		while (i > left && a[i - 1] > a[right]) {
			--i;
		}
		for (int j = i - 1; j >= left; --j) {
			if (a[j] > a[right]) {
				return false;
			}
		}
		return isPostOrder(a, left, i - 1) && isPostOrder(a, i, right - 1);
	}
	
	/**
	 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
	 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
	 * @param root
	 * @param target
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> list = new ArrayList<>();
		depthFirstSearch(root, target, result, list);
		return result;
    }
	
	public static void depthFirstSearch(TreeNode root, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list) {
		if(root != null) {
			list.add(root.val);
			target -= root.val;
			//target==0同时到达叶子节点时代表找到一条路径
			if(target == 0 && root.left == null && root.right == null) {
				result.add(new ArrayList<>(list));
			}
			depthFirstSearch(root.left, target, result, list);
			depthFirstSearch(root.right, target, result, list);
			//递归到叶子节点如果还没有找到路径，就要回退到父节点继续寻找
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
	 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
	 * 
	 * 解题思路：
	 * 1.核心是中序遍历的非递归算法。
	 * 2.修改当前遍历节点与前一遍历节点的指针指向。
	 * @param pRootOfTree
	 * @return
	 */
	public TreeNode convert(TreeNode pRootOfTree) {
		if (pRootOfTree == null) {
			return pRootOfTree;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode root = pRootOfTree;
		TreeNode pre = null;
		boolean isFirst = true;
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				if (isFirst) {
					pRootOfTree = root;// 将中序遍历序列中的第一个节点记为root
					pre = pRootOfTree;
					isFirst = false;
				} else {
					pre.right = root;
					root.left = pre;
					pre = root;
				}
				root = root.right;
			}
		}
		return pRootOfTree;
	}

	/**
	 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
	 * @param root
	 * @return
	 */
	public boolean IsBalanced_Solution(TreeNode root) {
		return getDepth(root) != -1;
	}

	private int getDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = getDepth(root.left);
		if (leftHeight == -1) {
			return -1;
		}
		int rightHeight = getDepth(root.right);
		if (rightHeight == -1) {
			return -1;
		}
		return Math.abs(leftHeight - rightHeight) > 1 ? -1 : 1 + Math.max(leftHeight, rightHeight);
	}

	/**
	 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
	 * 
	 * 思路：首先根节点以及其左右子树，左子树的左子树和右子树的右子树相同左子树的右子树和右子树的左子树相同即可，采用递归
	 * 非递归也可，采用栈或队列存取各级子树根节点
	 */
	boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null) {
			return true;
		}
		return isSymmetrical(pRoot.left, pRoot.right);
	}

	boolean isSymmetrical(TreeNode left, TreeNode right) {
		if (left == null) {
			return right == null;
		} else if (right == null) {
			return false;
		} else if (left.val != right.val) {
			return false;
		} else {
			return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
		}
	}

	/**
	 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
	 * 
	 * 
	 */
	public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		// s1存奇数层节点
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		s1.push(pRoot);
		// s2存偶数层节点
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

		while (!s1.empty() || !s2.empty()) {
			if (!s1.empty()) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				while (!s1.empty()) {
					TreeNode node = s1.pop();
					if (node != null) {
						temp.add(node.val);
						System.out.print(node.val + " ");
						s2.push(node.left);
						s2.push(node.right);
					}
				}
				if (!temp.isEmpty()) {
					list.add(temp);
					System.out.println();
				}
			} else if (!s2.empty()){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				while (!s2.empty()) {
					TreeNode node = s2.pop();
					if (node != null) {
						temp.add(node.val);
						System.out.print(node.val + " ");
						s1.push(node.right);
						s1.push(node.left);
					}
				}
				if (!temp.isEmpty()) {
					list.add(temp);
					System.out.println();
				}
			}
		}
		return list;
	}

	/**
	 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
	 */
	ArrayList<ArrayList<Integer> > Print1(TreeNode pRoot) {
		// s1存奇数层节点
		Queue<TreeNode> s1 = new LinkedList<TreeNode>();
		s1.offer(pRoot);
		// s2存偶数层节点
		Queue<TreeNode> s2 = new LinkedList<TreeNode>();

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

		while (!s1.isEmpty() || !s2.isEmpty()) {
			if (!s1.isEmpty()) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				while (!s1.isEmpty()) {
					TreeNode node = s1.poll();
					if (node != null) {
						temp.add(node.val);
						System.out.print(node.val + " ");
						s2.offer(node.left);
						s2.offer(node.right);
					}
				}
				if (!temp.isEmpty()) {
					list.add(temp);
					System.out.println();
				}
			} else if (!s2.isEmpty()){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				while (!s2.isEmpty()) {
					TreeNode node = s2.poll();
					if (node != null) {
						temp.add(node.val);
						System.out.print(node.val + " ");
						s1.offer(node.left);
						s1.offer(node.right);
					}
				}
				if (!temp.isEmpty()) {
					list.add(temp);
					System.out.println();
				}
			}
		}
		return list;
	}
	
	/**
	 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
	 */
	ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (pRoot == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(pRoot);
		while (!queue.isEmpty()) {
			int size = queue.size();
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				temp.add(node.val);
				if(node.left != null) {
					queue.offer(node.left);
				}
				if(node.right != null) {
					queue.offer(node.right);
				}
			}
			result.add(temp);
		}
		return result;
	}

	int index = -1; // 计数变量

	/**
	 * 请实现两个函数，分别用来序列化和反序列化二叉树
	 */
	String Serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		if (root == null) {
			sb.append("#,");
			return sb.toString();
		}
		sb.append(root.val + ",");
		sb.append(Serialize(root.left));
		sb.append(Serialize(root.right));
		return sb.toString();
	}

	TreeNode Deserialize(String str) {
		index++;
		String[] strr = str.split(",");
		TreeNode node = null;
		if (!strr[index].equals("#")) {
			node = new TreeNode(Integer.valueOf(strr[index]));
			node.left = Deserialize(str);
			node.right = Deserialize(str);
		}
		return node;
	}

	/**
	 * 给定一颗二叉搜索树，请找出其中的第k大的结点。例如， 
	 * 		5 
	 * 	   / \ 
	 *    3   7 
	 *   /\   /\ 
	 *  2  4  6 8 
	 * 中，按结点数值大小顺序第三个结点的值为4。
	 * 
	 * 二叉搜索树按照中序遍历的顺序打印出来正好就是排序好的顺序。所以，按照中序遍历顺序找到第k个结点就是结果。
	 */
	TreeNode KthNode(TreeNode pRoot, int k) {
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
		TreeNode node = pRoot;
		int index = 0;
		while (node != null || !treeNodeStack.isEmpty()) {
			while (node != null) {
				treeNodeStack.push(node);
				node = node.left;
			}
			if (!treeNodeStack.isEmpty()) {
				node = treeNodeStack.pop();
				System.out.print(node.val + " ");
				if(++index == k) {
					return node;
				}
				node = node.right;
			}
		}
		return null;
    }

	public static void main(String[] args) {
		int[] pre = { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] in = { 4, 7, 2, 1, 5, 3, 8, 6 };
		TreeNode node = TreeNode.reConstructBinaryTree(pre, in);
		preOrder(node);
		postOrder(node);
		System.out.println("二叉树深度:" + TreeDepth(node));
	}
}

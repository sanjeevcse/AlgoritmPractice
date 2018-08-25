package com.src.dev.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTree {
	static int max_level = 0;

	public static class TreeNode {
		int data;
		int hDistance;
		TreeNode left;
		TreeNode right;

		TreeNode(int data) {
			this.data = data;
			hDistance = Integer.MAX_VALUE;
		}
	}

	public void preorder(TreeNode root) {
		if (root != null) {
			System.out.printf("%d ", root.data);
			preorder(root.left);
			preorder(root.right);
		}
	}

	public void preorderIter(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode n = stack.pop();
			System.out.printf("%d ", n.data);
			if (n.right != null)
				stack.push(n.right);
			if (n.left != null) {
				stack.push(n.left);
			}
		}
	}

	public static TreeNode lca(TreeNode root, int n1, int n2) {
		if (root == null)
			return null;
		if (root.data == n1 || root.data == n2)
			return root;
		TreeNode left = lca(root.left, n1, n2);
		TreeNode right = lca(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		}
		return (left != null) ? left : right;
	}

	public void inorderIter(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode currentNode = root;
		while (!stack.empty() || currentNode != null) {
			if (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.left;
			} else {
				TreeNode n = stack.pop();
				System.out.printf("%d ", n.data);
				currentNode = n.right;
			}
		}
	}

	public static int leafCount(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		else
			return leafCount(root.left) + leafCount(root.right);

	}

	public static int treeHeight(TreeNode root) {
		if (root == null)
			return 0;
		else {
			int l = treeHeight(root.left);
			int r = treeHeight(root.right);
			return Math.max(r + 1, l + 1);
		}
	}

	public static void deleteTree(TreeNode root) {
		if (root == null)
			return;
		deleteTree(root.left);
		deleteTree(root.right);
		System.out.println("deleted node is :" + root.data);
		root = null;
	}

	public static void verticalSum(TreeNode root, TreeMap<Integer, Integer> map, int level) {
		if (root == null)
			return;

		if (map.get(level) != null) {
			Integer sum = map.get(level) + root.data;
			map.put(level, sum);
		} else {
			map.put(level, root.data);
		}
		verticalSum(root.left, map, level - 1);
		verticalSum(root.right, map, level + 1);
	}

	public static void addNodeValues(TreeNode root, int key) {
		if (root == null)
			return;
		if (root.data == key) {
			root.data = root.left.data + root.right.data;
		} else {
			addNodeValues(root.left, key);
			addNodeValues(root.right, key);
		}
	}

	/*
	 * public static void printExtreme(TreeNode root) { if(root == null) return;
	 * 
	 * }
	 */
	public static void verticleTraversal(TreeNode root, TreeMap<Integer, ArrayList<Integer>> map, int level) {
		if (root == null)
			return;

		ArrayList<Integer> list = map.get(level);
		if (list == null) {
			list = new ArrayList<>();
			list.add(root.data);
		} else {
			list.add(root.data);
		}
		map.put(level, list);
		verticleTraversal(root.left, map, level - 1);
		verticleTraversal(root.right, map, level + 1);
	}

	public static void leftView(TreeNode root, int level) {
		if (root == null)
			return;
		if (max_level < level) {
			System.out.println(root.data + " ");
			max_level = level;
		}
		leftView(root.left, level + 1);
		leftView(root.right, level + 1);
	}

	public static void topView(TreeNode root, TreeMap<Integer, Integer> map, int level) {
		if (root == null)
			return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		root.hDistance = level;
		while (!queue.isEmpty()) {
			TreeNode temp = queue.poll();
			level = temp.hDistance;
			if (map.get(level) == null)
				map.put(level, temp.data);
			if (temp.left != null) {
				temp.left.hDistance = level - 1;
				queue.add(temp.left);
			}
			if (temp.right != null) {
				temp.right.hDistance = level + 1;
				queue.add(temp.right);
			}
		}
	}

	public static void bottomView(TreeNode root, TreeMap<Integer, Integer> map, int level) {
		if (root == null)
			return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		root.hDistance = level;
		while (!queue.isEmpty()) {
			TreeNode temp = queue.poll();
			level = temp.hDistance;
			map.put(level, temp.data);
			if (temp.left != null) {
				temp.left.hDistance = level - 1;
				queue.add(temp.left);
			}
			if (temp.right != null) {
				temp.right.hDistance = level + 1;
				queue.add(temp.right);
			}
		}
	}

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		TreeNode rootNode = createBinaryTree();

		/*
		 * //PreOrder traversal System.out.println("Using recursion:");
		 * bt.preorder(rootNode);
		 */

		/*
		 * //Iterative Preorder Traversal bt.preorderIter(rootNode);
		 */

		/*
		 * //Iterative Inorder Traversal bt.inorderIter(rootNode);
		 */

		/*
		 * //Leaf Count System.out.println("No. of leaf are: " +
		 * leafCount(rootNode)); deleteTree(rootNode);
		 */

		/*
		 * //Height of Tree System.out.println("height is :" +
		 * treeHeight(rootNode));
		 */

		/*
		 * //LCA of two nodes TreeNode temp = lca(rootNode, 80, 50);
		 * System.out.println(temp.data);
		 */

		/*
		 * //Add Child nodes of given key addNodeValues(rootNode, 60);
		 */

		/*
		 * //Vertical Sum TreeMap<Integer, Integer> map = new TreeMap<>();
		 * verticalSum(rootNode, map, 0); for(Entry<Integer, Integer>
		 * entry:map.entrySet()) { System.out.println(entry.getValue()); }
		 */

		/*
		 * // Vertical Traversal TreeMap<Integer, ArrayList<Integer>> map = new
		 * TreeMap<>(); verticleTraversal(rootNode, map, 0); for(Entry<Integer,
		 * ArrayList<Integer>> entry:map.entrySet()) {
		 * System.out.println(entry.getKey()); }
		 */

		/*
		 * //Left View leftView(rootNode, 1);
		 */

		/*
		 * //Top View TreeMap<Integer, Integer> map = new TreeMap<>();
		 * topView(rootNode, map, 0); for(Entry<Integer, Integer>
		 * entry:map.entrySet()) { System.out.println(entry.getKey()); }
		 */

		/*
		 * //Bottom View TreeMap<Integer, Integer> map = new TreeMap<>();
		 * bottomView(rootNode, map, 0); for(Entry<Integer, Integer>
		 * entry:map.entrySet()) { System.out.println(entry.getValue()); }
		 */

		/*
		 * // Print Extreme TreeMap<Integer, Integer> map = new TreeMap<>();
		 * List<Integer> list = new ArrayList<>(); topView(rootNode, map, 0);
		 * for (Entry<Integer, Integer> entry : map.entrySet()) {
		 * list.add(entry.getValue()); } System.out.println(
		 * "LeftExtremeElement :" + list.get(0)); System.out.println(
		 * "RightExtremeElement :" + list.get(list.size()-1));
		 */
	}

	private static TreeNode createBinaryTree() {
		TreeNode rootNode = new TreeNode(40);
		TreeNode node20 = new TreeNode(20);
		TreeNode node10 = new TreeNode(10);
		TreeNode node30 = new TreeNode(30);
		TreeNode node60 = new TreeNode(60);
		TreeNode node50 = new TreeNode(50);
		TreeNode node70 = new TreeNode(70);
		TreeNode node75 = new TreeNode(75);
		TreeNode node80 = new TreeNode(80);
		TreeNode node90 = new TreeNode(90);
		TreeNode node95 = new TreeNode(95);
		rootNode.left = node20;
		rootNode.right = node60;
		node20.left = node10;
		node20.right = node30;
		node60.left = node50;
		node60.right = node70;
		node70.left = node75;
		node75.left = node80;
		node70.right = node90;
		node75.left = node95;
		return rootNode;
	}

}

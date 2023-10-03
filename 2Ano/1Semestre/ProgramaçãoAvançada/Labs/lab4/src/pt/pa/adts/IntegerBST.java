/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author brunomnsilva
 */
public class IntegerBST implements IntegerBinarySearchTree {

	private TreeNode root;

	public IntegerBST() {
		this.root = null;
	}

	public IntegerBST(int rootValue) {
		this.root = new TreeNode(rootValue, null, null, null);
	}

	@Override
	public int sumOdd() throws EmptyContainerException {
		if (this.root == null) {
			throw new EmptyContainerException("Root is null");
		} return sumOdd(this.root);
	}

	private int sumOdd(TreeNode node) {
		if (node == null) {
			return 0;
		} else if (node.element % 2 != 0) {
			System.out.println(node.element);
			return node.element + sumOdd(node.left) + sumOdd(node.right);
		} return 0 + sumOdd(node.left) + sumOdd(node.right);
	}
   
	@Override
	public int sumInternals() throws EmptyContainerException {
		return sumInternals(this.root);
	}
		
	private int sumInternals(TreeNode node) {
		if (node == null) {
			return 0;
		} else if (!node.hasLeft() && !node.hasRight()) {
			return 0;
		} return 1 + sumInternals(node.left) + sumInternals(node.right);
	}

	@Override
	public int countGreaterThan(Integer value) throws EmptyContainerException {
		return countGreaterThan(value, this.root);	
	}

	private int countGreaterThan(Integer value, TreeNode node) {
		if (node == null) {
			return 0;
		} else if (node.element > value) {
			return 1 + countGreaterThan(value, node.left) + countGreaterThan(value, node.right);
		} return 0 + countGreaterThan(value, node.left) + countGreaterThan(value, node.right);
	}

	@Override
	public Set<Integer> greaterThan(Integer value) {
		Set<Integer> elems = new HashSet<>();

		greaterThan(value, this.root, elems);
		return elems;
	}

	private void greaterThan(Integer value, TreeNode node, Set<Integer> elems) {
		if (node == null) {
			return ;
		} else if (node.element > value) {
			elems.add(node.element);
		} greaterThan(value, node.left, elems);
		greaterThan(value, node.right, elems);
	}
  
	@Override
	public boolean isEmpty() {
		return (this.root == null);
	}

	@Override
	public int size() {
		return size(this.root);
	}

	private int size(TreeNode treeRoot) {
		if (treeRoot == null) {
			return 0;
		}

		return 1 + size(treeRoot.left) + size(treeRoot.right);
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(TreeNode treeRoot) {
		if (treeRoot == null) {
			return -1;
		}

		return 1 + Math.max(height(treeRoot.left), height(treeRoot.right));
	}

	@Override
	public boolean exists(Integer elem) {
		if (isEmpty()) {
			return false;
		}

		return exists(elem, this.root);
	}

	private boolean exists(Integer elem, TreeNode treeRoot) {
		if (treeRoot == null) {
			return false;
		}
		if (elem == treeRoot.element) {
			return true;
		}

		if (elem < treeRoot.element) {
			return exists(elem, treeRoot.left);
		} else {
			return exists(elem, treeRoot.right);
		}
	}

	@Override
	public void insert(Integer elem) {
		if (isEmpty()) {
			this.root = new TreeNode(elem, null, null, null);
		} else {
			insert(elem, this.root);
		}
	}

	private void insert(Integer elem, TreeNode treeRoot) {
		if (treeRoot == null) {
			throw new IllegalArgumentException("Invalid usage.");
		}

		if (elem == treeRoot.element) {
			return;
		}

		if (elem < treeRoot.element) {
			if (treeRoot.hasLeft()) {
				insert(elem, treeRoot.left);
			} else {
				treeRoot.setLeft(new TreeNode(elem, treeRoot, null, null));
			}
		} else {
			if (treeRoot.hasRight()) {
				insert(elem, treeRoot.right);
			} else {
				treeRoot.setRight(new TreeNode(elem, treeRoot, null, null));
			}
		}
	}

	@Override
	public void remove(Integer elem) throws EmptyContainerException {
		if(isEmpty()) {
			throw new EmptyContainerException();
		}
		remove(elem, this.root);
	}
	
	private void remove(Integer elem, TreeNode treeRoot)  {
		if( treeRoot == null ) return;
		
		if( elem == treeRoot.element ) 
			removeNode(treeRoot);
		else if( elem < treeRoot.element ) // Left subtree recursivity
			remove(elem, treeRoot.left);
		else // Right subtree recursivity
			remove(elem, treeRoot.right);
	}
	
	private void removeNode(TreeNode treeRoot) {
		if( treeRoot == null ) return; // Defensive programming
		
		TreeNode parent = treeRoot.parent;

		// Case 1: It is an external node, just remove it
		if( treeRoot.left == null && treeRoot.right == null ) {
			
			if(parent == null) {
				// It's the general tree root, since it has no predecessor
				this.root = null;
			} else if( parent.left == treeRoot ) {
				parent.left = null;
			} else { //parent.right == treeRoot                
				parent.right = null;
			}
		}
		// Case 3: Has both subtrees. Two options here:
		// 1 - Replace the element by the biggest element of the left subtree, or;
		// 2 - Replace the element by the smaller element of the right subtree.
		// We will use the option no. 2
		else if( treeRoot.left != null && treeRoot.right != null ) {
			int minimumRight = minimum(treeRoot.right);
			// The order of both instructions is critical; If they are replaced
			// the removam will occur after the substition, hence the value won't
			// exist in the tree anymore
			remove(minimumRight);
			treeRoot.element = minimumRight;
			
		}
		// Case 2: Only one subtree; replace the node for that same subtree
		else {
			// Get the root reference of that subtree
			TreeNode subTree = (treeRoot.left != null) ? treeRoot.left : treeRoot.right;

			// Update: Oct 16th 2019 Scenario where treeRoot is root (no parent)
			if(treeRoot == this.root) {
				this.root = subTree;  
				subTree.parent = null;
			} else {
				if( parent.left == treeRoot ) {
					parent.left = subTree;                    
				} else {
					parent.right = subTree;
				}
				// Update subtree parent
				subTree.parent = parent;
			}
			
		}
	}

	@Override
	public Integer minimum() throws EmptyContainerException {
		if (isEmpty()) {
			throw new EmptyContainerException();
		}

		return minimum(this.root);
	}
	
	private Integer minimum(TreeNode treeRoot) {
		TreeNode cur = treeRoot;
		while (cur.hasLeft()) {
			cur = cur.left;
		}
		return cur.element;
	}

	@Override
	public Integer maximum() throws EmptyContainerException {
		if (isEmpty()) {
			throw new EmptyContainerException("The BST is empty.");
		}

		TreeNode cur = this.root;
		while (cur.hasRight()) {
			cur = cur.right;
		}
		return cur.element;
	}

	@Override
	public Iterable<Integer> inOrder() {
		List<Integer> list = new ArrayList<>();
		inOrder(this.root, list);
		return list;
	}
	
	private void inOrder(TreeNode treeRoot, List<Integer> elements) {
		if( treeRoot == null) return;
		
		inOrder(treeRoot.left, elements);
		elements.add( treeRoot.element );
		inOrder(treeRoot.right, elements);
	}

	@Override
	public Iterable<Integer> preOrder() {
		//TODO: completar trabalho autónomo, nao faz parte do lab
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Iterable<Integer> posOrder() {
		//TODO: completar tabalho autonomo, nao faz parte do lab
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Iterable<Integer> breadthOrder() {
		Queue<TreeNode> queue = new ArrayDeque<>();
		ArrayList<Integer> elems = new ArrayList<>();
		TreeNode node;

		queue.add(this.root);
		while (!queue.isEmpty()) {
			node = queue.poll();
			elems.add(node.element);
			if (node.hasLeft()) {
				queue.add(node.left);
			} if (node.hasRight()) {
				queue.add(node.right);
			}
		} return elems;
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "Empty tree.";
		}

		StringBuilder sb = new StringBuilder();
		inOrderPrettyString(root, new StringBuilder(), true, sb);
		return sb.toString();
	}

	private void inOrderPrettyString(TreeNode treeRoot,
			StringBuilder prefix, boolean isTail, StringBuilder sb) {

		if (treeRoot.right != null) {
			inOrderPrettyString(treeRoot.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
		}

		sb.append(prefix).append(isTail ? "└── " : "┌── ").append(treeRoot.element).append("\n");

		if (treeRoot.left != null) {
			inOrderPrettyString(treeRoot.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		}

	}


	private class TreeNode {

		private int element;
		private TreeNode left;
		private TreeNode right;
		private TreeNode parent;

		public TreeNode(int element, TreeNode parent, TreeNode left, TreeNode right) {
			this.element = element;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public TreeNode(int element) {
			this(element, null, null, null);
		}

		public boolean hasLeft() {
			return left != null;
		}

		public boolean hasRight() {
			return right != null;
		}
	   

		public void setLeft(TreeNode node) {
			left = node;
		}

		public void setRight(TreeNode node) {
			right = node;
		}
		
	}

}

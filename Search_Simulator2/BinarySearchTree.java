
public class BinarySearchTree {
	//A node in BST has left and right child
	static class Node {
		int index, key, rank;
		String link;
		Node left, right, parent;
		
		Node (int index, int totalscore, String url, int rank) {
			key = totalscore;
			this.index = index;
			link = url;
			this.rank = rank;
			left = right = parent = null;
		}
	}
	//Root of Binary Search Tree
	Node root;
	//Build a Binary Search Tree
	public BinarySearchTree () {
		root = null;
	}
	
	//Search Node by a given key in BST
	public static Node search(Node root, int key) {
		if (root == null || root.key == key)
			return root;
		if (key < root.key)
			return search(root.left, key);
		else return search(root.right, key);
	}
	
	//Go through and print every node by the order of score.
	public static void InorderTreeWalk(Node root) {
		if (root != null) {
			InorderTreeWalk(root.left);
			System.out.println(root.link + " Score: " + root.key + " Rank: " + root.rank);
			InorderTreeWalk(root.right);
		}
	}
	
	//The Iterator of Search method.
	public Node IterativeSearch(Node root,int key) {
		while(root != null && key != root.key) {
			if (key < root.key) root = root.left;
			else root = root.right;
		}
		return root;
	}
	
	//Return the node with Minimum Score in the tree.
	public static Node TreeMinimum(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	//Return the node with Maximum Score in the tree.
	public static Node TreeMaximum(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	//Return the successor of the Node x.
	public Node Successor(Node x) {
		if (x.right != null)
			return TreeMinimum(x.right);
		Node y = x.parent;
		while (y != null && x == y.right) {
			x = y;
			y = y.parent;
		}
		return y;
	}
	
	//Add Node z into Tree T.
	public static void Insert(BinarySearchTree T, Node z) {
		Node y = null;
		Node x = T.root;
		while (x != null) {
			y = x;
			if (z.key < x.key) x = x.left;
			else x = x.right;
		}
		z.parent = y;
		if (y == null)
			T.root = z; //tree T was empty
		else if (z.key < y.key)
			y.left = z;
		else y.right =z;
	}
	
	//Delete Node z in the Tree T.
	public static void Delete(BinarySearchTree T, Node z) {
		Node y;
		if (z.left == null) Transplant(T, z, z.right);
		else if (z.right == null) Transplant(T, z, z.left);
		else {
			y = TreeMinimum(z.right);
			if (y.parent != z) {
				Transplant(T, y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			Transplant(T, z, y);
			y.left = z.left;
			y.left.parent = y;
		}
	}
	
	public static void DeletebyRank(BinarySearchTree T, int rank) {
		while(T.root != null && rank != T.root.rank) {
			if (rank > T.root.rank) T.root = T.root.left;
			else T.root = T.root.right;
		}
		Delete(T, T.root);
	}
	
	//Transplant Node u and v in the Tree T.
	public static void Transplant(BinarySearchTree T, Node u, Node v) {
		if (u.parent == null) {
			T.root = v;
		}
		else if (u == u.parent.left) {
			u.parent.left = v;
		}
		else u.parent.right = v;
		if (v != null) v.parent = u.parent;
	}
	
	//Give rank to every node in Tree T.
	public static void giveRank(BinarySearchTree T) {
		Node max = BinarySearchTree.TreeMaximum(T.root);
        int rank = 1;
        while(max != null) {
        	max.rank = rank;
        	rank++;
        	if (max.left != null) max = BinarySearchTree.TreeMaximum(max.left);
        	else {
        		Node y = max.parent;
        		while (y != null && max == y.left) {
        			max = y;
        			y = y.parent;
        		}
        		max = y;
        	}
		}
	}
	
	//Test this class.
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.InorderTreeWalk(tree.root);
	}
}

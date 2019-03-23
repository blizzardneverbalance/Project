
public class RBTree{
	//A node in BST has left and right child
		static class Node {
			int index, key, rank;
			String link;
			String color;
			Node left, right, parent;
			//A node contains index, total score, URL, rank and color.
			Node (int index, int totalscore, String url, int rank, String c) {
				key = totalscore;
				this.index = index;
				link = url;
				this.rank = rank;
				left = right = parent = null;
				this.color = c;
			}
		}
		//Red Black Tree has nil node.
		static Node nil = new Node(-1, -1, null, -1, "BLACK");
		//Root of Red Black Tree
		Node root;
		//Build a Red Black Tree
		public RBTree() {
			this.root = nil;
		}
		
		//Search Node by a given key in RBTree
		public static Node search(Node root, int key) {
			if (root == null || root.key == key)
				return root;
			if (key < root.key)
				return search(root.left, key);
			else return search(root.right, key);
		}
		
		//Go through and print every node by the order of score.
		public static void InorderTreeWalk(Node root) {
			if (root != null && root != nil) {
				InorderTreeWalk(root.left);
				System.out.println(root.link + " Score: " + root.key + " Rank: " + root.rank + " Color: "+ root.color);
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
			while (node.left != null && node.left != nil) {
				node = node.left;
			}
			return node;
		}
		
		//Return the node with Maximum Score in the tree.
		public static Node TreeMaximum(Node node) {
			while (node.right != null && node.right != nil) {
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
		
		//Transplant node u and node v in Tree T.
		public static void Transplant(RBTree T, Node u, Node v) {
			if (u.parent == nil) T.root = v;
			else if (u == u.parent.left) u.parent.left = v;
			else u.parent.right = v;
			v.parent = u.parent;
		}
		
		//Rank every node in Tree T.
		public static void giveRank(RBTree T) {
			Node max = RBTree.TreeMaximum(T.root);
	        int rank = 1;
	        while(max != null && max != nil) {
	        	max.rank = rank;
	        	rank++;
	        	if (max.left != null && max.left != nil) max = RBTree.TreeMaximum(max.left);
	        	else {
	        		Node y = max.parent;
	        		while (y != null && y != nil && max == y.left) {
	        			max = y;
	        			y = y.parent;
	        		}
	        		max = y;
	        	}
			}
		}
		
		//left rotate node x in Red Black Tree T.
		public static void leftRotate(RBTree T, Node x) {
			Node y = x.right;
			x.right = y.left;
			if(y.left != T.nil) y.left.parent = x;
			y.parent = x.parent;
			if(x.parent == T.nil) T.root = y;
			else if(x == x.parent.left) x.parent.left = y;
			else x.parent.right = y;
			y.left = x;
			x.parent = y;
		}
		
		//right rotate node x in Red Black Tree T.
		public static void rightRotate(RBTree T, Node x) {
			Node y = x.left;
			x.left = y.right;
			if(y.right != T.nil) y.right.parent = x;
			y.parent = x.parent;
			if(x.parent == T.nil) T.root = y;
			else if(x == x.parent.right) x.parent.right = y;
			else x.parent.left = y;
			y.right = x;
			x.parent = y;
		}
		
		//Insert node z into Red Black Tree T.
		public static void Insert(RBTree T, Node z) {
			Node y = nil;
			Node x = T.root;
			while (x != nil) {
				y = x;
				if (z.key < x.key) x = x.left;
				else x = x.right;
			}
			z.parent = y;
			if (y == nil) T.root = z;
			else if (z.key < y.key) y.left = z;
			else y.right = z;
			z.left = nil;
			z.right = nil;
			z.color = "RED";
			insertFixup(T, z);
		}
		
		//Fix-up after Insertion.
		public static void insertFixup(RBTree T, Node z) {
			while (z.parent.color == "RED") {
				if (z.parent == z.parent.parent.left) {
					Node y = z.parent.parent.right;      //y now is z's uncle
					if (y.color == "RED") {
						z.parent.color = "BLACK";        //Case 1
						y.color = "BLACK";               //Case 1
						z.parent.parent.color = "RED";   //Case 1
						z = z.parent.parent;             //Case 1
					}
					else if (z == z.parent.right) {      //y.color is BLACK
						z = z.parent;                    //Case 2
						leftRotate(T, z);                //Case 2
					}
					else {
						z.parent.color = "BLACK";        //Case 3
						z.parent.parent.color = "RED";   //Case 3
						rightRotate(T, z.parent.parent); //Case 3
					}
				}
				else {
					Node y = z.parent.parent.left;       
					if (y.color == "RED") {				 
						z.parent.color = "BLACK";		 //Case 1
						y.color = "BLACK";				 //Case 1
						z.parent.parent.color = "RED";   //Case 1
						z = z.parent.parent;			 //Case 1
					}
					else if (z == z.parent.left) {		 //y.color is BLACK
						z = z.parent;					 //Case 2
						rightRotate(T, z);				 //Case 2
					}
					else {
						z.parent.color = "BLACK";		 //Case 3
						z.parent.parent.color = "RED";	 //Case 3
						leftRotate(T, z.parent.parent);	 //Case 3
					}
				}
			}
			T.root.color = "BLACK";
		}
		
		//Delete certain Node z in Tree T.
		public static void delete(RBTree T, Node z) {
			Node y = z;
			Node x;
			String yOriginColor = y.color;
			if (z.left == nil) {
				x = z.right;
				Transplant(T, z, z.right);
			}
			else if (z.right == nil) {
				x = z.left;
				Transplant(T, z, z.left);
			}
			else {
				y = TreeMinimum(z.right);
				yOriginColor = y.color;
				x = y.right;
				if (y.parent == z) {
					x.parent = y;
				}
				else {
					Transplant(T, y, y.right);
					y.right = z.right;
					y.right.parent = y;
				}
				Transplant(T, z, y);
				y.left = z.left;
				y.left.parent = y;
				y.color = z.color;
			}
			if (yOriginColor == "BLACK") deleteFixup(T, x);
		}
		
		//Fix-up after deletion.
		public static void deleteFixup(RBTree T, Node x) {
			while(x != T.root && x.color == "BLACK") {
				if (x == x.parent.left) {
					Node w = x.parent.right;
					if (w.color == "RED") {			//Case 1
						w.color = "BLACK";			//Case 1
						x.parent.color = "RED";		//Case 1
						leftRotate(T, x.parent);	//Case 1
						w = x.parent.right;			//Case 1
					}
					if (w.left.color == "BLACK" && w.right.color == "BLACK") { //x is still x.p.left
						w.color = "RED";			//Case 2
						x = x.parent;				//Case 2
					}
					else if (w.right.color == "BLACK") {
						w.left.color = "BLACK";		//Case 3
						w.color = "RED";			//Case 3
						rightRotate(T, w);			//Case 3
						w = x.parent.right;			//Case 3
					}
					else {
						w.color = x.parent.color;	//Case 4
						x.parent.color = "BLACK";	//Case 4
						w.right.color = "BLACK";	//Case 4
						leftRotate(T, x.parent);	//Case 4
						x = T.root;					//Case 4
					}
				}
				else {
					Node w = x.parent.left;
					if (w.color == "RED") {
						w.color = "BLACK";			//Case 1
						x.parent.color = "RED";		//Case 1
						rightRotate(T, x.parent);	//Case 1
						w = x.parent.left;			//Case 1
					}
					if (w.right.color == "BLACK" && w.left.color == "BLACK") {	//x is still x.p.left
						w.color = "RED";			//Case 2
						x = x.parent;				//Case 2
					}
					else if (w.left.color == "BLACK") {
						w.right.color = "BLACK";	//Case 3
						w.color = "RED";			//Case 3
						leftRotate(T, w);			//Case 3
						w = x.parent.left;			//Case 3
					}
					else {
						w.color = x.parent.color;	//Case 4
						x.parent.color = "BLACK";	//Case 4
						w.left.color = "BLACK";		//Case 4
						rightRotate(T, x.parent);	//Case 4
						x = T.root;					//Case 4
					}
				}
			}
			x.color = "BLACK";
		}
		
		//Short function to print a Node.
		public static void sop(Node z) {
			System.out.println(z.link + " Score: " + z.key + " Rank: " + z.rank + " Color: "+ z.color);
		}
		
		//Test insertion and deletion.
		public static void main(String[] args) {
			RBTree T = new RBTree();
			Node n12 = new Node(12, 112, "http://blogs.sjsu.edu/today/", 30, "RED");
			Node n13 = new Node(13, 144, "http://www.sjsu.edu/discover/research/index.html", 29, "RED");
			Node n7 = new Node(7, 153, "http://www.sjsu.edu/discover/administration/index.html", 28, "RED");
			Node n9 = new Node(9, 157, "http://www.sjsu.edu/calendars/", 27, "RED");
			Node n24 = new Node(24, 161, "http://www.sjsu.edu/advising/", 26, "RED");
			Node n5 = new Node(5, 171, "http://www.sjsu.edu/about_sjsu/", 25, "RED");
			Node n22 = new Node(22, 179, "http://www.sjsu.edu/about_sjsu/visiting/index.html", 30, "RED");
			Node n2 = new Node(2, 180, "http://www.sjsu.edu/#content", 30, "RED");
			Node n16 = new Node(16, 184, "http://one.sjsu.edu/", 30, "RED");
			Node n3 = new Node(3, 201, "http://www.sjsu.edu", 30, "RED");
			Node n8 = new Node(8, 216, "http://www.sjsuspartans.com/", 30, "RED");
			Node n14 = new Node(14, 227, "http://www.sjsu.edu/about_sjsu/visiting/", 30, "RED");
			Node n30 = new Node(30, 229, "http://www.sjsu.edu/students/safety/index.html", 30, "RED");
			Node n20 = new Node(20, 231, "http://www.sjsu.edu/housingoptions/index.html", 30, "RED");
			Node n1 = new Node(1, 234, "http://www.sjsu.edu/", 30, "RED");
			Node n26 = new Node(26, 249, "http://www.sjsu.edu/diversity/", 30, "RED");
			Node n29 = new Node(29, 266, "http://library.sjsu.edu", 30, "RED");
			Node n18 = new Node(18, 271, "http://www.sjsu.edu/calendars/index.html", 30, "RED");
			Node n17 = new Node(17, 288, "http://www.sjsu.edu/admissions/", 30, "RED");
			Node n28 = new Node(28, 297, "http://www.sjsu.edu/students/health/index.html", 30, "RED");
			Node n6 = new Node(6, 317, "http://www.sjsu.edu/academics/", 30, "RED");
			Node n19 = new Node(19, 319, "http://www.sjsu.edu/finances/index.html", 30, "RED");
			Node n27 = new Node(27, 329, "http://www.sjsu.edu/commencement/graduates/", 30, "RED");
			Node n23 = new Node(23, 359, "http://www.sjsu.edu/students/index.html", 30, "RED");
			Node n4 = new Node(4, 360, "http://www.sjsu.edu/discover/index.html", 30, "RED");
			Node n21 = new Node(21, 367, "http://www.sjsu.edu/students/new/index.html", 30, "RED");
			Node n15 = new Node(15, 380, "http://www.sjsu.edu/future_students/index.html", 30, "RED");
			Node n25 = new Node(25, 401, "http://www.sjsu.edu/students/campuslife/", 30, "RED");
			Node n10 = new Node(10, 408, "http://www.sjsu.edu/discover/initiatives/index.html", 30, "RED");
			Node n11 = new Node(11, 449, "http://library.sjsu.edu/", 30, "RED");
			Insert(T, n1);
			Insert(T, n2);
			Insert(T, n3);
			Insert(T, n4);
			Insert(T, n5);
			Insert(T, n6);
			Insert(T, n7);
			Insert(T, n8);
			Insert(T, n9);
			Insert(T, n10);
			Insert(T, n11);
			Insert(T, n12);
			Insert(T, n13);
			Insert(T, n14);
			Insert(T, n15);
			Insert(T, n16);
			Insert(T, n17);
			Insert(T, n18);
			Insert(T, n19);
			Insert(T, n20);
			Insert(T, n21);
			Insert(T, n22);
			Insert(T, n23);
			Insert(T, n24);
			Insert(T, n25);
			Insert(T, n26);
			Insert(T, n27);
			Insert(T, n28);
			Insert(T, n29);
			Insert(T, n30);
			giveRank(T);
			InorderTreeWalk(T.root);
			delete(T, n3);
			giveRank(T);
			InorderTreeWalk(T.root);
		}
} 

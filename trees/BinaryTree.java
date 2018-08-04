

public class BinaryTree<T extends Comparable<T>> {
	
	
	private Node<T> root;

	public class Node<E extends Comparable<E>> implements Comparable<E> {
		private Node<E> left;
		private Node<E> right;
		E data;

		public Node(E data) {
			this.data = data;
		}

		@Override
		public int compareTo(E data) {
			return this.data.compareTo(data);
		}
	}

	public void insert(T data) {
		root = insert(root, data);
	}

	private Node<T> insert(Node<T> node, T data) {
		if (node == null) {
			node = new Node<T>(data);
		}
		else if (node.compareTo(data) > 0) {
			node.left = insert(node.left, data);
		}
		else if (node.compareTo(data) < 0) {
			node.right = insert(node.right, data);
		}
		return node;
	}

	
	public void inorder() {
		System.out.print("Inorder traversal -> ");
		inorder(root);
		System.out.println("End Inorder traversal");
	} 
	
	private void inorder(Node<T> node) {
		if (node == null) {
			return;
		}
		inorder(node.left);
		System.out.print(node.data + " ");
		inorder(node.right);
	}
	
	public void preorder() {
		System.out.print("Preorder traversal -> ");
		preorder(root);
		System.out.println("End Preorder traversal");
	} 
	
	private void preorder(Node<T> node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preorder(node.left);
		preorder(node.right);
	}

	public void postorder() {
                System.out.print("Postorder traversal -> ");
                postorder(root);
                System.out.println("End Postorder traversal");
        }       
                
        private void postorder(Node<T> node) {
                if (node == null) {
                        return;
                }
                postorder(node.left);
                postorder(node.right);
                System.out.print(node.data + " ");
        }

	public int height() {
		return height(root);
	}
	
	private int height(Node<T> node) {
		if (node == null) {
			return 0;
		}
		int rheight = height(node.right);
		int lheight = height(node.left);
		return 1 + max(lheight, rheight);
	}
	
	private int max(int a, int b) {
		return a > b ? a : b;
	}
	/**
	* it will use height first and then only it will recursively call
	* for each level of the tree
	*/
	public void levelorder() {
		int height = height(root);
		System.out.println("Height of tree is -> " + height);
		System.out.println();
		System.out.print("Level Order traversal");
		for (int i=1; i<= height; i++) {
			System.out.print("Level " + i + "->");
			levelorder(root, i);
			System.out.println();
		}	
 		System.out.println("Level Order traversal");
	}

	/**
	* For level order traversal, we must know the height of the tree
	* though it is not a nice approach, but giving recursive approach
	* helps in solving other related problems
	*
	*
	*/
	private void levelorder(Node<T> node, int level) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			System.out.print(node.data + " ");
		}
		else if (level > 1) {
			levelorder(node.left, level - 1);
			levelorder(node.right, level - 1);
		}			
	}

	public void buildTree(T[] arr) {
		root = insertLevelRecursion(root, arr, 0, arr.length);

	}

	private Node<T> insertLevelRecursion(Node<T> node, T[] arr, int start, int size) {
		int left  = 2 * start + 1;
		int right = 2 * start + 2;

		if (left > size || right > size) {
			return node;
		}

		if (node == null) {
			node = new Node<T>(arr[start]);
		}

		if (node.left == null && node.right == null) {
			if (left < size) {
				node.left = new Node<T>(arr[left]);
			}
			if (right < size) {
				node.right = new Node<T>(arr[right]);
			}
		}
		insertLevelRecursion(node.left, arr, left, size); 
		insertLevelRecursion(node.right, arr, right, size); 
		return node;
	}

	public void inorderIterative() {
		inorderiterative(root);
	}

	private void inorderIterative(Node<T> node) {
	}	


	public static void main(String[] args) {
		BinaryTree<String> tree = new BinaryTree<String>();
		tree.inorder();
		tree.insert("3");
		tree.insert("1");
		tree.insert("5");
		tree.insert("4");
		tree.insert("6");
		tree.inorder();
		tree.preorder();
		tree.postorder();
		tree.levelorder();


		BinaryTree<String> tree2 = new BinaryTree<String>();
		String[] arr = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
		tree2.buildTree(arr);
		tree2.inorder();
		tree2.preorder();
		tree2.postorder();
		tree2.levelorder();
	}
}

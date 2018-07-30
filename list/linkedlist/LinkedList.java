

public class LinkedList<T> {

	private Node<T> head;

	public class Node<T> {
		Node<T> next;
		T data;
		public Node(T data) {
			this.data = data;
		}
	}
	
	public void addFirst(T data) {
		Node<T> node = new Node<>(data);
		if (head == null) {
			head = node;
			return;
		}
		node.next = head;
		head = node;
	}

	public void addLast(T data) {
		Node<T> node = new Node<>(data);
		Node<T> current = head;
		while(current != null && current.next != null) {
			current = current.next;
		}
		if (current == null) {
			head = node;
		}
		else {
			current.next = node;	
		}
	}

	/**
	 * Given a singly linked list, find middle of the linked list. 
	 * For example, if given linked list is 1->2->3->4->5 then output should be 3. 
	 * If there are even nodes, then there would be two middle nodes, 
	 * we need to print second middle element. 
	 * For example, if given linked list is 1->2->3->4->5->6 then output should be 4.
	 * 
	 * Output:
	 * Your function should return data of linked list.  If linked list is empty, then return -1.
	 */
	public T middle() {
		Node<T> middle = middle(head);
		if (middle != null) {
			return middle.data;
		}
		return null;
	}
	
	private Node<T> middle(Node<T> node) {
		Node<T> fastPointer = node;
		Node<T> slowPointer = node;
		
		while (slowPointer != null && fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}
		return slowPointer;	
		
	}


	public void print() {
		System.out.print("Current list -> ");  
		print(head);
	}
	
	private void print(Node<T> node) {
		Node<T> current = head;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();
	}

        public void printreverse() {
		printreverse(head);
		System.out.println();
	}

	private void printreverse(Node<T> node) {
		if (node == null) {
			return;
		}
		printreverse(node.next);
		System.out.print(node.data + " ");
	}

	public void reverse() {
		head = reverse(head);
	}

	public void reverseInBlocks(int blockSize) {
		head = reverseInBlocks(head, blockSize);
	} 

	private Node<T> reverseInBlocks(Node<T> node, int blockSize) {
		if (node == null || node.next == null) {
			return node;
		}
		Node<T> current = node;
		Node<T> prev = null;
		Node<T> next = null;
		int k = 1;
		while (current != null && k <= blockSize) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			k = k + 1;
		}
		if (next != null) {
			node.next = reverseInBlocks(next, blockSize);
		}
		return prev;
	}
	
	private Node<T> reverse(Node<T> node) {
		Node<T> prev = null;
		Node<T> next = null;
		Node<T> current = node;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}

	public void reverseRecursive() {
		head = reverseRecursive2(head);
	}

	private Node<T> reverseRecursive(Node<T> node) {
		if (node == null || node.next == null) {
			return node;
		}
		Node<T> prev = node;
		Node<T> next = node.next;
		Node<T> current = reverseRecursive(next);
		next.next = prev;
		prev.next = null;
		return current;
	}

	private Node<T> reverseRecursive2(Node<T> node) {
		if (node == null || node.next == null) {
			return node;
		}
		Node<T> current = reverseRecursive2(node.next);
		node.next.next = node;
		node.next = null;
		return current;
	}
	
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		list.print();
		System.out.println("Middle -> " + list.middle());
		list.addLast("First");
		list.print();
		System.out.println("Middle -> " + list.middle());
		list.addLast("Second");
		list.print();
		System.out.println("Middle -> " + list.middle());
		list.addLast("Third");
		list.print();
		System.out.println("Middle -> " + list.middle());
	        list.addLast("Fourth");
		list.print();
		System.out.println("Middle -> " + list.middle());
	}
}

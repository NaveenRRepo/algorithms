

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

	public void print() {
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
		list.reverse();
		list.print();
		list.addFirst("Third");
		list.reverse();
		list.reverse();
		list.addFirst("Second");
		list.addFirst("First");
		list.print();
		list.reverse();
		list.print();
		list.reverse();
		list.reverseRecursive();
		list.print();
		list.printreverse();
	}
}

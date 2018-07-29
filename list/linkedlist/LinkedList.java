

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

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		list.addFirst("Admin");
		list.print();
	}
}

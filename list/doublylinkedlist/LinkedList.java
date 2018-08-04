

public class LinkedList<T extends Comparable<T>> {
	
	private class Node<E extends Comparable<E>> implements Comparable<E> {
		Node<E> prev;
		Node<E> next;
		E data;

		public Node(E data) {
			this.data = data;
		}

		@Override
		public int compareTo(E data) {
			return this.data.compareTo(data);
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size = 0;


	public void addFirst(T data) {
		Node<T> newNode = new Node<>(data);
		size++;		
		if (head == null) {
			head = newNode;
			tail = head;
			return;
		}
		Node<T> node = head;
		// this is where we need to decide
		// if insert is at beginning or insert in the last
		// if insert is at last
		// then you need to insert using tail pointer instead of head and traversing through whole list
		newNode.next = head;
		head.prev = newNode;
		head = newNode;	
	}

	public void addLast(T data) {
		Node<T> newNode = new Node<>(data);
		size++;
		if (head == null) {
			head = newNode;
			tail = head;
			return;
		}
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
	}


	public void add(int pos, T data) throws IndexOutOfBoundsException {
		int len = size();
		if (pos < 0 || pos > len) {
			throw new IndexOutOfBoundsException();
		}

		int index = len - pos;
		Node<T> node = null;
		if (index < pos) {
			node = tail;
			while(index-- >= 1) {
				node = node.prev;	
			}
		}
		else {
			node = head;
			while (pos-- >= 1) {
				node = node.next;
			}
		}
		Node<T> newNode = new Node<T>(data);
		if (node == tail) {
			if (node == null) {
				head = newNode;
				tail = head;
			} 
			else {
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
			}
		}
		else if (node == head) {
			if (node == null) {
				node = newNode;
				head = node;
				tail = head;
			}
			else {
				newNode.next = head.next;
				if (head.next != null) {
					head.next.prev = newNode;
				}
				head.next = newNode;
				newNode.prev = head;
			}
		}
		else {
                	node.next.prev = newNode;
                	newNode.next = node.next;
                	newNode.prev = node;
                	node.next = newNode;
		}
		System.out.println("size");
		size = size + 1;
	}

	public void traverseForward() {
		Node<T> node = head;
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}

	public void traverseBackward() {
		Node<T> node = tail;
		while (node != null) {
			System.out.print(node.data + " ");
                        node = node.prev;
		}
		System.out.println();
	}

	public int size() {
		return size;
	}


	public void remove(int pos) {
		int len = size();
		System.out.println("length" + len);
                if (pos < 0 || pos > len) {
                        throw new IndexOutOfBoundsException();
                }
		if (len == 0) {
			return;
		}
		
		if (pos == 0) {
			System.out.println("Inside");
			if (head.next == null) {
				head.data = null;
				head.prev = null;
				head.next = null;
				head = null;
			}
			else {
                                head.data = null;
                                head.prev = null;
				head.next.prev = null;
                                head  = head.next;
			}
		}
		else if (pos == len - 1) {
			if (tail.prev == null) {
				tail.data = null;
				tail.prev = null;
				tail.next = null;
				tail = null;
			}
			else {
				tail.prev.next = null;
				tail.data = null;
				tail.prev = null;
				tail = tail.prev;
				
			}
		}
		else {
			int index = len - pos;
                	Node<T> node = null;
                	if (index < pos) {
                        	node = tail;
                        	while(index-- >= 1) {
                                	node = node.prev;
                        	}
                	}
                	else {
                        	node = head;
                        	while (pos-- >= 1) {
                                	node = node.next;
                        	}
                	}
                	if (node != null) {
                		if (node.prev != null) {
					node.prev.next = node.next;
					node.next.prev = node.prev;
					node.prev = null;
					node.next = null;
					node = null;
				}
                	}
		}
                size = size - 1;
	}

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("Hello");
		list.addLast("Last");
		list.addFirst("High");
		list.addLast("Happens");
		list.add(0, "Third");
		list.add(5, "Foourth");
		list.add(3, "tooyrth");
		list.add(6, "Fotttth");
		list.traverseForward();
		list.traverseBackward();
		LinkedList<String> list2 = new LinkedList<String>();
		list2.remove(0);
		list2.traverseForward();
		list2.add(0, "Sita");
		list2.traverseForward();
		list2.remove(0);
		list2.traverseForward();

		list2.remove(0);
                list2.traverseForward();
                list2.add(0, "Sita");
                list2.traverseForward();
                list2.remove(0);
                list2.traverseForward();

		list2.add(0, "Sita");
		list2.add(1, "Ram");
		list2.add(2, "Hero");
		list2.traverseForward();
		list2.remove(1);
		list2.traverseForward();
		list2.remove(1);
		list2.traverseForward();
		list2.remove(0);
		list2.traverseForward();
	}
}


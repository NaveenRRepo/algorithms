

public class LinkedList<T extends Comparable<T>> {

	private Node<T> head;

	public class Node<E extends Comparable<E>> implements Comparable<E> {
		Node<E> next;
		E data;

		public Node(E data) {
			this.data = data;
		}

		@Override
		public int compareTo(E thatData) {
			return this.data.compareTo(thatData);
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


	public int length() {
		return length(head);
	}
	
	private int length(Node<T> node) {
		int len = 0;
		Node<T> current = node;
		while(current != null) {
			current = current.next;
			len++;
		}
		return len;
	}
	
	/**
	* Using merge sort technique
	*/	
	public void sort() {
		head = sort(head);
	}

	private Node<T> sort(Node<T> node) {
		if (node == null || node.next == null) {
			return node;
		}
		Node<T> partitionNode = partition(node);
		Node<T> rightList = partitionNode.next;
		
		// cut the left list 
		partitionNode.next = null;
		
		Node<T> left  = sort(node);
		Node<T> right = sort(rightList);

		return merge(left, right);
	}

	private Node<T> merge(Node<T> a, Node<T> b) {
		 Node<T> result = null;
        	/* Base cases */
        	if (a == null) {
            		return b;
		}
        	if (b == null) {
            		return a;
		}
 
        	/* Pick either a or b, and recur */
        	if (a.compareTo(b.data) <= 0) 
        	{
           	 	result = a;
            		result.next = merge(a.next, b);
        	} 
        	else
        	{
            		result = b;
            		result.next = merge(a, b.next);
        	}
       	 	return result;
	}

	private Node<T> partition(Node<T> node) {
		if (node == null) { 
			return node;
		}
		
        	Node<T> fastptr = node.next;
        	Node<T> slowptr = node;
         
        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle node
        	while (fastptr != null)
        	{
            		fastptr = fastptr.next;
            		if(fastptr != null)
            			{
                			slowptr = slowptr.next;
                			fastptr = fastptr.next;
            		}
        	}
        	return slowptr;
	}		 	
	
	/**
	* Nth node from end 
	* 1 2 3 4 5 pos = 2 
	* Expected output should be 4 
	* so fix a current pointer in the beginning 
  	* go to the link where pos = 0; current will be pointing to 2
	* then start a pointer again from beginning
	* shift both of them till current reaches end of the list, the diff between 
	* both the pointers yeild a node from pos = 2 from end
	* one more catch is there in case there is no node from end at pos then it must 
	* return null
	* 
        */
	
	public T nodeFromLast(int pos) {
             	Node<T> node = nodeFromLast(head, pos);
             	if (node == null) {
                	return null;
             	}
        	return node.data;
     	}

     	private Node<T> nodeFromLast(Node<T> node, int pos) {
    		if (pos == 0) {
    	 		return null;
    	     	}
             	Node<T> current = node;
             	int count = 1;
             	while (current != null && count < pos) {
                	current = current.next;
                     	count = count + 1;
             	}
             	if (current == null) {
                	return null;
             	}
             	Node<T> expectedNode = node;
             	while (current != null && current.next != null) {
                	current = current.next;
                     	expectedNode = expectedNode.next;
             	}
             	return expectedNode;
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


	public void rotate(int numberOfNodes, boolean clock) {
		head = rotate(head, numberOfNodes, clock);
	}

	private Node<T> rotate(Node<T> node, int numberOfNodes, boolean clock) {
		if (node == null || numberOfNodes <=0) {
			return node;
		}
		Node<T> current = node;
		Node<T> last = null;
		Node<T> newHead = null;
		int len = 0;
		while (current != null) {
			last = current;
			current = current.next;
			len++;
		}
		int kthNode = numberOfNodes % len;
		if (kthNode == 0) {
			return node;
		}
		
		if (clock) {
			kthNode = len - kthNode;
		}
		current = node;
		while (kthNode > 1) {
			current = current.next;
			kthNode--;
		}
		last.next = node;
		newHead = current.next;
		current.next = null;
		return newHead;
	}

	public void findAndRemoveCycle() {
		findAndRemoveCycle(head);
	}

	private void findAndRemoveCycle(Node<T> node) {
		Node<T> slowPtr = head;
		Node<T> fastPtr = head;

		while (slowPtr != null && fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr == fastPtr) {
				break;
			}
		}
		if (slowPtr != fastPtr) {
			System.out.println("No cycle found in List");
			return;
		}
		System.out.println("Found a cycle at node " + slowPtr.data);

		slowPtr = node;
		while(slowPtr.next != fastPtr.next) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next;
		}
		fastPtr.next = null;
	}
		
	public void buildListToFormCycle() {
		this.head.next.next.next.next.next = this.head.next.next;
	} 

	public void mergeTwoSortedListsInPlace(LinkedList<T> thatList) {
		head = mergeTwoSortedListsInPlace(this.head, thatList.head);
	}      

	private Node<T> mergeTwoSortedListsInPlace(Node<T> node1, Node<T> node2) {
		if (node1 == null) {
			return node2;
		}
		if (node2 == null) {
			return node1;
		}

		Node<T> currentPtr1 = node1;
		Node<T> currentPtr2 = node2;
		Node<T> prev = null;
		while(true) {
			if (currentPtr1 == null || currentPtr2 == null) {
				break;
			}
			if (currentPtr1.compareTo(currentPtr2.data) >=0) {
				Node<T> next = currentPtr2.next;
				if (prev == null) {
					currentPtr2.next = currentPtr1;
					node1 = currentPtr2;
					prev = node1;
				}
				else {
					prev.next = currentPtr2;
					currentPtr2.next = currentPtr1;
					prev = currentPtr2;
				}
				currentPtr2 = next;
			}
			else {
				prev = currentPtr1;
				currentPtr1 = currentPtr1.next;
			}
		}

		if (currentPtr2 != null) {
			prev.next = currentPtr2;
		}

		return node1;
	}


	public void pairWiseSwap() {
		head = pairWiseSwap(head);
	}
	
	private Node<T> pairWiseSwap(Node<T> node) {
		if (node == null || node.next == null) {
			return node;
		}
		
		Node<T> prev = node;
		Node<T> current = node.next;

		//save head of the list
		node = current;
				
		Node<T> next = null;
		
		while (current != null) {
			next = current.next;
			current.next = prev;
			
			if (next == null || next.next == null) {
                		prev.next = next;
                		break;
            		}

			prev.next = next.next;
			prev = next;
			current = next.next;
		}
		
		return node;
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
		System.out.println("Expected Nth Node from last for pos 1 -> " +  list.nodeFromLast(1));
	        System.out.println("Rotating clockwise by 0");
		list.rotate(0,true);
		list.print();
	        System.out.println("Rotating clockwise by 1");
		list.rotate(1,true);
		list.print();
	        System.out.println("Rotating clockwise by 2");
		list.rotate(2,true);	
		list.print();
	        System.out.println("Rotating clockwise by 3");
		list.rotate(3,true);	
		list.print();
	        System.out.println("Rotating clockwise by 4");
		list.rotate(4,true);
		list.print();
	        System.out.println("Rotating clockwise by 5");
		list.rotate(5,true);	
		list.print();
	        System.out.println("Rotating anti clockwise by 0");
		list.rotate(0,false);
		list.print();
	        System.out.println("Rotating anti clockwise by 1");
		list.rotate(1,false);
		list.print();
	        System.out.println("Rotating anti clockwise by 2");
		list.rotate(2,false);	
		list.print();
	        System.out.println("Rotating anti clockwise by 3");
		list.rotate(3,false);	
		list.print();
	        System.out.println("Rotating anti clockwise by 4");
		list.rotate(4,false);
		list.print();
	        System.out.println("Rotating anti clockwise by 5");
		list.rotate(5,false);	
		list.print();
		System.out.println("Length of linked list -> " + list.length());
		list.sort();
		list.print();

		LinkedList<Integer> list2 = new LinkedList<Integer>();
	        list2.addLast(50);
		list2.addLast(20);
		list2.addLast(15);
		list2.addLast(4);
		list2.addLast(10);	
		list2.buildListToFormCycle();
		list2.findAndRemoveCycle();
		list2.print();


		LinkedList<Integer> sll2 = new LinkedList<Integer>();
		sll2.addLast(6);
		sll2.addLast(8);
		sll2.addLast(37);
		sll2.addLast(48);
		sll2.addLast(52);
	
		sll2.print();
	
		LinkedList<Integer> sll1 = new LinkedList<Integer>();
		sll1.addLast(11);
		sll1.addLast(12);
		sll1.addLast(33);
		sll1.addLast(44);
		sll1.print();
		System.out.println("After merge two sorted list");
		
		sll1.mergeTwoSortedListsInPlace(sll2);
		
		sll1.print();
		System.out.println("Operation pairwise");

		LinkedList<Integer> pwsll = new LinkedList<Integer>();
		pwsll.pairWiseSwap();
		pwsll.print();
		pwsll.addLast(20);
		pwsll.pairWiseSwap();
                pwsll.print();
		pwsll.addLast(30);
		pwsll.pairWiseSwap();
                pwsll.print();
		pwsll.addLast(40);
		pwsll.pairWiseSwap();
                pwsll.print();
		pwsll.addLast(50);
		pwsll.pairWiseSwap();
                pwsll.print();
	}
}

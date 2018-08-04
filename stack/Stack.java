
import java.util.Arrays;

public class Stack<T> {
	private T[] arr;
	private int size;
	private int capacity;
	private int top;

	public Stack() {
		this.capacity = 10;
		arr = (T[])new Object[this.capacity];
		this.size = 0;
	}

	public Stack(int initialCapacity) {
		this.capacity = initialCapacity;
		arr = (T[]) new Object[capacity];
		this.size = 0;
	}

	public void push(T data) {
		ensureCapacity();
		arr[top++]=data;
		size = size + 1;
	}

	public void ensureCapacity() {
		if (size == capacity) {
			capacity = capacity*2;
			arr = Arrays.copyOf(arr, capacity);
		}
	}

	public T pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("Underflow Exception");
		}
		T item = arr[top-1];
		size = size - 1;
		top--;
		return item;
	}

	public T peek() throws Exception {
		if (isEmpty()) {
			throw new Exception("Underflow Exception");
		}
		return arr[top-1];
	} 

	public boolean isEmpty() {
		return size == 0;
	}
	
	public static void main(String args[]) {
		Stack<String> stack = new Stack<String>();
		try {
			//System.out.println(stack.pop());
		stack.push("Ram");
		stack.push("Geeta");
		System.out.println("Stack peek " + stack.peek());
		System.out.println("Stack pop  " + stack.pop());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
		



public class HashMap<K,V> {

	private static final int DEFAULT_CAPACITY = 5;
	private int capacity;

	private Entry<K,V>[] table;

	@SuppressWarnings("unchecked")
	public HashMap(int capacity) {
		this.capacity = capacity;
		this.table = new Entry[capacity];
	}
	
	@SuppressWarnings("unchecked")
	public HashMap() {
		this.capacity = DEFAULT_CAPACITY;
		this.table = new Entry[capacity];
	}

	static class Entry<K,V> {
		K key;
		V value;
		Entry<K,V> next;
	
		public Entry(K Key, V value, Entry<K,V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public void put(K key, V value) {
		Entry<K,V> newEntry = mew Entry<>(key, value, null);
		if (key == null) {
			hash = 0;
			table[hash] = newEntry;
			return;
		}
		else {
			int hash = hash(key);
			if (table[hash] == null) {
				table[hash] = new Entry(key, value, null);
				return;
			}
			else {
				Entry<K,V> previous = null;
				Entry<K,V> current = table[hash];
				while(current != null) {
					if (current.key.equals(key)) {
						if (previous == null) {
							newEntry.next = current.next;				
							table[hash] = newEntry;
							return;
						}
						else {
							newEntry.next = current.next;
							previous.next = newEntry;
							return;
						}
					}
					previous = current;
					current = current.next;
				}
				previous.next = newEntry; 
			}
		}  
	}

	public V get(K key) {
		if (key == null) {
			if (table[hash] != null) {
				return table[hash];
			}
			else return null;
		}
		else {
			int hash = hash(key);
			Entry<K, V> current = table[hash];
			while(current != null) {
				if (key.equals(current.key)) {
					return current.value;
				}
				current = current.next;
			}					
		}
		return null;
	}

	public boolean remove(K key) {
	}
	
	public int hash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
}

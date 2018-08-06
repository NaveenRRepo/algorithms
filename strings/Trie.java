

public class Trie<T>{
	
	private TrieNode<T> root;

	private class TrieNode<E> {
		TrieNode<E>[] node = null;

		public TrieNode() {
			node = new TrieNode<E>[26];
		}
	}

	public Trie() {
		root = new TrieNode<T>();
	}

} 


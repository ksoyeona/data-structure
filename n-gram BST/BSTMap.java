package ucsdcse12pa7student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The Node class will hold all the contents for a BSTMap
 *
 * @param <K>
 * @param <V>
 */
class Node<K, V> {
	K key;
	V value;
	Node<K, V> left, right;

	public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
}

/**
 * This class is for use in the return of .entries()
 *
 * @param <K>
 * @param <V>
 */
class Entry<K, V> {
	K key;
	V value;

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

}

class EntryValueComparator implements Comparator<Entry<String, Integer>> {

	public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
		return e1.value - e2.value;
	}
}

/**
 *
 * @param <K>
 * @param <V>
 */
public class BSTMap<K, V> implements OrderedDefaultMap<K, V> {

	Node<K, V> root;
	int size;
	Comparator<K> comparator;
	V defaultValue;

	public BSTMap(Comparator<K> comparator, V defaultValue) {
		this.root = null;
		this.size = 0;
		this.comparator = comparator;
		this.defaultValue = defaultValue;
	}

	public BSTMap(Comparator<K> comparator) {
		this.root = null;
		this.size = 0;
		this.comparator = comparator;
		this.defaultValue = null;
	}

	/**
	 * Internal helper method for get
	 * 
	 * Recursive on the node argument. Throws NoSuchElementException if the key is
	 * not found in the tree and the defaultValue is null, returns the defaultValue
	 * if it is defined.
	 * 
	 * @param node
	 * @param k
	 * @return
	 */
	V get(Node<K, V> node, K k) {
		if (node == null) {
			if (this.defaultValue == null) {
				throw new NoSuchElementException(k.toString());
			} else {
				return this.defaultValue();
			}
		}
		if (this.comparator.compare(node.key, k) < 0) {
			return get(node.right, k);
		} else if (this.comparator.compare(node.key, k) > 0) {
			return get(node.left, k);
		} else {
			return node.value;
		}
	}

	@Override
	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("null key");
		}
		return this.get(this.root, key);
	}

	/**
	 * Internal helper method for set.
	 * 
	 * Recursive on the node argument. Returns a new node if it reaches a leaf,
	 * indicating that the key is not yet in the tree, otherwise it returns a
	 * reference to the Node that was passed in (after changing it).
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return
	 */
	Node<K, V> set(Node<K, V> node, K key, V value) {
		if (node == null) {
			this.size += 1;
			return new Node<K, V>(key, value, null, null);
		}
		if (this.comparator.compare(node.key, key) < 0) {
			node.right = this.set(node.right, key, value);
			return node;

		} else if (this.comparator.compare(node.key, key) > 0) {
			node.left = this.set(node.left, key, value);
			return node;

		} else {
			node.value = value;
			return node;
		}
	}

	@Override
	public void set(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		this.root = this.set(this.root, key, value);
	}

	/**
	 * Internal helper method for containsKey.
	 * 
	 * Recursive on node.
	 * 
	 * @param node
	 * @param k
	 * @return true if the node is in the tree, false otherwise
	 */
	boolean containsKey(Node<K, V> node, K k) {
		if (node == null) {
			return false;
		}
		if (this.comparator.compare(node.key, k) < 0) {
			return containsKey(node.right, k);
		} else if (this.comparator.compare(node.key, k) > 0) {
			return containsKey(node.left, k);
		} else {
			return true;
		}
	}

	@Override
	public boolean containsKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException("null key");
		}
		return this.containsKey(this.root, key);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public V defaultValue() {
		return this.defaultValue;
	}
	
	// for storing keys
	ArrayList<K> list = new ArrayList<K>();

	/**
	 * Internal helper method for keys()
	 * 
	 * Recursive on the node argument. Returns list containing all keys in the tree
	 * in ascending order
	 * 
	 * @param node
	 * @return List<K>
	 */
	private List<K> listKeys(Node<K, V> node) {
		if (node == null) {
			return list;
		}

		if (node.key != null) {
			listKeys(node.left);
			list.add(node.key);
			listKeys(node.right);
		}
		return list;
	}

	@Override
	public List<K> keys() {

		return listKeys(this.root);
	}

	// for storing values
	ArrayList<V> vlist = new ArrayList<V>();

	/**
	 * Internal helper method for values()
	 * 
	 * Recursive on the node argument. Returns list containing all values in the
	 * tree in in order according to their corresponding keys.
	 * 
	 * @param node
	 * @return List<K>
	 */
	private List<V> listValues(Node<K, V> node) {
		if (node == null) {
			return vlist;
		}
		if (node.value != null) {
			listValues(node.left);
			vlist.add(node.value);
			listValues(node.right);
		}
		return vlist;
	}

	@Override
	public List<V> values() {

		return listValues(this.root);
	}

	// for storing entries to return
	ArrayList<Entry<K, V>> ls = new ArrayList<Entry<K, V>>();

	/**
	 * Internal helper method for entries()
	 * 
	 * Recursive on the node argument. Returns list of all entries in order
	 * according to the keys
	 * 
	 * @param node
	 * @return List<Entry<K, V>>
	 */
	private List<Entry<K, V>> entries(Node<K, V> node) {
		if (node == null) {
			return ls;
		}
		entries(node.left);

		Entry<K, V> ent = new Entry<K, V>(node.key, node.value);
		ls.add(ent);

		entries(node.right);

		return ls;
	}

	@Override
	public List<Entry<K, V>> entries() {

		return entries(this.root);
	}

	// variable storing the largest key
	K largest;

	/**
	 * Internal helper method for floor(K key)
	 * 
	 * Recursive on the node argument. Returns the largest key in the tree smaller
	 * than query.
	 * 
	 * @param node
	 * @param k
	 * 
	 * @return K
	 */
	private K floorKey(Node<K, V> node, K k) {

		if (node == null) {
			return largest;
		}

		if (this.comparator.compare(node.key, k) < 0) {

			largest = node.key;

			return floorKey(node.right, k);
		}
		if (this.comparator.compare(node.key, k) > 0) {

			return floorKey(node.left, k);
		}
		return node.key;
	}

	@Override
	public K floor(K key) {

		return floorKey(this.root, key);
	}

	// variable storing the smallest key
	K smallest;

	/**
	 * Internal helper method for ceiling(K key)
	 * 
	 * Recursive on the node argument. Returns the smallest key in the tree larger
	 * than query.
	 * 
	 * @param node
	 * @param k
	 * 
	 * @return K
	 */
	private K ceilingKey(Node<K, V> node, K k) {

		if (node == null) {
			return smallest;
		}

		if (this.comparator.compare(node.key, k) < 0) {

			return ceilingKey(node.right, k);
		}
		if (this.comparator.compare(node.key, k) > 0) {

			smallest = node.key;
			return ceilingKey(node.left, k);
		}
		return node.key;
	}

	@Override
	public K ceiling(K key) {

		return ceilingKey(this.root, key);
	}

	// for storing keys in the range
	//ArrayList<K> keyList = new ArrayList<K>();

	/**
	 * Internal helper method for range(K low, K high)
	 * 
	 * Recursive on the node argument. Returns all the keys in the map that are
	 * between low (inclusive) and high (exclusive)
	 * 
	 * @param low
	 * @param high
	 * 
	 * @return List<K>
	 */
	private List<K> rangeKeys(Node<K, V> node, K low, K high, ArrayList<K> keyList) {
		
		
		if (node == null) {
			return keyList;
		}

		// when node's key is greater than low
		if (this.comparator.compare(node.key, low) > 0) {

			    rangeKeys(node.left, low, high,keyList);
		}
		
		if (this.comparator.compare(node.key, low) >= 0 && 
				this.comparator.compare(node.key, high) < 0) {

				keyList.add(node.key);
			
		}

		// when node's key is less than low
		if (this.comparator.compare(node.key, high) < 0) {

			rangeKeys(node.right, low, high,keyList);
		}

		return keyList;

	}

	@Override
	public List<K> range(K low, K high) {

		if (this.comparator.compare(high, low) < 0) {
			throw new IllegalArgumentException();
		}
		
		ArrayList<K> keyList = new ArrayList<K>();

		return rangeKeys(this.root, low, high, keyList );
	}

}

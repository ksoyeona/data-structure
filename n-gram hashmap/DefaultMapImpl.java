package cse12pa6student;

import java.util.*;

public class DefaultMapImpl<K, V> implements DefaultMap<K, V> {
	/*
	 * You will need to implement all the DefaultMap methods here. See the
	 * DefaultMap interface for their descriptions.
	 */

	Map<K, V> map;
	V defaultValue;

	public DefaultMapImpl(V defaultV) {

		this.map = new HashMap<K, V>();
		this.defaultValue = defaultV;
	}

	/*
	 * Sets key to hold value: - If key is not present, adds it (size increases) -
	 * If key is present, updates it (size doesn't increase)
	 * 
	 * Throws IllegalArgumentException if key is null (there is no such restriction
	 * for value)
	 * 
	 */
	public void set(K key, V value) {

		if (key == null) {
			throw new IllegalArgumentException();
		}

		else {
			this.map.put(key, value);
		}
	}

	/*
	 * Returns the value associated with key if it has been set
	 *
	 * If the defaultValue is null and the key is not found, throws
	 * NoSuchElementException
	 * 
	 * If key is not found and defaultValue is non-null, returns the defaultValue
	 * 
	 * Throws IllegalArgumentException if key is null
	 */
	public V get(K key) {

		if (key == null) {
			throw new IllegalArgumentException();
		}

		if (this.map.containsKey(key) == false && defaultValue != null) {
			return defaultValue;
		}

		if (this.map.containsKey(key) == false && defaultValue == null) {
			throw new NoSuchElementException();
		}

		else {
			return this.map.get(key);
		}
	}

	/*
	 * Returns true if the given key was set by set, false otherwise Throws
	 * IllegalArgumentException if key is null
	 */
	public boolean containsKey(K key) {

		if (this.map.containsKey(key) == true) {
			return true;
		}
		if (key == null) {
			throw new IllegalArgumentException();
		} else {
			return false;
		}
	}

	/*
	 * Returns the number of key/value pairs
	 */
	public int size() {

		return this.map.size();
	}

	/*
	 * Returns the default value for this map
	 */
	public V defaultValue() {

		return this.defaultValue;
	}

	/*
	 * Returns a list of all keys in any order
	 */
	public List<K> keys() {

		int x = this.map.keySet().size();

		List<K> aList = new ArrayList<K>(x);
		for (K key : this.map.keySet()) {
			aList.add(key);
		}
		return aList;
	}

	/*
	 * Returns a list of all values in any order
	 */
	public List<V> values() {

		List<V> vallist = new ArrayList<V>(this.map.size());
		for (K key : this.map.keySet()) {

			vallist.add(this.map.get(key));
		}
		return vallist;
	}
}

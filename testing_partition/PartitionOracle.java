package ucsdcse12pa5student;

import java.util.*;

public class PartitionOracle {

	/**
	 * Feel free to use this method to call partition. It catches any exceptions or
	 * errors and returns a definitely-invalid pivot (-1) to turn errors into
	 * potential failures. For example, in testPartition, you may use
	 * 
	 * runPartition(p, someArray, someLow, someHigh)
	 * 
	 * instead of
	 * 
	 * p.partition(someArray, someLow, someHigh)
	 * 
	 * @param p
	 * @param strs
	 * @param low
	 * @param high
	 * @return
	 */
	public static int runPartition(Partitioner p, String[] strs, int low, int high) {
		try {
			return p.partition(strs, low, high);
		} catch (Throwable t) {
			return -1;
		}
	}

	public static String isValidPartitionResult
	(String[] before, int low, int high, int pivot, String[] after) {

		if (before.length != after.length) {
			return new String("array lengths differ");
		}

		if (pivot >= after.length || pivot < 0) {
			return new String("pivot is out of bound");
		}

		for (int i = 0; i < after.length; i++) {
			// indicates whether array after contains everything from array before
			boolean contains = false;

			for (int j = 0; j < before.length; j++) {

				// check every element in array before is contained in array after
				if (before[i].equals(after[j])) {
					contains = true;
				}
			}

			if (contains == false) {
				return new String("elements missing");
			}

		}

		// Test whether no values at indices other than those from low (inclusive) to
		// high (exclusive)
		// changed their values
		for (int i = 0; i < low; i++) {

			if (before[i].compareTo(after[i]) != 0) {
				return new String("indices before low has changed their values");
			}
		}
		for (int i = high; i < before.length; i++) {

			if (before[i].compareTo(after[i]) != 0) {
				return new String("indices from high has changed their values");
			}
		}

		// check whether partition returns some pivot index between low (inclusive) and
		// high (exclusive)
		if (pivot < low || pivot >= high) {
			return new String("does not return pivot between low and high");
		}

		// checks whether all indices from low up to the pivot index the string
		// is smaller than or equal to (according to compareTo) the value at the pivot
		// index
		for (int i = low; i <= pivot; i++) {
			// if element is greater than pivot,
			if (after[i].compareTo(after[pivot]) > 0) {
				return new String("element before pivot is greater than pivot");
			}
		}

		// check all indices from the pivot index up to high - 1, the string is larger
		// than
		// or equal to (according to compareTo) the value at the pivot index
		for (int i = pivot; i < high; i++) {
			// if element is smaller than pivot,
			if (after[i].compareTo(after[pivot]) < 0) {
				return new String("element after pivot is less than pivot");
			}
		}
		return null;
	}

	public static String[] generateInput(int n) {

		String[] newStr = new String[n];

		for (int i = 0; i < n; i++) {

			Random r = new Random();
			int asciiForACapLetter = r.nextInt(26) + 65;
			newStr[i] = Character.toString((char) (asciiForACapLetter));
		}
		return newStr;
	}

	public static CounterExample findCounterExample(Partitioner p) {

		String[] str = generateInput(1000);
		String[] original = Arrays.copyOf(str, str.length);

		int low = 3;
		int high = 11;

		int pivotReturned = runPartition(p, str, low, high);

		if (isValidPartitionResult(original, low, high, pivotReturned, str) == null) {
			return null;
		}

		return new CounterExample(original, low, high, pivotReturned, str,
				isValidPartitionResult(original, low, high, pivotReturned, str));
	}

}

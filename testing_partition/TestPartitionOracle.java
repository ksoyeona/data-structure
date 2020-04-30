package ucsdcse12pa5student;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;


class DiscussionPartitioner implements Partitioner {

	public static void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}

	public int partition(String[] array, int low, int high) {
		if (low == high) {
			return low;
		}
		int pivotIndex = high - 1;
		String pivot = array[pivotIndex];
		int smallerBeforeIndex = low;
		int largerAfterIndex = high - 2;
		while (largerAfterIndex >= smallerBeforeIndex) {
			if (Integer.parseInt(array[smallerBeforeIndex]) > Integer.parseInt(pivot)) {
				swap(array, smallerBeforeIndex, largerAfterIndex);
				largerAfterIndex -= 1;
			} else {
				smallerBeforeIndex += 1;
			}
		}

		if (Integer.parseInt(array[smallerBeforeIndex]) < Integer.parseInt(pivot)) {
			swap(array, smallerBeforeIndex + 1, pivotIndex);
			System.out.println("array after: " + Arrays.toString(array));
			System.out.println("pivot moved to index " + (smallerBeforeIndex + 1) + "\n");
			return smallerBeforeIndex + 1;
		} else {
			swap(array, smallerBeforeIndex, pivotIndex);
			System.out.println("array after: " + Arrays.toString(array));
			System.out.println("pivot moved to index " + (smallerBeforeIndex) + "\n");
			return smallerBeforeIndex;
		}
	}
}

class PartitionFromClass implements Partitioner {
	public void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}

	public int partition(String[] array, int low, int high) {
		int pivotIndex = high - 1;
		String pivot = array[pivotIndex];
		int smallerBefore = low;
		int largerAfter = high - 2;
		while (smallerBefore < largerAfter) {
			if (array[smallerBefore].compareTo(pivot) < 0) {
				smallerBefore += 1;
			} else {
				swap(array, smallerBefore, largerAfter);
				largerAfter -= 1;
			}
		}
		if (array[smallerBefore].compareTo(pivot) < 0) {
			swap(array, smallerBefore + 1, pivotIndex);
			return smallerBefore + 1;
		} else {
			swap(array, smallerBefore, pivotIndex);
			return smallerBefore;
		}
	}

}

class CopyFirstElementPartition implements Partitioner {
	public int partition(String[] strs, int low, int high) {
		if (high - low < 1)
			return 0;
		for (int i = 0; i < strs.length; i += 1) {
			strs[i] = strs[0];
		}
		return 0;
	}
}

class PoorPartition implements Partitioner {

	public int partition(String[] strs, int low, int high) {

		return -1;
	}

}

public class TestPartitionOracle {

	@Test
	public void testClassPartition() {
		CounterExample ce = PartitionOracle.findCounterExample(new PartitionFromClass());
		System.out.println(ce);

		assertNull(ce);
	}

	@Test
	public void testPoorPartition() {
		CounterExample ce = PartitionOracle.findCounterExample(new PoorPartition());
		System.out.println(ce);

		assertNotNull(ce);
	}

	@Test
	public void testDiscussionPartitioner() {
		CounterExample ce = PartitionOracle.findCounterExample(new DiscussionPartitioner());
		System.out.println(ce);

		assertNull(ce);
	}

	@Test
	public void testCopyFirstElementPartition() {
		CounterExample ce = PartitionOracle.findCounterExample(new CopyFirstElementPartition());
		System.out.println(ce);

		assertNotNull(ce);
	}

	@Test
	public void testCorrectIsValidPartitionResult() {

		String[] bef = { "s", "e", "d", "a", "f", "g", "a", "a" };
		String[] aft = { "s", "a", "d", "e", "g", "f", "a", "a" };

		assertEquals(null, PartitionOracle.isValidPartitionResult(bef, 1, 6, 3, aft));

	}

	@Test
	public void testIncorrectIsValidPartitionResult() {

		String[] bef = { "Q", "A", "I", "X", "H", "S", "G", "Q" };
		String[] after = { "Q", "A", "I", "X", "H", "S", "G", "Q" };

		assertNotEquals(null, PartitionOracle.isValidPartitionResult(bef, 2, 5, 3, after));
	}

	@Test
	public void testPivot() {

		String[] bef = { "e", "d", "f", "a", "g", "a", "c", "b" };
		String[] aft = { "e", "d", "a", "f", "g", "a", "c", "b" };

		assertEquals(null, PartitionOracle.isValidPartitionResult(bef, 2, 5, 3, aft));

	}

	@Test
	public void testHighLowIsValidPartitionResult() {

		String[] bef = { "4", "0", "3", "1", "5", "6", "2" };
		String[] aft = { "4", "0", "1", "3", "5", "6", "2" };

		assertEquals(null, PartitionOracle.isValidPartitionResult(bef, 1, 5, 3, aft));

	}

	@Test
	public void testHigherThanHighChangedValue() {

		String[] bef = { "4", "3", "1", "5", "2" };
		String[] aft = { "1", "4", "3", "2", "5" };

		assertNotEquals(null, PartitionOracle.isValidPartitionResult(bef, 0, 4, 1, aft));

	}

	@Test
	public void testWrongLengthIsValidPartitionResult2() {

		String[] bef = { "4", "3", "1", "5", "2" };
		String[] aft = { "1", "2", "3", "4" };

		assertNotEquals(null, PartitionOracle.isValidPartitionResult(bef, 0, 4, 1, aft));

	}

	@Test
	public void testIndexOutOfBoundPivot() {

		String[] bef = { "4", "0", "3", "1", "5", "6", "2" };
		String[] aft = { "0", "1", "2", "3", "4", "5", "6" };

		assertNotEquals(null, PartitionOracle.isValidPartitionResult(bef, 0, 4, 10, aft));

	}
	
	@Test 
	public void testElemAfterPivotIsGreaterThanPivot() {
		
		String[] bef = {"S", "I", "M", "L", "V", "S", "G", "Y", "E", "L", "P","B", "H", "D", "H"};
		String[] aft = {"S", "I", "M", "L", "V", "S", "G", "Y", "E", "L", "P", "B", "H", "D","H"};
	
		assertEquals(null, PartitionOracle.isValidPartitionResult(bef, 2, 9, 7, aft));
	}
	
	@Test
	public void testGenerateInput() {

		String[] newStr = PartitionOracle.generateInput(13);

		assertEquals(13, newStr.length);
	}

}

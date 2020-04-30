package ucsdcse12pa5student;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		
		String[] str = PartitionOracle.generateInput(13000);
		String[] original = Arrays.copyOf(str, str.length);

		int low = 1000;
		int high =2000;
		
		long timeBefore = System.nanoTime();
		PartitionOracle.isValidPartitionResult(original, low, high, 5, str);
		long duration = (System.nanoTime() - timeBefore);
		
		System.out.println(duration);
	}
}

package cse12pa4student;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		String[] toRun = { "F","E","D","C","A", "B" };

		Measure.callDummies();

		List<Measurement> toConvert = Measure.measure(toRun, 10, 25);

		String s = Measure.measurementsToCSV(toConvert);

		System.out.println(s);

	}
}

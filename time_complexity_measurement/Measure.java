package cse12pa4student;

import java.util.ArrayList;
import java.util.List;
import static cse12pa4mysteries.Mysteries.*;

public class Measure {

	public static List<Measurement> measure(String[] toRun, int startN, int stopN) {

		List<Measurement> finalList = new ArrayList<Measurement>();

		for (int j = 0; j < toRun.length; j++) {

			for (int i = startN; i <= stopN; i++) {

				long timeBefore = System.nanoTime();

				if (toRun[j].equals("A")) {
					mysteryA(i);

				} else if (toRun[j].equals("B")) {
					mysteryB(i);

				} else if (toRun[j].equals("C")) {
					mysteryC(i);

				} else if (toRun[j].equals("D")) {
					mysteryD(i);

				} else if (toRun[j].equals("E")) {
					mysteryE(i);

				} else if (toRun[j].equals("F")) {
					mysteryF(i);

				}

				long duration = (System.nanoTime() - timeBefore);

				Measurement m = new Measurement(toRun[j], i, duration);

				finalList.add(m);
			}
		}

		return finalList;
	}

	public static String measurementsToCSV(List<Measurement> toConvert) {
	
		StringBuilder str = new StringBuilder("name,n,nanoseconds" + System.lineSeparator());

		for (int i = 0; i < toConvert.size(); i++) {

			String letter = toConvert.get(i).name;

			int value = toConvert.get(i).valueOfN;

			long time = toConvert.get(i).nanosecondsToRun;

			str = str
					.append(letter + "," + String.valueOf(value) + "," 
			+ String.valueOf(time) + System.lineSeparator());
		}

		return str.toString();
	}

	/* Add any helper methods you find useful */
	static void callDummies() {

		String[] dummy = {};
		List<Measurement> list = measure(dummy, 0, 0);
		measurementsToCSV(list);

	}

}

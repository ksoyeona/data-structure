package ucsdcse12pa7student;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Loader {
	public static String FILES_PATH = "./data";

	public static OrderedDefaultMap<Integer, OrderedDefaultMap<String, Integer>> generateDatabase(Path path)
			throws IOException {

		OrderedDefaultMap<String, Integer> inMap = new BSTMap<>(String::compareTo, 0);

		// create DefaultMapImpl to return
		OrderedDefaultMap<Integer, OrderedDefaultMap<String, Integer>> outerMap = 
				new BSTMap<>(Integer::compareTo,inMap);

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.txt")) {
			for (Path pat : stream) {

				String txt = "";

				String filename = pat.toString();

				int year = Integer.parseInt
						(filename.substring(filename.length() - 8, filename.length() - 4));

				for (String line : Files.readAllLines(pat)) {
					txt += line + " ";
				}

				String[] splittedStr = txt.toLowerCase().split(" ");

				ArrayList<String> filtered = filterString(splittedStr);

				// if year does not exist as a key in the outer map, add it as a key
				if (outerMap.containsKey(year) == false) {

					OrderedDefaultMap<String, Integer> innerMap = new BSTMap<>(String::compareTo,0);

					outerMap.set(year, innerMap);
				}

				// *********FOR 1GRAM***************
				for (int i = 0; i < filtered.size(); i++) {
					// if innerMap does not contain the gram string as key, set the string as the
					if (outerMap.get(year).containsKey(filtered.get(i)) == false) {

						outerMap.get(year).set(filtered.get(i), 1);
					}

					// if innerMap contains the gram string as key, increment the word count only
					else {
						int freq = outerMap.get(year).get(filtered.get(i));
						freq++;
						outerMap.get(year).set(filtered.get(i), freq);
					}
				}

				// *******FOR 2GRAM***************
				for (int t = 0; t < filtered.size() - 1; t++) {

					String twoW = new String(filtered.get(t) + " " + filtered.get(t + 1));

					// if innerMap does not contain the gram string as key, set the string as the
					// key
					if (outerMap.get(year).containsKey(twoW) == false) {

						outerMap.get(year).set(twoW, 1);
					}

					// if innerMap contains the gram string as key, increment the word count only
					else {
						int freq = outerMap.get(year).get(twoW);
						freq++;
						outerMap.get(year).set(twoW, freq);
					}
				}
				// *******FOR 3GRAM***************
				for (int s = 0; s < filtered.size() - 2; s++) {

					String threeW = new String(filtered.get(s) + " " + filtered.get(s + 1) + 
							" " + filtered.get(s + 2));

					// if innerMap does not contain the gram string as key, set the string as the
					// key
					if (outerMap.get(year).containsKey(threeW) == false) {

						outerMap.get(year).set(threeW, 1);
					}

					// if innerMap contains the gram string as key, increment the word count only
					else {
						int freq = outerMap.get(year).get(threeW);
						freq++;
						outerMap.get(year).set(threeW, freq);
					}
				}

			}
		} catch (IOException | DirectoryIteratorException x) {

			System.err.println(x);
		}
		return outerMap;
	}

	/*
	 * This helper method returns ArrayList of Strings after removing string that
	 * does not contain lowercase alphabet from the parameter String array.
	 * 
	 * @param string array to be filtered
	 * 
	 * @return String ArrayList that does not contain elements without lowercase
	 * alphabet
	 */
	public static ArrayList<String> filterString(String[] strlist) {

		ArrayList<String> al = new ArrayList<>(Arrays.asList(strlist));

		for (int i = 0; i < al.size(); i++) {

			boolean containsAlphabet = false;

			String word = al.get(i);

			for (int x = 0; x < word.length(); x++) {
				int ascii = (int) (word.charAt(x));

				if (ascii >= 97 && ascii <= 122) {

					containsAlphabet = true;
					break;
				}
				if (containsAlphabet == false) {
					al.remove(i);
					i = i - 1;
					break;
				}
			}
		}
		return al;
	}

	public static OrderedDefaultMap<String, Integer> rangeSearch(
			OrderedDefaultMap<Integer, OrderedDefaultMap<String, Integer>> db, String low, String high) {

		OrderedDefaultMap<String, Integer> beforeMap = new BSTMap<>(String::compareTo, 0);

		OrderedDefaultMap<String, Integer> afterMap = new BSTMap<>(String::compareTo, 0);

		for (int year : db.keys()) {

			for (String grams : db.get(year).keys()) {

				if (grams.compareTo(low) >= 0 && grams.compareTo(high) < 0) {

					if (beforeMap.containsKey(grams) == false) {
						beforeMap.set(grams, 0);
					}

					if (beforeMap.containsKey(grams) == true) {
						int freq = beforeMap.get(grams);
						freq = freq + db.get(year).get(grams);
						beforeMap.set(grams, freq);
					}
				}
			}
		}
		List<String> al = beforeMap.range(low, high);

		for (int r = 0; r < al.size(); r++) {

			if (beforeMap.containsKey(al.get(r)) == true) {

				afterMap.set(al.get(r), beforeMap.get(al.get(r)));
			}
		}

		return beforeMap;

	}

	public static List<Entry<String, Integer>> topN(OrderedDefaultMap<Integer, OrderedDefaultMap<String, Integer>> db,
			String low, String high, int n) {

		OrderedDefaultMap<String, Integer> ranged = rangeSearch(db, low, high);

		List<Entry<String, Integer>> ls = ranged.entries();

		EntryValueComparator vc = new EntryValueComparator();

		Collections.sort(ls, Collections.reverseOrder(vc));

		ArrayList<Entry<String, Integer>> entList = new ArrayList<Entry<String, Integer>>();

		for (int i = 0; i < ls.size(); i++) {

			Entry<String, Integer> ent=new Entry<String,Integer>(ls.get(i).key, ls.get(i).value);
			entList.add(ent);
		}

		if (entList.size() < n) {
			return entList;
		}
		return entList.subList(0, n);
	}

	public static void main(String[] args) throws IOException {
		
		Path path = FileSystems.getDefault().getPath(Loader.FILES_PATH);

		OrderedDefaultMap<Integer,OrderedDefaultMap<String, Integer>> db=generateDatabase(path);

		Scanner in = new Scanner(System.in);

		System.out.print("Enter query: ");

		String query = in.nextLine();

		in.close();

		String[] splittedStr = query.toLowerCase().split("--");

		long before = System.nanoTime();

		List<Entry<String, Integer>> list = topN(db, splittedStr[0], splittedStr[1], 10);

		long after = System.nanoTime() - before;

		System.out.println(after);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).key + ": " + list.get(i).value);
		}
	}
}

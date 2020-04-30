package cse12pa6student;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Loader {

	public static String FILES_PATH = "./data/";

	public static DefaultMap<Integer, DefaultMap<String, Integer>> 
	generateDatabase(Path path) throws IOException {

		// create DefaultMapImpl to return
		DefaultMap<Integer, DefaultMap<String, Integer>> outerMap = new DefaultMapImpl(0);

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.txt")) {
			for (Path pat : stream) {

				String txt = "";

				// organize ArrayList of words by year in filename
				// ex. filename = "w_acad_1990.txt"
				String filename = pat.toString();

				// get year from the filename
				// ex. year = 1990
				int year = Integer.parseInt
						(filename.substring(filename.length() - 8, filename.length() - 4));

				for (String line : Files.readAllLines(pat)) {
					txt += line + " ";
				}

				// split words by space and add each word to an arraylist
				// ##4000357 On 5 April 1990 , the New York
				String[] splittedStr = txt.toLowerCase().split(" ");

				// remove elements that does not contain lowercase alphabet
				// On April the New York
				ArrayList<String> filtered = filterString(splittedStr);

				// if year does not exist as a key in the outer map, add it as a key
				if (outerMap.containsKey(year) == false) {

					DefaultMap<String, Integer> innerMap = new DefaultMapImpl<String, Integer>(0);

					outerMap.set(year, innerMap);
				}

				// *********FOR 1GRAM***************
				for (int i = 0; i < filtered.size(); i++) {
					// if innerMap does not contain the gram string as key, set the string as the
					// key FOR 1GRAM
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

					String threeW = new String
							(filtered.get(s) +" "+filtered.get(s + 1)+" "+filtered.get(s + 2));

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

		// i indicate index of words in string array
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

	public static Graph 
	makeGraph(DefaultMap<Integer, DefaultMap<String, Integer>> db, String[] query) {

		// make title
		String title = "";
		for (int i = 0; i < query.length; i++) {
			if (i != query.length - 1) {
				title = title.concat(query[i] + ";" + " ");
			}
			if (i == query.length - 1) {
				title = title.concat(query[i]);
			}
		}

		// construct List<Integer> years
		ArrayList<Integer> yrList = new ArrayList<Integer>();

		for (int j : db.keys()) {
			yrList.add(j);
		}

		Collections.sort(yrList);

		// construct input DefaultMap<String,List<Integer>> for graph
		DefaultMap<String, List<Integer>> data = new DefaultMapImpl(0);

		for (int q = 0; q < query.length; q++) {

			ArrayList<Integer> freqList = new ArrayList<Integer>();

			for (int year : yrList) {

				if (db.get(year).containsKey(query[q]) == true) {

					freqList.add(db.get(year).get(query[q]));

				}
				if (db.get(year).containsKey(query[q]) == false) {

					freqList.add(0);
				}
			}

			data.set(query[q], freqList);
		}

		Graph graph = new Graph(title, yrList, data);

		return graph;
	}

	public static void main(String[] args) throws IOException {

		Path path = FileSystems.getDefault().getPath(FILES_PATH);

		DefaultMap<Integer, DefaultMap<String, Integer>> outerMap = generateDatabase(path);

		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.print("Enter query: ");

			String query = in.nextLine();

			String[] splittedStr = query.toLowerCase().split(";");

			Graph g = makeGraph(outerMap, splittedStr);

			g.showChart();
		}

	}

}

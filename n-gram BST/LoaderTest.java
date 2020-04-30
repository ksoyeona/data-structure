package ucsdcse12pa7student;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.*;

public class LoaderTest {

	// These tests are commented out to avoid compile errors at the start, but
	// you're welcome to use them as you get things implemented.

	// You should definitely implement more tests than these, but they are a great
	// baseline to make sure everything is working well.

	@Test
	public void testTwoFilesDifferingYearsRangeQuery() {

		OrderedDefaultMap<Integer, OrderedDefaultMap<String, Integer>> theBigTree = new BSTMap<>(Integer::compareTo);

		OrderedDefaultMap<String, Integer> map1 = new BSTMap<String, Integer>(String::compareTo);
		map1.set("big words", 3);
		map1.set("big words are", 1);
		map1.set("irrelevant", 1);

		OrderedDefaultMap<String, Integer> map2 = new BSTMap<String, Integer>(String::compareTo);
		map2.set("big words", 2);
		map2.set("big words in", 1);
		map2.set("big words like", 1);
		map2.set("bigwordslike", 1);

		theBigTree.set(1990, map1);
		theBigTree.set(1991, map2);

		OrderedDefaultMap<String, Integer> rq = Loader.rangeSearch(theBigTree, "big words", "big words!");

		assertEquals(Arrays.asList(new String[] { "big words", "big words are", "big words in", "big words like" }),
				rq.keys());
		assertEquals(Arrays.asList(new Integer[] { 5, 1, 1, 1 }), rq.values());
	}

	@Test
	public void testSingleFile() throws IOException {
		Path path = FileSystems.getDefault().getPath("./test-data/single-file/");
		OrderedDefaultMap<Integer, OrderedDefaultMap<String, Integer>> theBigTree = Loader.generateDatabase(path);

		assertEquals("After loading a single file, should have one key", 1, theBigTree.size());

		List<Integer> keys = theBigTree.keys();
		assertEquals("After loading a single file from 2001, there should be 2001 as the only key", 2001,
				(int) keys.get(0));

		OrderedDefaultMap<String, Integer> year2001 = theBigTree.get(2001);
		assertEquals("'big words' should appear twice", 2, (int) year2001.get("big words"));
		assertEquals("'words' should appear 4 times", 4, (int) year2001.get("words"));
		assertEquals("'punctuation or words' should appear once", 1, (int) year2001.get("punctuation or words"));
		assertEquals("'words like logarithmic' should appear once (end-of-document example)", 1,
				(int) year2001.get("punctuation or words"));
	}

	@Test
	public void testTwoFilesSameYear() throws IOException {
		Path path = FileSystems.getDefault().getPath("./test-data/two-files-same-year/");
		OrderedDefaultMap<Integer, OrderedDefaultMap<String, Integer>> theBigTree = Loader.generateDatabase(path);

		assertEquals("After loading two files with the same year, should have one key", 1, theBigTree.size());

		List<Integer> keys = theBigTree.keys();
		assertEquals("After loading two files from 2001, there should be 2001 as the only key", 2001,
				(int) keys.get(0));

		OrderedDefaultMap<String, Integer> year2001 = theBigTree.get(2001);
		assertEquals("'big words' should appear 4 times", 4, (int) year2001.get("big words"));
		assertEquals("'words' should appear 7 times", 7, (int) year2001.get("words"));
		assertEquals("'punctuation or words' should appear once", 1, (int) year2001.get("punctuation or words"));
		assertEquals("'words like logarithmic' should appear once (end-of-document example)", 1,
				(int) year2001.get("punctuation or words"));
		assertEquals("'punctuation' should appear twice", 2, (int) year2001.get("punctuation"));
		assertEquals("'accumulated' should appear once", 1, (int) year2001.get("accumulated"));
	}

}

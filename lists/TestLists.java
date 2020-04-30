package cse12pa2student;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestLists {

	public static Collection<Object[]> LISTNUMS = Arrays.asList(new Object[][] { { "Linked" }, { "Array" } });
	private String listType;

	public TestLists(String listType) {
		super();
		this.listType = listType;
	}

	@Parameterized.Parameters(name = "{0}List")
	public static Collection<Object[]> bags() {
		return LISTNUMS;
	}

	private StringList makeList(String[] contents) {
		switch (this.listType) {
		case "Linked":
			return new LinkedSL(contents);
		case "Array":
			return new ArraySL(contents);
		}
		return null;
	}



	@Test
	public void testToArray() {
		String[] input = { "ad", "b/", "c3", "d@", "e", "f", "gVVV" };
		StringList s = makeList(input);
		assertArrayEquals(input, s.toArray());
	}

	@Test
	public void testToArray2() {
		String[] input = { "ad", "b/", "c3", "d@", "e", "f", "gVVV" };
		StringList s = makeList(input);
		assertEquals(false, s.toArray()[2] == "g");
	}

	@Test
	public void testArraySize() {
		String[] input = { "a", "b", "c", "d", "e", "f", "g" };
		StringList s = makeList(input);
		assertEquals(7, s.toArray().length);
	}

	@Test
	public void testNullArraySize() {
		String[] input = {};
		StringList s = makeList(input);
		assertEquals(0, s.toArray().length);
	}

	@Test
	public void testEmptyToArray() {
		String[] input = {};
		StringList s = makeList(input);
		assertArrayEquals(input, s.toArray());
	}

	@Test
	public void testIsEmpty() {
		String[] input = { "a", "b", "c" };
		StringList s = makeList(input);

		assertEquals(false, s.isEmpty());
	}

	@Test
	public void testIsEmpty2() {
		String[] input = {};
		StringList s = makeList(input);

		assertEquals(true, s.isEmpty());
	}

	@Test
	public void testTransformAllforArraySL() {

		String[] input = { "A", "b", "C", "Dr" };
		String[] expected = { "a", "b", "c", "dr" };

		ArraySL s = new ArraySL(input);
		s.transformAll(new LowerCaseTransformer());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void testTransformAllforLinkedSL() {

		String[] input = { "Aa", "b", "C" };
		String[] expected = { "aa", "b", "c" };

		LinkedSL s = new LinkedSL(input);
		s.transformAll(new LowerCaseTransformer());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testHeartTransformAllforLinkedSL() {

		String[] input = { "A", "B", "c" };
		String[] expected = { "A<3", "B<3", "c<3" };

		LinkedSL s = new LinkedSL(input);
		s.transformAll(new heartTransformer());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testHeartTransformAllforArraySL() {

		String[] input = { "A", "B", "C" };
		String[] expected = { "A<3", "B<3", "C<3" };

		ArraySL s = new ArraySL(input);
		s.transformAll(new heartTransformer());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testHeartTransformAllforArraySL2() {

		String[] input = {};
		String[] expected = {};

		ArraySL s = new ArraySL(input);
		s.transformAll(new heartTransformer());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testHeartTransformAllforLinkedSL2() {

		String[] input = {};
		String[] expected = {};

		LinkedSL s = new LinkedSL(input);
		s.transformAll(new heartTransformer());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void uppercaseTransformAllforArraySL() {

		String[] input = { "zA", "b", "c" };
		String[] expected = { "ZA", "B", "C" };

		ArraySL s = new ArraySL(input);
		s.transformAll(new UpperCaseTransformer());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void uppercaseTransformAllforArraySL1() {

		String[] input = { "zA", ":)", "c", " " };
		String[] expected = { "ZA", ":)", "C", " " };

		ArraySL s = new ArraySL(input);
		s.transformAll(new UpperCaseTransformer());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void transformNullforArraySL() {

		String[] input = {};

		ArraySL s = new ArraySL(input);
		s.transformAll(new UpperCaseTransformer());

		assertEquals(0, s.toArray().length);

	}

	@Test
	public void testChooseAllforArraySL() {

		String[] input = { "I", "longerword", "ko", "love", "care" };
		String[] expected = { "longerword" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new LongWordChooser());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void testChooseAllforLinkedSL() {

		String[] input = { "short", "longerword", "ee", "ff", "loveyou" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new LongWordChooser());

		String[] expected = { "longerword", "loveyou" };

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testChooseAll2forArraySL() {

		String[] input = { "short", "hahahaha", "I", "longerword", "you", "michael", "love", "abcdefg" };
		String[] expected = { "short", "I", "you", "love" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new ShortWordChooser());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void testChooseAll2forLinkedSL() {

		String[] input = { "short", "hahahaha", "I", "longerword", "you", "michael", "love" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new ShortWordChooser());

		String[] expected = { "short", "I", "you", "love" };

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testChooseAll3forArraySL() {

		String[] input = { "apple", "aba", "orange", "ahi" };
		String[] expected = { "apple", "aba", "ahi" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void testLengthChooseAllforArraySL() {

		String[] input = { "apple", "apple cider", "orange", "ahi" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertEquals(3, s.toArray().length);
	}

	@Test
	public void testLengthChooseAllforLinkedSL() {

		String[] input = { "apple", "apple cider", "orange", "ahi" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertEquals(3, s.toArray().length);
	}

	@Test
	public void testChooseAll3forLinkedSL() {

		String[] input = { "apple", "apple cider", "orange", "ahi" };
		String[] expected = { "apple", "apple cider", "ahi" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testChooseAllforArraySL4() {

		String[] input = { "apple", "apple cider", "ahi" };
		String[] expected = { "apple", "apple cider", "ahi" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testChooseOneRemoveItemForArraySL() {

		String[] input = { "apple", "b" };
		String[] expected = { "apple" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testLinkedSLConstructorSize() {

		String[] input = { "apple", "b" };
		LinkedSL sl = new LinkedSL(input);

		assertEquals(2, sl.size);

	}

	@Test
	public void testLinkedSLConstructorSize2() {

		String[] input = { "a", "b", "c", "d", "e", "f", "g" };
		LinkedSL sl = new LinkedSL(input);

		assertEquals(7, sl.size);

	}

	@Test
	public void testLinkedSLConstructorSize3() {

		String[] input = { "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d",
				"e", "f", "g" };

		LinkedSL sl = new LinkedSL(input);

		assertEquals(input.length, sl.size);

	}

	@Test
	public void testLinkedSLConstructorSize1() {

		String[] input = {};
		LinkedSL sl = new LinkedSL(input);

		assertEquals(0, sl.size);

	}

	@Test
	public void testLinkedSLConstructorValue2() {

		String[] input = { "a", "b", "c", "d" };
		LinkedSL sl = new LinkedSL(input);

		assertEquals("b", sl.front.next.next.value);

	}

	@Test
	public void testLinkedSLConstructorValue3() {

		String[] input = { "a", "b" };
		LinkedSL sl = new LinkedSL(input);

		assertEquals(null, sl.front.next.next.next);

	}

	@Test
	public void testLinkedSLConstructorNullNode() {

		String[] input = {};
		LinkedSL sl = new LinkedSL(input);

		assertEquals(null, sl.front.next);
	}

	@Test
	public void testLinkedSLConstructorValue() {

		String[] input = { "apple", "b" };
		LinkedSL sl = new LinkedSL(input);

		assertEquals("apple", sl.front.next.value);

	}

	@Test
	public void testLinkedSLChooseEmpty() {

		String[] input = {};
		String[] expected = {};

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testChooseOneRemoveItemForLinkedSL() {

		String[] input = { "ahi" };
		String[] expected = { "ahi" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void testChooseNoItemForArraySL() {

		String[] input = { "hi", "b", "c", "d", "e" };
		String[] expected = {};

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseToKeepNoneForLinkedSL() {

		String[] input = { "sdfg", "wert", "walden", "thorou", "pond" };
		String[] expected = {};

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseToKeepNoneForArraySL() {

		String[] input = { "ja", "hi" };
		String[] expected = {};

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseToKeepEverythingForArraySL() {

		String[] input = { "aa", "apple", "aha", "as", "ago" };
		String[] expected = { "aa", "apple", "aha", "as", "ago" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseTwoKeepSecondLinkedSL() {

		String[] input = { "ja", "hi", "apple" };
		String[] expected = { "apple" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseTwoKeepSecondArraySL() {

		String[] input = { "ja", "hi", "apple" };
		String[] expected = { "apple" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseLargeToEmptyLinkedSL() {

		String[] input = { "j", "hi", "pple", "f", "s", "let", "me" };
		String[] expected = {};

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void chooseLargeToEmptyArraySL() {

		String[] input = { "j", "hi", "pple", "f", "s", "let", "me" };
		String[] expected = {};

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void chooseLargeToOneLinkedSL() {

		String[] input = { "a", "b", "c", "d", "e", "f" };
		String[] expected = { "a" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void chooseLargeToOneArraySL() {

		String[] input = { "a", "b", "c", "d", "e", "f" };
		String[] expected = { "a" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());
	}

	@Test
	public void chooseFirstLastLinkedSL() {

		String[] input = { "ok", "as", "apple", "hey" };
		String[] expected = { "as", "apple" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseFirstLastArraySL() {

		String[] input = { "ok", "as", "apple", "hey" };
		String[] expected = { "as", "apple" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertArrayEquals(expected, s.toArray());

	}

	@Test
	public void chooseAllSizeArraySL() {

		String[] input = { "ok", "as", "apple", "hey" };

		ArraySL s = new ArraySL(input);
		s.chooseAll(new SpecificWordChooser());

		assertEquals(2, s.size);

	}

	@Test
	public void chooseAllSizeLinkedSL() {

		String[] input = { "ok", "as", "apple", "hey", "good" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertEquals(2, s.size);

	}

	@Test
	public void chooseAllSizeLinkedSL3() {

		String[] input = { "ok" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertEquals(s.front.next, null);
	}

	@Test
	public void chooseAllSizeLinkedSL2() {

		String[] input = { "ok", "hey", "good" };

		LinkedSL s = new LinkedSL(input);
		s.chooseAll(new SpecificWordChooser());

		assertEquals(0, s.size);

	}

	@Test
	public void chooseAllNullSizeLinkedSL() {

		String[] input = {};

		LinkedSL s = new LinkedSL(input);

		assertEquals(0, s.size);

	}

}

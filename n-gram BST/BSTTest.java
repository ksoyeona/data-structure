package ucsdcse12pa7student;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {

	@Test
	public void testSetAndGet() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("a", 1);
		assertEquals(1, (int) bst.get("a"));
	}
	
	@Test
	public void testKeys() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("a", 1);
		bst.set("b", 2);
		bst.set("c", 3);
		bst.set("d", 4);
		bst.set("e", 5);
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");
		al.add("e");
		
		assertEquals(al, bst.keys());
	}
	
	@Test
	public void testValues() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("a", 1);
		bst.set("b", 2);
		bst.set("c", 3);
		bst.set("d", 4);
		bst.set("e", 5);
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(5);
		
		assertEquals(al, bst.values());
	}

	@Test
	public void testEntries() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("a", 1);
		bst.set("b", 2);
		bst.set("c", 3);
		bst.set("d", 4);
		bst.set("e", 5);
		
		ArrayList<Entry<String,Integer>> al = new ArrayList<Entry<String,Integer>>();
		
		al.add(new Entry<String,Integer>("a",1));		
		al.add(new Entry<String,Integer>("b",2));
		al.add(new Entry<String,Integer>("c",3));
		al.add(new Entry<String,Integer>("d",4));
		al.add(new Entry<String,Integer>("e",5));
		
		assertEquals(al.get(2).value, bst.entries().get(2).value);
	}
	
	@Test
	public void testFloor() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("s", 1);
		bst.set("e", 2);
		bst.set("y", 3);
		bst.set("b", 4);
		bst.set("r", 5);
		bst.set("c", 6);
		bst.set("h", 6);
		
		assertEquals("c",bst.floor("d"));
	}
	
	@Test
	public void testCeiling() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("s", 1);
		bst.set("e", 2);
		bst.set("y", 3);
		bst.set("a", 4);
		bst.set("r", 5);
		bst.set("c", 6);
		bst.set("h", 6);
		
		assertEquals("e",bst.ceiling("d"));
		
	}
	
	@Test
	public void testRange() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("s", 1);
		bst.set("e", 2);
		bst.set("y", 3);
		bst.set("a", 4);
		bst.set("r", 5);
		bst.set("c", 6);
		bst.set("h", 6);
		
		ArrayList<String> al = new ArrayList<String>();

		al.add("c");
		al.add("e");
		al.add("h");
		al.add("r");
		
		Collections.sort(al);
		
		assertEquals(al,bst.range("b","s"));
		
	}
	
	@Test
	public void testRangeRightWorstCaseTree() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("a", 1);
		bst.set("b", 2);
		bst.set("c", 3);
	
		bst.set("", 0);
		bst.set("", 0);
		bst.set("", 0);
		
		bst.set("d", 4);
		bst.set("e", 5);
		bst.set("f", 6);
		bst.set("g", 7);
		bst.set("h", 1);
		bst.set("i", 2);
		bst.set("j", 3);
		bst.set("k", 4);
		bst.set("l", 5);
		bst.set("m", 6);
		bst.set("n", 7);
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");
		al.add("e");
		al.add("f");
		al.add("g");
		al.add("h");
		al.add("i");
		al.add("j");
		al.add("k");
		al.add("l");
		al.add("m");
		
		Collections.sort(al);
		
		assertEquals(al,bst.range("a","n"));
		
	}
	
	@Test
	public void testRangeLeftWorstCaseTree() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("g", 1);
		bst.set("f", 2);
		bst.set("e", 3);
		bst.set("d", 4);
		bst.set("c", 5);
		bst.set("b", 6);
		bst.set("a", 7);
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");
		
		Collections.sort(al);
		
		assertEquals(al,bst.range("a","e"));
		
	}
	
	@Test
	public void testHalfBalancedTree() {
		BSTMap<String, Integer> bst = new BSTMap<>(String::compareTo);
		bst.set("g", 1);
		bst.set("c", 2);
		bst.set("x", 3);
		bst.set("b", 4);
		bst.set("a", 5);
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("g");
		al.add("x");
		
		Collections.sort(al);
		
		assertEquals(al,bst.range("a","z"));
		
	}

}

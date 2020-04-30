package cse12pa3student;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolvers {

	/* Helper method to compare two mazes */
	public void checkMaze(SearchWorklist wl, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, wl);
		if(expected == null) { assertNull(s); }
		else {
			String actualStr = formatMaze(startMaze.showSolution());
			String expectedStr = formatMaze(expected);
			assertEquals(expectedStr, actualStr);
		}
	}
	
	/* Helper method to format String[] output as String */
	public String formatMaze(String[] arr) {
		String result = "";
		for (String s: arr)
			result += "\n"+s;
		return (result+"\n");
	}

	/* Worklist Tests */
	
	@Test
	public void testStackWorklist() {
		Square s1 = new Square(0,0, false);
		Square s2 = new Square(1,0, false);
		Square s3 = new Square(1,1, false);
		SearchWorklist swl = new StackWorklist();
		
		swl.add(s1);
		swl.add(s2);
		assertEquals(false, swl.isEmpty());
		
		swl.remove(); 
		swl.remove();
		assertEquals(true, swl.isEmpty());
		
		//Ensure stack behavior
		swl.add(s1);
		swl.add(s2);
		swl.add(s3);
		assertEquals(s3, swl.remove());
	}
	
	@Test
	public void testQueueWorklist() {
		Square s1 = new Square(0,0, false);
		Square s2 = new Square(1,0, false);
		Square s3 = new Square(1,1, false);
		SearchWorklist qwl = new QueueWorklist();
		
		qwl.add(s1);
		qwl.add(s2);
		assertEquals(false, qwl.isEmpty());
		
		qwl.remove(); 
		qwl.remove();
		assertEquals(true, qwl.isEmpty());
		
		//Ensure queue behavior
		qwl.add(s1);
		qwl.add(s2);
		qwl.add(s3);
		assertEquals(s1, qwl.remove());
	}	
	
	@Test
	public void testClassExample() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_##S",
				"F___"
			});
		String[] stackExpected = {
				"#_#_",
				"****",
				"*##S",
				"F___"
			};
		checkMaze(new StackWorklist(), m, stackExpected);
	}
	
	@Test
	public void testClassExampleQueue() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_##S",
				"F___"
			});
		String[] Expected = {
				"#_#_",
				"____",
				"_##S",
				"F***"
			};
		checkMaze(new QueueWorklist(), m, Expected);
	}

	@Test
	public void testStackQueueDifferentStack() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_##S",
				"__F_"
			});
		String[] stackExpected = {
				"#_#_",
				"****",
				"*##S",
				"**F_"
			};
		checkMaze(new StackWorklist(), m, stackExpected);
	}
	
	@Test
	public void testStackQueueDifferentQueue() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_##S",
				"__F_"
			});
		String[] Expected = {
				"#_#_",
				"____",
				"_##S",
				"__F*"
			};
		checkMaze(new QueueWorklist(), m, Expected);
	}
	
	@Test
	public void testOneStepStack() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"F_S#",
				"__#_"
			});
		String[] stackExpected = {
				"#_#_",
				"***_",
				"F_S#",
				"__#_"
			};
		checkMaze(new StackWorklist(), m, stackExpected);
	}
	
	@Test
	public void testOneStepQueue() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"F_S#",
				"__#_"
			});
		String[] Expected = {
				"#_#_",
				"____",
				"F*S#",
				"__#_"
			};
		checkMaze(new QueueWorklist(), m, Expected);
	}
	
	@Test
	public void testDiscussionExStack() {
		Maze m = new Maze(new String[]{
				"#_##",
				"__S_",
				"_#__",
				"F___"
			});
		String[] Expected = {
				"#_##",
				"**S_",
				"*#__",
				"F___"
			};
		checkMaze(new StackWorklist(), m, Expected);
	}
	
	@Test
	public void testDiscussionExQueue() {
		Maze m = new Maze(new String[]{
				"#_##",
				"__S_",
				"_#__",
				"F___"
			});
		String[] Expected = {
				"#_##",
				"__S_",
				"_#*_",
				"F**_"
			};
		checkMaze(new QueueWorklist(), m, Expected);
	}
	
	@Test
	public void testSFRightNextStack() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_###",
				"FS__"
			});
		String[] stackExpected = {
				"#_#_",
				"____",
				"_###",
				"FS__"
			};
			
		checkMaze(new StackWorklist(), m, stackExpected);
	}
	
	@Test
	public void testSFRightNextQueue() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_###",
				"FS__"
			});
		String[] Expected = {
				"#_#_",
				"____",
				"_###",
				"FS__"
			};
			
		checkMaze(new QueueWorklist(), m, Expected);
	}
	
	
	@Test
	public void testDiffDimensionQueue() {
		Maze m = new Maze(new String[]{
				"_#_",
				"___",
				"##S",
				"F__"
			});
		String[] Expected = {
				"_#_",
				"___",
				"##S",
				"F**"
			};
		checkMaze(new QueueWorklist(), m, Expected);
	}
	
	@Test
	public void testDiffDimensionStack() {
		Maze m = new Maze(new String[]{
				"_#_",
				"___",
				"##S",
				"F__"
			});
		String[] Expected = {
				"_#_",
				"___",
				"##S",
				"F**"
			};
		checkMaze(new StackWorklist(), m, Expected);
	}
	
	@Test
	public void testFailingOrder() {
		String[] expected = {
				"#_#_",
				"****",
				"*##S",
				"F___"
			};
		String[] actualAndWrong= {
				"#_#_",
				"____",
				"_##S",
				"F***"
			};
		assertNotEquals(formatMaze(expected), formatMaze(actualAndWrong));
	}
	
	@Test
	public void testOneAwayQueue() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"____",
				"S_F_"
			});
		String[] Expected = {
				"#_#_",
				"____",
				"____",
				"S*F_"
			};
		checkMaze(new QueueWorklist(), m, Expected);
	}
	
	@Test
	public void testOneAwayStack() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"____",
				"S_F_"
			});
		String[] Expected = {
				"#_#_",
				"***_",
				"*_*_",
				"S_F_"
			};
		checkMaze(new StackWorklist(), m, Expected);
	}
	
	@Test
	public void testNoSolutionQueue() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_###",
				"S#F_"
			});
		
		assertEquals(MazeSolver.solve(m, new QueueWorklist()), null);
	}
	
	@Test
	public void testNoSolutionStack() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"____",
				"_###",
				"S#F_"
			});
		
		assertEquals(MazeSolver.solve(m, new StackWorklist()), null);
	}

	@Test
	public void testDiagonalStack() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"_#S#",
				"__#_",
				"F___"
			});
		
		assertEquals(MazeSolver.solve(m, new StackWorklist()), null);
	}
	
	@Test
	public void testDiagonalQueue() {
		Maze m = new Maze(new String[]{
				"#_#_",
				"_#S#",
				"__#_",
				"F___"
			});
		
		assertEquals(MazeSolver.solve(m, new QueueWorklist()), null);
	}
	
}




package cse12pa3student;

public abstract class MazeSolver {

	public static Square solve(Maze maze, SearchWorklist wl) {

		maze.start.visit();
		wl.add((Square)(maze.start));

		while (wl.isEmpty() == false) {

			Square current = (Square)(wl.remove());

			if (current == maze.finish) {

				return current;
			}

			Square rightSq = returnRightSquare(current, maze);
			if (rightSq != null) {
				rightSq.visit();
				rightSq.setPrevious(current);
				wl.add(rightSq);
			}

			Square lowerSq = returnLowerSquare(current, maze);
			if (lowerSq != null) {
				lowerSq.visit();
				lowerSq.setPrevious(current);
				wl.add(lowerSq);
			}

			Square leftSq = returnLeftSquare(current, maze);
			if (leftSq != null) {
				leftSq.visit();
				leftSq.setPrevious(current);
				wl.add(leftSq);
			}

			Square upperSq = returnUpperSquare(current, maze);
			if (upperSq != null) {
				upperSq.visit();
				upperSq.setPrevious(current);
				wl.add(upperSq);
			}
		}
		return null;
	}

	// following four helper methods returns adjacent square if they are unvisited
	// and not walls.
	public static Square returnLeftSquare(Square s, Maze m) {

		if (s.getCol() == 0) {
			return null;
		}

		Square sq = m.contents[s.getRow()][s.getCol() - 1];

		if (sq.getIsWall() == false && sq.isVisited() == false) {
			return sq;
		}
		return null;
	}

	public static Square returnLowerSquare(Square s, Maze m) {

		if (s.getRow() == m.rows - 1) {
			return null;
		}

		Square sq = m.contents[s.getRow() + 1][s.getCol()];

		if (sq.getIsWall() == false && sq.isVisited() == false) {
			return sq;
		}
		return null;
	}

	public static Square returnUpperSquare(Square s, Maze m) {

		if (s.getRow() == 0) {
			return null;
		}

		Square sq = m.contents[s.getRow() - 1][s.getCol()];

		if (sq.getIsWall() == false && sq.isVisited() == false) {
			return sq;
		}
		return null;
	}

	public static Square returnRightSquare(Square s, Maze m) {

		if (s.getCol() == m.cols - 1) {
			return null;
		}

		Square sq = m.contents[s.getRow()][s.getCol() + 1];

		if (sq.getIsWall() == false && sq.isVisited() == false) {
			return sq;
		}
		return null;
	}
}

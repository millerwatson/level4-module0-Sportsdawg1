package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		maze.cells[0][randGen.nextInt(h)].setWestWall(false);
		maze.cells[w-1][randGen.nextInt(h)].setEastWall(false);
		//4. select a random cell to start
		Cell startCell = maze.cells[randGen.nextInt(w)][randGen.nextInt(h)];
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(startCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisitedNeighbors.size() > 0) {
			
			//C1. select one at random.
			Cell randCell = unvisitedNeighbors.get(randGen.nextInt(unvisitedNeighbors.size()));
			//C2. push it to the stack
			uncheckedCells.push(randCell);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, randCell);
			//C4. make the new cell the current cell and mark it as visited
			System.out.println(currentCell);
			currentCell = randCell;
			System.out.println(currentCell);
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		else if(unvisitedNeighbors.size() == 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX()-1 == c2.getX() && c1.getY() == c2.getY()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		} else if(c1.getX() == c2.getX() && c1.getY()-1 == c2.getY()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		} else if(c1.getX()+1 == c2.getX() && c1.getY() == c2.getY()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		} else if(c1.getX() == c2.getX() && c1.getY()+1 == c2.getY()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		try {
			if (!maze.cells[c.getX()-1][c.getY()-1].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()-1][c.getY()-1]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		} try {
			if (!maze.cells[c.getX()][c.getY()-1].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		} try {
			if (!maze.cells[c.getX()+1][c.getY()-1].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()+1][c.getY()-1]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		} try {
			if (!maze.cells[c.getX()-1][c.getY()].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		} try {
			if (!maze.cells[c.getX()+1][c.getY()].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		} try {
			if (!maze.cells[c.getX()-1][c.getY()+1].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()-1][c.getY()+1]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		} try {
			if (!maze.cells[c.getX()][c.getY()+1].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()][c.getY()+1]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		} try {
			if (!maze.cells[c.getX()+1][c.getY()+1].hasBeenVisited()) {
				neighbors.add(maze.cells[c.getX()+1][c.getY()+1]);
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("oob");
		}
		return neighbors;
	}
}

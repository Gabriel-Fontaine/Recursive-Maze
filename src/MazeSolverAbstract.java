import becker.robots.City;
import becker.robots.Direction;
import becker.robots.RobotSE;

public abstract class MazeSolverAbstract extends RobotSE{ // this abstract class is a child of the main robot task and its properties
	public MazeSolverAbstract(City arg0, int arg1, int arg2, Direction arg3, int arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public abstract void solveMaze(); // initializes the method solveMaze as having abstract properties and allows for both children class of this (MazeSolverFromTopLeft and MazeSolverFromBottomRight) to possess this method
	
	public abstract void moveEast();
	public abstract void moveWest();
	public abstract void moveNorth();
	public abstract void moveSouth();

	public abstract double getEastProbability();
	public abstract double getWestProbability();
	public abstract double getNorthProbability();
	public abstract double getSouthProbability();
	
	
	
	/**
	 * @param numMoves refers to the specified amount of times that it must need to move forward
	 * @param move is a method which is called to control all of the robot's movements to ensure that it can simplify the amount of times that would be needed to ask the robot to move a distance
	 */
	
	public void moves(int numMoves) {
		for (int movesToDo = 0; movesToDo < numMoves; movesToDo += 1) { // moves multiple times based on the value of numMoves that were given to it
			super.move();
		}
		
	}
	
	
}

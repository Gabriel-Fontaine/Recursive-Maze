import java.text.DecimalFormat;

import becker.robots.City;
import becker.robots.Direction;
public class RobotTaskA3 {
	
	/**
	 * @author Gabriel Fontaine
	 * @param runs the following program when it is called from the main class
	 */
	public void run() {
		// TODO Auto-generated method stub		
		
		
		City city = new City (15,15); // creates a new blank city size 15 by 15
		DecimalFormat roundBetter = new DecimalFormat("#.##");

		
		MazeSolverAbstract mazeSolverFromBottomRight = null; // telling the computer that the following objects are to have a null type as they are not assigned to any specific properties yet, and they are used in reference to the abstract child class MazeSolverAbstract
		MazeSolverAbstract mazeSolverFromTopLeft = null;  // is assigning it a null value for this object due to the fact the robot has an abstract child class that possesses similar properties
		
		MazeDesign mazeDesigner = new MazeDesign(city, 0, 1, Direction.SOUTH, 500); // note that it goes city, row number, then column number in respect to the properties of the robots that are being created, with mazeDesigner not being applicable to the child class MazeSolverAbstract
		mazeSolverFromBottomRight = new MazeSolverFromBottomRight(city, 20, 21, Direction.WEST, 500); // represents a robot which will solve the maze from the bottom right corner, each of these subsequent robots being associated with their own respective child classes essentially grandchildren of this class, as they each possess the knowledge of the abstract class MazeSolverAbstract
		mazeSolverFromTopLeft = new MazeSolverFromTopLeft(city, 0, 1, Direction.EAST, 500); // represents a robot which will solve the maze from the bottom right corner
		
		
		
		mazeDesigner.border(); // generates a maze border via telling the maze designer robot that it must first sketch a border for the walls in the maze to be located within
		mazeDesigner.generateMaze(); // generates the walls within the maze via placing "things" that act as walls that the robot should not be able to pass through
		
		mazeSolverFromBottomRight.solveMaze(); // the robot which is located at the bottom right of the maze, as seen in its street and avenue values assigned to it, is instructed to solve the maze, knowing that the rest of the code will progress once this robot has succeeded or failed to solve it
		mazeSolverFromTopLeft.solveMaze(); // similarly to the robot in the bottom right of the maze, this robot starts at the top left of the maze and will attempt to solve the maze
		
		if (mazeSolverFromBottomRight.canPickThing() == true) {// after the maze has successfully located an exit from the bottom to the top it will obtain the wall for it to be clear
			mazeSolverFromBottomRight.pickAllThings(); // the following code within these if statements is used to eliminate the walls exiting the mazes, thereby allowing for a definitive exit to the maze from the initial position of the robot located at the bottom right to the robot located at the top left
		}
		
		if (mazeSolverFromTopLeft.canPickThing() == true) { // after the maze has successfully located an exit from the top to the bottom it will obtain the wall for it to be clear
			mazeSolverFromTopLeft.pickAllThings(); // only picks an exit wall up if the robot is actually on a wall as it exits the maze
		}
		
		// represents bot 1 from the bottom right
		// keep in mind that an intersection is considered to be any time the robot moves, due to the fact that there is a potential for the robot to reorient itself and change its direction from North to any other direction, while reassigning that rotated direction to be the robot's new North facing straight
		System.out.println("The probability of the time that the Robot from the bottom right will move west is: " + roundBetter.format(mazeSolverFromBottomRight.getWestProbability()) + "%");
		System.out.println("The probability of the time that the Robot from the bottom right will move east is: " + roundBetter.format(mazeSolverFromBottomRight.getEastProbability()) + "%");
		System.out.println("The probability of the time that the Robot from the bottom right will move north is: " + roundBetter.format(mazeSolverFromBottomRight.getNorthProbability()) + "%");
		System.out.println("The probability of the time that the Robot from the bottom right will move south is: " + roundBetter.format(mazeSolverFromBottomRight.getSouthProbability()) + "%");
		System.out.println();
		
		// represents bot 2 from the top left
		// keep in mind that an intersection is considered to be any time the robot moves, due to the fact that there is a potential for the robot to reorient itself and change its direction from North to any other direction, while reassigning that rotated direction to be the robot's new North facing straight
		System.out.println("The probability of the time that the Robot from the top left will move west is: " + roundBetter.format(mazeSolverFromTopLeft.getWestProbability()) + "%");
		System.out.println("The probability of the time that the Robot from the top left will move east is: " + roundBetter.format(mazeSolverFromTopLeft.getEastProbability()) + "%");
		System.out.println("The probability of the time that the Robot from the top left will move north is: " + roundBetter.format(mazeSolverFromTopLeft.getNorthProbability()) + "%");
		System.out.println("The probability of the time that the Robot from the top left will move south is: " + roundBetter.format(mazeSolverFromTopLeft.getSouthProbability()) + "%");
		
		
		
		
		
	}
}

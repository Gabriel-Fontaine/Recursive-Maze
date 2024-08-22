import java.awt.Color;
import java.util.Random;

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.RobotSE;

public class MazeDesign extends RobotSE{ // this class' main purpose is to simply sketch the entire maze using another robot so that other robots can solve it successfully

	
	Random random = new Random(); // a variable called random is created for the purpose of using random values later throughout the code
	
	
	/**
	 * 
	 * @param city
	 * @param i
	 * @param j
	 * @param south
	 * @param k
	 */
	
	public MazeDesign(City city, int i, int j, Direction south, int k) {
		// TODO Auto-generated constructor stub
		super(city, i, j, south, k);
	}
	
	
	/**
	 * 
	 * nextRow is a method which is used for the purpose of progressing to the next row from either the east or west direction based on the direction it was currently facing
	 */
	
	public void nextRow(){
		if (this.getDirection() == Direction.EAST) { // checks to see if the robot is facing east so that it can acknowledge if it must go to the next row from east to west or west to east vice versa
			this.turnRight();
			this.move();
			this.turnRight();
		} else { // goes from facing west to facing east on the subsequent row
			this.turnLeft();
			this.move();
			this.turnLeft();
		}
	}
	
	
	/**
	 * 
	 * border is a method which is called for the purposes of forming a border that the robots solving the maze can navigate through, ensuring that robots are able to successfully generate walls throughout the maze within the designated border size
	 */
	
	public void border(){
		this.setSpeed(30); // by default making the robot faster so that the user does not have to manually increase its speed
		this.setColor(Color.BLACK); // changing the colour of the robot to be black for the robot that is designing the maze, so that  the user can aesthetically identify aspects of the robots
		for (int repeatTurn = 0; repeatTurn < 2; repeatTurn += 1){ // this for loop is simply used, knowing that the robot will be making
			for (int verticalWall = 0; verticalWall < 20; verticalWall += 1){ // when creating the borders of the maze since it will form a rectangle or square based on the values assigned in these loops, as altering the end case of this loop will make the borders of the maze longer vertically (sides of the maze, left and right)
				this.move();
				this.putThing();
			}
			this.turnLeft(); // by turning left we are enabling the robot to go to the position for it to be ready from facing vertical to horizontal and similarly later when going from horizontal to vertical in this loop
			
			for (int horizontalWall = 0; horizontalWall < 20; horizontalWall += 1){ // altering the end condition of this loop will alter how wide the maze will be in respect to its border, with it having the robot move in a straight direction horizontally for the bottom and top
				this.move();
				this.putThing();
			}
			this.turnLeft();
		}
	}
	
	
	/**
	 * 
	 * generateMaze is a method which is called for the purposes of forming walls that the robot is supposed to navigate around
	 */
	
	public void generateMaze() {
		int[] placeThing = new int [20]; // an array with a size of 5 suggesting that 5 walls can show up in a row
		
		this.move(); // by having the robot initially move forward and left from when it has initially finished the maze, it can prepare itself for the accumulation of placing things throughout the maze that can be seen as walls that the robot must navigate around
		this.turnLeft();
		
		for (int rowNum = 0; rowNum < 19; rowNum += 1) { // 19 since the column is shifted over one so there is an offset in terms of the numbering
			for (int arrayPos = 0; arrayPos < 6; arrayPos += 1) { // generates randomly assigned numbers for the array with the limit on the loop corresponding on how plausible the maze is to solve the lower this number is in correspondence with the size of the maze, the easier the maze will be to solve (as a general idea, it should be a bit more less than half of the maximum size, as I do not like recursion in regards to generation and programming)
				placeThing[arrayPos] = random.nextInt(20); // will give the array placeThing a random number based on its position in the array starting at zero to its max number, knowing that it can generate a random number representing the column and row that the robot is going to place things at
			}
			
			for (int columnNum = 0; columnNum < 20; columnNum += 1) { // is going to progress throughout the columns throughout the maze
				for (int positionInArray = 0; positionInArray < 20; positionInArray += 1) { // is going to cycle throughout the array, to check if the array location matches with a thing on the column that matches the robot's current location
					if (columnNum == placeThing[positionInArray]) { // if there is a thing on the same location as the column that the robot is currently located in, the robot will place a thing based on if it matches the designated location it was told to in the array
						this.putThing(); // places a thing which resembles a wall throughout the maze
					}
				}
				this.moves(1); // will continue to move throughout the row of the maze, knowing that it has either placed a thing on a specified location that it was told to in the array, or it did not acknowledge there being one and continued moving
			}
			this.nextRow(); // should move on to the next row after it has completed checking and generating a row with "walls"
		}
	}
	
	
	/**
	 * @param numMoves refers to the specified amount of times that it must need to move forward
	 * @param move is a method which is called to control all of the robot's movements to ensure that it can simplify the amount of times that would be needed to ask the robot to move a distance
	 */
	
	public void moves(int numMoves) {
		for (int movesToDo = 0; movesToDo < numMoves; movesToDo += 1) { // if this method is told that it must move numerous amounts of times, it will reference this method knowing that it will move multiple times compared to having to recopy code
			super.move(); // override this due to the fact that it must ensure that it can move via this firstly
		}
		
	}
	

}

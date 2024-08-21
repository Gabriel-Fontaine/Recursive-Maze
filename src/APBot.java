import java.util.ArrayList;
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.RobotSE;
public class APBot extends RobotSE {
	
	ArrayList<Integer> thingCoordinatesX = new ArrayList<Integer>(); // represents avenues of the robot
	ArrayList<Integer> thingCoordinatesY = new ArrayList<Integer>();  // represents streets of the robot
	int mazeAttempts = 0; // the number of attempts the maze has made in trying to solve the maze
	
	/**
	 *
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param nT
	 */
	public APBot(City arg0, int arg1, int arg2, Direction arg3, int nT) {
		super(arg0, arg1, arg2, arg3, nT);
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * 
	 * paints the interior area of a given polygon or area
	 */
	
	public void findAreasToPaint() {
		this.setSpeed(40); // increases default speed
		if (thingNotInFront() == false){ // in front of wall ahead immediately
			turnLeft();
			thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
			thingCoordinatesY.add(this.getStreet());
			this.move();
		//	System.out.println("Moved North");
			if (thingNotInFront() == false){ // scenario when wall adjacent to robot on left is blocked
				turnAround();
				thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
				thingCoordinatesY.add(this.getStreet());
				this.move();
			//	System.out.println("Moved North");
				if (thingNotInFront() == false){
					turnRight();
					thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
					thingCoordinatesY.add(this.getStreet());
					this.move();
			//		System.out.println("Moved South");
				} else {
					if (thingNotInFront() == false) {
						thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
						thingCoordinatesY.add(this.getStreet());
						this.move();
			//			System.out.println("Moved West");
					}
				}
			} else {
				if (thingNotInFront() == false) {
					thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
					thingCoordinatesY.add(this.getStreet());
					this.move();
			//		System.out.println("Moved North");
				}
			}
		} else {
			turnLeft();
			thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
			thingCoordinatesY.add(this.getStreet());
			this.move();
			if (thingNotInFront() == true) {
				thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
				thingCoordinatesY.add(this.getStreet());
				this.move();
		//		System.out.println("Moved East");
			} else {
				turnRight();
				thingCoordinatesX.add(this.getAvenue()); // sets the current coordinate position to the arraylist for positions
				thingCoordinatesY.add(this.getStreet());
				this.move();
		//		System.out.println("Moved West");
			}
		}
		mazeAttempts += 1; // an attempt to solve the maze
		if (mazeAttempts < 30) { // we want it to place all of the things in its backpack, since it was assigned to originally have a total of 100 things in it, with the final run of the program placing the last thing
			this.findAreasToPaint(); // recurse this method
		} else {
			paintArea(); // call the method to paint all of the areas that have been found
		}
		
	}
	
	

	/**
	 * 
	 * paintArea is a method which tells the robot to paint the coordinates corresponding to the locations located from navigating throughout the inside of the polygon
	 */
	
	private void paintArea() {
		for (int arrayPos = 0; arrayPos < thingCoordinatesX.size(); arrayPos += 1) { // cycling through the arraylist base on the coordinate position and location
			this.goToThingLocation(thingCoordinatesX.get(arrayPos), thingCoordinatesY.get(arrayPos)); // goes to the necessary location corresponding to its x and y values
		//	System.out.println(thingCoordinatesX.get(arrayPos) + ", " + thingCoordinatesY.get(arrayPos));
		}
		
		
		if (this.countThingsInBackpack() > 30) { // simply makes the compilation fo the program faster
			this.turnRight(); // adjusting the robot to prepare for it having to test for any secondary or tertiary layers within the spiral of the maze
			this.move();
			mazeAttempts = 0;
		//	System.out.println("second loop"); // acknowledges to the program that it has begun looping within itself
			thingCoordinatesX.clear(); // clearing the arraylists for the purpose of having a subsequent
			thingCoordinatesY.clear();
			findAreasToPaint(); // checking to see which areas it should paint again through doing a second loop of a spiral within next inner layer of the edge of the shape
		}
	}
	
	/**
	 * 
	 * goToThingLocation is a method which has the robot change its current position to prepare to paint and place things at the specified location
	 * @param thingPosX represents the position of the thing in the x-axis
	 * @param thingPosY represents the position of the thing in the y-axis
	 */
	
	public void goToThingLocation(int thingPosX, int thingPosY){
		
		do { // looping to reorient and alter the coordinates of the robot to match with its intended starting location
			if (this.frontIsClear() == true){
				// adjusting x-axis
				if (this.getAvenue() < thingPosX){ // reorient the robot to face the correct direction in the x-axis
					this.lookEast();
					this.move();
				} else if (this.getAvenue() > thingPosX){
					this.lookWest();
					this.move();
				} // if it is located at the correct street don't do anything since we want it to stay on that specified street
				
				// adjusting y-axis
				if (this.getStreet() > thingPosY){ // reorient the robot to face the correct direction in the y-axis
					this.lookNorth();
					this.move();
				} else if (this.getStreet() < thingPosY){
					this.lookSouth();
					this.move();
				} // if it is located at the correct avenue don't do anything since we want it to stay on that specified avenue
			} else{
				if (this.getDirection() == Direction.NORTH || this.getDirection() == Direction.SOUTH){
					this.turnLeft();
					this.move();
				} else if (this.getDirection() == Direction.EAST || this.getDirection() == Direction.WEST){
					this.turnLeft();
					this.move();
				}
			}
		} while (this.getAvenue() != thingPosX || this.getStreet() != thingPosY); // this will continue looping until the robot has arrived at the the corner of the polygon at the coordinates which correspond to the paths of avenue 3 and street 8
		if (this.countThingsInBackpack() > 0){ // ensurring that the robot will not die if it tries to place things when it does not have enough
			this.putThing(); // places a thing at the intended destination
		}
	}
	
	
	
	/**
	 * 
	 * thingNotInFront is a method that is used to identify if there is a thing directly in front of the robot relative to its current position
	 * @return a true or false value which corresponds to the fact of if there is a thing directly in front of the robot from what direction it was previously facing
	 */
	
	
	private boolean thingNotInFront() { // note that thingNotInFront is the same as frontIsClear
			
		if (this.canPickThing() == true) { // is going to check if it is currently on a thing, if it is not, it should turn around to its current location and progress throughout the code either returning a boolean value to the method solving the maze that there is or is not a thing in front of it
			this.turnAround(); // rearranges the robot so that it can go back to its current location with the knowledge of if there is a thing in fron of it
			super.move();
			this.turnAround();
			return false; // there is a thing in front of the robot
		} else {
			return true; // there is a thing in front of the robot
		}
			
	}
	
	
	
	
	
	
	/**
	 *
	 * isSouthClear is a method which is called to determine if there is a thing (essentially a wall or obstacle) that is in front of the robot, as if that is so, then the robot should not be allowed to move in that direction accordingly
	 * @return provides the program with a boolean value which determines the actuality of if the front direction facing south is clear or not
	 */
	
	private Boolean isSouthClear(){
		this.lookSouth();
		this.move();
		if (this.canPickThing() == true){ // a thing is directly in front of it facing south
			this.turnAround();
			this.move();
			return false;
		} else {
			this.turnAround();
			this.move();
			return true;
		}
	}
	
	
	/**
	 *
	 * isNorthClear is a method which is called to determine if there is a thing (essentially a wall or obstacle) that is in front of the robot, as if that is so, then the robot should not be allowed to move in that direction accordingly
	 * @return provides the program with a boolean value which determines the actuality of if the front direction facing north is clear or not
	 */
	
	private Boolean isNorthClear(){
		this.lookNorth();
		this.move();
		if (this.canPickThing() == true){ // a thing is directly in front of it facing north
			this.turnAround();
			this.move();
			return false;
		} else {
			this.turnAround();
			this.move();
			return true;
		}
	}
	
	
	/**
	 *
	 * isWestClear is a method which is called to determine if there is a thing (essentially a wall or obstacle) that is in front of the robot, as if that is so, then the robot should not be allowed to move in that direction accordingly
	 * @return provides the program with a boolean value which determines the actuality of if the front direction facing west is clear or not
	 */
	
	private Boolean isWestClear(){
		this.lookWest();
		this.move();
		if (this.canPickThing() == true){ // a thing is directly in front of it facing west
			this.turnAround();
			this.move();
			return false;
		} else {
			this.turnAround();
			this.move();
			return true;
		}
	}
	
	
	/**
	 *
	 * isEastClear is a method which is called to determine if there is a thing (essentially a wall or obstacle) that is in front of the robot, as if that is so, then the robot should not be allowed to move in that direction accordingly
	 * @return provides the program with a boolean value which determines the actuality of if the front direction facing east is clear or not
	 */
	
	private Boolean isEastClear(){
		this.lookEast();
		this.move();
		if (this.canPickThing() == true){ // a thing is directly in front of it facing east
			this.turnAround();
			this.move();
			return false;
		} else {
			this.turnAround();
			this.move();
			return true;
		}
	}
	
	
	
	
	/**
	 * 
	 * moveNorth() is a method that tells the robot to keep turning until it is facing north then move in that direction
	 */
	
	private void moveNorth() {
		if (!isFacingNorth()){
			turnLeft();
			moveNorth();
		}
		else
			move();
	}
	
	
	/**
	 * 
	 * moveSouth() is a method that tells the robot to keep turning until it is facing south then move in that direction
	 */
	
	private void moveSouth() {
		if (!isFacingSouth()){
			turnLeft();
			moveSouth();
		}
		else
			move();
	}
	
	
	/**
	 * 
	 * moveWest() is a method that tells the robot to keep turning until it is facing west then move in that direction
	 */
	
	private void moveWest() {
		if (!isFacingWest()){
			turnLeft();
			moveWest();
		}
		else
			move();
	}
	
	
	/**
	 * 
	 * moveEast() is a method that tells the robot to keep turning until it is facing east then move in that direction
	 */
	
	private void moveEast() {
		if (!isFacingEast()){
			turnLeft();
			moveEast();
		}
		else
			move();
	}
	
	
	
	
	/**
	 * 
	 * lookSouth() is a method which is used to have the robot keep turning until it is facing south
	 */
	
	private void lookSouth() {
		if (!isFacingSouth()){
			turnLeft();
			lookSouth();
		}
	}
	

	/**
	 * 
	 * lookNorth() is a method which is used to have the robot keep turning until it is facing north
	 */
	
	private void lookNorth() {
		if (!isFacingNorth()){
			turnLeft();
			lookNorth();
		}
	}
	

	/**
	 * 
	 * lookEast() is a method which is used to have the robot keep turning until it is facing east
	 */
	
	private void lookEast() {
		if (!isFacingEast()){
			turnLeft();
			lookEast();
		}
	}
	

	/**
	 * 
	 * lookWest() is a method which is used to have the robot keep turning until it is facing west
	 */
	
	private void lookWest() {
		if (!isFacingWest()){
			turnLeft();
			lookWest();
		}
	}
	
	
}

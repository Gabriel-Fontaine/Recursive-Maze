import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.RobotSE;
/**
*
* @author
*
*/
public class MazeBot extends RobotSE {
	
	private Random random = new Random(); // a variable called random is created for the purpose of using random values later throughout the code
	private ArrayList<String> correctPath = new ArrayList<String>(); // should represent the possible paths the robot can take
	private ArrayList<String> shortestCorrectPath = new ArrayList<String>(); // represents the shortest possible path that is correct and leads to the final destination, being different from the previous arraylist as this is adjusted over time, while the other arraylist is used for every single trial and simulation, this is exclusively used for the most accurate simulations that are shorter and more concise
	private int numActions = 0; // represents the total amount of times that a robot has moved throughout the maze or turned and made an action
	private int shortestPath = 1000000; // should initially be a very large number so that the numMoves variable can use it as a reference for how it should perceive a shorter path in retrospective to this path value
	private int simulationsRan = 0; // represents the number of times the robot has tried to solve the maze
	private Boolean hasReturnedHome = true; // represents the fact that the robot has gone back to its original starting location from having navigated throughout the maze
	private Boolean shortestPathFound = false; // a variable which represents the fact of if the robot has finished running all of its necessary simulations and test trials, stating the fact of if the robot should continue to call itself
	
	
	/**
	 *
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param i
	 */
	
	public MazeBot(City arg0, int arg1, int arg2, Direction arg3, int i) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *
	 * pickThing is a method which is called to check if the robot has completed the maze by finding the thing hidden within the maze, alongside telling the program that the compilation and simulation number has been adjusted and altered after having ran through a full simulation or trial of it navigating throughout the maze
	 */
	
	public void reachedEnd() { // an overridden method which is run to ensure that a check is performed prior so we're not grabbing air
		if (this.getAvenue() == 0 && this.getStreet() == 0 && hasReturnedHome == true) { // realizes it is on top of a thing being the little yellow orb things
			if (numActions < shortestPath) {
				shortestPath = numActions; // telling the robot that the shortest path should be the path that takes the least amount of time, with us reassigning this value based on if the new path taken is shorter than the previous path
				shortestCorrectPath.clear(); // clearing the data of the previous fastest procedure of solving the maze
				for (int shortestPathPos = 0; shortestPathPos < correctPath.size(); shortestPathPos += 1) { // looping throughout the previous arraylist since a new shortest path has been identified, so therefore the previous arraylist path is being reassigned to this new arraylist for later use
					shortestCorrectPath.add(correctPath.get(shortestPathPos)); // converting the extrapolated information from the previous array list to have it be converted into the current shortest path array list
				}
				
			}
			
			correctPath.clear(); // wiping all data from this array list so that it can gather new data for future comparisons
			numActions = 0; // telling it that it should start counting the number of moves from this point of reference
			simulationsRan += 1; // tells the program that the number of times a robot has successfully reached the end of the maze (the thing), incrementing it thereby showing that it has completed a trial of it navigating throughout the maze
			System.out.println("shortest num actions: " + shortestPath); // shows to the viewer the fastest way that simulations have shown which can allow for the robot to escape the maze via finding the thing to acquire
			System.out.println("sim #: " + simulationsRan); // saying that a simulation of the robot solving the maze has been run once successfully
			hasReturnedHome = false;
			this.goToStart(); // calls the function goToStart to instruct to the robot that it must go back to its original starting location
		}
	}
	
	/**
	 *
	 * solve is a method which is called to have the robot run solving the maze, with it running through providing the robot random path generated instructions so that the robot can best optimize the path it is traversing over time
	 */
	
	public void solve(){
		this.setSpeed(100); // go fast initially so that the user does not have to change the initial speed for the robot, while it is running its various simulation pathfinding, as after it has fully maneuvered to identify an effective pathway, it will then reduce its speed
		this.setColor(Color.CYAN); // changing the colour of the robot initially to show how it is distinctly in a simulation phase, rather than the proper solving aspect of the code
		
		int path = random.nextInt(4); // generates a random path for the robot to traverse through when it is thinking if it should move either north, south, east, or west, with each of these initial generated numbers corresponding to one of the following cardinal directions
		
		if (path == 1) { // assigning a generated number of 1 as south
			this.lookSouth();
			correctPath.add("south"); // acknowledges to the arraylist that this data should be added as the robot performed such an action
			numActions += 1;
		} else if (path == 2) { // assigning a generated number of 2 as east
			this.lookEast();
			correctPath.add("east"); // acknowledges to the arraylist that this data should be added as the robot performed such an action
			numActions += 1;
		} else if (path == 3) { // assigning a generated number of 3 as west
			this.lookWest();
			correctPath.add("west"); // acknowledges to the arraylist that this data should be added as the robot performed such an action
			numActions += 1;
		} else if (path == 0){ // assigning a generated number of 0 as north
			this.lookNorth();
			correctPath.add("north"); // acknowledges to the arraylist that this data should be added as the robot performed such an action
			numActions += 1;
		}
		
		
		if (this.frontIsClear() == true) { // if the robot is not facing a wall it should move forward, progressing throughout the maze
			this.move(); // acknowledges to the arraylist that this data should be added as the robot performed such an action
			correctPath.add("move");
			numActions += 1; // represents the total number of times the robot has had to physically move throughout the maze from its initial starting location
		}
		
		this.reachedEnd(); // checks to see if it has reached the goal of the maze via calling the pickThing method to see if the end clause of this maze has been met
		
		
		
		// note that in altering the simulationsRan value to a greater value, you will have a greater accuracy and precision in mitigating the amount of detours and false intersections that the robot passes through
		if (simulationsRan == 5000) { // has finished running simulations and knows the optimal way from those trials to traverse throughout the maze with minimal error
			this.setSpeed(3); // makes the robot slower for when it is finally ready to pick up the thing
			this.setColor(Color.RED); // changes colour to signify it is ready to properly solve the maze
			
			for (int arrayPos = 0; arrayPos <= shortestCorrectPath.size(); arrayPos += 1){ // the following loop is used to extrapolate and analyze the following data within the arraylist that possesses the data of the robot traversing the fastest and shortest possible path throughout the maze, rather than have it perform an extraneous and unnecessary path
				// converts the following information into actions from within the arraylist, suggesting that these actions should correspond to specific orders based on the index of the array representing the exact order that the robot should conduct these actions from
				if (shortestCorrectPath.get(arrayPos) == "north") {
					this.lookNorth();
				} else if (shortestCorrectPath.get(arrayPos) == "south") {
					this.lookSouth();
				} else if (shortestCorrectPath.get(arrayPos) == "west") {
					this.lookWest();
				} else if (shortestCorrectPath.get(arrayPos) == "east") {
					this.lookEast();
				} else if (shortestCorrectPath.get(arrayPos) == "move") {
					this.move();
				}
			}
			getMazeSolved(); // a method is called to clarify that the robot has finished solving the maze with the shortest possible path and is currently located at (0, 0)
			shortestPathFound = true; // tells the robot that it has identified the shortest possible path
		}
		
		
	}
	
	/**
	 *
	 * getMazedSolved is a getter method which has the purpose of telling the robot task if the robot has completed the maze or not
	 * @return a true or false value which represents the end clause of the maze simulations, and
	 */
	
	public Boolean getMazeSolved(){
		if (shortestPathFound == false){ // telling it that if the shortest path has not been found, than the software program should continue to loop and recurse
			return false; // provides the user with the factual information of if the maze has been solved, with this telling the recursive method calling that it should continue to recurse
		} else {
			return true; // the maze has been solved most successfully
		}
	}
	
	
	
	
	/**
	 *
	 * goToStart is a method which is called to tell the robot that is must go back to its initial starting location from which was specified originally, knowing that from this location it can re-run and proceed to enact numerous other simulations of solving the maze
	 */
	
	public void goToStart(){ // navigates back to its initial location via moving along the right side of the maze
		do { // note that in navigating back to its original location, the robot will not be checking to see if it can pick up a thing
			if (frontIsClear() == false){ // wall facing north
				this.turnRight();
				if (frontIsClear() == false){ // wall facing east
					this.turnAround();
					if (frontIsClear() == false){ // wall facing west
						this.turnLeft();
						this.move();
					} else { // wall is not facing west
						this.move();
					}
				} else {
					this.move();
				}
			} else {
				this.turnRight();
				if (frontIsClear() == true){
					this.move();
				} else {
					this.turnLeft();
					this.move();
				}
				
			}
	
		} while (this.getAvenue() != 4 || this.getStreet() != 3); // the end condition for when the robot acknowledges that it has reached its destination from its initial starting position, with it looping numerous amounts of times until it has reached the desired destination
		hasReturnedHome = true; // tells a boolean value which is used to assign the specified location and designated knowledge to the robot for later use and reference
		this.lookSouth(); // orders the robot to look south due to the fact that it is originally looking south in all trials of the robot's traversal of the maze
	}
	
	
	/**
	 *
	 * lookEast is a method which is called which will reorient the robot so that will turn facing east or right essentially
	 */
	
	private void lookEast(){
		if (this.getDirection() == Direction.NORTH){
			this.turnRight();
		} else if (this.getDirection() == Direction.WEST){
			this.turnAround();
		} else if (this.getDirection() == Direction.SOUTH){
			this.turnLeft();
		} // if is facing east, then just go east, since that is the intended direction
	}
	
	
	/**
	 *
	 * lookWest is a method which is called which will reorient the robot so that it will turn facing west or left essentially
	 */
	
	private void lookWest(){
		if (this.getDirection() == Direction.NORTH){
			this.turnLeft();
		} else if (this.getDirection() == Direction.EAST){
			this.turnAround();
		} else if (this.getDirection() == Direction.SOUTH){
			this.turnRight();
		} // if is facing west, then just go west, since that is the intended direction
	}
	
	
	/**
	 *
	 * lookNorth is a method which is called which will reorient the robot so that it will turn facing north or essentially up in relation to the screen
	 */
	
	private void lookNorth(){
		if (this.getDirection() == Direction.WEST){
			this.turnRight();
		} else if (this.getDirection() == Direction.EAST){
			this.turnLeft();
		} else if (this.getDirection() == Direction.SOUTH){
			this.turnAround();
		} // if is facing north, then just go north, since that is the intended direction
	}
	
	
	/**
	 *
	 * lookSouth is a method which is called which will reorient the robot so that it will turn facing south or essentially looking down relative to the screen
	 */
	
	private void lookSouth(){
		if (this.getDirection() == Direction.WEST){
			this.turnLeft();
		} else if (this.getDirection() == Direction.EAST){
			this.turnRight();
		} else if (this.getDirection() == Direction.NORTH){
			this.turnAround();
		} // if is facing south, then just go south, since that is the intended direction
	}
}



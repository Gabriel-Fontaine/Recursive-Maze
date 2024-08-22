//import the becker library
import becker.robots.*;
/**
*RobotTask class
*@author Gabriel Fontaine
*@version 2024
*/
public class RobotTask
{
	/**
	 *run method
	 *@param none
	 *return void
	 */
	public void run ()
	{
		//construct city
		MazeCity mc = new MazeCity (8, 8, 1, 1); // new random maze
		// new MazeBot reference starting in middle of maze facing SOUTH
		MazeBot bot = new MazeBot (mc, 3, 4, Direction.SOUTH, 0); // starts at street 3, avenue 4 facing south
		
		do { // loops initially
			bot.solve(); //instructs the bot to solve the maze
		} while (bot.getMazeSolved() == false); // provides the initial looping for the process of the maze being solved and navigated by the robot, with this loop being necessary due to the fact that it is impossible to recurse this method within itself otherwise, as each time the robot functionally has finished solving the maze in a simulation, the robot requires another reference as to which object is being utilized in the process of simulating and calling the same method
		
	}
}



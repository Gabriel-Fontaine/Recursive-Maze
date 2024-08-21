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
		City c = null;

		int rC = (int)Math.floor((Math.random()*5));

		System.out.println(rC+"");

		if (rC==0)
			c = new City("Paint0.wld");
		else if (rC==1)
			c = new City("Paint1.wld");
		else if (rC==2)
			c = new City("Paint2.wld");
		else if (rC==3)
			c = new City("Paint3.wld");
		else if (rC==4)
			c = new City("Paint4.wld");

		
		APBot bot = new APBot(c, 2, 6, Direction.SOUTH, 100); // is responsible for identifying the locations of where various things should be placed
		bot.findAreasToPaint();
		
		

	}
}

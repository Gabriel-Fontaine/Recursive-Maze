/**
* RobotMain Class
* Task - A2 MazeNavigator Recursion: The purpose of this assignment was to showcase the potential of how recursive methods can be used in terms of referencing and calling the same methods numerous amounts of times with the intention of finding a base case scenario that can complete the intended goal as simple as possible, with this task requiring us to solve the maze through identifying the simplest way to traverse to the destination of coordinates (0, 0)
* U5A2
* @author Gabriel Fontaine
* @version 2024
*/
public class RobotMain
{
   /**
   * main method
   * runs a robot task, this method is needed so Java knows where to start
   *
   * @param arg - optional input arguments from the operating system, not used
   * @return void
   */
   public static void main (String[] args)
   {
     RobotTask task = new RobotTask ();
     task.run ();
   }
}



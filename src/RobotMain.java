/**
* RobotMain Class
* This class creates simply creates a robot task and runs it.
* Task - A3 PainterBot: Use recursion to have a robot identify how it can successfully loop itself through exploring throughout an area of a polygon, placing things all throughout the inside of it
* U5A3
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

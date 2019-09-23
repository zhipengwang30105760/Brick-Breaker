// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We figure out all the thing by ourselves

/**
 * The Ball class encapsulates the basic data and behaviors to model the ball within the brick breaker game.
 * All Ball objects have a fixed radius of 10 pixels.
 * @author zhipengwang@unomaha.edu ksone@unomaha.edu
 */

public class Ball 
{
	/**
	 * The fixed value of the ball radius.
	 */
	private static final int RADIUS = 10;
	
	/**
	 * The x coordinate of the Ball's center.
	 */
	private int xCoordinate;
	
	/**
	 * The y coordinate of the Ball's center.
	 */
	private int yCoordinate;
	
	/**
	 * The horizontal trajectory of the ball.
	 */
	private int xTrajectory;
	
	/**
	 * The vertical trajectory of the ball.
	 */
	private int yTrajectory;
	/**
	 * Creates a default Ball object with an initial location of (100, 100)
	 * and an initial trajectory of 3 pixels in both x and y dimensions.
	 */
	public Ball()
	{
		this.xCoordinate = 100;
		this.yCoordinate = 100;
		this.xTrajectory = 3;
		this.yTrajectory = 3;
	}
	
	/**
	 * Creates a Ball object with a custom initial location and a default trajectory of 
	 * 3 pixels in both the x and y dimensions.
	 * @param x The initial x position of this Ball.
	 * @param y The initial y position of this Ball.
	 */
	public Ball(int x, int y)
	{
		if (x > 0) 
		{
			this.xCoordinate = x;
		}
		if (y > 0)
		{
			this.yCoordinate = y;	
		}
		this.xTrajectory = 3;
		this.yTrajectory = 3;	
	}
	
	/**
	 * Retrieves the current x position of this Ball's center.
	 * @return xCoordinate The x coordinate of the Ball's center.
	 */
	public int getX()
	{
		return xCoordinate;
	}
	
	/**
	 * Retrieves the current y position of this Ball's center.
	 * @return yCoordinate The y coordinate of the Ball's center.
	 */
	public int getY() 
	{
		return yCoordinate;
	}
	
	/**
	 * Retrieves the radius of this Ball.
	 * @return RADIUS The radius of this Ball.
	 */
	public int getRadius()
	{
		return RADIUS;
	}
	
	/**
	 * Causes this Ball's current horizontal trajectory to reverse direction by 180 degrees.
	 */
	public void bounceHorizontal()
	{
		xTrajectory = xTrajectory * -1;
	}
	
	/**
	 * Causes this Ball's current vertical trajectory to reverse direction by 180 degrees.
	 */
	public void bounceVertical()
	{
		yTrajectory = yTrajectory * -1;
	}
	
	/**
	 * This mutator method will move the position of this Ball through 
	 * one time step in the game by applying the x and y 
	 * trajectory offsets to compute the new center position of this Ball.
	 */
	public void moveOnce()
	{
		xCoordinate = xCoordinate + xTrajectory;
		yCoordinate = yCoordinate + yTrajectory;
		
	}
	
	/**
	 * Retrieves a string representation of this Ball formatted as: 
	 * "Ball at (x, y) moving (dx, dy)" where x and y are the current x and y 
	 * positions of this Ball's center and dx and dy are the current trajectory 
	 * offsets for this Ball that are applied in each moveOnce operation.
	 * @return A String that contains the information of the current ball's position
	 * and the trajectory of both x coordinate and y coordinate. 
	 */
	public String toString() 
	{
		return "Ball at (" + xCoordinate + ", " + yCoordinate 
				+ ") moving (" + xTrajectory + ", " + yTrajectory + ")";
	}
}

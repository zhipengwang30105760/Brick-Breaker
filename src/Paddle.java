// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We got help of debugging isTouching method by Dr.Dorn

/**
 * This class encapsulates data and behaviors for a Paddle within the brick breaker game.
 * Once created a Paddle can move in the x dimension, but retains a fixed y coordinate.
 * Further all Paddle objects have a fixed height of 10 pixels and fixed width of 80 pixels.
 * @author zhipengwang@unomaha.edu ksone@unomaha.edu
 */

public class Paddle 
{
	/**
	 * The fixed height of the paddle.
	 */
	private static final int HEIGHT = 10;
	/**
	 * The fixed width of the paddle.
	 */
	private static final int WIDTH = 80;
	/**
	 * The overlap that a ball get in the paddle.
	 */
	private final int overlap = 2;
	/**
	 * The x coordinate of the top-left corner of paddle.
	 */
	private int x;
	/**
	 * The y coordinate of the top-left corner of paddle.
	 */
	private int y;
	
	/**
	 * Creates a new Paddle object at a specified coordinate location in the model.
	 * @param leftIn The x position of the top-left corner for the new Paddle.
	 * @param topIn The y position of the top-left corner for the new Paddle.
	 */
	public Paddle(int leftIn, int topIn)
	{
		this.x = leftIn;
		this.y = topIn;
	}
	
	/**
	 * Retrieves the x coordinate of the current left side of this Paddle.
	 * @return x The x coordinate.
	 */
	public int getLeft()
	{
		return x;
	}
	/**
	 * Retrieves the y coordinate of the fixed top of this Paddle.
	 * @return y The y coordinate.
	 */
	public int getTop()
	{
		return y;
	}
	/**
	 * Retrieves the fixed width of this Paddle object.
	 * @return WIDTH The width of the Paddle in pixels.
	 */
	public int getWidth()
	{
		return WIDTH;
	}
	/**
	 * Retrieves the fixed height of this Paddle object.
	 * @return HEIGHT The height of the Paddle in pixels.
	 */
	public int getHeight()
	{
		return HEIGHT;
	}
	
	/**
	 * Updates the state of this Paddle object to correspond to a new x-positionfor its left-side. 
	 * This method has no impact on the y positioning of thePaddle.
	 * @param newLeft The new x coordinate for the Paddle's left side. 
	 * A logical minimum of 0is enforced on the Paddle, so negative values will result in a new positionof 0.
	 */
	public void moveTo(int newLeft)
	{
		if (newLeft >= 0) 
		{
			x = newLeft;
		}
		else
		{
			x = 0;
		}
	}
	/**
	 * This method implements a collision detection algorithm to identify 
	 * whether this Paddle is currently being hit by a given Ball object. 
	 * It will produce are turn value to signal which side, if any, is currently being hit.
	 * @param theBall The Ball to examine for collision with this Paddle.
	 * @return position A valid TouchPosition state representing where theBall is intersecting this Paddle.
	 * When no collision is detected at all NONE should be returned. 
	 * Otherwise, TOP, LEFT, or RIGHT will be returned corresponding 
	 * to which side of this Paddle is currently being hit by theBall. 
	 * Note, a value of BOTTOM is not possible under standard physics rules 
	 * for brick breaker and thus is not expected.
	 */
	
	public TouchPosition isTouching(Ball theBall)
	{
		TouchPosition position = TouchPosition.NONE;
		
		//This is the version based on the description from the supplemental materials
		//Top
		if ((theBall.getX() + theBall.getRadius() >= this.x) 
				&& (theBall.getX() - theBall.getRadius() + 1 <= this.x + this.getWidth()) 
				&& (theBall.getY() + theBall.getRadius() >= this.y) 
				&& (theBall.getY() + theBall.getRadius() <= this.y + this.overlap))
		{
			position = TouchPosition.TOP;
		}
		//Left
		else if (this.checkLeft(theBall))
		{
			position = TouchPosition.LEFT;
		}
		//Right
		else if (this.checkRight(theBall))
		{
			position = TouchPosition.RIGHT;
		}	
		return position;
	}
	/**
	 * The isTouching method helper that attempt to detect ball hits left side.
	 * @param theBall The ball's coordinate.
	 * @return Whether ball hits paddle's left side or not. True is touched, false is not.
	 */
	private boolean checkLeft(Ball theBall)
	{
		return (theBall.getX() + theBall.getRadius() >= this.x) 
				&& (theBall.getX() + theBall.getRadius() <= this.x + this.overlap)
				&& (theBall.getY() + theBall.getRadius() >= this.y) 
				&& (theBall.getY() - theBall.getRadius() + 1 <= this.y + this.getHeight());
	}
	/**
	 * The isTouching method helper that attempt to detect ball hits right side. 
	 * @param theBall The ball's coordinate.
	 * @return Whether ball hits paddle's right side or not. True is touched, false is not.
	 */
	
	private boolean checkRight(Ball theBall)
	{
		return (theBall.getX() - theBall.getRadius() + 1 <= this.x + this.getWidth() - 1)
				&& (theBall.getX() - theBall.getRadius() + 1 
						>=  this.x + this.getWidth() - 1 - this.overlap)
				&& (theBall.getY() + theBall.getRadius() - 1 >= this.y) 
				&& (theBall.getY() - theBall.getRadius() + 1 <= this.y + this.getHeight() - 1);
	}
	
	/*
	  public TouchPosition isTouching(Ball theBall)
	{
		TouchPosition position = TouchPosition.NONE;
		
		//This is the version based on the description from the supplemental materials
		
		//Top
		if ((theBall.getX() >= this.x) 
				& (theBall.getX() <= this.x + Paddle.WIDTH - 1) 
				& (theBall.getY() + theBall.getRadius() - 1 >= this.y) 
				& (theBall.getY() + theBall.getRadius() - 1 <= this.y + 2))
		{
			position = TouchPosition.TOP;
		}
		else if ((theBall.getY() >= this.y) & (theBall.getY() <= this.y + Paddle.HEIGHT - 1))
		{
			//Left
			if ((theBall.getX() >= this.x - theBall.getRadius() + 1) 
					& (theBall.getX() <= this.x - theBall.getRadius() + 1 + 2))
			{
				position = TouchPosition.LEFT;
			}
			//Right
			else if ((theBall.getX() - theBall.getRadius() + 1 <= this.x + Paddle.WIDTH - 1)
					& (theBall.getX() - theBall.getRadius() + 1 >= this.x + Paddle.WIDTH - 1 - 2))
			{
				position = TouchPosition.RIGHT;
			}
		}	
		return position;
		
	}
	 */
}

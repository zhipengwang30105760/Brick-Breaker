// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We got help of debugging isTouching method by Dr.Dorn
import javafx.scene.paint.Color;
/**
 * This class is used to store state information corresponding to a single Brick within the brick breaker game model. 
 * It provides basic information about the Brick's position as well as 
 * information about the current strength of a Brick. 
 * All Bricks in brick breaker have a height of 20 and a width of 50, regardless of other state information.
 * @author zhipengwang@unomaha.edu ksone@unomaha.edu
 */
public class Brick 
{
	/**
	 * The fixed height of brick.
	 */
	private static final int HEIGHT = 20;
	/**
	 * The fixed width of brick.
	 */
	private static final int WIDTH = 50;
	/**
	 * The possible overlap that a ball can get in the brick.
	 */
	private final int overlap = 2;
	/**
	 * The x coordinate of the top-left corner of this brick.
	 */
	private int x;
	/**
	 * The y coordinate of the top-left corner of this brick.
	 */
	private int y;
	/**
	 * The strength for a brick.
	 */
	private int hits;
	
	/**
	 * Creates a basic Brick object at a specified position. The new Brick will default with 
	 * a strength of 3, meaning that it will break after 3 hits by a ball.
	 * @param topIn The y position of the top of this Brick in the model.
	 * @param leftIn The x position of the left side of this Brick in the model.
	 */
	public Brick(int topIn, int leftIn)
	{
		this.y = topIn;
		this.x = leftIn;
		this.hits = 3;
	}
	/**
	 * Creates a custom Brick object at a specified position with a specified strength.
	 * @param topIn The y position of the top of this Brick in the model.
	 * @param leftIn The x position of the left side of this Brick in the model.
	 * @param hitsIn The initial strength of this Brick. A value greater than zero 
	 * represents that this Brick will break after that number of hits.
	 * A value of 0 represents a "non-brick" or empty space in the model. 
	 * A value of -1 represents that thisBrick cannot be broken at all, regardless of the number of hits.
	 */
	public Brick(int topIn, int leftIn, int hitsIn)
	{
		this.x = leftIn;
		this.y = topIn;
		this.hits = hitsIn;
	}
	/**
	 * Retrieves the y coordinate of the top of this Brick.
	 * @return y The y coordinate of the top edge.
	 */
	public int getTop() 
	{
		return y;
	}
	/**
	 * Retrieves the x coordinate of the left side of this Brick.
	 * @return x The x coordinate of the left edge.
	 */
	public int getLeft()
	{
		return x;
	}
	/**
	 * Retrieves the width of this Brick.
	 * @return WIDTH The width of the Brick.
	 */
	public int getWidth()
	{
		return WIDTH;
	}
	
	/**
	 * Retrieves the height of this Brick.
	 * @return HEIGHT The height of the Brick.
	 */
	public int getHeight()
	{
		return HEIGHT;
	}
	/**
	 * The current color to represent this Brick's break ability state.
	 * @return color The color of the brick.
	 */
	public Color getColor()
	{	
		Color color = null;
		if (this.hits <= -1)
		{
			color = Color.BLACK;
			//System.out.println("BLACK");
		}
		else if (this.hits == 0)
		{
			color = Color.WHITE;
			//System.out.println("WHITE");
		}
		else if (this.hits == 1)
		{
			color = Color.RED;
			//System.out.println("RED");
		}
		else if (this.hits == 2)
		{
			color = Color.YELLOW;
			//System.out.println("YELLOW");
		}
		else if (this.hits >= 3)
		{
			color = Color.GREEN;
			//System.out.println("GREEN");
		}
		return color;
	}
	
	/**
	 * This mutator method will update this Brick's state data to account for being hit by the Ball once.
	 * @return result True when the hit caused this brick to break completely, false otherwise.
	 */
	public boolean hit()
	{
		boolean result = false;
		if (this.hits > 0) 
		{
			this.hits--;
			this.getColor();
			if (this.hits == 0)
			{
				result = true;
			}
		}
		return result;	
	}
	
	/**
	 * This method implements a collision detection algorithm to identify 
	 * whether this Brick is currently being hit by a given Ball object. 
	 * It will produce are turn value to signal which side, if any, is currently being hit.
	 * @param theBall The Ball to examine for collision with this Brick.
	 * @return position A valid TouchPosition state representing where theBall is intersecting this Brick.
	 * When no collision is detected at all or this Brick is already broken, NONE should be returned. 
	 * Otherwise, TOP, BOTTOM, LEFT, or RIGHT will be returned corresponding to 
	 * which side of this Brick is currently being hit by theBall.
	 */
	public TouchPosition isTouching(Ball theBall)
	{
		TouchPosition position = TouchPosition.NONE;
		if (this.hits != 0)
		{
			//Top
			if (this.checkTop(theBall))
			{
				position = TouchPosition.TOP;
			}
			//Bottom
			else if (this.checkBottom(theBall))
			{
				position = TouchPosition.BOTTOM;
			}
			//Left
			else if (this.checkLeft(theBall))
			{
				position = TouchPosition.LEFT;
			}
			//Right
			else if (checkRight(theBall))
			{
				position = TouchPosition.RIGHT;
			}
			
		}
		return position;
	}
	
	/**
	 * The isTouching method helper that is used to detect the ball hits brick's bottom side.
	 * @param theBall The coordinate of the ball's center.
	 * @return If true, ball hits bottom. If false, ball doesn't hit bottom.
	 */
	private boolean checkBottom(Ball theBall)
	{
		return (theBall.getX() + theBall.getRadius() >= this.x) 
				&& (theBall.getX() - theBall.getRadius() + 1 <= this.x + this.getWidth())
				&& (theBall.getY() - theBall.getRadius() + 1 <= this.y + this.getHeight()) 
				&& (theBall.getY() - theBall.getRadius() + 1 
						>= this.y + this.getHeight() - this.overlap);
	}
	/**
	 * The isTouching method helper that is used to detect the ball hits brick's top.
	 * @param theBall The coordinate of ball's center.
	 * @return If true, ball hits top. If false, ball doesn't hit top.
	 */
	private boolean checkTop(Ball theBall)
	{
		return ((theBall.getX() + theBall.getRadius() >= this.x) 
				&& (theBall.getX() - theBall.getRadius() + 1 <= this.x + this.getWidth()))
				&& ((theBall.getY() + theBall.getRadius() >= this.y)
				&& (theBall.getY() + theBall.getRadius() <= this.y + this.overlap));
	}
	/**
	 * The isTouching method helper that is used to detect the ball hits brick's left.
	 * @param theBall The coordinate of ball's center.
	 * @return If true, ball hits left. If false, ball doesn't hit left.
	 */
	private boolean checkLeft(Ball theBall)
	{
		return (theBall.getY() - theBall.getRadius() + 1 <= this.y + this.getHeight())
				&& (theBall.getY() + theBall.getRadius() >= this.y)
				&& (theBall.getX() + theBall.getRadius() >= this.x)
				&& (theBall.getX() + theBall.getRadius() <= this.x + this.overlap);
	}
	/**
	 * The isTouching method helper that is used to detect the ball hits brick's right.
	 * @param theBall The coordinate of ball's center.
	 * @return If true, ball hits right. If false, ball doesn't hit right.
	 */
	private boolean checkRight(Ball theBall)
	{
		
		return (theBall.getY() - theBall.getRadius() + 1 <= this.y + this.getHeight())
				&& (theBall.getY() + theBall.getRadius() >= this.y)
				&& (theBall.getX() - theBall.getRadius() + 1 <= this.x + this.getWidth())
				&& (theBall.getX() - theBall.getRadius() + 1 
						>= this.x + this.getWidth() - this.overlap);
	}
	
	/**
	 * Retrieves a String representation of this Brick's current object state. The string will beformatted as: 
	 * "Brick at (tlx, tly, brx, bry)" where tlx/tly are the x and y coordinates of the top/left cornerand brx/bry
	 * are the x and y coordinates of the bottom right corner of this Brick.
	 * @return "Brick at (tlx, tly, brx, bry)" where tlx/tly are the x and y coordinates of the top/left 
	 * cornerand brx/bry are the x and y coordinates of the bottom right corner of this Brick.
	 */
	public String toString()
	{
		return "Brick at " + "(" + x + ", " + y + ", "  + (x + WIDTH - 1) + ", " + (y + HEIGHT - 1) + ")";
	}
}	
		
	

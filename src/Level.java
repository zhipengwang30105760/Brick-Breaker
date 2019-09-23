// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We got help for brickHitChecking method from Ms. Vlasnik by adding a isTouching boolean
//variable to end the loop quickly. We got help of debugging updateOneStep() method by Dr. Dorn to figure out the 
//problem.
import javafx.scene.paint.Color;

/**
 * This class models a basic game level in the brick breaker game. 
 * It allows for the creation of new levels, simulation of game steps, and retrieval of current state information. 
 * All levels contain a grid of 5 rows and 7 columns of Bricks (some of which may have been broken),
 * a single Ball, and a single Paddle.
 * @author zhipengwang@unomaha.edu ksone@unomaha.edu
 */
public class Level 
{
	/**
	 * The fixed x coordinate of the first brick in brick array.
	 */
	private static final int X = 10;
	/**
	 * The fixed y coordinate of the first brick in brick array.
	 */
	private static final int Y = 40;
	/**
	 * The fixed numbers of row for the brickArray.
	 */
	private static final int ROW = 5;
	/**
	 * The fixed numbers of column for the brickArray.
	 */
	private static final int COL = 7;
	/**
	 * The fixed distance between each brick for both horizontal and vertical.  
	 */
	private static final int GAP = 5;
	/**
	 * The width of the game window.
	 */
	private int width;
	/**
	 * The height of the game window.
	 */
	private int height;
	/**
	 * The fixed distance from game window's bottom to paddle.
	 */
	private final int paddleDistance = 20;
	/**
	 * The ball used in the game.
	 */
	private Ball ball;
	/**
	 * The brick array used in the game.
	 */
	private Brick[][] brickArray;
	/**
	 * The gameState for a game.
	 */
	private GameState gameState;
	/**
	 * The paddle for the game.
	 */
	private Paddle paddle;
	
	/**
	 * Creates a default level with a given dimension. 
	 * The default brick configuration is a 5 row and7 column grid of Brick objects,
	 * each which requires 3 hits to break.
	 * Bricks are arranged starting 40 pixels from the top edge of the screen 
	 * and 10 pixels from the left edge of the screen. 
	 * Bricks should be spaced with 5 pixels between each Brick in both dimensions.
	 * The Ball will start in the center of the screen, 
	 * and the Paddle's top-left edge should be positioned at the left edge
	 * and 20 pixels up from the bottom of the screen.
	 * @param widthIn The logical width of the new level in pixels.
	 * @param heightIn The logical width of the new level in pixels.
	 */
	public Level(int widthIn, int heightIn)
	{
		//width of the window
		this.width = widthIn;
		//height of the window
		this.height = heightIn;
		//start x coordinate for first brick
		int originalX = X;
		//start y coordinate for first brick
		int originalY = Y;
		brickArray = new Brick[ROW][COL];
		int i, j;
		//This is a way to create a brickArray based on the requirement
		for (i = 0; i < brickArray.length; i++)
		{
			for (j = 0; j < brickArray[i].length; j++)
			{
				brickArray[i][j] = new Brick(originalY, originalX, 3);
				//originalX = originalX + Brick.WIDTH - 1 + this.gap - 1;
				//originalX = originalX + Brick.WIDTH - 1 + this.gap;
				originalX = originalX + brickArray[0][0].getWidth() + GAP;
			}
			originalX = X;
			//originalY = originalY + Brick.HEIGHT - 1 + this.gap - 1;
			//originalY = originalY + Brick.HEIGHT - 1 + this.gap;
			originalY = originalY + brickArray[0][0].getHeight() + GAP;
		}
		ball = new Ball(width / 2, height / 2); 
		//paddle = new Paddle(0, height - paddleDistance + 1);
		paddle = new Paddle(0, height - paddleDistance);
		gameState = GameState.PLAYING;
	}
	
	/**
	 * Creates a level with a given dimension and specified brick configuration. The Brick configuration
	 * is specified through an array of String values where each character corresponds to a single Brick.
	 * This array of Strings is guaranteed to contain 5 valid Strings, each of which will have 7 characters.
	 * Characters in the string will correspond to one value in {'*', '0', '1', '2', '3'} where: 
	 * '*' signifies a brick that cannot be broken
	 * '0' signifies a "ghost brick" that is already broken 
	 * '1' signifies a brick that requires one hit to break
	 * '2' signifies a brick that requires two hits to break 
	 * '3' signifies a brick that requires three hits to break 
	 * Input strings are assumed valid and no error checking is provided. 
	 * Bricks are arranged starting 40 pixels from the top edge of the screen and 10 pixels
	 * from the left edge of the screen. 
	 * Bricks should be spaced with 5 pixels between each Brick in both dimensions. 
	 * The Ball will start in the center of the screen, 
	 * and the Paddle's top-left edge should be positioned at the left
	 * edge and 20 pixels up from the bottom of the screen.
	 * @param widthIn The logical width of the new level in pixels.
	 * @param heightIn The logical height of the new level in pixels.
	 * @param brickConfig The configuration array specifying the grid of Bricks to use in this new level.
	 */
	public Level(int widthIn, int heightIn, String[] brickConfig)
	{
		this.width = widthIn;
		this.height = heightIn;
		//read the array from brickConfig.
		int[][] array = this.convert(brickConfig);
		//start x coordinate for the first brick
		int originalX = X;
		//start y coordinate for the first brick
		int originalY = Y;
		int i, j;
		brickArray = new Brick[5][7];
		//This is a way to create a brickArray based on the requirement
		for (i = 0; i < brickArray.length; i++)
		{
			for (j = 0; j < brickArray[i].length; j++)
			{
				brickArray[i][j] = new Brick(originalY, originalX, array[i][j]);
				//originalX = originalX + Brick.WIDTH - 1 + this.gap - 1;
				//originalX = originalX + Brick.WIDTH - 1 + this.gap;
				originalX = originalX + brickArray[0][0].getWidth() + Level.GAP;
			}
			originalX = X;
			//originalY = originalY + Brick.HEIGHT - 1 + this.gap - 1;
			//originalY = originalY + Brick.HEIGHT - 1 + this.gap;
			originalY = originalY + brickArray[0][0].getHeight() + Level.GAP;
		}
		//ball in the center of the window
		ball = new Ball(width / 2, height / 2); 
		//paddle = new Paddle(0, height - paddleDistance + 1);
		paddle = new Paddle(0, height - paddleDistance);
		gameState = GameState.PLAYING;
	}
	/**
	 * The helper method that convert the brickConfig array from Level specific constructor
	 * to an two-dimensional integer array.
	 * @param brickConfig The configuration array specifying the grid of Bricks to use in this new level.
	 * @return array The array that contains all the specified hints for brick array.
	 */
	private int[][] convert(String[] brickConfig)
	{
		int i, j;
		//new array to store the value of brickConfig that convert to integer
		int[][] array = new int[ROW][COL];
		for (i = 0; i < array.length; i++)
		{
			for (j = 0; j < array[i].length; j++)
			{
				switch (brickConfig[i].charAt(j))
				{
					case '*': array[i][j] = -1;
						break;
					case '0': array[i][j] = 0;
						break;
					case '1': array[i][j] = 1;
						break;
					case '2': array[i][j] = 2;
						break;
					case '3': array[i][j] = 3;	
						break;
					default: 
						System.out.println("Wrong");
				}		
			}
			System.out.println();
		}
		return array;
	}
	/**
	 * This method updates this level's model data based on simulating one time step in the game. Specifically the 
	 * following will occur: 
	 * (1) The ball will be moved based on its last known trajectory, 
	 * (2) The ball will bounce off the side walls of the screen horizontally if hitting the 
	 * left or right sides, and vertically if hitting the top. 
	 * (3) The ball will bounce in the appropriate direction off the paddle if they are touching
	 * (4) The ball will bounce in the appropriate direction off any Brick 
	 * it is currently touching and the Brick will 
	 * react as required when hit by a Ball. 
	 * (5) The end game state (won or lost) will be updated if all possible Bricks are broken or the
	 * Ball hits the bottom of the screen, respectively.
	 */
	public void updateOneStep()
	{
		if (gameState == GameState.PLAYING)
		{
			ball.moveOnce();
			this.wallHitChecking();
			this.topHitChecking();
			this.paddleHitChecking();
			this.brickHitChecking();
			//this.getGameStatus();
			//this.checkGameState();
			// if the ball didn't touch anything, game state doesn't need to be changed
			// and we don't need to do anything. 
		}	
	}
	/**
	 * The updateOneStep helper method that check whether the ball touch the
	 * left side or right side wall or not. If it touched, it will cause bounce horizontal.
	 */
	private void wallHitChecking()
	{
		//making sure that ball would not touch the bottom 
		//or top and it touches left or right edge.
		//ball bounce on left or right. X trajectory * -1 and game still playing.
		if (ball.getX() + ball.getRadius() >= this.width - 1 
				|| ball.getX() - ball.getRadius() <= 0)
		{
			ball.bounceHorizontal();
		}
	}
	/**
	 * The updateOneStep helper method that check whether the ball touch the
	 * top of the game's window or not. If touched, it will cause bounce vertical.
	 */
	private void topHitChecking()
	{
		if (ball.getY() - ball.getRadius() <= 0)
		{
			ball.bounceVertical();
		}
	}
	/**
	 * The updateOneStep helper method that check whether the ball touch the
	 * paddle or not. If touched, it will bounce based on which side the ball touched.
	 */
	
	private void paddleHitChecking()
	{
		if (paddle.isTouching(ball) == TouchPosition.TOP)
		{
			ball.bounceVertical();
		}
		else if (paddle.isTouching(ball) == TouchPosition.LEFT 
				|| paddle.isTouching(ball) == TouchPosition.RIGHT)
		{
			ball.bounceHorizontal();
		}
		//gameState = GameState.PLAYING;
	}
	
	/**
	 * The updateOneStep helper method that check whether the ball touch the
	 * brick or not. If touched, the ball will bounce based on which side it touched. 
	 */
	private void brickHitChecking()
	{
		int i, j;
		boolean isTouching = false;
		for (i = ROW - 1; i >= 0 && !isTouching; i--)
		{
			for (j = 0; j < COL && !isTouching; j++)
			{
				if (brickArray[i][j].isTouching(ball) == TouchPosition.BOTTOM 
						|| brickArray[i][j].isTouching(ball) == TouchPosition.TOP)
				{
					ball.bounceVertical();
					brickArray[i][j].hit();
					isTouching = true;
					//System.out.printf("Touching brick[%d][%d]\n", i, j);
					//System.out.println(brickArray[i][j].getColor());
				}
				else if (brickArray[i][j].isTouching(ball) == TouchPosition.LEFT
						|| brickArray[i][j].isTouching(ball) == TouchPosition.RIGHT)
				{
					ball.bounceHorizontal();
					brickArray[i][j].hit();
					isTouching = true;
					//System.out.printf("Touching brick[%d][%d]\n", i, j);
					//System.out.println(brickArray[i][j].getColor());
				}
			}
		}
	}
	/**
	 * Retrieves the Ball object in this Level.
	 * @return ball The Ball used in the model.
	 */
	public Ball getBall()
	{
		return ball;
	}
	/**
	 * Retrieves a 5 by 7 array of Brick objects corresponding to the current grid of Bricks in the model.
	 * @return bricks Data for the Bricks in the game level.
	 */
	public Brick[][] getBricks()
	{
		return brickArray;
	}
	/**
	 * Retrieves state information about the game's current progress based on the last simulated step.
	 * @return gameState The appropriate state if the user has WON or LOST the game. 
	 * If neither,PLAYING will be reported.
	 */
	public GameState getGameStatus()
	{
		int total = 0; 
		int i, j;
		if (ball.getY() + ball.getRadius() < this.height)
		{
			for (i = 0; i < ROW; i++)
			{
				for (j = 0; j < COL; j++)
				{
					if (brickArray[i][j].getColor() == Color.BLACK
							|| brickArray[i][j].getColor() == Color.WHITE)
					{
						total = total + 1;
					}
				}
			}
			//System.out.printf("current broken brick is %d\n", total); 
			if (total == COL * ROW)
			{
				gameState = GameState.WON;
			}
		}
		else if (ball.getY() + ball.getRadius() >= this.height)
		{
			gameState = GameState.LOST;
		}
		return gameState;
	}
	/**
	 * Retrieves the Paddle object in this Level.
	 * @return paddle The Paddle used in the model.
	 */
	public Paddle getPaddle()
	{
		return paddle;
	}
}

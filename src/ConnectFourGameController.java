import java.awt.Graphics;

import javax.swing.JPanel;


public class ConnectFourGameController 
{
	private IConnectFourWinner winner;
	
	private Slot[][] grid;
	private Slot mostRecent;

	private int player; // 0 = yellow player, 1 = red player
	private int occupiedSlots;
	private int winningPlayer; // 0 = yellow player, 1 = red player

	public static final int EMPTY_SLOT_COLOR_ID = 0;
	public static final int YELLOW_PLAYER_ID = 1;
	public static final int RED_PLAYER_ID = 2;

	private static int GAME_SIZE = 0;
	private static int AMOUNT_TO_WIN = 0;
	private static int MAX_SLOT_COUNT;
	private static int x = 0;
	private static int y = 0;

	/**
	 * 
	 * @param gridX initial x point
	 * </br>
	 * <strong>gridY</strong> initial y point
	 * </br>
	 * <strong>gridSize</strong> amount of slots in 1 row or column
	 *  </br>
	 * <strong>amountToWin</strong> amount of slots needed to win
	 *  </br>
	 * <strong>winner</strong> functionality to determine winner
	 * 
	 */
	public ConnectFourGameController(int gridX, int gridY, int gridSize, int amountToWin, IConnectFourWinner winner)
	{
		GAME_SIZE = gridSize;
		grid = new Slot[GAME_SIZE][GAME_SIZE];
		x = gridX;
		y = gridY;
		this.winner = winner;
		AMOUNT_TO_WIN = amountToWin;
		MAX_SLOT_COUNT = GAME_SIZE * GAME_SIZE;
		initialize();
	}

	/**
	 * Initializes the position of the slots in the grid relative to (x,y)
	 */
	private void initialize()
	{
		int currX = x;
		int currY = y;
		for (int i = 0; i < GAME_SIZE; i++)
		{
			for (int j = 0; j < GAME_SIZE; j++)
			{
				grid[i][j] = new Slot(currX, currY, i, j);
				currX += Slot.SIZE_X;

			}
			currY += Slot.SIZE_Y;
			currX =x;
		}

		player = YELLOW_PLAYER_ID; //Setting up first player
		winningPlayer = 0;
		occupiedSlots = 0;
	}

	/**
	 * Sets the values in the slot's in the grid to empty
	 */
	public void clearGrid()
	{
		for (int i = 0; i < GAME_SIZE; i++)
		{
			for (int j = 0; j < GAME_SIZE; j++)
			{
				grid[i][j].makeSlotEmpty();
			}
		}
		player = YELLOW_PLAYER_ID; //Setting up first player
		occupiedSlots = 0;
	}
	/**
	 * Checks if the grid is full
	 * @return <strong>True</strong> if the grid is full
	 * </br>
	 * <strong>False</strong> if the grid is not full
	 */
	public boolean isGridFull()
	{
		if (occupiedSlots == MAX_SLOT_COUNT)
			return true;

		return false;
	}

	
	public void draw(Graphics g, JPanel panel)
	{
		for (int i = 0; i < GAME_SIZE; i++)
		{
			for (int j = 0; j < GAME_SIZE; j++)
			{
				grid[i][j].draw(g, panel);
			}
		}
	}
	
	/**
	 * Checks if its possible to drop a chip in the desired column. If so
	 * the method will drop the chip in the appropriate row.
	 * @param column integer of the desired Column to drop the chip
	 * @return <strong>True</strong> if the chip has been dropped
	 * <br><strong>False</strong> if the column is full and chip cannot be dropped<br/>
	 */
	public boolean dropChip(int column)
	{
		for (int j = GAME_SIZE-1; j >= 0; j--) //Starts from the bottom of the column and works its way up
		{
			if(grid[j][column].getSlotColor() == Slot.EMPTY_SLOT_COLOR_ID)
			{
				if(player == YELLOW_PLAYER_ID)
				{
					grid[j][column].changeToYellow();
				}
				else
					grid[j][column].changeToRed();

				mostRecent = grid[j][column];
				occupiedSlots++;
				
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if the most recent move is the winning move. 
	 * @return true when move was the winning move, else false
	 * if it was not the winning move.  
	 */
	public boolean checkWinner()
	{
		boolean isWinner = winner.isWinner(mostRecent, grid);
		
		if(isWinner)
			winningPlayer = player;
		
		return isWinner;
		
	}

	public void nextPlayer()
	{
		if (player == YELLOW_PLAYER_ID)
		{
			player = RED_PLAYER_ID;
		}
		else
			player = YELLOW_PLAYER_ID; 
	}

	public String getWinner()
	{
		if (winningPlayer == YELLOW_PLAYER_ID)
			return "Player 1 (yellow)";
		else
			return "Player 2 (red)";
	}


	public static int getGAME_SIZE() 
	{
		return GAME_SIZE;
	}

	public static int getAMOUNT_TO_WIN() 
	{
		return AMOUNT_TO_WIN;
	}

	public static int getX() 
	{
		return x;
	}

	public static int getY() 
	{
		return y;
	}

}

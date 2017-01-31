
public class NormalWinning implements IConnectFourWinner
{

	public boolean isWinner(Slot mostRecent, Slot[][] grid)
	{
		int amountToWin = ConnectFourGameController.getAMOUNT_TO_WIN();
		int gameSize = ConnectFourGameController.getGAME_SIZE();
		int count = 0;

		int row = mostRecent.getRow();
		int column = mostRecent.getColumn();

		int value = grid[row][column].getSlotColor();


		// Check for winner to the right of the most recently placed chip
		while (column < gameSize && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			column++;
		}

		
		// Check for winner to the left of the most recently placed chip
		column = mostRecent.getColumn() - 1;

		while (column >= 0 && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			column--;
		}
		
		
		// Check for winner below the most recently placed chip
		count = 0;
		column = mostRecent.getColumn();

		while (row < gameSize && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			row++;
		}


		// Check for winner above the most recently placed chip
		row = mostRecent.getRow() - 1;

		while (row >= 0 && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			row--;
		}


		// Check for winner diagonally upwards to the right (top half)
		count = 0;
		row = mostRecent.getRow();

		while (row >= 0 && column < gameSize && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			row--;
			column++;
		}


		// Check for winner diagonally upwards to the right (bottom half)
		row = mostRecent.getRow() + 1;
		column = mostRecent.getColumn() - 1;

		while (row < gameSize && column >= 0 && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			row++;
			column--;
		}


		// Check for winner diagonally upwards to the left (bottom half)
		count = 0;
		row = mostRecent.getRow();
		column = mostRecent.getColumn();

		while (row < gameSize && column < gameSize && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			row++;
			column++;
		}

		
		// Check for winner diagonally upwards to the left (top half)
		row = mostRecent.getRow() - 1;
		column = mostRecent.getColumn() - 1;

		while (row >= 0 && column >= 0 && grid[row][column].getSlotColor() == value)
		{
			count++;

			if (count >= amountToWin)
				return true;
			

			row--;
			column--;
		}
	
	return false;
	}

}

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Arrow
{
	private int x;
	private int y;
	private int column = 0;
	private int row = 0;
	private static BufferedImage image;
	
	private static final int SIZE_X = 75;
	private static final int SIZE_Y = 75;
	
	private int startingBoundaryX;
	private int startingBoundaryY;
	private int endingBoundaryX;
	private int endingBoundaryY;
	
	public Arrow (int x, int y, int startingBoundaryX, 
			int startingBoundaryY, int endingBoundaryX, int endingBoundaryY)
	{
		this.x = x;
		this.y = y;
		this.startingBoundaryX = startingBoundaryX;
		this.startingBoundaryY = startingBoundaryY;
		this.endingBoundaryX = endingBoundaryX;
		this.endingBoundaryY = endingBoundaryY;
	}
	
	public boolean moveLeft()
	{
		x -= SIZE_X;

		if (x < startingBoundaryX)
		{
			x += SIZE_X;
			return false;
		}
		column--;
		return true;
	}
	
	public boolean moveRight()
	{
		x += SIZE_X;
		if (x > endingBoundaryX)
		{
			x -= SIZE_X;
			return false;
		}
		column++;
		return true;
		
	}
	
	public void draw(Graphics g, JPanel panel)
	{
			
		g.drawImage(image, x, y, SIZE_X, SIZE_Y, null);
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public static int getSizeX()
	{
		return SIZE_X;
	}
	
	public static int getSizeY()
	{
		return SIZE_Y;
	}
	
	public static void setImage(BufferedImage i)
	{
		image = i;
	}

}

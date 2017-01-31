

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Slot 
{
	private int x;
	private int y;
	private int slotColor; // 0 = empty, 1 = yellow, 2 = red
	private BufferedImage image;
	private int row;
	private int column;
	
	private static BufferedImage redSlot;
	private static BufferedImage yellowSlot;
	private static BufferedImage emptySlot;
	
	public static final int EMPTY_SLOT_COLOR_ID = 0;
	public static final int YELLOW_SLOT_COLOR_ID = 1;
	public static final int RED_SLOT_COLOR_ID = 2;
	public static final int SIZE_X = 75;
	public static final int SIZE_Y = 75;
	
	public Slot(int x, int y, int row, int column)
	{
		this.x = x;
		this.y = y;
		this.row = row;
		this.column = column;
		this.makeSlotEmpty();
	}
	
	public void draw(Graphics g, JPanel panel)
	{
		g.drawImage(image, x, y, SIZE_X, SIZE_Y, panel);
	}
	
	public static void setRedSlot(BufferedImage redSlot) {
		Slot.redSlot = redSlot;
	}
	public static void setYellowSlot(BufferedImage yellowSlot) {
		Slot.yellowSlot = yellowSlot;
	}
	public static void setEmptySlot(BufferedImage emptySlot) {
		Slot.emptySlot = emptySlot;
	}
	
	public static int getSizeX()
	{
		return SIZE_X;
	}
	
	public static int getSizeY()
	{
		return SIZE_Y;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	public int getSlotColor()
	{
		return slotColor;
	}
	
	public void changeToRed()
	{
		image = Slot.redSlot;
		slotColor = Slot.RED_SLOT_COLOR_ID;
	}
	
	public void changeToYellow()
	{
		image = Slot.yellowSlot;
		slotColor = Slot.YELLOW_SLOT_COLOR_ID;
	}
	
	public void makeSlotEmpty()
	{
		image = Slot.emptySlot;
		slotColor = Slot.EMPTY_SLOT_COLOR_ID;
	}
	
}


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Connect4 extends JFrame
{	
	
	public final static int SCREEN_SIZE_X = 850;
	public final static int SCREEN_SIZE_Y = 850;
	
	public final static int DEFAULT_WIN = 4;
	public final static int DEFAULT_SIZE = 7;
	
	public final static int MAX_WIN = 9;
	public final static int MAX_SIZE = 9;
	
	public static void main(String[] args)
	{
		int amountToWin;
		int size;
		
		try
		{
			size = Integer.parseInt(args[0]);
			amountToWin = Integer.parseInt(args[1]);
			
			if(size > MAX_SIZE) // If the desired size exceeds the max size the program allows
			{
				
				JOptionPane.showMessageDialog(new JFrame("Error!"),
					    "Size is too big, game will continue with default values.",
					    "Error!", JOptionPane.WARNING_MESSAGE);
				amountToWin = DEFAULT_WIN;
				size = DEFAULT_SIZE;
			}
			
			if(amountToWin > size || amountToWin == 0 && size == 0) // Cannot have a winner
			{
				JOptionPane.showMessageDialog(new JFrame("Error!"),
					    "This game wont have a winner, game will continue with default values.",
					    "Error!", JOptionPane.WARNING_MESSAGE);
				amountToWin = DEFAULT_WIN;
				size = DEFAULT_SIZE;
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame("Error!"),
				    "Error processing inputs, game will continue with default values.",
				    "Error!", JOptionPane.WARNING_MESSAGE);
			amountToWin = DEFAULT_WIN;
			size = DEFAULT_SIZE;
		}
		Connect4 connect4 = new Connect4();
		connect4.start(size, amountToWin);
	}
	
	// Load game board
	private void start(int size, int amountToWin) 
	{
		JFrame frame = new JFrame("Connect 4");
		
		frame.setSize(SCREEN_SIZE_X, SCREEN_SIZE_Y);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel game = new ConnectFourGame(size, amountToWin, SCREEN_SIZE_X, SCREEN_SIZE_Y);
		
		frame.add(game);
		
		frame.setVisible(true);
	}
	
}

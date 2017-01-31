import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ConnectFourGame extends JPanel implements KeyListener
{
	
	private ConnectFourGameController connectFour;
	private Arrow arrow;
	
	private final static int BUTTON_PANEL_Y = 40;
	private final static int BUTTON_PANEL_X = 100;
	
	private final static String CONTROLS_MESSAGE = "-To move left or right, press the left and right arrow keys.\n" +
			"-To insert a chip into a slot, press the down arrow key.\n";
	
	public ConnectFourGame(int size, int amountToWin, int gameSizeX, int gameSizeY)
	{
		loadImages();
		connectFour = new ConnectFourGameController(
				gameSizeX/2 - size*Slot.getSizeX()/2, 
				(gameSizeY + BUTTON_PANEL_Y)/2 - size*Slot.getSizeY()/2, 
				size, amountToWin, new NormalWinning());
		
		
		arrow = new Arrow(ConnectFourGameController.getX(), 
				ConnectFourGameController.getY() - Arrow.getSizeY(), 
				ConnectFourGameController.getX(), 0, 
				ConnectFourGameController.getX() + 
				(ConnectFourGameController.getGAME_SIZE() - 1)*Slot.SIZE_X,0);
		
		
		loadComponents();
		loadGame();
		
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	
	private void loadGame()
	{
		setBackground(Color.cyan);
		connectFour.clearGrid();
		this.setFocusable(true);
		repaint();
	}

	private void loadImages()
	{
		try 
		{
			URL in = getClass().getClassLoader().getResource("slot.png");
			
			BufferedImage image = ImageIO.read(in);
			Slot.setEmptySlot(image);
			
			in = getClass().getClassLoader().getResource("red_slot.png");
			image = ImageIO.read(in);
			Slot.setRedSlot(image);
			
			in = getClass().getClassLoader().getResource("yellow_slot.png");
			image = ImageIO.read(in);
			Slot.setYellowSlot(image);
			
			in = getClass().getClassLoader().getResource("arrow.png");
			image = ImageIO.read(in);
			Arrow.setImage(image);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	
	private void loadComponents()
	{
		// Creates button that displays instructions
		JButton controls = new JButton();
		controls.setPreferredSize(new Dimension(BUTTON_PANEL_X, BUTTON_PANEL_Y/2));
		controls.setText("Controls");
		controls.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(new JFrame("Controls"),
					    CONTROLS_MESSAGE,
					    "Controls", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		// Creates button that helps with creating a new game
		JButton newGame = new JButton();
		newGame.setPreferredSize(new Dimension(BUTTON_PANEL_X, BUTTON_PANEL_Y/2));
		newGame.setText("New Game");
		newGame.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				JFrame NewGame = new JFrame("New Game?");
				NewGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				int n = JOptionPane.showConfirmDialog(NewGame, 
						"Would you like to start a new game?",
						"New Game?", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION)
				{
					loadGame();
				}
			}
			
		});
		
		controls.setFocusable(false);
		newGame.setFocusable(false);
		add(controls);
		add(newGame);
	}
	
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		connectFour.draw(g, this);
		arrow.draw(g, this);
	}
	
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT)
		{
			if (arrow.moveLeft())
				this.repaint();
		}
		else if (keyCode == KeyEvent.VK_RIGHT)
		{
			if (arrow.moveRight())
				this.repaint();
		}
		else if (keyCode == KeyEvent.VK_DOWN)
		{
			if (connectFour.dropChip(arrow.getColumn()))
			{
				repaint();
				
				boolean playAgain = false;
				if ( (playAgain = connectFour.checkWinner() ))
				{
					JOptionPane.showMessageDialog(new JFrame("We have a winner!"),
						    connectFour.getWinner() + " has won!",
						    "We have a winner!", JOptionPane.INFORMATION_MESSAGE);
				}
				else if ( (playAgain = connectFour.isGridFull()) )
				{
					System.out.println("The board is full. There is no winner!");
				
					JOptionPane.showMessageDialog(new JFrame("We have a tie!"),
						    "We have a tie! Nobody wins!", "We have no winner!",
						    JOptionPane.INFORMATION_MESSAGE);
				}
				
				if(playAgain)
				{
					JFrame jf = new JFrame("Play again?");
					jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					int n = JOptionPane.showConfirmDialog(jf, 
							"Would you like to play again?",
							"Play again?", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION)
					{
						this.loadGame();
						return;
					}
					else 
					{
						System.exit(0);
					}
				}
				
				connectFour.nextPlayer();
				
			}
			
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{
		
	}
	
}

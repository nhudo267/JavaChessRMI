import javax.swing.*;

import java.awt.*;
import java.rmi.registry.Registry;
import java.util.Objects;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
public class ChessClient extends JFrame 
{
	private static final long serialVersionUID = 1L;// i need it for some reason
	
	//String
	public static String serverIP = "";						//String will hold IP
	
	//booleans for loop operations and method status checks
	public static boolean ipEntered = false;				// when let the client know when the user has entered the IP
	public static boolean connectionFailed = false; 		// used to control loop for connecting
	public static boolean playerTurn = false;				// lets the client know whether
	public static boolean gameSelected = false; 			// used for loop selecting game
	public static boolean pieceSelected = false;			// used for loops and methods
	public static boolean gameSuccessfullyJoined = false;  	// used for loops and methods
	public static boolean kingTaken = false;				// used for loops and methods
	public static boolean gameStarted = false;				// used for loops and methods
	public static boolean gameExited = false;
	
	//ints for storing game data
	public static int gameSessionSelected = -1; 			// will let the client know which session to interact with
	public static int playerColor = 0; 						// will let client know whether it is the white or black pieces
	public static int selectionX = -1; 						// used to pass where to move pieces to the swap method
	public static int selectionY = -1;						// used to pass where to move pieces to the swap method
	public static int moves = 0;							// keeps track of available moves
	
	// create UI JFrame objects
	private JPanel pan = new JPanel(); 								//panel that holds it all
	public static JButton[][] buttonArray = new JButton[8][8]; 		//array that stores game buttons
	private JLabel promptLab = new JLabel("Welcome to JavaChess!"); //label that prompts player
	private static JLabel playerColorLab = new JLabel(""); 			//label that tells player what color they are
	public static JButton quitBut = new JButton("Quit");  			//quit button
	
	//Image icons for all different pieces - self explanatory
	ImageIcon wpawn = new ImageIcon(getClass().getResource("wpawn.png"));
	ImageIcon bpawn = new ImageIcon(getClass().getResource("bpawn.png"));
	ImageIcon wbishop = new ImageIcon(getClass().getResource("wbishop.png"));
	ImageIcon bbishop = new ImageIcon(getClass().getResource("bbishop.png"));
	ImageIcon wrook = new ImageIcon(getClass().getResource("wrook.png"));
	ImageIcon brook = new ImageIcon(getClass().getResource("brook.png"));
	ImageIcon wqueen = new ImageIcon(getClass().getResource("wqueen.png"));
	ImageIcon bqueen = new ImageIcon(getClass().getResource("bqueen.png"));
	ImageIcon wking = new ImageIcon(getClass().getResource("wking.png"));
	ImageIcon bking = new ImageIcon(getClass().getResource("bking.png"));
	ImageIcon wknight = new ImageIcon(getClass().getResource("wknight.png"));
	ImageIcon bknight = new ImageIcon(getClass().getResource("bknight.png"));
	ImageIcon blank = new ImageIcon(getClass().getResource("blank.png"));

	//create client and other needed frames
	private static ChessClient client;
	private static AddressFrame addressFrame;
	private static SessionFrame sessionFrame;

	// create registries and sessions
	private static Registry[] reg = new Registry[3];
	private static GameSessionInterface[] sesh = new GameSessionInterface[3];

	
	public ChessClient() 
	{
		this.setSize(500, 650);
	
		// use awt.Toolkit to get screensize and set xPos and yPos to the center of the screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		
		// set location to center defined by xPos and yPos
		this.setLocation(xPos, yPos);
		
		//use removeMinMaxClose method to keep user from closing window with X but still be able to drag window
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		promptLab.setFont(new Font("Dialog", Font.BOLD, 17));
		playerColorLab.setFont(new Font("Dialog", Font.BOLD, 15));
		
		// make pan have a GridBagLayout
		pan = new JPanel();
		pan.setLayout(new GridBagLayout());

		// create variable c for constraints
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 10;
		c.gridx = 0;
		c.gridy = 0;

		pan.add(promptLab, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 2;
		c.weighty = 2;
		c.gridwidth = 1;

		// initialize each button in the array and match the color accordingly
		for (int i = 0; i <= 7; i++) 
		{
			for (int j = 0; j <= 7; j++) 
			{
				buttonArray[i][j] = new JButton();
				buttonArray[i][j].addActionListener(new ButtonListener());
				if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) 
				{
					buttonArray[i][j].setBackground(Color.gray);
				} 
				else 
				{
					buttonArray[i][j].setBackground(Color.white);
				}
			}
		}

		for (int i = 1; i <= 8; i++) 
		{
			for (int j = 1; j <= 8; j++) 
			{
				c.gridx = i;
				c.gridy = j;
				pan.add(buttonArray[i - 1][j - 1], c);
			}
		}

		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.CENTER;
		quitBut.addActionListener(new ButtonListener());
		pan.add(quitBut, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 3;
		c.gridx = 4;
		c.gridy = 10;

		pan.add(playerColorLab, c);
		
		this.add(pan);
	}
	
	public static void main(String[] args) throws InterruptedException, RemoteException 
	{
		getIPAndConnect();

		while (true) 
		{
			promptSessions();
			game();
		}
	}	
  
  
  }

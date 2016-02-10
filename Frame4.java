import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class Frame4 extends JFrame implements MouseListener, ActionListener
{
	// ### field ###
	// battleship game instance
	Battleship gameInstance = new Battleship();
	// game table
	JTable gameTable = new JTable(10,10);
	// button
	JButton goButton = new JButton("Go");
	// set counter
	boolean showDone = false;
	int setCounter = 0;
	// ship type
	int shipType[] = new int[18];
	// label
	JLabel playerShiphealthCompLabel = new JLabel("YOUR SHIP STATUS");
	JLabel playerShip0 = new JLabel("        Aircraft Carrier");
	JLabel playerShip1 = new JLabel("        Battleship");
	JLabel playerShip2 = new JLabel("        Cruiser");
	JLabel playerShip3 = new JLabel("        Destroyer I");
	JLabel playerShip4 = new JLabel("        Destroyer II");
	JLabel playerShip5 = new JLabel("        Submarine I");
	JLabel playerShip6 = new JLabel("        Submarine II");
	JLabel compShiphealthCompLabel = new JLabel("ENEMY SHIP STATUS");
	JLabel compShip0 = new JLabel("        Aircraft Carrier");
	JLabel compShip1 = new JLabel("        Battleship");
	JLabel compShip2 = new JLabel("        Cruiser");
	JLabel compShip3 = new JLabel("        Destroyer I");
	JLabel compShip4 = new JLabel("        Destroyer II");
	JLabel compShip5 = new JLabel("        Submarine I");
	JLabel compShip6 = new JLabel("        Submarine II");
	
	// ### constructor ### 
	public Frame4()
	{
		// frame
		super("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(150,150,880,650);
		setResizable(false);
		// main panel
		Container mainPanel = getContentPane();
		// menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// menu
		JMenu startMenu = new JMenu("Start");
		menuBar.add(startMenu);
		JMenuItem aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		// menu item
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setEnabled(false);
		JMenuItem loadMenuItem = new JMenuItem("Load");
		loadMenuItem.setEnabled(false);
		JMenuItem highScoreMenuItem = new JMenuItem("High Score");
		highScoreMenuItem.setEnabled(false);
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		newMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		loadMenuItem.addActionListener(this);
		highScoreMenuItem.addActionListener(this);
		quitMenuItem.addActionListener(this);
		startMenu.add(newMenuItem);
		startMenu.add(saveMenuItem);
		startMenu.add(loadMenuItem);
		startMenu.add(highScoreMenuItem);
		startMenu.add(quitMenuItem);
		// how to play
		JMenuItem howToPlayMenuItem = new JMenuItem("How to play");
		aboutMenu.add(howToPlayMenuItem);
		howToPlayMenuItem.addActionListener(this);
		// user panel
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(25,1));
		// game panel
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(null);
		// split panel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,userPanel,gamePanel);
		mainPanel.add(splitPane,BorderLayout.CENTER);
		splitPane.setEnabled(false);
		splitPane.setDividerSize(5);
		splitPane.setDividerLocation(230);
		// table
		gamePanel.add(gameTable);
		gameTable.setEnabled(false);
		// gameTable.setForeground(Color.WHITE);
		// gameTable.setBackground(Color.BLUE);
		gameTable.setRowHeight(50);
		gameTable.setBounds(70,50,500,500);
		gameTable.setBorder(BorderFactory.createRaisedBevelBorder());
		// button
		goButton.setEnabled(false);
		goButton.addActionListener(this);
		// game work
		gameTable.addMouseListener(this);
		// user panel grid setup
		Border paddingBorder = BorderFactory.createEmptyBorder(10,5,10,10);
		Border outsideBorder = BorderFactory.createRaisedBevelBorder();
		playerShiphealthCompLabel.setBorder(BorderFactory.createCompoundBorder(outsideBorder, paddingBorder));
		userPanel.add(playerShiphealthCompLabel);
		userPanel.add(new JLabel(""));
		userPanel.add(playerShip0);
		userPanel.add(playerShip1);
		userPanel.add(playerShip2);
		userPanel.add(playerShip3);
		userPanel.add(playerShip4);
		userPanel.add(playerShip5);
		userPanel.add(playerShip6);
		playerShip0.setEnabled(false);
		playerShip1.setEnabled(false);
		playerShip2.setEnabled(false);
		playerShip3.setEnabled(false);
		playerShip4.setEnabled(false);
		playerShip5.setEnabled(false);
		playerShip6.setEnabled(false);
		userPanel.add(new JLabel(""));
		compShiphealthCompLabel.setBorder(BorderFactory.createCompoundBorder(outsideBorder, paddingBorder));
		userPanel.add(compShiphealthCompLabel);
		userPanel.add(new JLabel(""));
		userPanel.add(compShip0);
		userPanel.add(compShip1);
		userPanel.add(compShip2);
		userPanel.add(compShip3);
		userPanel.add(compShip4);
		userPanel.add(compShip5);
		userPanel.add(compShip6);
		compShip0.setEnabled(false);
		compShip1.setEnabled(false);
		compShip2.setEnabled(false);
		compShip3.setEnabled(false);
		compShip4.setEnabled(false);
		compShip5.setEnabled(false);
		compShip6.setEnabled(false);
		userPanel.add(new JLabel(""));
		userPanel.add(new JLabel(""));
		userPanel.add(new JLabel(""));
//		userPanel.add(goButton);
		// frame
		setVisible(true);
		// shipType
		shipType[0] = 0;
		shipType[1] = 0;
		shipType[2] = 0;
		shipType[3] = 0;
		shipType[4] = 0;
		shipType[5] = 1;
		shipType[6] = 1;
		shipType[7] = 1;
		shipType[8] = 1;
		shipType[9] = 2;
		shipType[10] = 2;
		shipType[11] = 2;
		shipType[12] = 3;
		shipType[13] = 3;
		shipType[14] = 4;
		shipType[15] = 4;
		shipType[16] = 5;
		shipType[17] = 6;
	}
	
	// updates game grid during game time
	public void updateGameTable()
	{
	  for(int i = 0; i <= 9; i++)
		{
	  	for(int j = 0; j <= 9; j++)
	  	{
	  	  int state = gameInstance.gridComp[i][j];
	  	  if(state == 9)
	  	  {
	  	    gameTable.setValueAt("     HIT",i,j);
	  	  }
	  	  else if(state == 8)
	  	  {
	  	    gameTable.setValueAt("       *",i,j);
	  	  }
	  	  else
	  		{
	  	  	gameTable.setValueAt("",i,j);
	  		}
	  	}
	  }
	}
	
  // updates game grid during player grid setting
	public void updateSetTable()
	{
	  for(int i = 0; i <= 9; i++)
		{
	  	for(int j = 0; j <= 9; j++)
	  	{
	  	  int state = gameInstance.grid[i][j];
	  	  if(state == 7)
	  		{
	  	    gameTable.setValueAt("",i,j);
	  		}
	  	  else
	  		{
	  	    gameTable.setValueAt("      X",i,j);
	  		}
	  	}
	  }
	}
	
	// ### action listener ###
	public void actionPerformed(ActionEvent me)
	{
	  if(me.getActionCommand() == "New")
	  {
	    System.out.println("Pressed: New");
	    setCounter = 0;
	    System.out.println("set counter reseted");
	    updateSetTable();
	    System.out.println("Set grid updated");
			Battleship battleship = new Battleship();
			gameInstance = battleship;
			System.out.println("Game instance created");
			gameInstance.setGrid();
			System.out.println("Blank grid generated for player and computer");
			gameInstance.setCompShips();
			System.out.println("Computer ship placement done");
			System.out.println("Computer grid copied");
			gameInstance.setGameModeOff();
			System.out.println("Game mode 0FF");
			gameInstance.setSetModeOn();
			System.out.println("Set Mode ON");
			goButton.setEnabled(false);
			System.out.println("Go button disabled");
			updateSetTable();
			System.out.println("Set grid updated");
			System.out.println("Cell color rendered");
			// ship status
			playerShip0.setEnabled(false);
			playerShip1.setEnabled(false);
			playerShip2.setEnabled(false);
			playerShip3.setEnabled(false);
			playerShip4.setEnabled(false);
			playerShip5.setEnabled(false);
			playerShip6.setEnabled(false);
			compShip0.setEnabled(true);
			compShip1.setEnabled(true);
			compShip2.setEnabled(true);
			compShip3.setEnabled(true);
			compShip4.setEnabled(true);
			compShip5.setEnabled(true);
			compShip6.setEnabled(true);
		}
		else if(me.getActionCommand() == "Save")
	  {
			System.out.println("Pressed: Save");
		}
		else if(me.getActionCommand() == "Load")
	  {
			System.out.println("Pressed: Load");
		}
		else if(me.getActionCommand() == "High Score")
	  {
			System.out.println("Pressed: High Score");
		}
		else if(me.getActionCommand() == "Quit")
	  {
			System.out.println("Pressed: Quit");
			dispose();
		}
//		else if(me.getActionCommand() == "Go")
//	  {
//			System.out.println("Pressed: Go");
//			gameInstance.setGameModeOn();
//			System.out.println("Game mode ON");
//			gameInstance.setSetModeOff();
//			System.out.println("Set Mode OFF");
//			goButton.setEnabled(false);
//			System.out.println("Go button disabled");
//			updateGameTable();
//			System.out.println("Game grid updated");
//		}
		else if(me.getActionCommand() == "How to play")
	  {
			String guide = "Go to \"Start\" and create a new game by pressing \"New\". \nFollow the ship sequence in the \"YOUR SHIP STATUS\" when inputting your ships. \nThe game will not know if you did not put the ship correctly, so it is the matter of sincerity. \nThat is, for example if you scatter the cells of a ship of size 5 across the grid.";
			guide = guide + "\nPress \"Go\" when your are ready to play.";
			guide = guide + "\n\nSHIP SIZE INFO (LENGTH):";
			guide = guide + "\n   Aircraft Carrier - 5";
			guide = guide + "\n   Battleship - 4:";
			guide = guide + "\n   Cruiser - 3";
			guide = guide + "\n   Destroyer - 2";
			guide = guide + "\n   Submarine - 1";
			JOptionPane.showMessageDialog(this, guide);
		}
		else
		{
		}
	}
	
	// ### mouse clicked ###
	public void mouseClicked(MouseEvent me)
	{
		int x = me.getX();
		int y = me.getY();
		int row = y/50;
		int column = x/50;
		System.out.println("Clicked @ Row: " + row + " Column: " + column);
		if(gameInstance.getGameMode() == false && gameInstance.getSetMode() == true && gameInstance.checkPlacement(row,column) == true)
		{
			setCounter = setCounter + 1;
			gameInstance.setPlayerShip(row,column,shipType[setCounter - 1]);
			gameInstance.health[shipType[setCounter - 1]]++;
			System.out.println("Placed " + setCounter + " ship out of 18");
			updateSetTable();
			System.out.println("Set grid updated");
			if(setCounter >= 18)
			{
			  System.out.println("Player ship placement done");
				setCounter = 0;
				System.out.println("Set counter reset");
				gameInstance.setGameModeOff();
				System.out.println("Game Mode OFF");
				gameInstance.setSetModeOff();
				System.out.println("Set Mode OFF");
				goButton.setEnabled(true);
				System.out.println("Go button enabled");
				showDone = true;
//				String goMessage = "Ship placement done";
//				JOptionPane.showMessageDialog(this, goMessage);
		  }
			else
			{
			 System.out.println("Input next piece please");
			}
		}
		else if(gameInstance.getGameMode() == true && gameInstance.getSetMode() == false && gameInstance.checkMove(row,column) == true)
		{
			gameInstance.playerTurn(row,column);
			System.out.println("Player input computed successfully");
			gameInstance.compTurn();
			System.out.println("Computer made its move");
			updateGameTable();
			// sound
			if(gameTable.getValueAt(row, column).equals("     HIT") == true)
			{
				java.awt.Toolkit.getDefaultToolkit().beep();
			}
			System.out.println("Game grid updated");
			for(int i = 0; i < 7; i++)
			{
				System.out.println("[Player] Ship " + i + " healthComp: " + gameInstance.healthComp[i]);
				System.out.println("[Computer] Ship " + i + " healthComp: " + gameInstance.healthComp[i]);
			}
			if(gameInstance.getGameResult() == 1)
			{
				System.out.println("You win!");
				java.awt.Toolkit.getDefaultToolkit().beep();
		  }
			else if(gameInstance.getGameResult() == 2)
			{
				System.out.println("Computer win");
				java.awt.Toolkit.getDefaultToolkit().beep();
		  }
			else 
			{
				System.out.println("Play on");
		  }
		}
		else
		{
		  System.out.println("Nothing happens");
		}
		// grid update
    // ship 0
		if(gameInstance.health[0] == 0)
		{
			playerShip0.setEnabled(false);
		}
		else if(gameInstance.health[0] == 5)
		{
			playerShip0.setEnabled(true);
		}
	  // ship 1
		if(gameInstance.health[1] == 0)
		{
			playerShip1.setEnabled(false);
		}
		else if(gameInstance.health[1] == 4)
		{
			playerShip1.setEnabled(true);
		}
	  // ship 2
		if(gameInstance.health[2] == 0)
		{
			playerShip2.setEnabled(false);
		}
		else if(gameInstance.health[2] == 3)
		{
			playerShip2.setEnabled(true);
		}
	  // ship 3
		if(gameInstance.health[3] == 0)
		{
			playerShip3.setEnabled(false);
		}
		else if(gameInstance.health[3] == 2)
		{
			playerShip3.setEnabled(true);
		}
	  // ship 4
		if(gameInstance.health[4] == 0)
		{
			playerShip4.setEnabled(false);
		}
		else if(gameInstance.health[4] == 2)
		{
			playerShip4.setEnabled(true);
		}
		// ship 5
		if(gameInstance.health[5] == 1)
		{
			playerShip5.setEnabled(true);
		}
		else if(gameInstance.health[5] == 0)
		{
			playerShip5.setEnabled(false);
		}
		// ship 6
		if(gameInstance.health[6] == 1)
		{
			playerShip6.setEnabled(true);
		}
		else if(gameInstance.health[6] == 0)
		{
			playerShip6.setEnabled(false);
		}
		// computer
	  // ship 0
		if(gameInstance.healthComp[0] == 0)
		{
			compShip0.setEnabled(false);
		}
		else if(gameInstance.healthComp[0] == 5)
		{
			compShip0.setEnabled(true);
		}
	  // ship 1
		if(gameInstance.healthComp[1] == 0)
		{
			compShip1.setEnabled(false);
		}
		else if(gameInstance.healthComp[1] == 4)
		{
			compShip1.setEnabled(true);
		}
	  // ship 2
		if(gameInstance.healthComp[2] == 0)
		{
			compShip2.setEnabled(false);
		}
		else if(gameInstance.healthComp[2] == 3)
		{
			compShip2.setEnabled(true);
		}
	  // ship 3
		if(gameInstance.healthComp[3] == 0)
		{
			compShip3.setEnabled(false);
		}
		else if(gameInstance.healthComp[3] == 2)
		{
			compShip3.setEnabled(true);
		}
	  // ship 4
		if(gameInstance.healthComp[4] == 0)
		{
			compShip4.setEnabled(false);
		}
		else if(gameInstance.healthComp[4] == 2)
		{
			compShip4.setEnabled(true);
		}
		// ship 5
		if(gameInstance.healthComp[5] == 0)
		{
			compShip5.setEnabled(false);
		}
		else if(gameInstance.healthComp[5] == 1)
		{
			compShip5.setEnabled(true);
		}
		// ship 6
		if(gameInstance.healthComp[6] == 0)
		{
			compShip6.setEnabled(false);
		}
		else if(gameInstance.healthComp[6] == 1)
		{
			compShip6.setEnabled(true);
		}
		// game result
		if(gameInstance.getGameMode() == true && gameInstance.getSetMode() == false)
		{
			if(gameInstance.getGameResult() == 1)
			{
				JOptionPane.showMessageDialog(this, "You win!");
		  }
			else if(gameInstance.getGameResult() == 2)
			{
				JOptionPane.showMessageDialog(this, "You lose!");
		  }
		}
		// set done
		if(showDone)
		{
			updateGameTable();
			String goMessage = "Ship placement done";
			showDone = false;
			JOptionPane.showMessageDialog(this, goMessage);
			gameInstance.setGameModeOn();
			System.out.println("Game mode ON");
			gameInstance.setSetModeOff();
			System.out.println("Set Mode OFF");
//			goButton.setEnabled(false);
//			System.out.println("Go button disabled");
			System.out.println("Game grid updated");
			
	  }
	}
	
	// ### mouse pressed ###
	public void mousePressed(MouseEvent me)
	{
	}
	
	// ### mouse released ###
	public void mouseReleased(MouseEvent me)
	{
	}
	
	// ### mouse entered ###
	public void mouseEntered(MouseEvent me)
	{
	}
	
	// ### mouse exited ###
	public void mouseExited(MouseEvent me)
	{
	}
	
	// ### main ###
	public static void main(String args[])
	{
		Frame4 test = new Frame4();
	}
	
}  // end of line
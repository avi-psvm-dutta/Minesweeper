package gui;

import java.awt.*;

import javax.swing.*;

import data.Grid;

public class Console extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public JPanel gamePanel; //JPanel which contains all the buttons
	private JMenuBar menuBar; //Menu Bar
	private JMenu fileMenu; //File pull down menu
	public JMenuItem newGame, customize; //File menu options
	public JButton[][] buttons = new JButton[Grid.height][Grid.width]; //buttons which the user clicks to play
	public static final Color buttonColor = new Color(210, 210, 238); //color of a button
	private static final int buttonSize = 30; //height and width of a button
	
	public Console() //constructor
	{
		setLayout(new FlowLayout()); //set the layout of JFrame
		setTitle("Minesweeper"); //set the title to "Minesweeper"
		setVisible(true); //set the JFrame as visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); //terminate the program when the close button is clicked
		
		menuBar = new JMenuBar(); //initialize the menu bar
		fileMenu = new JMenu("File"); //initialize the File pull down menu
		newGame = new JMenuItem("New Game"); //initialize the File menu New Game option
		customize = new JMenuItem("Customize"); //initialize the File menu Customize option
		menuBar.add(fileMenu); //add the File pull down menu to the menu bar
		fileMenu.add(newGame); //add the New Game option to the File menu
		fileMenu.add(customize); //add the Customize option to the File menu
		setJMenuBar(menuBar); //add the menu bar to the JFrame
		
		gamePanel = new JPanel(); //initialize the panel that holds the buttons
		gamePanel.setLayout(new GridLayout(Grid.height, Grid.width, 1, 1)); //set the layout of the panel to a grid of appropriate size with 1 pixel separation between buttons
		
		for(int rowIndex = 0; rowIndex < Grid.height; rowIndex ++)
		{
			for(int colIndex = 0; colIndex < Grid.width; colIndex ++)
			{
				buttons[rowIndex][colIndex] = new JButton(); //initialize each button
				buttons[rowIndex][colIndex].setBackground(buttonColor); //set the color of each button
				buttons[rowIndex][colIndex].setPreferredSize(new Dimension(buttonSize, buttonSize)); //set the size of each button
				gamePanel.add(buttons[rowIndex][colIndex]); //add each button to the panel
			}
		}
		
		add(gamePanel); //add the game panel to the JFrame
		pack(); //set the size of the JFrame appropriately
	}
}
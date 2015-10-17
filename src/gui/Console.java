package gui;

import java.awt.*;

import javax.swing.*;

import data.Grid;

public class Console extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public JPanel gamePanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	public JMenuItem newGame, customize;
	public JButton[][] buttons = new JButton[Grid.height][Grid.width];
	public static final Color buttonColor = new Color(210, 210, 238);
	private static final int buttonSize = 30;
	
	public Console()
	{
		setLayout(new FlowLayout());
		setTitle("Minesweeper");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newGame = new JMenuItem("New Game");
		customize = new JMenuItem("Customize");
		menuBar.add(fileMenu);
		fileMenu.add(newGame);
		fileMenu.add(customize);
		setJMenuBar(menuBar);
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(Grid.height, Grid.width, 1, 1));
		
		for(int rowIndex = 0; rowIndex < Grid.height; rowIndex ++)
		{
			for(int colIndex = 0; colIndex < Grid.width; colIndex ++)
			{
				buttons[rowIndex][colIndex] = new JButton();
				buttons[rowIndex][colIndex].setBackground(buttonColor);
				buttons[rowIndex][colIndex].setPreferredSize(new Dimension(buttonSize, buttonSize));
				gamePanel.add(buttons[rowIndex][colIndex]);
			}
		}
		
		add(gamePanel);
		pack();
	}
}
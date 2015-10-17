package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import data.*;
import gui.*;

public class Minesweeper
{
	private Grid grid; //active grid
	private Console game; //active window
	
	public Minesweeper() //constructor
	{
		game = new Console(); //initialize window
		grid = new Grid(); //initialize grid
		
		game.newGame.addActionListener(new NewGame()); //make the New Game pull down menu item clickable
		
		for(int rowIndex = 0; rowIndex < Grid.height; rowIndex ++)
		{
			for(int colIndex = 0; colIndex < Grid.width; colIndex ++)
			{
				game.buttons[rowIndex][colIndex].addActionListener(new LeftClick(rowIndex, colIndex)); //add a left click listener for each button
				game.buttons[rowIndex][colIndex].addMouseListener(new RightClick(rowIndex, colIndex)); //add a right click listener for each button
			}
		}
		
		game.customize.addActionListener(new CustomizeConsole(game));//make the Customize pull down menu item clickable
	}
	
	public static void main(String[] args)
	{
		new Minesweeper(); //initialize game
	}
	
	private class NewGame implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			for(int rowIndex = 0; rowIndex < Grid.height; rowIndex ++)
			{
				for(int colIndex = 0; colIndex < Grid.width; colIndex ++)
				{
					game.buttons[rowIndex][colIndex].setIcon(null); //reset all icons of each button
					game.buttons[rowIndex][colIndex].setBackground(Console.buttonColor); //reset the color of each button
				}
			}
			
			game.gamePanel.setBackground(null); //set background of the game panel to null (from red if lose, from green if win)
			grid = new Grid(); //initialize a new grid to play with
		}	
	}
	
	private class CustomizeConsole implements ActionListener
	{
		private Console mainWindow;
		
		public CustomizeConsole(Console mainWindow)
		{
			this.mainWindow = mainWindow;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			new Customize(mainWindow); //initialize a customize window
		}
	}
	
	private class LeftClick implements ActionListener
	{
		private int rowIndex, colIndex;
		
		public LeftClick(int rowIndex, int colIndex)
		{
			this.rowIndex = rowIndex;
			this.colIndex = colIndex;
		}

		//method to change the look of all buttons after each move
		private void update()
		{
			for(int row = 0; row < Grid.height; row ++)
			{
				for(int col = 0; col < Grid.width; col ++)
				{
					if(grid.discoveredSquare[row][col]) //current square has been discovered, so change how it looks
					{
						if(grid.grid[row][col] == 0) //0 square
						{
							game.buttons[row][col].setIcon(null); //remove any old icon
							game.buttons[row][col].setBackground(new Color(238, 238, 238)); //set the color of the button to white
						}
						else
							game.buttons[row][col].setIcon(new ImageIcon(String.valueOf(grid.grid[row][col]) + ".png")); //set the icon as the appropriate image corresponding to the value contained by the square
					}
				}
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			grid.discoveredSquare[rowIndex][colIndex] = true; //mark the clicked on square as discovered
			
			if(grid.grid[rowIndex][colIndex] == -1) //the player has clicked on a mine
			{
				game.gamePanel.setBackground(new Color(255, 0, 0)); //change the color of the panel to red
				for(rowIndex = 0; rowIndex < Grid.height; rowIndex ++)
					for(colIndex = 0; colIndex < Grid.width; colIndex ++)
						grid.discoveredSquare[rowIndex][colIndex] = true; //reveal the entire grid
				
				update();
			}

			if(grid.grid[rowIndex][colIndex] == 0) //0 has been clicked on, call the floodfill method
				grid.floodFill(grid.grid, rowIndex, colIndex);
			
			update();
			
			if(grid.won()) //the player has won the game
				game.gamePanel.setBackground(new Color(0, 255, 0)); //change the color of the panel to green
		}
	}
	
	public class RightClick extends MouseAdapter implements MouseListener
	{
		private int rowIndex, colIndex;
		
		public RightClick(int rowIndex, int colIndex)
		{
			this.rowIndex = rowIndex;
			this.colIndex = colIndex;
		}
		
		public void mouseClicked(MouseEvent e)
		{
			if(e.getButton() == 3)
				game.buttons[rowIndex][colIndex].setIcon(new ImageIcon("-1.png")); //set the icon of the clicked button to a mine
		}
	}
}
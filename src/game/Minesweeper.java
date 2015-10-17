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
	private Grid grid;
	private Console game;
	
	public Minesweeper()
	{
		game = new Console();
		grid = new Grid();
		
		game.newGame.addActionListener(new NewGame());
		
		for(int rowIndex = 0; rowIndex < Grid.height; rowIndex ++)
		{
			for(int colIndex = 0; colIndex < Grid.width; colIndex ++)
			{
				game.buttons[rowIndex][colIndex].addActionListener(new LeftClick(rowIndex, colIndex));
				game.buttons[rowIndex][colIndex].addMouseListener(new RightClick(rowIndex, colIndex));
			}
		}
		
		game.customize.addActionListener(new CustomizeConsole(game));
	}
	
	public static void main(String[] args)
	{
		new Minesweeper();
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
					game.buttons[rowIndex][colIndex].setIcon(null);
					game.buttons[rowIndex][colIndex].setBackground(Console.buttonColor);
				}
			}
			
			game.gamePanel.setBackground(null);
			grid = new Grid();
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
			new Customize(mainWindow);
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

		private void update()
		{
			for(int row = 0; row < Grid.height; row ++)
			{
				for(int col = 0; col < Grid.width; col ++)
				{
					if(grid.discoveredSquare[row][col])
					{
						if(grid.grid[row][col] == 0)
						{
							game.buttons[row][col].setIcon(null);
							game.buttons[row][col].setBackground(new Color(238, 238, 238));
						}
						else
							game.buttons[row][col].setIcon(new ImageIcon(String.valueOf(grid.grid[row][col]) + ".png"));
					}
				}
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			grid.discoveredSquare[rowIndex][colIndex] = true;
			
			if(grid.grid[rowIndex][colIndex] == -1)
			{
				game.gamePanel.setBackground(new Color(255, 0, 0));
				for(rowIndex = 0; rowIndex < Grid.height; rowIndex ++)
					for(colIndex = 0; colIndex < Grid.width; colIndex ++)
						grid.discoveredSquare[rowIndex][colIndex] = true;
				
				update();
			}

			if(grid.grid[rowIndex][colIndex] == 0)
				grid.floodFill(grid.grid, rowIndex, colIndex);
			
			update();
			
			if(grid.won())
			{
				game.gamePanel.setBackground(new Color(0, 255, 0));
			}
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
				game.buttons[rowIndex][colIndex].setIcon(new ImageIcon("-1.png"));
		}
	}
}
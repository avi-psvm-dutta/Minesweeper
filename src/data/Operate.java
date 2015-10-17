package data;

import data.Grid;

//Class to perform operations on the grid, such as flood fill and checking for a win
public abstract class Operate
{
	public boolean[][] discoveredSquare = new boolean[Grid.height][Grid.width]; //boolean array to store if the corresponding grid value has been discovered
	
	public void floodFill(int[][] grid, int seedX, int seedY) //recursive function to clear all adjacent squares if the user has clicked on a 0
	{
		if(grid[seedX][seedY] != 0) //not a 0, stop recursing
			return ;
		
		try
		{
			if(!discoveredSquare[seedX - 1][seedY])
			{
				discoveredSquare[seedX-1][seedY] = true; //mark this square as discovered
				floodFill(grid, seedX - 1,  seedY); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
		
		try
		{
			if(!discoveredSquare[seedX][seedY - 1])
			{
				discoveredSquare[seedX][seedY - 1] = true; //mark this square as discovered
				floodFill(grid, seedX, seedY - 1); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
		
		try
		{
			if(!discoveredSquare[seedX + 1][seedY])
			{
				discoveredSquare[seedX + 1][seedY] = true; //mark this square as discovered
				floodFill(grid, seedX + 1, seedY); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
		
		try
		{
			if(!discoveredSquare[seedX][seedY + 1])
			{
				discoveredSquare[seedX][seedY + 1] = true; //mark this square as discovered
				floodFill(grid, seedX, seedY + 1); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
		
		try
		{
			if(!discoveredSquare[seedX - 1][seedY - 1])
			{
				discoveredSquare[seedX - 1][seedY - 1] = true; //mark this square as discovered
				floodFill(grid, seedX - 1, seedY - 1); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
		
		try
		{
			if(!discoveredSquare[seedX + 1][seedY + 1])
			{
				discoveredSquare[seedX + 1][seedY + 1] = true; //mark this square as discovered
				floodFill(grid, seedX + 1, seedY + 1); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
		
		try
		{
			if(!discoveredSquare[seedX - 1][seedY + 1])
			{
				discoveredSquare[seedX - 1][seedY + 1] = true; //mark this square as discovered
				floodFill(grid, seedX - 1, seedY + 1); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
		
		try
		{
			if(!discoveredSquare[seedX + 1][seedY - 1])
			{
				discoveredSquare[seedX + 1][seedY - 1] = true; //mark this square as discovered
				floodFill(grid, seedX + 1, seedY - 1); //flood fill the adjacent square
			}
		}catch(ArrayIndexOutOfBoundsException e){} //gone outside the grid
	}
	
	public boolean won() //returns true if you've clicked on everything apart from all the mines
	{
		int undiscoveredCount = 0;
		for(int rowIndex = 0; rowIndex < Grid.height; rowIndex++)
			for(int colIndex = 0; colIndex < Grid.height; colIndex++)
				if(!discoveredSquare[rowIndex][colIndex])
					undiscoveredCount ++;
		
		return undiscoveredCount == Grid.mines ? true : false;
	}	  
	
	protected boolean linearSearch(int[]a, int value)
	{ 
		for(int i=0; i < Grid.mines; i++)
			if(a[i] == value)
				return true;
		return false;
	}
}

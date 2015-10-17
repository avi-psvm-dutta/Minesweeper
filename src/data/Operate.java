package data;

import data.Grid;

public abstract class Operate
{
	public boolean[][] discoveredSquare = new boolean[Grid.height][Grid.width];
	
	public void floodFill(int[][] grid, int seedX, int seedY)
	{
		if(grid[seedX][seedY] != 0)
			return ;
		
		try
		{
			if(!discoveredSquare[seedX - 1][seedY])
			{
				discoveredSquare[seedX-1][seedY] = true;
				floodFill(grid, seedX - 1, seedY);
			}
		}catch(Exception e){}
		
		try
		{
			if(!discoveredSquare[seedX][seedY - 1])
			{
				discoveredSquare[seedX][seedY - 1] = true;
				floodFill(grid, seedX, seedY - 1);
			}
		}catch(Exception e){}
		
		try
		{
			if(!discoveredSquare[seedX + 1][seedY])
			{
				discoveredSquare[seedX + 1][seedY] = true;
				floodFill(grid, seedX + 1, seedY);
			}
		}catch(Exception e){}
		
		try
		{
			if(!discoveredSquare[seedX][seedY + 1])
			{
				discoveredSquare[seedX][seedY + 1] = true;
				floodFill(grid, seedX, seedY + 1);
			}
		}catch(Exception e){}
		
		try
		{
			if(!discoveredSquare[seedX - 1][seedY - 1])
			{
				discoveredSquare[seedX - 1][seedY - 1] = true;
				floodFill(grid, seedX - 1, seedY - 1);
			}
		}catch(Exception e){}
		
		try
		{
			if(!discoveredSquare[seedX + 1][seedY + 1])
			{
				discoveredSquare[seedX + 1][seedY + 1] = true;
				floodFill(grid, seedX + 1, seedY + 1);
			}
		}catch(Exception e){}
		
		try
		{
			if(!discoveredSquare[seedX - 1][seedY + 1])
			{
				discoveredSquare[seedX - 1][seedY + 1] = true;
				floodFill(grid, seedX - 1, seedY + 1);
			}
		}catch(Exception e){}
		
		try
		{
			if(!discoveredSquare[seedX + 1][seedY - 1])
			{
				discoveredSquare[seedX + 1][seedY - 1] = true;
				floodFill(grid, seedX + 1, seedY - 1);
			}
		}catch(Exception e){}
	}
	
	protected boolean linearSearch(int[]a, int value)
	{ 
		for(int i=0; i < Grid.mines; i++)
			if(a[i] == value)
				return true;
		return false;
	}
}

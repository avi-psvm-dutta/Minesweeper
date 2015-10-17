package data;

import java.util.*;

public class Grid extends Operate
{
	/*
	 * the grid is an 2D integer array, holding certain values
	 * -1 is a mine
	 * n is the number of mines adjacent to that button
	 * n can also be 0
	 * */
	
	public static int width = 10, height = 10, mines = 10;
	public int[][] grid;

	/*
	 * mine positions are randomly generated and stored
	 * in a 1-D array indexed from 1 to height*width
	 * After the whole array is generated, the positions
	 * stored are marked as -1 which means mine
	 * */

	private void placeMines()
	{
		Random minePosition = new Random(); //int to store the random number
		int[] minePositions = new int[mines]; //array to store all the mine positions
		
		for(int index = 0; index < mines; index++)
		{
			int temp = minePosition.nextInt(height*width) + 1; //keep the random number here
			if(linearSearch(minePositions, temp)) //it's a repetition, try it again
			{
				index--;
				continue;
			}
			minePositions[index] = temp; //it's not been repeated, store the position
		}
		
		for(int count = 1, rowIndex = 0; rowIndex < height; rowIndex++)
		{
			for(int colIndex = 0; colIndex < width; count++, colIndex++)
			{
				if(linearSearch(minePositions, count)) //the grid index has been stored as a mine position
				{
					grid[rowIndex][colIndex] = -1; //mine
				}
			}
		}
	}
	
	private int calculateNumber(int row, int col)
	{
		int count = 0;
		
		if(row != 0)
			if(grid[row-1][col] == -1)
				count ++;
		if(row != height-1)
			if(grid[row+1][col] == -1)
				count ++;
		if(row != 0 && col != 0)
			if(grid[row-1][col-1] == -1)
				count ++;
		if(row != height-1 && col != width-1)
			if(grid[row+1][col+1] == -1)
				count ++;
		if(row != 0 && col != width-1)
			if(grid[row-1][col+1] == -1)
				count ++;
		if(row != height-1 && col != 0)
			if(grid[row+1][col-1] == -1)
				count ++;
		if(col != 0)
			if(grid[row][col-1] == -1)
				count ++;
		if(col != width-1)
			if(grid[row][col+1] == -1)
				count ++;
			
		return count;
	}
	
	private void placeNumbers()
	{
		for(int rowIndex = 0; rowIndex < height; rowIndex++)
			for(int colIndex = 0; colIndex < width; colIndex++)
				if(grid[rowIndex][colIndex] != -1)
					grid[rowIndex][colIndex] = calculateNumber(rowIndex, colIndex);
	}
	
	public Grid() //constructor to initialize values of the grid
	{
		
		grid = new int[height][width];
		for(int rowIndex = 0; rowIndex < height; rowIndex++)
		{
			for(int colIndex = 0; colIndex < width; colIndex++)
			{
				grid[rowIndex][colIndex] = 0;
				discoveredSquare[rowIndex][colIndex] = false;
			}
		}
		
		placeMines();
		placeNumbers();
	}
	
	public boolean won() //returns true if you've clicked on everything apart from all the mines
	{
		int undiscoveredCount = 0;
		for(int rowIndex = 0; rowIndex < height; rowIndex++)
			for(int colIndex = 0; colIndex < height; colIndex++)
				if(!discoveredSquare[rowIndex][colIndex])
					undiscoveredCount ++;
		
		return undiscoveredCount == mines ? true : false;
	}	  
}
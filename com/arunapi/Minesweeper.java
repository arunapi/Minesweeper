package com.arunapi;

public class Minesweeper{

  public static void main(String [] args){
    Cell[][] grid = startGame(8,5);
    printGrid(grid);
  }

  public static Cell[][] startGame(int gridSize, int numberOfMines){
    Cell[][] grid = new Cell[gridSize][gridSize];
    //Randomly pick a cell where we keep the first mine
    int minesPlaced = 0;
    while(minesPlaced<=numberOfMines){
      int row = (int)(Math.random()*(gridSize-1));
      int col = (int)(Math.random()*(gridSize-1));
      if(grid[row][col]!=null && grid[row][col].getValue()==9) continue;
      minesPlaced ++;
      for(int i=row-1<0?0:row-1;i<=row+1;i++){
	for(int j=col-1<0?0:col-1;j<=col+1;j++){
	  //value must be calculated based on i,j, mines
	  if(grid[i][j]==null) grid[i][j]=new Cell();
	  
	  if(row==i && col==j) grid[i][j].value=9;
	  else if(grid[i][j].value != 9) grid[i][j].value++;
	}
      }
    }

    return grid;


  }


  public static void printGrid(Cell[][] grid){
    for(int i=0;i< grid.length;i++){
      for(int j=0;j<grid[i].length;j++){
	if(grid[i][j]!=null)
	  System.out.print(grid[i][j].value+" ");
	else{
	  System.out.print("0 ");
	}
      }
      System.out.println();
    }
    System.out.println();

  }

}

class Cell{
    int value;
    boolean hidden;
    public Cell(){
      this.value = 0;
      hidden = true;
    }
    public int getValue(){
      return value;
    }
    
    public void unhide(){
      hidden = false;
    }

}

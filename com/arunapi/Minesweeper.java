package com.arunapi;

public class Minesweeper{

  public static void main(String [] args){
    Cell[][] grid = startGame(8,5);
    printGrid(grid, true);
    printGrid(grid, false);
    clicked(grid,3,1);
    printGrid(grid, false);
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

  public static boolean clicked(Cell[][] grid, int row, int col){
    if(row<0 || col<0 || row>grid.length-1 || col>grid[0].length-1){
      return true;
    }
    //if clicked on null or 0 cell reveal cells until cell with value<9  
    if(grid[row][col]==null){
      grid[row][col] = new Cell();
      grid[row][col].visible=true;
      clicked(grid,row,col-1);
      clicked(grid,row,col+1);
      clicked(grid,row-1,col);
      clicked(grid,row-1,col-1);
      clicked(grid,row-1,col+1);      
      clicked(grid,row+1,col);            
      clicked(grid,row+1,col-1);            
      clicked(grid,row+1,col+1);            
    }
    //if clicked on a mine return false
    else if(grid[row][col].value ==9){
      grid[row][col].visible=true;
      return false;
    }
    else if(grid[row][col].value!=0){
      grid[row][col].visible=true;
    }
    
    return true;
    //if clicked on cell with a value < 9 reveal just that
  
  }
  
  public static void printGrid(Cell[][] grid, boolean displayValues){
    for(int i=0;i< grid.length;i++){
      for(int j=0;j<grid[i].length;j++){
	if(grid[i][j]!=null)
	  if(displayValues)
	    System.out.print(grid[i][j].value+" ");
	  else if(grid[i][j].visible){
	    System.out.print(grid[i][j].value+" ");
	  }
	  else{
	    System.out.print("- ");
	  }
	else{
	  if(displayValues)
	    System.out.print("0 ");
	  else
	    System.out.print("- ");
	}
      }
      System.out.println();
    }
    System.out.println();

  }

}

class Cell{
    int value;
    boolean visible;
    public Cell(){
      this.value = 0;
      visible = false;
    }
    public int getValue(){
      return value;
    }
    
    public void unhide(){
      visible = false;
    }

}

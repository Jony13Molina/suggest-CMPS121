package com.example.jonny.connectfour;

/**
 * Created by jonny on 10/30/2017.
 */

public class GameModel
{

   //Declaring global variables for the game board
   public  int rowNum;
   public  int numColumns;
   public  boolean winner;
   public GridClass[][] grids;
    //Declaring enum to keep track of players turn
    enum playerTurn
    {
        PLAYER_ONE, PLAYER_TWO
    }
    //Declaring variable to keep track of current Player
    public playerTurn whoseTurn;
    //constructor for game, specifies what our board consists of
    public GameModel(int columns, int rows)
    {
        rowNum = rows;
        numColumns = columns;
        //make a board game
      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < columns; c++) {

            grids = new GridClass[columns][rows];
        }
      }
    }

    //method to alternate between turns
    public void takeTurns()
    {
        if(whoseTurn == playerTurn.PLAYER_ONE)
        {
          whoseTurn = playerTurn.PLAYER_TWO;
        }
        else
        {
           whoseTurn =playerTurn.PLAYER_ONE;
        }
    }
    public int rowLoc(int column) {

        int r = rowNum ;

            for(column = 0; column <= numColumns; column++)
            {
                float row = (column-1/r)+1;
                int roundTo = (int) row;
                if (roundTo < 0 || roundTo > 6) {
                    return -1;
                }

                return roundTo;
            }
        return -1;
    }
    //place disk in a cell
    public boolean dropDisk(int column, int row)
    {
        if(GridClass.noDisk) {
            grids[column][row].assignPlayer(whoseTurn);
        }
        return false;

    }

    //algorithm to decide the winner

    public boolean whoWins(int columns, int rows)
    {
        //assigning values for height and width
        rows = grids.length;
        columns = grids[0].length;
        final int nullGrid = 0;


        //iterate rows from top to bottom
        //iterate columns from left to right
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                dropDisk(columns, rows);
                if (grids.equals(nullGrid)) {
                    //dont bother with the empty grids
                    continue;
                }
                //check right side
                if (((c + 3) < columns) && (dropDisk(r, c + 1) &&dropDisk
                        (r,c + 2)  && dropDisk(r, c + 3) )){
                    winner = true;
                    return winner;
                }
                //check uo and right
                if (c + 3 < columns && dropDisk(r + 1, c + 1) && dropDisk
                        (r + 2, c + 2) && dropDisk(r + 3, c + 3)) {
                    winner = true;
                    return winner;
                }
                //check up and left
                if (c - 3 >= 0 && dropDisk(r + 1, c - 1) && dropDisk
                        (r + 2, c - 2) && dropDisk(r + 3, c - 3)) {
                    winner = true;
                    return winner;
                }
            }

        }
        //no winner
            return false;

    }

/*-----helper sub class for creating grids and defining grids ---------*/
    public static class GridClass
    {
        public static boolean noDisk = false;
        public GridClass()

        {//grids start off with no Disk inside of them
            noDisk = true;
        }
        public GameModel.playerTurn assignGrid;
        public void assignPlayer(GameModel.playerTurn assignGrid)
        {
            this.assignGrid = assignGrid;
            //when a player drop a disk into a grid, the grid is no longer empty
            noDisk = false;
        }
    }

}
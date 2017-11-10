package com.example.jonny.connectfour;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.jonny.connectfour.GameModel.playerTurn.PLAYER_ONE;

/**
 * Created by jonny on 11/4/2017.
 */

public class gameScreen extends Fragment
{
    //decare variables for the game
    public static  int columnNum = 7;
    public static  int numRows = 6;
    private View gameLayout;
    public GameModel gameBoard;

    public ImageView [][]grids;
    public ImageView currentPlayer;
    public TextView  winnerDisplay;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.gamescreen,container, false);

        gameBoard = new GameModel(columnNum, numRows);

        gameLayout = root.findViewById(R.id.connectfour_board);
        constructTable();
        gameLayout.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent moveAnimation) {
                int column;
                switch(moveAnimation.getAction()) {
                //set motion
                        case MotionEvent.ACTION_UP:
                        column = columnlocation(moveAnimation.getX());
                        if (column != -1) {
                            dropDiskM(column);
                        }

                }

                return true;
            }
        });
        //set the display and the winner display, but winner display gone until a player wins
        currentPlayer = (ImageView)root.findViewById(R.id.Current_PDisplay);
        currentPlayer.setImageResource(animateTurn());
        winnerDisplay = (TextView) root.findViewById(R.id.Winner_display);

        return root;
    }
    public void constructTable()
    {
        //set grids to create table
        grids = new ImageView[numRows][columnNum];
        for(int rows = 0; rows < numRows; rows++)
         {
             ViewGroup setRow = (ViewGroup) ((ViewGroup) gameLayout).getChildAt(rows);
             setRow.setClipChildren(false);
              for(int columns = 0; columns< columnNum; columns++)
              {
                  ImageView setTable = (ImageView) setRow.getChildAt(columns);
                  setTable.setImageResource(android.R.color.transparent);
                  grids[rows][columns] = setTable;
              }
         }
    }
//method for locating column
    public int columnlocation(float locationX)
    {
        float columnWidth = grids[0][0].getHeight();
        float column = locationX/columnWidth;
        int roundTo = (int)column;
        if(roundTo < 0 || roundTo> 6)
        {
            return -1;
        }
        return roundTo;
    }

    //method to change player
    public int animateTurn()
    {
       if(gameBoard.whoseTurn == PLAYER_ONE)
       {

           return R.drawable.orange_disk;

       }else {
           return R.drawable.disk_red;
       }


    }
//method that changes the player image
    public void takeAturns()
    {
        gameBoard.takeTurns();
        currentPlayer.setImageResource(animateTurn());
    }
    //method to drop the disk
    public void dropDiskM(int column)
    {
        if(gameBoard.winner)
        {

            return;
        }


        int row = gameBoard.rowLoc(column);

        if(row == -1)
        {

            return;
        }
        else {
            ImageView disktoFall = grids[row][column];
            float animation = -(disktoFall.getHeight() * row
                    + disktoFall.getHeight() + 30);
            disktoFall.setY(animation);
            disktoFall.setImageResource(animateTurn());
            TranslateAnimation animateFall = new TranslateAnimation(0, 0,
                    0, Math.abs(animation));
            animateFall.setDuration(850);
            animateFall.setFillAfter(true);
            disktoFall.startAnimation(animateFall);
            gameBoard.dropDisk(column, row);
        }

        if(gameBoard.whoWins(column, row)){
            wplayerWins();
        }
        else
        {
            takeAturns();
        }

    }
    //set which players wins
    public void wplayerWins()
    {
       if(gameBoard.whoWins(numRows, columnNum))
       {
          int colorPlayer1;
          //make text visible to see winner and set color orange
          colorPlayer1 = getResources().getColor(R.color.Player1);
          winnerDisplay.setVisibility(View.VISIBLE);
          winnerDisplay.setTextColor(colorPlayer1);

       }
       else
       {
           int colorPlayer2;
           //make colored text visible if player2 wins
           colorPlayer2 = getResources().getColor(R.color.Player2);
           winnerDisplay.setVisibility(View.VISIBLE);
           winnerDisplay.setTextColor(colorPlayer2);
       }



    }


}

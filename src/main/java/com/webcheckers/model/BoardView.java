package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * BoardView generates the board which is played on by the two players.
 *
 * @version 1.0
 */

public class BoardView implements Iterable<Row>{

    //attributes
    private ArrayList<Row> rowArrayList = new ArrayList<>();
    private Player player1;
    private Player player2;
    private boolean playerOneIsRed;

    public BoardView(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        playerOneIsRed = Math.random() > .5;

        //creates 8 rows to fill the board, in which those rows created make the spaces to fill those rows
        for (int i = 0; i < 8; i++) {
            rowArrayList.add(new Row(i));
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return this.getRowArrayList().iterator();
    }


    public ArrayList<Row> getRowArrayList() {
        return rowArrayList;
    }

    public boolean playerOneIsRed() {
        return playerOneIsRed;
    }

    public void setRowArrayList(ArrayList<Row> rowArrayList) {
        this.rowArrayList = rowArrayList;
    }

    /**
     * rotate is used to flip the board so the player side is always on the bottom
     *
     * @return instance of BoardView where the board is rotated 180 degrees
     */
    public BoardView rotate(){
        BoardView newBoard = this;
        ArrayList<Row> newRows = new ArrayList<>();
        Row tempRow;
        ArrayList<Space> spaceArrayList;
        for (int i = 0; i < 8; i++) {
            tempRow = new Row(i);
            spaceArrayList = new ArrayList<>();
            for (int j = 7; j >= 0 ; j--) {
                spaceArrayList.add(this.getRowArrayList().get(7-i).getSpaceArrayList().get(j));
            }
            tempRow.setSpaceArrayList(spaceArrayList);
            newRows.add(tempRow);
        }
        newBoard.setRowArrayList(newRows);
        return newBoard;
     }
}

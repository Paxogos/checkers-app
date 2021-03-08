package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardView implements Iterable<Row>{
    
    private ArrayList<Row> rowArrayList = new ArrayList<>();
    private Player player1;
    private Player player2;

    private boolean playerOneIsRed;



    public BoardView(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        playerOneIsRed = Math.random() > .5;

        for (int i = 0; i < 8; i++) {
            rowArrayList.add(new Row(i));
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return new BoardIterator<>(this);

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

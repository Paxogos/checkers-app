package com.webcheckers.model;

import com.webcheckers.util.Position;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The type Board view represents a game of checkers.
 */
public class BoardView implements Iterable<Row>{

    public static int GRID_LENGTH = 8;
    private ArrayList<Row> rowArrayList = new ArrayList<>();

    /**
     * Instantiates a new BoardView/game.
     *
     */
    public BoardView() {
        for (int i = 0; i < 8; i++) {
            rowArrayList.add(new Row(i));
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return this.getRowArrayList().iterator();
    }


    /**
     * Gets the row array list.
     *
     * @return the array list of rows
     */
    public ArrayList<Row> getRowArrayList() {
        return rowArrayList;
    }


    /**
     * Sets the row array list.
     *
     * @param rowArrayList the array list of rows
     */
    public void setRowArrayList(ArrayList<Row> rowArrayList) {
        this.rowArrayList = rowArrayList;
    }

    /**
     * Rotates board view 180 degrees.
     *
     * @return a new board view that is rotated
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

    public Space getSpace(int row, int cell) {
        return this.getRowArrayList().get(row).getSpaceArrayList().get(cell);
    }
}

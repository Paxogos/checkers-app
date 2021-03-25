package com.webcheckers.util;

import com.webcheckers.ui.PostCheckTurnRoute;

public class Position {
    //attributes
    private int row;
    private int cell;

    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }
    public int getRow() {
        return row;
    }
    public int getCell() {
        return cell;
    }

    public void mirrorRow(){ // because javascript positions are weird  ??
        row = 7 - row;
    }

    public String toString() {
        return "[" + row + ", " + cell + "]";
    }
}

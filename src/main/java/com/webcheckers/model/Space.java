package com.webcheckers.model;

public class Space {

    // must be 0 to 7
    private int cellIdx;

    public Space(int cellIdx) {
        this.cellIdx = cellIdx;
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public boolean isValid(){

        return false;
    }

    public Piece getPiece(){

        return null;
    }

    @Override
    public String toString() {
        return "Space{" +
                "cellIdx=" + cellIdx +
                '}';
    }
}

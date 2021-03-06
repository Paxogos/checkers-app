package com.webcheckers.model;

import java.io.PipedReader;

public class Space {

    // must be 0 to 7
    private int rowIdx;
    private int cellIdx;
    private Piece piece;

    public Space(int rowIdx,int cellIdx) {
        this.rowIdx = rowIdx;
        this.cellIdx = cellIdx;
        if((rowIdx+cellIdx)%2 == 1){
            if(rowIdx<3){
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
            }else if(rowIdx > 4){
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
            }
        }
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public boolean isValid(){
        if((rowIdx+cellIdx)%2 == 0){
            return false;
        }
        return true;
    }

    public Piece getPiece(){
        return piece;
    }

    @Override
    public String toString() {
        return "Space{" +
                "cellIdx=" + cellIdx +
                '}';
    }
}

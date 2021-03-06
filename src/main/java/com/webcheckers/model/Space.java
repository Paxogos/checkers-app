package com.webcheckers.model;

/**
 * Space represents a square on the board, many squares are used to make up the
 * board which checkers is played on.
 *
 * @version 1.0
 * @author Aaron Shulte, Jack Sipley
 */

public class Space {

    // must be 0 to 7
    private int rowIdx;
    private int cellIdx;
    private Piece piece;

    public Space(int rowIdx,int cellIdx) {
        this.rowIdx = rowIdx;
        this.cellIdx = cellIdx;

        //determines where the starting piece should go
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

    /**
     *
     * @return true if piece is in the right starting place, false otherwise
     */
    public boolean isValid(){
        return (rowIdx + cellIdx) % 2 != 0;
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

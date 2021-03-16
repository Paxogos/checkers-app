package com.webcheckers.model;

/**
 * Space represents a square on the board, many squares are used to make up the
 * board which checkers is played on.
 *
 * @author Aaron Shulte, Jack Sipley
 * @version 1.0
 */
public class Space {

    // must be 0 to 7
    private int rowIdx;
    private int cellIdx;
    private Piece piece;

    /**
     * Instantiates a new Space.
     *
     * @param rowIdx  the row idx
     * @param cellIdx the cell idx
     */
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

    /**
     * Gets cell idx.
     *
     * @return the cell idx
     */
    public int getCellIdx() {
        return cellIdx;
    }

    /**
     * Gets row idx.
     *
     * @return the row idx
     */
    public int getRowIdx() {
        return rowIdx;
    }

    /**
     * Is valid boolean.
     *
     * @return true if piece is in the right starting place, false otherwise
     */
    public boolean isValid(){
        return (rowIdx + cellIdx) % 2 != 0;
    }

    /**
     * Get piece piece.
     *
     * @return the piece
     */
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

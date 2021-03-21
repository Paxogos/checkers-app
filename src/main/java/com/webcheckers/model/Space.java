package com.webcheckers.model;
import com.webcheckers.ui.PostBackupMoveRoute;
import com.webcheckers.util.Position;

/**
 * Space represents a square on the board, many squares are used to make up the
 * board which checkers is played on.
 *
 * @author Aaron Shulte, Jack Sipley
 * @version 1.0
 */
public class Space {

    public enum SpaceState{OPEN,OCCUPIED,INVALID}

    // must be 0 to 7
    private int rowIdx;
    private int cellIdx;
    private Piece piece;
    private SpaceState spaceState;
    private Position position;



    /**
     * Instantiates a new Space.
     *
     * @param rowIdx  the row idx
     * @param cellIdx the cell idx
     */
    public Space(int rowIdx,int cellIdx) {
        if(rowIdx<0 || rowIdx > 7 || cellIdx <0 || cellIdx > 7){
            throw new IllegalArgumentException("Indices out of bounds, must be 0 to 7");
        }
        this.rowIdx = rowIdx;
        this.cellIdx = cellIdx;
        this.position = new Position(rowIdx,cellIdx);

        //determines where the starting piece should go
        if((rowIdx+cellIdx)%2 == 1){
            if(rowIdx<3){
                piece = new Piece(Piece.Color.RED, Piece.Type.SINGLE);
                this.spaceState = SpaceState.OCCUPIED;
            }else if(rowIdx > 4){
                piece = new Piece(Piece.Color.WHITE, Piece.Type.SINGLE);
                this.spaceState = SpaceState.OCCUPIED;
            }else{
                this.spaceState = SpaceState.OPEN;
            }
        }else{
            this.spaceState = SpaceState.INVALID;
        }
    }

    public SpaceState getSpaceState() {
        return spaceState;
    }

    public Position getPosition() {
        return position;
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

}

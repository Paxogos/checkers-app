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
            if(rowIdx > 4){
                piece = new Single(Piece.Color.RED);
                this.spaceState = SpaceState.OCCUPIED;
            }else if(rowIdx < 3){
                piece = new Single(Piece.Color.WHITE);
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
        return (rowIdx + cellIdx) % 2 != 0 && spaceState != SpaceState.OCCUPIED;
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

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.spaceState = SpaceState.OCCUPIED;
    }

    public Piece removePiece() {
        this.spaceState = SpaceState.OPEN;
        Piece remove = this.piece;
        this.piece = null;
        return remove;
    }

    public boolean isOccupied() {
        return this.spaceState == SpaceState.OCCUPIED || this.piece != null;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Space))
            return false;
        Space newSpace = (Space)obj;
        return newSpace.spaceState == this.spaceState &&
                ((this.piece == null && newSpace.getPiece() == null) || this.piece.equals(newSpace.piece));
    }

    public Space(int rowIdx, int cellIdx, Piece piece) {
        this.rowIdx = rowIdx;
        this.cellIdx = cellIdx;

        if ((rowIdx+cellIdx)%2 != 1) {
            this.piece = null;
            this.spaceState = SpaceState.INVALID;
        }

        else if (piece == null)
            this.spaceState = SpaceState.OPEN;
        else
            setPiece(piece);
    }
}

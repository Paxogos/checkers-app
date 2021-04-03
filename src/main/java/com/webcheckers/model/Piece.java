package com.webcheckers.model;
import com.webcheckers.model.Game.MoveResult;
import com.webcheckers.util.Position;

/**
 * Piece represents one checker on the board, which is used to play the game of checkers
 * @version 1.0
 */
public abstract class Piece {

    /**
     * The enum Color represents the team the piece is on
     */
    public enum Color {RED, WHITE}

    public enum Type {SINGLE, KING}

    //attributes
    private Color color;
    private Type type;

    /**
     * Instantiates a new Piece.
     *
     * @param color the color
     */
    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    /**
     * Is king boolean.
     *
     * @return true if Piece is type King, false otherwise
     */
    public boolean isKing() { return this instanceof King;
    }

    /**
     * Is single boolean
     *
     * @return true if Piece is type Single, false otherwise
     */
    public boolean isSingle() {
        return this instanceof Single;
    }


    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets type
     *
     * @return the type
     */
    public Type getType() { return this.type; }

    public abstract MoveResult makeMove(Move move, Board board);

    public abstract boolean canJump(Position currentPosition, Board board);

    /**
     * Checks if this single can jump in any direction
     *
     * @param currentPosition       current position of the piece
     * @param board                 current configuration of the board
     * @return  true if piece can legally jump another piece
     */
    protected boolean canJumpForward(Position currentPosition, Board board) {

        boolean canJumpRight;
        boolean canJumpLeft;

        // Corrects the direction of movement based on piece color
        // Red technically moves backward, white moves forward
        int directionCorrector = -1;
        if (this.getColor() == Color.WHITE)
            directionCorrector = 1;

        //Distance in each direction for a jump
        int deltaY = 2 * directionCorrector;
        int deltaX = 2 * directionCorrector;

        int currentRow = currentPosition.getRow();
        int currentCell = currentPosition.getCell();

        // Checks if a jump to the left or right is out of bounds in the y-direction
        if (currentRow + deltaY >= Board.GRID_LENGTH || currentRow + deltaY < 0)
            return false;

        // Checks if a jump to the left is out of bounds in the x-direction
        if (currentCell + deltaX >= Board.GRID_LENGTH || currentCell + deltaX < 0)
            canJumpLeft = false;
        else {
            Position leftJump = new Position (currentRow + deltaY, currentCell + deltaX);
            Position leftJumpCapture = new Move(currentPosition, leftJump).midpoint();
            canJumpLeft = !board.getSpace(leftJump).isOccupied() && board.getPieceAt(leftJumpCapture) != null &&
                    board.getPieceAt(leftJumpCapture).getColor() != this.getColor(); }

        // Checks if a jump to the right is out of bounds in the x-direction
        if (currentCell - deltaX >= Board.GRID_LENGTH || currentCell - deltaX < 0)
            canJumpRight = false;
        else {
            Position rightJump = new Position (currentRow + deltaY, currentCell - deltaX);
            Position rightJumpCapture = new Move(currentPosition, rightJump).midpoint();
            canJumpRight = !board.getSpace(rightJump).isOccupied() && board.getPieceAt(rightJumpCapture) != null &&
                    board.getPieceAt(rightJumpCapture).getColor() != this.getColor(); }


        return canJumpLeft || canJumpRight;

    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Piece))
            return false;
        return ((Piece) obj).getType() == this.type && ((Piece) obj).getColor() == this.color;
    }

    //toString used for unit testing
    public String toString() {
        return "Piece: {" + this.color + ", " + this.type + "}";
    }
}

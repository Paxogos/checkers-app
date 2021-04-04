package com.webcheckers.model;

import com.webcheckers.util.Position;

import static com.webcheckers.model.Piece.Type.KING;

public class King extends Piece{


    /**
     * Instantiates a new Piece.
     *
     * @param color the color
     */
    public King(Color color) {
        super(color, KING);
    }

    @Override
    public Game.MoveResult makeMove(Move move, Board board) {

        int deltaX = Math.abs(move.end().getCell() - move.start().getCell());
        int deltaY = Math.abs(move.end().getRow() - move.start().getRow());

        Position midpoint = move.midpoint();
        Piece jumpee = board.getPieceAt(midpoint);

        if (board.getSpace(move.end()).isOccupied())
            return Game.MoveResult.OCCUPIED;

        else if (deltaX == 2 && deltaY == 2 && jumpee != null && jumpee.getColor() != this.getColor())
            return Game.MoveResult.JUMP;

        else if (deltaY == 1 && deltaX == 1)
            return Game.MoveResult.SIMPLE_MOVE;

        else
            return Game.MoveResult.SINGLE_RESTRICTED;


    }

    @Override
    public boolean canJump(Position currentPosition, Board board) {
        return this.canJumpForward(currentPosition, board) || this.canJumpBackward(currentPosition, board);
    }

    private boolean canJumpBackward(Position currentPosition, Board board) {

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
        if (currentRow - deltaY >= Board.GRID_LENGTH || currentRow - deltaY < 0)
            return false;

        // Checks if a jump to the left is out of bounds in the x-direction
        if (currentCell + deltaX >= Board.GRID_LENGTH || currentCell + deltaX < 0)
            canJumpLeft = false;
        else {
            Position leftJump = new Position (currentRow - deltaY, currentCell + deltaX);
            Position leftJumpCapture = new Move(currentPosition, leftJump).midpoint();
            canJumpLeft = !board.getSpace(leftJump).isOccupied() && board.getPieceAt(leftJumpCapture) != null &&
                    board.getPieceAt(leftJumpCapture).getColor() != this.getColor(); }

        // Checks if a jump to the right is out of bounds in the x-direction
        if (currentCell - deltaX >= Board.GRID_LENGTH || currentCell - deltaX < 0)
            canJumpRight = false;
        else {
            Position rightJump = new Position (currentRow - deltaY, currentCell - deltaX);
            Position rightJumpCapture = new Move(currentPosition, rightJump).midpoint();
            canJumpRight = !board.getSpace(rightJump).isOccupied() && board.getPieceAt(rightJumpCapture) != null &&
                    board.getPieceAt(rightJumpCapture).getColor() != this.getColor(); }


        return canJumpLeft || canJumpRight;

    }
}

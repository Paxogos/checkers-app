package com.webcheckers.model;

import com.webcheckers.util.Position;

public class Single extends Piece{

    /**
     * Instantiates a new Piece.
     *
     * @param color the color
     */
    public Single(Color color) {
        super(color, Type.SINGLE);
    }

    @Override
    public Game.MoveResult makeMove(Move move, Board board) {

        int directionCorrector = -1;

        Piece movingPiece = board.getPieceAt(move.start());

        if (movingPiece.getColor() == Color.WHITE)
            directionCorrector = 1;

        int deltaX = move.end().getCell() - move.start().getCell();
        int deltaY = (move.end().getRow() - move.start().getRow()) * directionCorrector;

        Position midpoint = move.midpoint();
        Piece jumpee = board.getPieceAt(midpoint);

        if (movingPiece == null)
            return Game.MoveResult.INVALID;


        if (board.getSpace(move.end()).isOccupied())
            return Game.MoveResult.OCCUPIED;

        else if (Math.abs(deltaX) == 2 && deltaY == 2 && jumpee != null && jumpee.getColor() != movingPiece.getColor())
            return Game.MoveResult.JUMP;

        else if (deltaY == 1 && Math.abs(deltaX) == 1)
            return Game.MoveResult.SIMPLE_MOVE;

        else if (!(deltaY == 1 && Math.abs(deltaX) == 1))
            return Game.MoveResult.SINGLE_RESTRICTED;

        else { return Game.MoveResult.INVALID; }

    }

    public boolean canJump(Position currentPosition, Board board) {

        boolean canJumpRight;
        boolean canJumpLeft;

        int directionCorrector = -1;

        if (this.getColor() == Color.WHITE)
            directionCorrector = 1;

        int deltaY = 2 * directionCorrector;
        int deltaX = 2 * directionCorrector;

        int currentRow = currentPosition.getRow();
        int currentCell = currentPosition.getCell();

        if (currentRow + deltaY > Board.GRID_LENGTH || currentRow + deltaY < 0)
            return false;


        if (currentCell + deltaX > Board.GRID_LENGTH || currentCell + deltaX < 0)
            canJumpLeft = false;
        else {
        Position leftJump = new Position (currentRow + deltaY, currentRow + deltaX);
        Position leftJumpCapture = new Move(currentPosition, leftJump).midpoint();
        canJumpLeft = !board.getSpace(leftJump).isOccupied() &&
                board.getPieceAt(leftJumpCapture).getColor() != this.getColor(); }

        if (currentCell - deltaX > Board.GRID_LENGTH || currentCell - deltaX < 0)
            canJumpRight = false;
        else {
        Position rightJump = new Position (currentRow + deltaY, currentCell - deltaX);
        Position rightJumpCapture = new Move(currentPosition, rightJump).midpoint();
        canJumpRight = !board.getSpace(rightJump).isOccupied() &&
                board.getPieceAt(rightJumpCapture).getColor() != this.getColor(); }


        return canJumpLeft || canJumpRight;
    }
}

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
        Piece movingPiece = board.getSpace(move.start()).getPiece();

        Boolean movingPieceIsRed = movingPiece.getColor() == Color.RED;


        int deltaX = move.end().getCell() - move.start().getCell();
        int deltaY = move.start().getRow() - move.end().getRow();

        Space midpoint = board.getSpace(new Position(move.start().getRow() - deltaY/2, move.start().getCell() + deltaX/2));
        Piece jumpee = midpoint.getPiece();

        if (movingPiece == null)
            return Game.MoveResult.INVALID;

        if ((Math.abs(deltaX) == 2 && deltaY == 2 && jumpee != null &&
                jumpee.getColor() != movingPiece.getColor() && movingPieceIsRed) ||
                (Math.abs(deltaX) == 2 && deltaY == -2 && jumpee != null &&
                        jumpee.getColor() != movingPiece.getColor() && !movingPieceIsRed))
            return Game.MoveResult.JUMP;

        else if (board.getSpace(move.end()).isOccupied())
            return Game.MoveResult.OCCUPIED;

        else if ((deltaY == 1 && Math.abs(deltaX) == 1 && movingPiece.getColor() == Color.RED) ||
                (deltaY == -1 && Math.abs(deltaX) == 1 && movingPiece.getColor() == Color.WHITE))
            return Game.MoveResult.SIMPLE_MOVE;

        else if (!(deltaY == 1 && Math.abs(deltaX) == 1))
            return Game.MoveResult.SINGLE_RESTRICTED;

        else { return Game.MoveResult.INVALID; }

    }
}

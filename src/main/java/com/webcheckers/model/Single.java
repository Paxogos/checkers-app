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

        int deltaX = move.end().getColumn() - move.start().getColumn();
        int deltaY = move.start().getRow() - move.end().getRow();

        Space midpoint = board.getSpace(new Position(move.start().getRow() - deltaY/2, move.start().getColumn() + deltaX/2));
        Piece jumpee = midpoint.getPiece();

        if (movingPiece == null)
            return Game.MoveResult.INVALID;

        if (Math.abs(deltaX) == 2 && deltaY == 2 && jumpee != null && jumpee.getColor() != movingPiece.getColor())
            return Game.MoveResult.JUMP;

        else if (board.getSpace(move.end()).isOccupied())
            return Game.MoveResult.OCCUPIED;

        else if (deltaY == 1 && Math.abs(deltaX) == 1)
            return Game.MoveResult.SIMPLE_MOVE;

        else if (!(deltaY == 1 && Math.abs(deltaX) == 1))
            return Game.MoveResult.SINGLE_RESTRICTED;

        else {
            return Game.MoveResult.INVALID;
        }

    }
}

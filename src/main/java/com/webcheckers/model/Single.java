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
}

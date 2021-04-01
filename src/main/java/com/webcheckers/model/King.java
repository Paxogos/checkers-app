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
        //TODO
        return Game.MoveResult.INVALID;
    }

    @Override
    public boolean canJump(Position position, Board board) {
        //TODO
        return false;
    }
}

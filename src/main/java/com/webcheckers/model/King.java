package com.webcheckers.model;

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
    public Game.MoveResult makeMove(Move move, BoardView board) {
        return Game.MoveResult.INVALID;
    }


}

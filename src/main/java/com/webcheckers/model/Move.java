package com.webcheckers.model;

import com.webcheckers.util.Position;
import com.webcheckers.model.Game.MoveResult;

public class Move {

    private Position start;
    private Position end;

    private Piece capturedPiece = null;

    private MoveResult moveResult = MoveResult.SIMPLE_MOVE;


    /**
     * Move constructor with only positions
     *
     * @param start     start of the move
     * @param end       end of the move
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Move constructor using a move and Piece if there was a captured piece
     *
     * @param move              the move
     * @param capturedPiece     the captured piece, if there was one
     */
    public Move(Move move, Piece capturedPiece) {
        this.start = move.start();
        this.end = move.end();
        this.capturedPiece = capturedPiece;

        if (capturedPiece == null)
            this.moveResult = MoveResult.SIMPLE_MOVE;
        else
            this.moveResult = MoveResult.JUMP;
    }


    /**
     * The start position of the move
     *
     * @return      start position
     */
    public Position start() {
        return this.start;
    }

    /**
     * The end position of the move
     *
     * @return      end position
     */
    public Position end() {
        return this.end;
    }

    /**
     * The midpoint position of the move
     *
     * @return
     */
    public Position midpoint() {
        return new Position((end.getRow()-start.getRow())/2 + start.getRow(),
                (end.getCell()-start.getCell())/2 + start.getCell());
    }

    public MoveResult getType() { return this.moveResult; }

    /**
     * Get the captured piece of the move
     *
     * @return      piece that was captured, if it exists; null otherwise
     */
    public Piece getCapturedPiece() { return this.capturedPiece; }



    public String toString() {
        return "[" + start.getRow() + ", " + start.getCell() + "] -> [" + end.getRow() + ", " + end.getCell() + "]";
    }
}

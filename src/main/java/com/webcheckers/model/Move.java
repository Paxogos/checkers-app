package com.webcheckers.model;

import com.webcheckers.util.Position;
import com.webcheckers.model.Game.MoveResult;

public class Move {

    private Position start;
    private Position end;

    private Piece capturedPiece = null;

    private MoveResult moveResult = MoveResult.SIMPLE_MOVE;


    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    // Create a new move with a captured piece if it is a jump
    public Move(Move move, Piece capturedPiece) {
        this.start = move.start();
        this.end = move.end();
        this.capturedPiece = capturedPiece;

        if (capturedPiece == null)
            this.moveResult = MoveResult.SIMPLE_MOVE;
        else
            this.moveResult = MoveResult.JUMP;
    }


    public Position start() {
        return this.start;
    }

    public Position end() {
        return this.end;
    }

    public Position midpoint() {
        return new Position((end.getRow()-start.getRow())/2 + start.getRow(),
                (end.getCell()-start.getCell())/2 + start.getCell());
    }

    public MoveResult getType() { return this.moveResult; }

    /**
     * Return a move that is the reverse of this one
     *
     * @return      a move from end to start (the reverse)
     */
    public Move reflect() {
        Position newStart = new Position(Board.GRID_LENGTH - this.start.getRow() - 1,
                Board.GRID_LENGTH - this.start.getCell() -1 );
        Position newEnd = new Position(Board.GRID_LENGTH - this.end.getRow() - 1,
                Board.GRID_LENGTH - this.end.getCell() - 1);

        return new Move(newStart, newEnd);
    }

    public Piece getCapturedPiece() { return this.capturedPiece; }

    public String toString() {
        return "[" + start.getRow() + ", " + start.getCell() + "] -> [" + end.getRow() + ", " + end.getCell() + "]";
    }
}

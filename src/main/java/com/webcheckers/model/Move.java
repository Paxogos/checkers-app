package com.webcheckers.model;

import com.webcheckers.util.Position;

public class Move {

    private Position start;
    private Position end;


    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
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

    /**
     * Return a move that is the reverse of this one
     *
     * @return      a move from end to start (the reverse)
     */
    public Move reverse() {
        return new Move(this.end, this.start);
    }

    public String toString() {
        return "[" + start.getRow() + ", " + start.getCell() + "] -> [" + end.getRow() + ", " + end.getCell() + "]";
    }
}

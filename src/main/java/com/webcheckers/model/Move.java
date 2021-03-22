package com.webcheckers.model;

import com.webcheckers.util.Position;

public class Move {

    private Position start;
    private Position end;

    private Player player;

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

    /**
     * Return a move that is the reverse of this one
     *
     * @return      a move from end to start (the reverse)
     */
    public Move reverse() {
        return new Move(this.end, this.start);
    }
}

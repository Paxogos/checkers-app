package com.webcheckers.model;

import java.util.HashSet;

public class Turn {

    private HashSet<Move> moves;

    public Turn() {
        moves = new HashSet<>();
    }

    public void addMove(Move move){
        moves.add(move);
    }


}

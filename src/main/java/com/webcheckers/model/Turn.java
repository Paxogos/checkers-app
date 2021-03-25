package com.webcheckers.model;

import java.util.*;

public class Turn {

    private final ArrayList<Move> moveList;

    public Turn () {

        this.moveList = new ArrayList<>();
    }

    public boolean canPlaySimpleMove() {
        int numSimpleMoves = 0;

        for (Move m: this.moveList) {
            if (m.getType() == Game.MoveResult.SIMPLE_MOVE)
                numSimpleMoves++;
            if (numSimpleMoves > 0)
                return false;
        }
        return true;
    }

    public boolean isContinuous(Move move) throws IndexOutOfBoundsException{

        if (moveList.size() == 0)
            return true;
        Move lastMove = this.moveList.get(moveList.size() - 1);
        System.out.println("Not continuous: " + move.start() + "->" + lastMove.end());
        return move.start().equals(lastMove.end());
    }

    public void addMove(Move move) {
        this.moveList.add(move);
    }

    /**
     * Takes the last move from the turn, removes it
     * from the moveList, and returns it
     *
     * @return      last move made/ null if list is empty
     */
    public Move popLastMove() throws IndexOutOfBoundsException{
        try {
            return this.moveList.remove(this.moveList.size() - 1);
        }catch (IndexOutOfBoundsException iobe) {
            return null;
        }
    }


}

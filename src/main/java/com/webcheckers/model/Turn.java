package com.webcheckers.model;

import java.util.*;

public class Turn {

    private final ArrayList<Move> moveList;

    public Turn () {

        this.moveList = new ArrayList<>();
    }


    /**
     * Checks if a jump move has already been played this turn
     * @return      true if a jump move has been played
     */
    public boolean hasPlayedJumpMove() {
        for (Move m: this.moveList) {
            if (m.getType() == Game.MoveResult.JUMP)
                return true;
        }
        return false;
    }

    /**
     * Checks if a simple move has already been played this turn
     * @return      true if a simple move has already been played
     */
    public boolean hasPlayedSimpleMove() {

        for (Move m: this.moveList) {
            if (m.getType() == Game.MoveResult.SIMPLE_MOVE)
                return true;
        }
        return false;
    }

    /**
     * Checks if the latest move starts where the last move ended
     * @param move      the latest move the player is attempting to make
     * @return          true if move.start() == lastMove.end()
     */
    public boolean isContinuous(Move move){

        if (moveList.size() == 0)
            return true;
        Move lastMove = this.moveList.get(moveList.size() - 1);
        System.out.println("Not continuous: " + move.start() + "->" + lastMove.end());
        return move.start().equals(lastMove.end());
    }

    /**
     * Make an additional move this turn
     * @param move
     */
    public void addMove(Move move) {
        this.moveList.add(move);
    }

    /**
     * Retrieve the last move played without removing it from the list
     *
     * @return Last move played
     * @throws IndexOutOfBoundsException
     */
    public Move getLastMove() throws IndexOutOfBoundsException {
        try {
            return this.moveList.get(this.moveList.size() - 1);
        }catch (IndexOutOfBoundsException iobe) {
            return null;
        }
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

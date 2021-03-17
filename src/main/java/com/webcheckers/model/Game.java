package com.webcheckers.model;
import com.webcheckers.util.Position;

public class Game {

    private BoardView board;
    private Player redPlayer;
    private Player whitePlayer;

    public enum MoveResult { INVALID, SIMPLE_MOVE, CAPTURE }

    public Game(Player red, Player white) {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new BoardView();
    }

    public Player getRedPlayer() { return this.redPlayer;}

    public Player getWhitePlayer() { return this.whitePlayer;}

    public BoardView getBoardView() { return this.board; }


    public MoveResult makeMove(Position start, Position end) {
        //TODO: 1) Implement AJAX functionality to call this
        //          with the given start and end position
        //      2) Get piece at start position
        //      3) ??? implement move logic somewhere (preferably
        //          in Game, since it has all required data)
        return MoveResult.INVALID;
    }


    public boolean isWon() {
        // TODO
        return false;
    }
}

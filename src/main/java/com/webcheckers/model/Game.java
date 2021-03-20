package com.webcheckers.model;
import com.webcheckers.ui.PostSignInRoute;
import com.webcheckers.util.Position;
import com.webcheckers.model.Piece.Color;

import java.util.HashSet;
import java.util.Iterator;

public class Game {

    private BoardView board;
    private Player redPlayer;
    private Player whitePlayer;

    private HashSet<Piece> redPieces = new HashSet<>();
    private HashSet<Piece> whitePieces = new HashSet<>();

    public enum MoveResult { INVALID, SIMPLE_MOVE, JUMP }

    public Game(Player red, Player white) {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new BoardView();

        addPiecesToGame();
    }

    public Player getRedPlayer() { return this.redPlayer;}

    public Player getWhitePlayer() { return this.whitePlayer;}

    public BoardView getBoardView() { return this.board; }


    public MoveResult makeMove(Position start, Position end) {
        //TODO: 1) Implement AJAX functionality to call this
        //          with the given start and end position
        //      2) Get piece at start position
        //      3) Pass the board and Move to the Piece to determine
        //         if it is valid
        //      4) If the MoveResult is a capture, then remove the
        //         captured piece from the appropriate HashSet
        return MoveResult.INVALID;
    }


    public boolean isWon() {
        // TODO
        return false;
    }


    private void addPiecesToGame() {

        for (int i = 0; i < BoardView.GRID_LENGTH; i++) {
            for (int j = 0; j < BoardView.GRID_LENGTH; j++) {
                Space currentSpace = this.board.getSpace(i, j);
                if (currentSpace.getPiece() != null) {
                    Piece piece = currentSpace.getPiece();

                    if (piece.getColor() == Color.RED)
                        this.redPieces.add(piece);
                    else
                        this.whitePieces.add(piece);
                }

            }
        }
    }
}

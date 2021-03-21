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

    public enum MoveResult { INVALID, SIMPLE_MOVE, JUMP, OCCUPIED, SINGLE_RESTRICTED, KING_RESTRICTED }

    public Game(Player red, Player white) {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new BoardView();

        addPiecesToGame();
    }

    public Player getRedPlayer() { return this.redPlayer;}

    public Player getWhitePlayer() { return this.whitePlayer;}

    public BoardView getBoardView() { return this.board; }


    public MoveResult makeMove(Move move) {

        Piece movingPiece = this.board.getSpace(move.start()).getPiece();

        if (movingPiece == null)
            return MoveResult.INVALID;

        return movingPiece.makeMove(move, this.board);
    }


    public boolean isWon() {
        // TODO
        return false;
    }


    private void addPiecesToGame() {

        for (int row = 0; row < BoardView.GRID_LENGTH; row++) {
            for (int col = 0; col < BoardView.GRID_LENGTH; col++) {
                Position position = new Position(row, col);
                Space currentSpace = this.board.getSpace(position);
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

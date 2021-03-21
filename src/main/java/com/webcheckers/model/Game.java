package com.webcheckers.model;
import com.webcheckers.util.Position;
import com.webcheckers.model.Piece.Color;

import java.util.HashSet;

public class Game {

    /**
     * The Game class
     *
     * This model class holds all of the information for a game of Checkers
     * between two players. It it what is used to determine which moves
     * the user can make and updates the board.
     *
     */
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;
    private Color activeColor = Color.RED;

    // Collections for checking how many pieces are left
    private HashSet<Piece> redPieces = new HashSet<>();
    private HashSet<Piece> whitePieces = new HashSet<>();

    public enum MoveResult { INVALID, SIMPLE_MOVE, JUMP, OCCUPIED, SINGLE_RESTRICTED, KING_RESTRICTED }

    /**
     * The Game constructor
     *
     * @param red       Red Player - Player that initiates
     * @param white     White Player - Player that is invited
     */
    public Game(Player red, Player white) {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new Board();

        addPiecesToGame();
    }

    public Player getRedPlayer() { return this.redPlayer;}

    public Player getWhitePlayer() { return this.whitePlayer;}

    public Board getBoard() { return this.board; }


    /**
     * Perform the given move if it is valid
     *
     * @param move      the requested move
     * @return          a MoveResult that tells the result of attempting
     *                  to move the piece
     */
    public MoveResult makeMove(Move move) {

        // Get the piece at the start position of the move
        Piece movingPiece = this.board.getSpace(move.start()).getPiece();

        if (movingPiece == null)
            return MoveResult.INVALID;

        // Uses the piece's logic to determine if the move is valid
        return movingPiece.makeMove(move, this.board);
    }


    /**
     * Checks if the game is over
     *
     * @return      Is the game over?
     */
    public boolean isWon() {
        // TODO
        return false;
    }

    /**
     * Changes switches the active color to the other one
     */
    public void toggleActivePlayer() {
        if (this.activeColor == Color.RED)
            this.activeColor = Color.WHITE;
        else
            this.activeColor = Color.RED;
    }

    /**
     * A private helper method to add all pieces to the
     * appropriate HashSet of pieces
     */
    private void addPiecesToGame() {

        for (int row = 0; row < Board.GRID_LENGTH; row++) {
            for (int col = 0; col < Board.GRID_LENGTH; col++) {
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

package com.webcheckers.model;


import com.webcheckers.util.Position;
import com.webcheckers.model.Piece.Color;

import java.util.HashSet;

public class Game {

    /**
     * The Game class
     * <p>
     * This model class holds all of the information for a game of Checkers
     * between two players. It it what is used to determine which moves
     * the user can make and updates the board.
     */
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;

    private Color activeColor = Color.RED;
    private Turn activeTurn;

    // Ints for checking how many pieces are left
    private int numWhitePieces = 0;
    private int numRedPieces = 0;

    public enum MoveResult {
        INVALID, SIMPLE_MOVE, JUMP, OCCUPIED, SINGLE_RESTRICTED,
        KING_RESTRICTED, SIMPLE_MOVES_EXCEEDED, NON_CONTINUOUS, EMPTY
    }


    /**
     * The Game constructor
     *
     * @param red   Red Player - Player that initiates
     * @param white White Player - Player that is invited
     */
    public Game(Player red, Player white) {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new Board();
        this.activeTurn = new Turn();

        addPiecesToGame();
    }

    public Player getRedPlayer() {
        return this.redPlayer;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Board getBoard() {
        return this.board;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public boolean isPlayersTurn(Player player) {
        if (player == redPlayer) {
            return Color.RED == activeColor;
        } else if (player == whitePlayer) {
            return Color.WHITE == activeColor;
        } else {
            throw new IllegalArgumentException("Provided player is not in the game");
        }
    }


    /**
     * Perform the given move if it is valid
     *
     * @param move the requested move
     * @return a MoveResult that tells the result of attempting
     * to move the piece
     */
    public MoveResult makeMove(Move move) {


        // Get the piece at the start position of the move
        Piece movingPiece = this.board.getSpace(move.start()).getPiece();

        if (movingPiece == null)
            return MoveResult.INVALID;

        if (!activeTurn.isContinuous(move))
            return MoveResult.NON_CONTINUOUS;

        MoveResult result = movingPiece.makeMove(move, this.board);

        // Uses the piece's logic to determine if the move is valid
        if (result == MoveResult.SIMPLE_MOVE) {

            if (!activeTurn.canPlaySimpleMove())
                return MoveResult.SIMPLE_MOVES_EXCEEDED;
            this.board.setSpaceToPiece(move.end(), movingPiece);
            this.board.removePieceAt(move.start());
            this.activeTurn.addMove(new Move(move, null));
        }

        // If the move was a jump, store the move with captured piece
        else if (result == MoveResult.JUMP) {
            this.board.setSpaceToPiece(move.end(), movingPiece);
            this.board.removePieceAt(move.start());
            Piece capturedPiece = this.board.removePieceAt(move.midpoint());
            this.activeTurn.addMove(new Move(move, capturedPiece));

            if (movingPiece.getColor() == Color.RED)
                numWhitePieces--;
            else
                numRedPieces--;

        }


        return result;

    }

    public void completeTurn() {
        activeTurn = new Turn();
        toggleActivePlayer();
    }


    /**
     * Checks to see if the game is over
     *
     * @return a boolean. True if the game has come to an end, false otherwise.
     */
    public boolean isGameOver() {
        return(numRedPieces == 0 || numWhitePieces == 0);
    }

    /**
     * Changes switches the active color to the other one
     */
    public void toggleActivePlayer() {
        if (this.activeColor == Color.RED) {
            this.activeColor = Color.WHITE;
        } else {
            this.activeColor = Color.RED;
        }
    }

    /**
     * Updates the board and game data to the previous move
     */
    public boolean backup() {
        Move lastMove = this.activeTurn.popLastMove();

        if (lastMove == null)
            return false;

        if (lastMove.getCapturedPiece() != null) {
            Piece capturedPiece = lastMove.getCapturedPiece();
            this.board.setSpaceToPiece(lastMove.midpoint(), capturedPiece);
            if (capturedPiece.getColor() == Color.RED)
                numRedPieces++;
            else
                numWhitePieces++;
        }

        this.board.setSpaceToPiece(lastMove.start(), this.board.getPieceAt(lastMove.end()));
        this.board.removePieceAt(lastMove.end());

        return true;


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
                        this.numRedPieces++;
                    else
                        this.numWhitePieces++;
                }

            }
        }
    }

    public int getNumWhitePieces() {
        return numWhitePieces;
    }

    public int getNumRedPieces() {
        return numRedPieces;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Game))
            return false;
        Game game = (Game) object;
        return game.getRedPlayer().equals(redPlayer) &&
                game.getWhitePlayer().equals(whitePlayer) &&
                game.getBoard().equals(board);
    }

}

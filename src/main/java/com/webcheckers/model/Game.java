package com.webcheckers.model;


import com.webcheckers.util.Position;
import com.webcheckers.model.Piece.Color;

public class Game {

    /**
     * The Game class
     * <p>
     * This model class holds all of the information for a game of Checkers
     * between two players. It it what is used to determine which moves
     * the user can make and updates the board.
     */
    private final Board board;
    private final Player redPlayer;
    private final Player whitePlayer;
    private Player winner;

    private Color activeColor = Color.RED;
    private Turn activeTurn;

    // Ints for checking how many pieces are left
    private int numWhitePieces = 0;
    private int numRedPieces = 0;

    /**
     * Enum for checking the status of a move
     */
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

    /**
     * Get the red Player object
     * @return      red player
     */
    public Player getRedPlayer() {
        return this.redPlayer;
    }

    /**
     * Get the White player object
     * @return      white player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * Get the board containing all of the spaces and pieces currently
     * in the game
     * @return      the board of the game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the color of the player whose turn it is
     * @return      active color currently taking their turn
     */
    public Color getActiveColor() {
        return activeColor;
    }

    /**
     *
     * @return      the number of white pieces in the game
     */
    public int getNumWhitePieces() {
        return numWhitePieces;
    }

    /**
     *
     * @return      the number of red pieces in the game
     */
    public int getNumRedPieces() {
        return numRedPieces;
    }

    /**
     * Checks if it is the player's turn at the moment
     *
     * @param player        player whose turn it might be
     * @return              whether it is the given player's turn
     */
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

        // If you attempt to move a piece that is not there
        if (movingPiece == null)
            return MoveResult.INVALID;

        // If you attempt to move a different piece in one turn
        if (!activeTurn.isContinuous(move))
            return MoveResult.NON_CONTINUOUS;

        MoveResult result = movingPiece.makeMove(move, this.board);

        // Uses the piece's logic to determine if the move is valid
        if (result == MoveResult.SIMPLE_MOVE) {

            if (activeTurn.hasPlayedSimpleMove())
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

            // Adjust the number of pieces accordingly
            if (movingPiece.getColor() == Color.RED)
                numWhitePieces--;
            else
                numRedPieces--;
        }

        return result;

    }

    /**
     * Resets the turn and toggles the player
     */
    public void completeTurn() {
        activeTurn = new Turn();
        toggleActivePlayer();
    }


    /**
     * Checks if the game is over
     *
     * @return Is the game over?
     */
    public boolean isGameOver() {
        if(numRedPieces == 0){
            winner = whitePlayer;
            return true;
        }else if(numWhitePieces == 0){
            winner = redPlayer;
            return true;
        }
        return false;
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

        // If the last move was a jump, add the captured piece
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



    /**
     * Checks if another game is equal to this game
     *
     * @param object    game to be compared
     * @return          true if their contents are equal
     */
    public boolean equals(Object object) {
        if (!(object instanceof Game))
            return false;
        Game game = (Game) object;
        return game.getRedPlayer().equals(redPlayer) &&
                game.getWhitePlayer().equals(whitePlayer) &&
                game.getBoard().equals(board);
    }

}

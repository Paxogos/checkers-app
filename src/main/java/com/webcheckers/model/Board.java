package com.webcheckers.model;

import com.webcheckers.util.Position;

public class Board {

    /**
     * The Board Class: used for representing the spaces and pieces currently
     * of the game.
     *
     * @author Tyler Talarico, aka T3-P0
     */

    public final static int GRID_LENGTH = 8;
    private final Space[][] board = new Space[GRID_LENGTH][GRID_LENGTH];

    /**
     * The Board constructor.
     *
     * Creates a 2D Array of Spaces to act as the model for the board.
     *
     */
    public Board() {
        for (int row = 0; row < GRID_LENGTH; row++) {
            for (int col = 0; col < GRID_LENGTH; col++) {
                board[row][col] = new Space(row, col);
            }
        }
    }

    /**
     * Get the space at the given position
     *
     * @param position      position to retrieve space from
     * @return              the space at that the given position
     */
    public Space getSpace(Position position) {
        return board[position.getRow()][position.getCell()];
    }

    /**
     * Return a new BoardView based on the current state of the board
     * in Red's perspective
     *
     * @return      BoardView of current Board
     */
    public BoardView getBoardViewForRed() {
        return new BoardView(this);
    }

    /**
     * Return a new BoardView based on the current state of the board
     * in White's perspective
     * @return      BoardView of current Board
     */
    public BoardView getBoardViewForWhite() {
        BoardView rotatedBoard = new BoardView(this);
        rotatedBoard.rotate();
        return rotatedBoard;
    }

    /**
     * Set piece at the given position to the given piece
     *
     * @param position      the position to set the piece of
     * @param piece         piece to set the space to
     */
    public void setSpaceToPiece(Position position, Piece piece) {
        board[position.getRow()][position.getCell()].setPiece(piece);
    }

    public Piece removePieceAt(Position position) {
        return board[position.getRow()][position.getCell()].removePiece();
    }

    /**
     * Get the piece at the given space
     *
     * @param position      position to get piece from
     * @return              the piece
     */
    public Piece getPieceAt(Position position) { return board[position.getRow()][position.getCell()].getPiece(); }


    /**
     * Checks if two boards have equivalent contents
     *
     * @param obj       object being compared
     * @return          true if contents are equal
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Board))
            return false;
        Board newBoard = (Board) obj;
        for (int row = 0; row < GRID_LENGTH; row++) {
            for (int cell = 0; cell < GRID_LENGTH; cell++) {
                Position getCell = new Position(row, cell);
                if (!newBoard.getSpace(getCell).equals(this.getSpace(getCell)))
                    return false;
            }
        }
        return true;
    }

    /**
     * Represents the board as a string
     *
     * @return      Board with ASCII art
     */
    public String toString() {

        StringBuilder boardAsString = new StringBuilder();

        for (int row = 0; row < GRID_LENGTH; row++) {
            for (int cell = 0; cell < GRID_LENGTH; cell++) {

                if (board[row][cell].getPiece() == null)
                    boardAsString.append(".");
                else if (board[row][cell].isValid())
                    boardAsString.append("x");
                else if (board[row][cell].getPiece().getColor() == Piece.Color.RED)
                    boardAsString.append("R");
                else
                    boardAsString.append("W");
            }
            boardAsString.append("\n");
        }
        return boardAsString.toString();

    }

}

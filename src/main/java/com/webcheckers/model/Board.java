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
    private Space[][] board = new Space[GRID_LENGTH][GRID_LENGTH];

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

    public void setSpaceToPiece(Position position, Piece piece) {
        board[position.getRow()][position.getCell()].setPiece(piece);
    }

    public void removePieceAt(Position position) {
        board[position.getRow()][position.getCell()].removePiece();
    }

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
}
package com.webcheckers.model;

import com.webcheckers.util.Position;

public class Board {



    private final static int GRID_LENGTH = 8;
    private Space[][] board = new Space[GRID_LENGTH][GRID_LENGTH];

    public Board() {
        for (int row = 0; row < GRID_LENGTH; row++) {
            for (int col = 0; col < GRID_LENGTH; col++) {
                board[row][col] = new Space(row, col);
            }
        }
    }

    public Space getSpace(Position position) {
        return board[position.getRow()][position.getColumn()];
    }

    public BoardView getBoardViewForRed() {
        return new BoardView(this);
    }

    public BoardView getBoardViewForWhite() {
        BoardView rotatedBoard = new BoardView(this);
        rotatedBoard.rotate();
        return rotatedBoard;
    }
}

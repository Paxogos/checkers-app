package com.webcheckers.model;

import java.util.Arrays;
import java.util.Iterator;

public class TestBoards {

    public static Game getGameFromBoardContents(String[] boardsContents, Piece.Color activeColor) {
        Iterator<String> contentsIterator = Arrays.stream(boardsContents).iterator();
        Space[][] board = new Space[Board.GRID_LENGTH][Board.GRID_LENGTH];

        for (int row = 0; row < Board.GRID_LENGTH; row++) {
            for (int col = 0; col < Board.GRID_LENGTH; col++) {

                String spaceContents = contentsIterator.next();
                Space newSpace;

                switch (spaceContents) {
                    case "w":
                        newSpace = new Space(row, col, new Single(Piece.Color.WHITE));
                        break;

                    case "W":
                        newSpace = new Space(row, col, new King(Piece.Color.WHITE));
                        break;

                    case "r":
                        newSpace = new Space(row, col, new Single(Piece.Color.RED));
                        break;

                    case "R":
                        newSpace = new Space(row, col, new King(Piece.Color.RED));
                        break;

                    default:
                        newSpace = new Space(row, col, null);
                }
                board[row][col] = newSpace;
            }
        }

        return new Game(new Board(board), activeColor);
    }

    public static String[] WHITE_3_4__3_6__2_7_RED_4_5 =
            {
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", "w",
                    ".", ".", ".", ".", "w", ".", "w", ".",
                    ".", ".", ".", ".", ".", "r", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", "."
            };

    public static String[] WHITE_3_4__RED_4_3__4_5__5_6 =
            {
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", "w", ".", ".", ".",
                    ".", ".", ".", "r", ".", "r", ".", ".",
                    ".", ".", ".", ".", ".", ".", "r", ".",
                    ".", ".", ".", ".", ".", ".", ".", ".",
                    ".", ".", ".", ".", ".", ".", ".", "."
            };

}

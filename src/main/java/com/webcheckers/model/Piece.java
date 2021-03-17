package com.webcheckers.model;

/**
 * Piece represents one checker on the board, which is used to play the game of checkers
 * @version 1.0
 */
public class Piece {

    /**
     * The enum Color represents the team the piece is on
     */
    public enum Color {RED, WHITE}

    //attributes
    private Color color;

    /**
     * Instantiates a new Piece.
     *
     * @param color the color
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Is king boolean.
     *
     * @return true if Piece is type King, false otherwise
     */
    public boolean isKing() { return this instanceof King;
    }

    /**
     * Is single boolean
     *
     * @return true if Piece is type Single, false otherwise
     */
    public boolean isSingle() {
        return this instanceof Single;
    }


    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }
}

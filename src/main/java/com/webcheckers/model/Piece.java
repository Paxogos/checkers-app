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

    public enum Type {SINGLE, KING}

    //attributes
    private Color color;
    private Type type;

    /**
     * Instantiates a new Piece.
     *
     * @param color the color
     */
    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
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

    /**
     * Gets type
     *
     * @return the type
     */
    public Type getType() { return this.type; }

    /**
     * Returns a toString of the stats of piece, used for unit testing in PieceTest
     *
     * @return A String that represents the state of Piece, which states the color and type of piece
     */
    public String toString() {
        return "Piece: {" + this.color + ", " + this.type + "}";
    }
}

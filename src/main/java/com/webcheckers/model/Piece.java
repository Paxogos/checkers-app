package com.webcheckers.model;

/**
 * Piece represents one checker on the board, which is used to play the game of checkers
 * @version 1.0
 */
public class Piece {

    /**
     * The enum Type represents whether a piece is a king or single stack
     */
    public enum Type {SINGLE, KING}

    /**
     * The enum Color represents the team the piece is on
     */
    public enum Color {RED, WHITE}

    //attributes
    private Type type;
    private Color color;

    /**
     * Instantiates a new Piece.
     *
     * @param type  the type
     * @param color the color
     */
    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Is king boolean.
     *
     * @return true if Piece is type King, false otherwise
     */
    public boolean isKing() {
        return type == Type.KING;
    }

    /**
     * Is single boolean
     *
     * @return true if Piece is type Single, false otherwise
     */
    public boolean isSingle() {
        return type == Type.SINGLE;
    }

    /**
     * Get type type.
     *
     * @return the type
     */
    public Type getType(){
        return type;
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

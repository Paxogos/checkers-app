package com.webcheckers.model;

/**
 * The type Piece.
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
     * @return the boolean
     */
    public boolean isKing() {
        return type == Type.KING;
    }

    /**
     * Is single boolean.
     *
     * @return the boolean
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

    @Override
    public String toString() {
        return "Piece{" +
                "type=" + type +
                ", color=" + color +
                '}';
    }
}

package com.webcheckers.model;

/**
 * Piece represents one checker on the board, which is used to play the game of checkers
 * @version 1.0
 */
public class Piece {

    public enum Type {SINGLE, KING}
    public enum Color {RED, WHITE}

    //attributes
    private Type type;
    private Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     *
     * @return true if Piece is type King, false otherwise
     */
    public boolean isKing() {
        return type == Type.KING;
    }

    /**
     *
     * @return true if Piece is type Single, false otherwise
     */
    public boolean isSingle() {
        return type == Type.SINGLE;
    }

    public Type getType(){
        return type;
    }
    public Color getColor() {
        return color;
    }
}

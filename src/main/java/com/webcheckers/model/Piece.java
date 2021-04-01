package com.webcheckers.model;
import com.webcheckers.model.Game.MoveResult;
import com.webcheckers.util.Position;

/**
 * Piece represents one checker on the board, which is used to play the game of checkers
 * @version 1.0
 */
public abstract class Piece {

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

    public abstract MoveResult makeMove(Move move, Board board);

    public abstract boolean canJump(Position position, Board board);

    public boolean equals(Object obj) {
        if (!(obj instanceof Piece))
            return false;
        return ((Piece) obj).getType() == this.type && ((Piece) obj).getColor() == this.color;
    }

    //toString used for unit testing
    public String toString() {
        return "Piece: {" + this.color + ", " + this.type + "}";
    }
}

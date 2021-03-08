package com.webcheckers.model;

public class Piece {

    public enum Type {SINGLE, KING}
    public enum Color {RED, WHITE}

    private Type type;
    private Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public boolean isKing() {
        return type == Type.KING;
    }
    public boolean isSingle() {
        return type == Type.SINGLE;
    }

    public Type getType(){
        return type;
    }

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

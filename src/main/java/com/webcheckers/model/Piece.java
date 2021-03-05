package com.webcheckers.model;

public class Piece {

    private enum type {SINGLE, KING}
    private enum color{RED, WHITE}

    public type getType(){
        return type.SINGLE;
    }

    public color getColor(){
        return color.RED;
    }
}

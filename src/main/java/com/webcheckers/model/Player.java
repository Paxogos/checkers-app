package com.webcheckers.model;

public class Player {

    // What color is the player
    public enum Color {RED, WHITE, NONE}

    private Color color;
    private String name;

    public Player(String userName, Color color) {
        this.color = color;
        this.name = userName;
    }



}

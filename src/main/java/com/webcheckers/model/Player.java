package com.webcheckers.model;

import java.util.Objects;

public class Player {

    // What color is the player
    public enum Color {RED, WHITE, NONE}

    private Color color;
    private String name;

    public Player(String name, Color color) {
        this.color = color;
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return color == player.color &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }
}

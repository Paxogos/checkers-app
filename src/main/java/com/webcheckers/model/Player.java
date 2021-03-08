package com.webcheckers.model;

public class Player {

    // What color is the player
    //public enum Color {RED, WHITE, NONE}

    private String name;

    public Player(String userName) {
        this.name = userName;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {

        // Checks if the two players have the same name
        if (other instanceof Player) {
            Player otherPlayer = (Player)other;
            return this.name.equals(otherPlayer.getName());
        }
        else
            return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }



}

package com.webcheckers.model;

/**
 * The type Player.
 */
public class Player {

    // What color is the player
    //public enum Color {RED, WHITE, NONE}

    private String name;

    /**
     * Instantiates a new Player.
     *
     * @param userName the user name
     */
    public Player(String userName) {
        this.name = userName;
    }

    /**
     * Gets the name of the Player.
     *
     * @return the name of the Player
     */
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

package com.webcheckers.model;

/**
 * Player is a class that holds the basics of a player. Two players are used to play a game of checkers
 * @version 1.0
 */
public class Player {

    //attributes
    private String name;

    public Player(String userName) {
        this.name = userName;
    }

    public String getName() {
        return this.name;
    }

    /**
     *
     * @param other is an object that is checked to see if it's the same as player
     * @return true if other is type Player with the same name as this, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Player) {
            Player otherPlayer = (Player)other;
            return this.name.equals(otherPlayer.getName());
        }
        else
            return false;
    }

    /**
     *
     * @return hashcode for the player object
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }



}

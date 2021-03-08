package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * The one and only player lobby
 */
public class PlayerLobby {

    // List containing all players
    HashMap<String, Player> playerList;

    public PlayerLobby() {
        this.playerList = new HashMap<>();
    }

    /**
     * The method for signing in
     *
     * @param userName      desired username
     * @return              the new Player object (null if the name is taken)
     */
    public Player signIn(String userName) {
        if (!hasPlayer(userName)) {
            Player newPlayer = new Player(userName);
            this.playerList.put(userName, newPlayer);
            return newPlayer; }

        else {
            return null;
        }
    }

    /**
     * Helper method for checking if a username is available
     *
     * @param playerName    desired name
     * @return              is the name available?
     */
    private boolean hasPlayer(String playerName) { return playerList.containsKey(playerName); }

    public Player getPlayer(String name) {
        Player player = null;
        try{
            player = playerList.get(name);
        }catch (NullPointerException e){
            System.out.println("Player not found");
        }
        return player;
    }

    // For testing
    /**
     * Get all other players on the site
     *
     * @param exclude   which name to not include in the
     *                  list (for the user)
     * @return          an ArrayList including all other player names
     */
    public ArrayList<String> getPlayers(String exclude) {
        ArrayList<String> players = new ArrayList<>();
        for (String name: this.playerList.keySet()) {
            if (!name.equals(exclude))
                players.add(name);
        }

        return players;
    }
}

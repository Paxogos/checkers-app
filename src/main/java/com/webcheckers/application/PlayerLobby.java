package com.webcheckers.application;

import com.webcheckers.model.Player;
import org.eclipse.jetty.util.log.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * The one and only player lobby
 */
public class PlayerLobby {

    // List containing all players
    HashMap<String, Player> playerList;

    public enum LoginAttempt {NAME_TAKEN, INVALID, VALID}

    public PlayerLobby() {
        this.playerList = new HashMap<>();
    }

    /**
     * The method for signing in
     *
     * @param userName      desired username
     * @return              the new Player object (null if the name is taken)
     */
    public LoginAttempt signIn(String userName) {

        if (userName.isBlank())
            return LoginAttempt.INVALID;

        else if (hasPlayer(userName)) {
            return LoginAttempt.NAME_TAKEN;
             }

        else {
            Player newPlayer = new Player(userName);
            this.playerList.put(userName, newPlayer);
            return LoginAttempt.VALID;
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

    public int getNumberPlayers() {
        return this.playerList.size();
    }
}

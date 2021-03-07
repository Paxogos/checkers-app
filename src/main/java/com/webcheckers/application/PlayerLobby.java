package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * The one and only player lobby
 */
public class PlayerLobby {

    HashMap<String, Player> playerList;

    public PlayerLobby() {
        this.playerList = new HashMap<>();
    }

    public Player signIn(String userName) {
        if (!hasPlayer(userName)) {
            Player newPlayer = new Player(userName);
            this.playerList.put(userName, newPlayer);
            return newPlayer; }

        else {
            return null;
        }
    }
    private boolean hasPlayer(String playerName) { return playerList.containsKey(playerName); }

    // For testing
    public ArrayList<String> getPlayers(String exclude) {
        ArrayList<String> players = new ArrayList<>();
        for (String name: this.playerList.keySet()) {
            if (!name.equals(exclude))
                players.add(name);
        }

        return players;
    }
}

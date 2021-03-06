package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashMap;


/**
 * The one and only player lobby
 */
public class PlayerLobby {

    HashMap<String, Player> playerList;

    public PlayerLobby() {
        this.playerList = new HashMap<>();
    }

    public Object signIn(String userName) {
        if (this.playerList.get(userName) == null) {
            Player newPlayer = new Player(userName, Player.Color.NONE);
            this.playerList.put(userName, newPlayer);
            return newPlayer; }

        else {
            return "Username is already taken. Please try another name.";
        }
    }

    public boolean hasPlayer(String playerName) { return playerList.containsKey(playerName); }
}

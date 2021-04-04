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
    HashMap<String, Player> availablePlayerList;
    HashMap<String, Player> busyPlayerList;

    public enum LoginAttempt {NAME_TAKEN, INVALID, VALID}

    // Constants
    private static final int MAX_CHARACTER_LIMIT = 11;
    private static final int MIN_CHARACTER_LIMIT = 4;

    public PlayerLobby() {
        this.availablePlayerList = new HashMap<>();
        this.busyPlayerList = new HashMap<>();
    }

    /**
     * The method for signing in
     *
     * @param desiredName      desired username
     * @return              the new Player object (null if the name is taken)
     */
    public LoginAttempt signIn(String desiredName) {

        String userName = desiredName.trim();
        if (nameIsInvalid(userName))
            return LoginAttempt.INVALID;

        else if (hasPlayer(userName)) {
            return LoginAttempt.NAME_TAKEN;
             }

        else {
            Player newPlayer = new Player(userName);
            this.availablePlayerList.put(userName, newPlayer);
            return LoginAttempt.VALID;
        }
    }

    public void signOut(Player currentUser) { // TODO handle situation where player signs out mid game
        availablePlayerList.remove(currentUser.getName());
        busyPlayerList.remove(currentUser.getName());

        System.out.println("test");
    }

    /**
     * Removes the player from the busyPlayerList and adds them to the availablePlayerList
     *
     * @param currentUser user playing the game
     */
    public void removePlayerFromGame(Player currentUser) {
        busyPlayerList.remove(currentUser.getName());
        availablePlayerList.remove(currentUser.getName());
    }

    /**
     * Checks if the given name is a valid username
     *
     * @param name      the desired name
     * @return          Does the name have the correct name length, begin with
     *                  an uppercase letter, and have a number?
     */
    private boolean nameIsInvalid(String name) {
        char[] letters = name.toCharArray();

        // Checks if name is empty
        if (name.isBlank())
            return true;

        // Checks if name is between 4 and 11 characters
        if (name.length() < MIN_CHARACTER_LIMIT || name.length() > MAX_CHARACTER_LIMIT)
            return true;

        // Checks if first character is an uppercase letter
        // Uses ASCII indexes (65 and 90 are the bounds of the uppercase letters)
        if ((int)letters[0] < 65 || (int)letters[0] > 90)
            return true;

        // For loop checks if each character is valid and if there is a number in the name
        // Each number is a significant index of the ASCII table
        boolean containsNumber = false;
        for (char character: letters) {
            int asciiIndex = (int)character;
            if (asciiIndex < 48 || (asciiIndex > 57 && asciiIndex < 65) || (asciiIndex > 90 && asciiIndex < 97) || asciiIndex > 122)
                return true;
            if (asciiIndex > 47 && asciiIndex < 58)
                containsNumber = true;
        }

        return !containsNumber;
    }
    /**
     * Helper method for checking if a username is available
     *
     * @param playerName    desired name
     * @return              is the name available?
     */
    private boolean hasPlayer(String playerName) {
        return availablePlayerList.containsKey(playerName) || busyPlayerList.containsKey(playerName); }

        public Player getPlayer(String name) {
        Player player = null;
        try{
            if ((player = availablePlayerList.get(name)) == null)
                player = busyPlayerList.get(name);
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
    public ArrayList<String> getAvailablePlayers(String exclude) {
        ArrayList<String> players = new ArrayList<>();
        for (String name: this.availablePlayerList.keySet()) {
            if (!name.equals(exclude))
                players.add(name);
        }

        return players;
    }

    public ArrayList<String> getBusyPlayers(String exclude) {
        ArrayList<String> players = new ArrayList<>();
        for (String name: this.busyPlayerList.keySet()) {
            if (!name.equals(exclude))
                players.add(name);
        }

        return players;
    }

    public int getNumberPlayers() {
        return this.availablePlayerList.size() + busyPlayerList.size();
    }

    public boolean isPlayerInGame(Player player) {
        if(player == null){
            throw new IllegalArgumentException("The provided player object is null");
        }
        return busyPlayerList.containsKey(player.getName());
    }

    public void setPlayerBusy(Player player) {
        availablePlayerList.remove(player.getName());
        busyPlayerList.put(player.getName(), player);
    }

    public void setPlayerAvailable(Player player) {
        busyPlayerList.remove(player.getName());
        availablePlayerList.put(player.getName(), player);
    }
}

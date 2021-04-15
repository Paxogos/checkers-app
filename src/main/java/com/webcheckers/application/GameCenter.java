package com.webcheckers.application;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.TestBoards;


import java.util.ArrayList;
import java.util.HashMap;
/**
 * GameCenter keeps track of the two players playing the same game, and if they're supposed to be in the same game together
 *
 * @version 1.0
 */

public class GameCenter {

    private final HashMap<String, Game> gamesList;
    private final HashMap<Integer, Game> gamesIDList;
    private final HashMap<Player, ArrayList<Player>> opponentList;


    private static int gameIDGenerator = 00000;


    public GameCenter() {
        this.gamesList = new HashMap<>();
        this.opponentList = new HashMap<>();
        this.gamesIDList = new HashMap<>();
    }

    /**
     *
     * @param player1 instance of Player that represents the first player to start the game
     * @param player2 instance of Player that represents player 2 who joins game after player
     * @return BoardView with a gamekey related to both players
     */
    public Game getGame(Player player1, Player player2, int testID) {
        ArrayList<Player> oppList;
        if (gameExists(player1, player2)) {
            return gamesList.get(getCorrectKey(player1, player2));
        } else {
            String gameKey = player1.getName() + player2.getName();
            int gameID = gameIDGenerator++;
            if(gameID == 99999){
                gameID = 00000;
            }
            Game currentGame;
            if(testID != 0){
                currentGame = TestBoards.getGameFromBoardContents(testID,player1,player2,gameID);
            }else{
                currentGame = new Game(player1, player2,gameID);
            }
            this.gamesList.put(gameKey,currentGame);
            this.gamesIDList.put(gameID,currentGame);

            /*
            checks if player1 has a list of opponents yet, if not a list is created.
            if player1 does have a list, we append the new opponent to that list.
            The same is then done for player2
             */
            if(!opponentList.containsKey(player1)){
                oppList = new ArrayList<>();
                oppList.add(player2);
                opponentList.put(player1,oppList);
            }else{
                oppList = opponentList.get(player1);
                oppList.add(player2);
            }

            if(!opponentList.containsKey(player2)){
                oppList = new ArrayList<>();
                oppList.add(player1);
                opponentList.put(player2,oppList);
            }else{
                oppList = opponentList.get(player2);
                oppList.add(player1);
            }
            return gamesList.get(gameKey);
        }
    }

    /**
     * Get game method using just the gameID
     *
     * @param gameID    id of the game
     * @return          respective game
     */
    public Game getGame(int gameID){
        Game game = gamesIDList.get(gameID);
        if (game == null){
            throw new IllegalArgumentException();
        }
        return game;
    }



    /**
     *
     * @param player1 instance of Player used to represent player 1
     * @param player2 instance of Player used to represent player 2
     * @return true if gamesList has the correct gamekey for the board being viewed, false otherwise
     */
    public Boolean gameExists(Player player1, Player player2) {
        if(player1 == null || player2==null){
            return false;
        }
        String gameKey1 = player1.getName() + player2.getName();
        String gameKey2 = player2.getName() + player1.getName();
        return (gamesList.containsKey(gameKey1) || gamesList.containsKey(gameKey2));
    }

    /**
     * Method for getting a key
     *
     * @param player1       player 1 of the game
     * @param player2       player 2 of the game
     * @return              the key of the game between the two players
     */
    public String getCorrectKey(Player player1, Player player2) {
        String gameKey1 = player1.getName() + player2.getName();
        String gameKey2 = player2.getName() + player1.getName();
        if (gamesList.containsKey(gameKey1)) {
            return gameKey1;
        } else if (gamesList.containsKey(gameKey2)) {
            return gameKey2;
        } else {
            System.out.println("Key not found");
            return null;
        }
    }

    /**
     * Checks if the player is in a game
     *
     * @param player    player to check
     * @return          true if they're in a game
     */
    public boolean isPlayerInGame(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("The provided player object is null");
        }
        if(opponentList.get(player) == null){

            return false;
        }
        return !opponentList.get(player).isEmpty();
    }

    /**
     * Get the current opponent of the given user
     *
     * @param currentUser       current user
     * @return                  thier opponent
     */
    public Player getCurrentOpponent(Player currentUser) {
        ArrayList<Player> opponents = opponentList.get(currentUser);
        if(opponents == null){
            return null;
        }else if(opponents.size() == 0){
            return null;
        }else if(opponents.size() == 1){
            return opponents.get(0);
        }else{
            return new Player("multipleOpponents");
        }
    }


    public ArrayList<Player> getOpponentList(Player player) {
        if(player == null){
            throw new IllegalArgumentException();
        }
        return opponentList.get(player);
    }

    /**
     * Remove a game from the games list
     *
     * @param player1   first player of the game
     * @param player2   second player of the game
     * @param id        id of the game
     */
    public void removeGame(Player player1, Player player2,int id) {
        gamesList.remove(getCorrectKey(player1, player2));
        gamesIDList.remove(id);
        ArrayList<Player> player1List = opponentList.get(player1);
        ArrayList<Player> player2List = opponentList.get(player2);
        player1List.remove(player2);
        player2List.remove(player1);
    }

    /**
     * Get a list of the games this player is partaking in
     *
     * @param player        player to get games of
     * @return              ArrayList of games
     */
    public ArrayList<String> getCurrentGameList(Player player) {
        if(player == null){
            throw new IllegalArgumentException();
        }
        ArrayList<Player> opponents = opponentList.get(player);
        if(opponents == null){
            return null;
        }
        ArrayList<String> opponentStrings = new ArrayList<>();
        Game currentGame;
        for (Player opponent : opponents) {
            opponentStrings.add(opponent.getName());
        }
        return opponentStrings;
    }
}

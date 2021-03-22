package com.webcheckers.application;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;


import java.util.HashMap;
/**
 * GameCenter keeps track of the two players playing the same game, and if they're supposed to be in the same game together
 *
 * @version 1.0
 */

public class GameCenter {

    private HashMap<String, Game> gamesList;
    private HashMap<Player, Player> opponentList;


    public GameCenter() {
        this.gamesList = new HashMap<>();
        this.opponentList = new HashMap<>();
    }

    /**
     *
     * @param player1 instance of Player that represents the first player to start the game
     * @param player2 instance of Player that represents player 2 who joins game after player
     * @return BoardView with a gamekey related to both players
     */
    public Game getGame(Player player1, Player player2) {
        if (gameExists(player1, player2)) {
            System.out.println("Returning game already started");
            return gamesList.get(getCorrectKey(player1, player2));
        } else {
            System.out.println("Returning new game");
            String gameKey = player1.getName() + player2.getName();
            System.out.println("GameKey: " + gameKey);
            this.gamesList.put(gameKey, new Game(player1, player2));
            System.out.println("Adding game to list");
            this.opponentList.put(player1, player2);
            System.out.println("Adding Player 1 to opponents");
            this.opponentList.put(player2, player1);
            System.out.println("Adding Player 2 to opponents");
            Game returnGame =  gamesList.get(gameKey);
            System.out.println("Returning above game");
            return returnGame;
        }
    }

    /**
     *
     * @param player1 instance of Player used to represent player 1
     * @param player2 instance of Player used to represent player 2
     * @return true if gamesList has the correct gamekey for the board being viewed, false otherwise
     */
    public Boolean gameExists(Player player1, Player player2) {
        String gameKey1 = player1.getName() + player2.getName();
        String gameKey2 = player2.getName() + player1.getName();
        return (gamesList.containsKey(gameKey1) || gamesList.containsKey(gameKey2));
    }

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

    public Player getCurrentOpponent(Player currentUser) { return opponentList.get(currentUser); }

    public BoardView getBoardForRed(Game game) { return game.getBoardView().rotate(); }

    public BoardView getBoardForWhite(Game game) { return game.getBoardView().rotate(); }
}

package com.webcheckers.application;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.model.Piece;

import java.util.HashMap;
/**
 * GameCenter keeps track of the two players playing the same game, and if they're supposed to be in the same game together
 *
 * @version 1.0
 */

public class GameCenter {

    HashMap<String, BoardView> gamesList;
    HashMap<Player, Piece.Color> playerColorList;
    HashMap<Player, Player> playerOpponentList;


    public GameCenter() {
        this.gamesList = new HashMap();
        this.playerColorList = new HashMap<>();
        this.playerOpponentList = new HashMap<>();
    }

    /**
     *
     * @param player1 instance of Player that represents the first player to start the game
     * @param player2 instance of Player that represents player 2 who joins game after player
     * @return BoardView with a gamekey related to both players
     */
    public BoardView getGame(Player player1, Player player2) {
        if (gameExists(player1, player2)) {
            return gamesList.get(getCorrectKey(player1, player2));
        } else {
            String gameKey = player1.getName() + player2.getName();
            playerColorList.put(player1, Piece.Color.RED);
            playerColorList.put(player2, Piece.Color.WHITE);
            playerOpponentList.put(player1, player2);
            playerOpponentList.put(player2, player1);
            gamesList.put(gameKey, new BoardView());
            return gamesList.get(gameKey);
        }
    }

    /**
     *
     * @param player1 instance of Player used to represent player 1
     * @param player2 instance of Player used to represent palyer 2
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

    public Piece.Color getPlayerColor(Player player) {
        return playerColorList.get(player);
    }

    public Player getPlayerOpponent(Player player) {
        if (playerOpponentList.containsKey(player)) {
            return playerOpponentList.get(player);
        }
        return null;
    }

    public boolean isPlayerInGame(Player player){
        return playerColorList.containsKey(player);
    }
}

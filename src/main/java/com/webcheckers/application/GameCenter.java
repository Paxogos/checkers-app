package com.webcheckers.application;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.model.Piece;

import java.util.HashMap;

public class GameCenter {

    HashMap<String, BoardView> gamesList;
    HashMap<Player, Piece.Color> playerColorList;
    HashMap<Player, Player> playerOpponentList;


    public GameCenter() {
        this.gamesList = new HashMap();
        this.playerColorList = new HashMap<>();
        this.playerOpponentList = new HashMap<>();
    }

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

    public Boolean gameExists(Player player1, Player player2) {
        String gameKey1 = player1.getName() + player2.getName();
        String gameKey2 = player2.getName() + player1.getName();
        if (gamesList.containsKey(gameKey1) || gamesList.containsKey(gameKey2)) {
            return true;
        } else {
            return false;
        }
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
}

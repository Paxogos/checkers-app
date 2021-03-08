package com.webcheckers.application;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;

public class GameCenter {


    public BoardView getGame(Player player1, Player player2) {
        return new BoardView(player1,player2);
    }

}

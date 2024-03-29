package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;

public class PostCheckTurnRoute implements Route {

    private final Gson gson;
    private GameCenter gameCenter;
    PlayerLobby playerLobby;

    /**
     * Route controller for checking if it is the current user's turn
     *
     * @param playerLobby  PlayerLobby object of the Webserver
     * @param gameCenter   GameCenter object of the WebServer
     * @param gson    Gson object used for converting JavaScript files
     */
    public PostCheckTurnRoute(Gson gson, GameCenter gameCenter, PlayerLobby playerLobby) {
        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
    }

    public Object handle(Request request, Response response) {

        Session httpSession = request.session();

        Player currentUser = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);
        Game currentGame = gameCenter.getGame(Integer.parseInt(request.queryParams("gameID")));


        boolean isPlayersTurn = currentGame.isPlayersTurn(currentUser);


        if (isPlayersTurn)
            return this.gson.toJson(Message.info("true"));
        else
            return this.gson.toJson(Message.info("false"));


    }
}

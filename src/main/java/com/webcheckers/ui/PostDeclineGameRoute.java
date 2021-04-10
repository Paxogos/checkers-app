package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostDeclineGameRoute implements Route {

    private Gson gson;
    private final PlayerLobby playerLobby;

    public PostDeclineGameRoute(Gson gson, PlayerLobby playerLobby) {
        this.gson = gson;
        this.playerLobby = playerLobby;
    }


    public Object handle(Request request, Response response) {

        Player currentUser = request.session().attribute(GetHomeRoute.PLAYER_ATTR);
        Message newGameRequest = playerLobby.getPlayerMessage(currentUser);






        return null;

    }

}

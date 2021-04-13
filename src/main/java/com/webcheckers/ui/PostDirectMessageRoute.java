package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostDirectMessageRoute implements Route {
    private final PlayerLobby playerLobby;
    private final Gson gson;

    public PostDirectMessageRoute(Gson gson, PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        this.gson = gson;
    }


    public Object handle(Request request, Response response) {

        Player currentUser = request.session().attribute(GetHomeRoute.PLAYER_ATTR);
        String message = request.queryParams("messageBox");
        Game currentGame = request.session().attribute(GetGameRoute.MOST_RECENT_GAME_ATTR);
        currentGame.sendDirectMessage(currentUser,message);

        response.redirect("/game");

        return gson.toJson(Message.info("Message Sent"));

    }
}

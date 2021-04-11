package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostDeleteNotificationRoute implements Route {

    private final PlayerLobby playerLobby;
    private final Gson gson;

    public PostDeleteNotificationRoute(Gson gson, PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        this.gson = gson;
    }


    public Object handle(Request request, Response response) {

        Player currentUser = request.session().attribute(GetHomeRoute.PLAYER_ATTR);
        playerLobby.deleteNotification(currentUser);


        return gson.toJson(Message.info("Notification Deleted"));

    }

}

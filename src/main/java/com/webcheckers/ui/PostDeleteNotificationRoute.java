package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostDeleteNotificationRoute implements Route {

    private final PlayerLobby playerLobby;

    public PostDeleteNotificationRoute(PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
    }


    public Object handle(Request request, Response response) {

        Player currentUser = request.session().attribute(GetHomeRoute.PLAYER_ATTR);
        playerLobby.deleteNotification(currentUser);

        return "";

    }
}

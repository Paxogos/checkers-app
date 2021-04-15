package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostDeclineGameRoute implements Route {

    private final PlayerLobby playerLobby;
    private final Gson gson;

    /**
     * Route controller for declining a game
     *
     * @param gson          Gson object used for converting JavaScript files
     * @param playerLobby   PlayerLobby object of the WebServer
     */
    public PostDeclineGameRoute(Gson gson, PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        this.gson = gson;
    }


    public Object handle(Request request, Response response) {

        Player currentUser = request.session().attribute(GetHomeRoute.PLAYER_ATTR);
        Message newGameRequest = playerLobby.getPlayerNotification(currentUser);
        Player sender = playerLobby.getPlayer(newGameRequest.getText());
        playerLobby.declineGameNotification(currentUser,sender);
        playerLobby.deleteNotification(currentUser);

        response.redirect(WebServer.GAME_URL);


        return gson.toJson(Message.info("Game Declined"));

    }

}

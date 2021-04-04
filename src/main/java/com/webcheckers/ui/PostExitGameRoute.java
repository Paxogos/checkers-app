package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;

public class PostExitGameRoute implements Route {
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostExitGameRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        Player currentUser = session.attribute("currentUser");

        //Remove player status from busy to available
        playerLobby.removePlayerFromGame(currentUser);
        response.redirect(WebServer.HOME_URL);

        return null;
    }
}

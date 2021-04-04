package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;

public class PostSignOutRoute implements Route {
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;
    final String USERNAME_PARAM = "userName";
    static final String SIGNED_OUT_ATTR = "playerSignOut";

    public PostSignOutRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        Player currentUser = session.attribute("currentUser");

        //Session attribute to set player sign out status
        session.attribute(SIGNED_OUT_ATTR, true);
        // needs to remove player from the lobby
        playerLobby.signOut(currentUser);

        session.removeAttribute("currentUser");
        response.redirect(WebServer.HOME_URL);

        return null;
    }
}

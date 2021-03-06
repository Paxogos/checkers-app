package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Objects;

public class PostSignInRoute implements Route {

    // Constants
    final String USERNAME_PARAM = "userName";
    final Message NAME_TAKEN = Message.info("Sorry, that name is already taken. Try another!");

    final String MESSAGE_ATTR = "message";

    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    public PostSignInRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        final Session httpSession = request.session();
        HashMap<String, Object> vm = new HashMap<>();
        final String name = request.queryParams(USERNAME_PARAM);

        Player newPlayer = playerLobby.signIn(name);

        if (newPlayer == null) {
            return templateEngine.render(GetSignInRoute.getSignInPage(NAME_TAKEN));
        }

        else {
            // TODO: store the player in the session, and update the home page

            response.redirect(WebServer.HOME_URL);
        }



        return null;
    }
}

package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Objects;

import static spark.Spark.halt;

public class PostSignInRoute implements Route {

    // Constants
    final String USERNAME_PARAM = "userName";
    final Message NAME_TAKEN = Message.info("Sorry, that name is already taken. Try another!");

    final String MESSAGE_ATTR = "message";
    final String PLAYER_ATTR = "currentUser";

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

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

        // Checks if the user is coming from a new browser
        if (httpSession.attribute(PLAYER_ATTR) == null) {

            // Attempt to sign in
            Player currentUser = playerLobby.signIn(name);
            System.out.println(playerLobby.getPlayers());
            // If the name is taken
            if (currentUser == null) {
                return templateEngine.render(GetSignInRoute.getSignInPage(NAME_TAKEN));
            } else {
                httpSession.attribute(PLAYER_ATTR, currentUser);
                return templateEngine.render(GetHomeRoute.getHomePage(currentUser));
            }

        }

        else {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }
    }
}
